package family.zambrana.engine.common;

import family.zambrana.engine.GameEngine;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;

/**
 * MiniPlugin
 * Base class for modular plugins within AtticusCore
 * <p>
 * Author: Atticus Zambrana
 * Created: 4/12/2026
 */
public abstract class MiniPlugin implements Listener {

    private String name;
    private String author;
    private String version;
    private boolean enabled;
    private GameEngine core;

    public MiniPlugin(String name, String author, String version) {
        this.name = name;
        this.author = author;
        this.version = version;
        this.core = GameEngine.getInstance();
        this.enabled = false;

        // Register events
        if (core != null) {
            Bukkit.getPluginManager().registerEvents(this, core);
            Bukkit.getLogger().info("[Engine] (" + name + ") Registered.");
        } else {
            Bukkit.getLogger().warning("[Engine] (" + name + ") Failed to register - game engine not available!");
        }
    }

    /**
     * Called when the mini plugin is enabled
     */
    public void onEnable() {
        if (!enabled) {
            enabled = true;
            Bukkit.getLogger().info("[Engine] (" + name + ") Enabled.");
        }
    }

    /**
     * Called when the mini plugin is disabled
     */
    public void onDisable() {
        if (enabled) {
            enabled = false;
            Bukkit.getLogger().info("[Engine] (" + name + ") Disabled.");
        }
    }

    /**
     * Called when the core plugin is reloading
     */
    public void onReload() {
        // Override this method to handle reload events
    }

    /**
     * Check if this mini plugin is enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Get the main AtticusCore instance
     */
    public GameEngine getCore() {
        return core;
    }

    /**
     * Get the name of this mini plugin
     */
    public String getName() {
        return name;
    }

    /**
     * Get the author of this mini plugin
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Get the version of this mini plugin
     */
    public String getVersion() {
        return version;
    }

    /**
     * Log a message with this mini plugin's name
     */
    public void log(String message) {
        Bukkit.getLogger().info("[Engine] (" + name + ") " + message);
    }

    /**
     * Log a warning with this mini plugin's name
     */
    public void logWarning(String message) {
        Bukkit.getLogger().warning("[Engine] (" + name + ") " + message);
    }

    /**
     * Log an error with this mini plugin's name
     */
    public void logError(String message) {
        Bukkit.getLogger().severe("[Engine] (" + name + ") " + message);
    }

    public void registerCommand(String name, CommandExecutor executor) {
        getCore().getCommand(name).setExecutor(executor);
    }
}
