package family.zambrana.engine.pets.cmd;

import family.zambrana.engine.util.EntityUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Tameable;

/**
 * PetInfoCmd
 * <p>
 * Author: Atticus Zambrana
 * Created: 4/18/2026
 */
public class PetInfoCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player player)) {
            sender.sendMessage("§cYou must be a player to use this command!");
            return true;
        }
        Player caller = (Player) sender;
        // Get the entity the player is looking at
        Entity entity = EntityUtil.getEntityPlayerIsLookingAt(caller, 10);
        if(entity == null) {
            caller.sendMessage("§cYou must be looking at a pet to get its info!");
            return true;
        }
        // Make sure the entity is a tameable entity
        if(!(entity instanceof Tameable tameable)) {
            caller.sendMessage("§cYou must be looking at a pet to get its info!");
            return true;
        }
        // Send the pet info to the player
        caller.sendMessage("§aPet Info:");
        caller.sendMessage("§7Owner: " + tameable.getOwner().getName());
        caller.sendMessage("§7Health: " + tameable.getHealth() + "/" + tameable.getMaxHealth());
        caller.sendMessage("§7Type: " + tameable.getType());
        return true;
    }
}
