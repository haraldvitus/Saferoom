package dk.haraldvitus.saferoom.listeners;

import dk.haraldvitus.saferoom.hooks.utils.RegionLeaveEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static dk.haraldvitus.saferoom.Saferoom.configConfigYML;
import static dk.haraldvitus.saferoom.listeners.RegionEnter.playersInRegion;

public class RegionLeave implements Listener {
    @EventHandler
    private void onLeave(RegionLeaveEvent e) {
        Player p = e.getPlayer();
        String regionID = e.getRegion().getId();

        for (String key : configConfigYML.getConfigurationSection("saferoom").getKeys(false)) {
            if (!key.equals("tid")) {
                String configRegionName = configConfigYML.getString("saferoom." + key + ".region-name");
                if (regionID.equalsIgnoreCase(configRegionName)) {
                    playersInRegion.remove(p);
                    break;
                }
            }
        }
    }
}
