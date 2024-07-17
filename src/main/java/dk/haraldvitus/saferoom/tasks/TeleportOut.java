package dk.haraldvitus.saferoom.tasks;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class TeleportOut {
    public static void teleportPlayer(Player player, String teleportOut) {
        if (teleportOut == null || teleportOut.isEmpty()) {
            return;
        }

        String[] parts = teleportOut.split(";");
        if (parts.length != 6) {
            return;
        }

        try {
            String worldName = parts[0];
            double x = Double.parseDouble(parts[1]);
            double y = Double.parseDouble(parts[2]);
            double z = Double.parseDouble(parts[3]);
            float yaw = Float.parseFloat(parts[4]);
            float pitch = Float.parseFloat(parts[5]);

            Location loc = new Location(Bukkit.getWorld(worldName), x, y, z, yaw, pitch);
            player.teleport(loc);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
