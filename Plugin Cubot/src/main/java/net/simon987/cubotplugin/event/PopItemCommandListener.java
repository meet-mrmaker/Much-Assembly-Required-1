package net.simon987.cubotplugin.event;

import net.simon987.cubotplugin.Cubot;
import net.simon987.cubotplugin.CubotInventory;
import net.simon987.server.GameServer;
import net.simon987.server.event.DebugCommandEvent;
import net.simon987.server.event.GameEvent;
import net.simon987.server.event.GameEventListener;
import net.simon987.server.game.objects.GameObject;

public class PopItemCommandListener implements GameEventListener {

    @Override
    public Class getListenedEventType() {
        return DebugCommandEvent.class;
    }

    @Override
    public void handle(GameEvent event) {

        DebugCommandEvent e = (DebugCommandEvent) event;

        if (e.getName().equals("popItem")) {

            GameObject object = GameServer.INSTANCE.getGameUniverse().getObject(e.getObjectId("objectId"));

            if (object != null) {

                if (object instanceof Cubot) {

                    CubotInventory inventory = (CubotInventory) ((Cubot) object).getHardware(CubotInventory.class);

                    e.reply("Removed item from inventory: " + inventory.popItem());
                } else {
                    e.reply("Object is not a Cubot");
                }

            } else {
                e.reply("Object not found: " + e.getLong("objectId"));
            }
        }
    }
}