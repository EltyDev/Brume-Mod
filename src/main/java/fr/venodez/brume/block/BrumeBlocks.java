package fr.venodez.brume.block;

import fr.venodez.brume.Brume;
import fr.venodez.brume.item.BrumeItems;
import fr.venodez.brume.itemgroup.BrumeItemGroup;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import twilightforest.TwilightForestMod;
import twilightforest.enums.BossVariant;

import java.util.function.Supplier;

public class BrumeBlocks {

    public static final DeferredRegister<Block> TF_BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TwilightForestMod.ID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Brume.ID);
    public static final RegistryObject<Block> boss_spawner_final_boss = TF_BLOCKS.register("boss_spawner_final_boss", () -> new TFBossSpawnerBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(-1.0F).notSolid().noDrops(), BossVariant.FINAL_BOSS));

    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = TF_BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        BrumeItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().group(BrumeItemGroup.BRUME_GROUP)));
    }

    public static void register(IEventBus eventBus) {
        TF_BLOCKS.register(eventBus);
        BLOCKS.register(eventBus);
    }


}
