package family.zambrana.engine.pets.cmd;

import family.zambrana.engine.util.EntityUtil;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Tameable;

/**
 * ChangeOwnerCmd
 * <p>
 * Author: Atticus Zambrana
 * Created: 4/18/2026
 */
public class ChangeOwnerCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player player)) {
            sender.sendMessage("§cYou must be a player to use this command!");
            return true;
        }
        Player caller = (Player) sender;
        // Make sure we provided the new owner as an argument
        if(args.length == 0) {
            caller.sendMessage("§cYou must provide a player name as an argument!");
            return true;
        }
        OfflinePlayer newOwner = Bukkit.getOfflinePlayer(args[0]);
        // First lets get the entity the player is looking at
        Entity entity = EntityUtil.getEntityPlayerIsLookingAt(caller, 10);
        if(entity == null) {
            caller.sendMessage("§cYou must be looking at a pet to change its owner!");
            return true;
        }
        // Make sure the entity is a tamable entity
        if(!(entity instanceof Tameable tamable)) {
            caller.sendMessage("§cYou must be looking at a pet to change its owner!");
            return true;
        }
        // Cast the entity to a tameable
        Tameable tameable = (Tameable) entity;
        // Set the owner
        tameable.setOwner(caller);
        // Send a message to the player
        caller.sendMessage("§7Updated the owner of this pet to §b" + newOwner.getName() + "§7.");

        return true;
    }
}
