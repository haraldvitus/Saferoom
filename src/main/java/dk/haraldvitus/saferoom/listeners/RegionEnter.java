package dk.haraldvitus.saferoom.listeners;

import dk.haraldvitus.saferoom.Saferoom;
import dk.haraldvitus.saferoom.hooks.utils.RegionEnterEvent;
import dk.haraldvitus.saferoom.utils.Group;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import static dk.haraldvitus.saferoom.Saferoom.configConfigYML;
import static dk.haraldvitus.saferoom.tasks.TeleportOut.teleportPlayer;
import static dk.haraldvitus.saferoom.utils.ActionbarAPI.send;

public class RegionEnter implements Listener {

    public static Set<Player> playersInRegion = new HashSet<>();

    @EventHandler
    private void onEnter(RegionEnterEvent e) {
        Player p = e.getPlayer();
        String regionID = e.getRegion().getId();
        int tidConfig = configConfigYML.getInt("saferoom.tid", -1);

        if (tidConfig <= 0) {
            return;
        }
        if (!Group.isVagt(p)) {
            return;
        }

        AtomicInteger tid = new AtomicInteger(tidConfig);

        for (String key : configConfigYML.getConfigurationSection("saferoom").getKeys(false)) {
            if (!key.equals("tid")) {
                String configRegionName = configConfigYML.getString("saferoom." + key + ".region-name");
                if (regionID.equalsIgnoreCase(configRegionName)) {
                    playersInRegion.add(p);

                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            if (!playersInRegion.contains(p)) {
                                cancel();
                                return;
                            }

                            int current = tid.decrementAndGet();

                            if (current < 0) {
                                cancel();
                                String teleportOut = configConfigYML.getString("saferoom." + key + ".teleport-out");
                                teleportPlayer(p, teleportOut);
                                return;
                            }

                            String ABTekst = configConfigYML.getString("actionbar-tekst", "&bTid: &f{time}").replace("&", "§").replace("{time}", String.valueOf(current));
                            send(p, ABTekst);
                        }
                    }.runTaskTimer(Saferoom.getInstance(), 20, 20);
                    break;
                }
            }
        }
    }
}
