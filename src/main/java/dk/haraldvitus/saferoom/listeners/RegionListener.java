package dk.haraldvitus.saferoom.listeners;

import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import dk.haraldvitus.saferoom.hooks.WorldGuard;
import dk.haraldvitus.saferoom.hooks.utils.RegionEnterEvent;
import dk.haraldvitus.saferoom.hooks.utils.RegionLeaveEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Set;

public class RegionListener implements Listener {

    @EventHandler
    public void onAnyMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        Location loc_to = event.getTo();
        Location loc_from = event.getFrom();

        Set<ProtectedRegion> regions_to = WorldGuard.getRegionsAt(loc_to);
        Set<ProtectedRegion> regions_from = WorldGuard.getRegionsAt(loc_from);

        if (!regions_to.equals(regions_from)) {

            for (ProtectedRegion region : regions_to) {

                if (!regions_from.contains(region)) {
                    RegionEnterEvent regionEnterEvent = new RegionEnterEvent(player, region);
                    Bukkit.getPluginManager().callEvent(regionEnterEvent);

                    if (regionEnterEvent.isCancelled()) event.setCancelled(true);

                } else regions_from.remove(region);
            }

            if (regions_from.size() > 0) {
                for (ProtectedRegion region : regions_from) {
                    RegionLeaveEvent regionLeaveEvent = new RegionLeaveEvent(player, region);
                    Bukkit.getPluginManager().callEvent(regionLeaveEvent);

                    if (regionLeaveEvent.isCancelled()) event.setCancelled(true);
                }
            }
        }
    }
}