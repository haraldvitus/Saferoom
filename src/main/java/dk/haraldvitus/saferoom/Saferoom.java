package dk.haraldvitus.saferoom;

import dk.haraldvitus.saferoom.commands.SaferoomCommand;
import dk.haraldvitus.saferoom.hooks.Luckperms;
import dk.haraldvitus.saferoom.hooks.WorldGuard;
import dk.haraldvitus.saferoom.listeners.RegionEnter;
import dk.haraldvitus.saferoom.listeners.RegionLeave;
import dk.haraldvitus.saferoom.listeners.RegionListener;
import dk.haraldvitus.saferoom.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Saferoom extends JavaPlugin {
    public static Saferoom instance;

    private static Config configConfig;
    public static FileConfiguration configConfigYML;

    @Override
    public void onEnable() {
        instance = this;

        if (!(new File(getDataFolder(), "config.yml")).exists())
            saveResource("config.yml", false);
        configConfig = new Config(this, null, "config.yml");
        configConfigYML = configConfig.getConfig();

        this.getCommand("saferoom").setExecutor(new SaferoomCommand());
        getServer().getPluginManager().registerEvents(new RegionEnter(), this);
        getServer().getPluginManager().registerEvents(new RegionLeave(), this);
        getServer().getPluginManager().registerEvents(new RegionListener(), this);

        registerWorldGuard();
        registerLuckPerms();

        getLogger().info("Saferoom aktiveret.");
    }

    @Override
    public void onDisable() {
        getLogger().info("Saferoom deaktiveret.");
    }

    public static Saferoom getInstance() {
        return instance;
    }

    private void registerWorldGuard() {
        if (!WorldGuard.setupWorldGuard()) {
            getLogger().severe("Saferoom - Kunne ikke finde WorldGuard (disabler pluginnet)");
            Bukkit.getScheduler().cancelTasks(this);
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }
    private void registerLuckPerms() {
        if (!Luckperms.setupLuckPerms()) {
            getLogger().severe("Saferoom - Kunne ikke finde LuckPerms (disabler pluginnet)");
            Bukkit.getScheduler().cancelTasks(this);
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }
}
