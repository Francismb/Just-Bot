import org.agile.bot.api.accessors.Inventory;
import org.agile.bot.api.accessors.Npcs;
import org.agile.bot.api.accessors.Players;
import org.agile.bot.api.utilities.Filter;
import org.agile.bot.api.utilities.Time;
import org.agile.bot.api.utilities.input.Mouse;
import org.agile.bot.api.wrappers.Item;
import org.agile.bot.api.wrappers.Tile;
import org.agile.bot.api.wrappers.entity.npc.Npc;
import org.agile.bot.script.Script;
import org.agile.bot.script.ScriptManifest;

import java.awt.*;
import java.util.*;

/**
 * User: Francis(AgileTM)
 * Date: 11/08/13
 * Time: 2:57 PM
 * Project: Client
 * Package: PACKAGE_NAME
 */
@ScriptManifest(
        name = "TestScript",
        version = 1.0,
        author = "Frazboyz",
        type = "Mining"
)
public class TestScript extends Script {

    String status = "NULL";


    @Override
    public boolean init() {
        return true;
    }

    @Override
    public int run() {
        if (Players.getLocal().isInCombat()) {
            Time.sleep(100);
        } else {
            final Npc npc = Npcs.getNearest(new Filter<Npc>() {
                @Override
                public boolean accept(Npc npc) {
                    return npc.getDefinition().getName().equalsIgnoreCase("Chicken");
                }
            });
            if (npc != null) {
                if (npc.getCharacterModel().getRandomPoint() != null) {
                    npc.interact("Attack");
                    Time.sleep(2000, new Time.Condition() {
                        @Override
                        public boolean valid() {
                            return Players.getLocal().isInCombat();
                        }
                    });
                }
            }
        }
        return 300;
    }

    @Override
    public void stop() {

    }

    @Override
    public void paint(Graphics graphics) {
        graphics.setColor(Color.CYAN);
        graphics.drawString("Status : " + status, 5, 40);
    }
}
