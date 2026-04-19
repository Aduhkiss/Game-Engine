package family.zambrana.engine;

import family.zambrana.engine.chat.Chat;
import family.zambrana.engine.database.DataSource;
import family.zambrana.engine.gameplay.NoCropTrample;
import family.zambrana.engine.pets.PetManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class GameEngine extends JavaPlugin {

    private static GameEngine instance;

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("[Engine] Starting Engine...");
        // Plugin startup logic
        instance = this;
        saveDefaultConfig();

        Chat chat = new Chat();
        DataSource dataSource = new DataSource();
        dataSource.initialize();

        // Gameplay Adjustments
        new NoCropTrample();
        new PetManager();

        Bukkit.getLogger().info("[Engine] Done. Welcome!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        instance = null;

        Bukkit.getLogger().info("[Engine] Shutting down...");
    }

    public static GameEngine getInstance() {
        return instance;
    }
}
