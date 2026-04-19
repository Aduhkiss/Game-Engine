package family.zambrana.engine.util;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.RayTraceResult;

/**
 * EntityUtil
 * <p>
 * Author: Atticus Zambrana
 * Created: 4/18/2026
 */
public class EntityUtil {
    public static Entity getEntityPlayerIsLookingAt(Player player, double maxDistance) {
        RayTraceResult result = player.getWorld().rayTraceEntities(
                player.getEyeLocation(),
                player.getEyeLocation().getDirection(),
                maxDistance,
                entity -> entity != player
        );

        if (result == null) {
            return null;
        }

        return result.getHitEntity();
    }
}
