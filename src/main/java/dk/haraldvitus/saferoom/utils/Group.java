package dk.haraldvitus.saferoom.utils;

import dk.haraldvitus.saferoom.hooks.Luckperms;
import org.bukkit.entity.Player;

import static dk.haraldvitus.saferoom.Saferoom.configConfigYML;
import static org.bukkit.Bukkit.getLogger;

public class Group {

    public static String getGroup(Player player) {
        try {
            return Luckperms.getPlayerLuckPermsGroups(player);
        } catch (Exception e) {
            getLogger().severe("Kunne ikke finde spillerens gruppe: " + player.getName() + " " + player.getUniqueId());
            e.printStackTrace();
            return "default";
        }
    }
    public static boolean isVagt(Player p){
        String group = getGroup(p);
        return configConfigYML.getList("vagt-groups").stream().anyMatch(target -> target.equals(group));
    }
}
