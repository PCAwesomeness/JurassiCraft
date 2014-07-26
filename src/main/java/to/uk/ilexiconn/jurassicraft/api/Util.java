package to.uk.ilexiconn.jurassicraft.api;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.MinecraftForge;
import to.uk.ilexiconn.jurassicraft.JurassiCraft;
import to.uk.ilexiconn.jurassicraft.data.Data;
import to.uk.ilexiconn.jurassicraft.data.config.Config;
import to.uk.ilexiconn.jurassicraft.data.config.ConfigData;
import to.uk.ilexiconn.jurassicraft.data.packet.PacketAnimation;
import to.uk.ilexiconn.jurassicraft.data.server.ServerProxy;

public class Util
{
    //Stuff
    @SidedProxy(serverSide = "to.uk.ilexiconn.jurassicraft.data.server.ServerProxy", clientSide = "to.uk.ilexiconn.jurassicraft.data.client.ClientProxy")
    private static ServerProxy proxy;
    private static Data data = new Data();
    private static Config config = new Config();
    protected static Object[][] stuff = new Object[4][1024];

    //Setters
    public static void addCreativeTab(int id, CreativeTabs creativeTab)
    {
        if (id != -1) stuff[0][id] = creativeTab;
    }

    public static void addItem(int id, Item item)
    {
        if (id != -1) stuff[1][id] = id;
        GameRegistry.registerItem(item, item.getUnlocalizedName());
    }

    public static void addBlock(int id, Block block)
    {
        if (id != -1) stuff[2][id] = block;
        GameRegistry.registerBlock(block, block.getUnlocalizedName());
    }

    public static void addBlockWithTileEntity(int id, BlockContainer block, Class<? extends TileEntity> tileEntity, boolean registerEntity)
    {
        addBlock(id, block);
        if (registerEntity) GameRegistry.registerTileEntity(tileEntity, tileEntity.getSimpleName());
    }

    public static void addBlockWithSubBlocks(int id, Block block, Class<? extends TileEntity> tileEntity, Class<? extends ItemBlock> itemBlock, boolean renderEntity)
    {
        if (id != -1) stuff[2][id] = block;
        GameRegistry.registerBlock(block, itemBlock, block.getUnlocalizedName());
        if (renderEntity) GameRegistry.registerTileEntity(tileEntity, tileEntity.getSimpleName());
    }

    public static void addEntity(String name, Class<? extends EntityLiving> entity, int color1, int color2, RenderLiving renderLiving)
    {
        int entityId = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerGlobalEntityID(entity, name, entityId, color1, color2);
        EntityRegistry.registerModEntity(entity, name, entityId, JurassiCraft.instance, 64, 1, true);
        getProxy().renderEntity(entity, renderLiving);
    }

    public static void addShapedRecipe(ItemStack output, Object... obj)
    {
        GameRegistry.addRecipe(output, obj);
    }

    public static void addShapelessRecipe(ItemStack output, Object... obj)
    {
        GameRegistry.addShapelessRecipe(output, obj);
    }

    public static void addGuiHandler(IGuiHandler handler)
    {
        NetworkRegistry.INSTANCE.registerGuiHandler(JurassiCraft.instance, handler);
    }

    public static void addBiome(int id, BiomeGenBase biome)
    {
        BiomeManager.warmBiomes.add(new BiomeManager.BiomeEntry(biome, id));
        BiomeManager.addSpawnBiome(biome);
        BiomeManager.addVillageBiome(biome, true);
    }

    public static void addBlockHandler(int id, ISimpleBlockRenderingHandler blockHandler)
    {
        if (id != -1) stuff[3][id] = blockHandler;
        RenderingRegistry.registerBlockHandler(blockHandler);
    }

    public static void addTileEntity(Class<? extends TileEntity> tileEntity)
    {
        GameRegistry.registerTileEntity(tileEntity, tileEntity.getSimpleName());
    }

    public static void addEvent(Object event, boolean fml)
    {
        if (fml) FMLCommonHandler.instance().bus().register(event);
        else MinecraftForge.EVENT_BUS.register(event);
    }

    //Getters
    public static String getModId()
    {
        return "jurassicraft:";
    }

    public static Data getData()
    {
        return data;
    }

    public static ServerProxy getProxy()
    {
        return proxy;
    }

    public static CreativeTabs getCreativeTab(int id)
    {
        return (CreativeTabs) stuff[0][id];
    }

    public static Item getItem(int id)
    {
        return (Item) stuff[1][id];
    }

    public static Block getBlock(int id)
    {
        return (Block) stuff[2][id];
    }

    public static int getRenderId(int id)
    {
        return ((ISimpleBlockRenderingHandler) stuff[3][id]).getRenderId();
    }

    public static Config getConfig()
    {
        return config;
    }

    public static ConfigData getConfigData()
    {
        return config.config;
    }

    public static void sendAnimationPacket(IAnimatedEntity entity, int animID)
    {
        if (FMLCommonHandler.instance().getEffectiveSide().isClient()) return;
        entity.setAnimID(animID);
        Entity e = (Entity) entity;
        JurassiCraft.packetPipeline.sendToAll(new PacketAnimation((byte) animID, e.getEntityId()));
    }
}
