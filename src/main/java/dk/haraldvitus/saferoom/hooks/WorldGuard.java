package dk.haraldvitus.saferoom.hooks;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.Set;


public class WorldGuard {
    public static WorldGuardPlugin worldGuardInstance;

    public static boolean setupWorldGuard() {
        if (Bukkit.getServer().getPluginManager().getPlugin("WorldGuard") == null) {
            return false;
        }
        worldGuardInstance = (WorldGuardPlugin) Bukkit.getPluginManager().getPlugin("WorldGuard");
        return true;
    }

    public static Set<ProtectedRegion> getRegionsAt(Location loc) {
        RegionManager regionManager = worldGuardInstance.getRegionManager(loc.getWorld());
        return regionManager.getApplicableRegions(loc).getRegions();
    }
}