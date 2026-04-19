package family.zambrana.engine.chat;

import family.zambrana.engine.common.MiniPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Chat
 * <p>
 * Author: Atticus Zambrana
 * Created: 4/13/2026
 */
public class Chat extends MiniPlugin {
    public Chat() {
        super("Chat Engine", "Atticus Zambrana", "1.0.0");
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        event.setCancelled(true);
        String message = event.getMessage();

        for(Player pl : Bukkit.getOnlinePlayers()) {
            pl.sendMessage("§a" + event.getPlayer().getName() + "§f: " + message);
        }
    }
}
