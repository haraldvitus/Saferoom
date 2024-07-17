package dk.haraldvitus.saferoom.hooks;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.user.User;
import net.luckperms.api.model.user.UserManager;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class Luckperms {

    public static LuckPerms luckPerms;

    public static LuckPerms getLuckPerms() {
        return luckPerms;
    }

    public static boolean setupLuckPerms() {
        if (Bukkit.getServer().getPluginManager().getPlugin("LuckPerms") == null) {
            return false;
        }
        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null) {
            luckPerms = provider.getProvider();
            return true;
        }
        return false;
    }

    public static String getPlayerLuckPermsGroups(OfflinePlayer player) {
        UUID uuid = player.getUniqueId();
        User user = getLuckPerms().getUserManager().getUser(uuid);
        if (user == null) {
            return getOfflinePlayerLuckPermsGroup(uuid).join();
        }
        return user.getPrimaryGroup();
    }

    private static CompletableFuture<String> getOfflinePlayerLuckPermsGroup(UUID uuid) {
        UserManager userManager = luckPerms.getUserManager();
        CompletableFuture<User> userFuture = userManager.loadUser(uuid);
        return userFuture.thenApplyAsync(User::getPrimaryGroup);
    }
}