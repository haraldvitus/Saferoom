package dk.haraldvitus.saferoom.commands;

import dk.haraldvitus.saferoom.Saferoom;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static org.bukkit.Bukkit.getLogger;

public class SaferoomCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            if (reloadConfig()) {
                sender.sendMessage("§aDu genindlæste configgen!");
                return true;
            }
            sender.sendMessage("§cKunne ikke genindlæse configgen... Tjek latest.log!");
            return true;
        }
        return false;
    }
    private boolean reloadConfig() {
        try {
            Saferoom.getInstance().reloadConfig();
            Saferoom.configConfigYML = Saferoom.getInstance().getConfig();
            return true;
        } catch (Exception e) {
            getLogger().severe("Saferoom - Der skete en fejl under reload af configgen.");
            e.printStackTrace();
            return false;
        }
    }
}
