package family.zambrana.engine.gameplay;

import family.zambrana.engine.common.MiniPlugin;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityChangeBlockEvent;

/**
 * NoCropTrample
 * It basically stops Entitys and players from destroying farmland when their dumb asses jump on it.
 * pretty simple.
 * <p>
 * Author: Atticus Zambrana
 * Created: 4/13/2026
 */
public class NoCropTrample extends MiniPlugin {
    public NoCropTrample() {
        super("Anti Crop Trample", "Atticus Zambrana", "1.0.0");
    }

    // pretty fuckin simple huh?
    @EventHandler
    public void onCropTrample(EntityChangeBlockEvent ev) {
        if(ev.getBlock().getType() == Material.FARMLAND) {
            ev.setCancelled(true);
        }
    }
}
