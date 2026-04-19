package family.zambrana.engine.pets;

import family.zambrana.engine.common.MiniPlugin;
import family.zambrana.engine.pets.cmd.ChangeOwnerCmd;
import family.zambrana.engine.pets.cmd.PetInfoCmd;

/**
 * PetManager
 * <p>
 * Author: Atticus Zambrana
 * Created: 4/18/2026
 */
public class PetManager extends MiniPlugin {
    public PetManager() {
        super("Pet Manager", "Atticus Zambrana", "1.0.0");

        registerCommand("changeowner", new ChangeOwnerCmd());
        registerCommand("petinfo", new PetInfoCmd());
    }


}
