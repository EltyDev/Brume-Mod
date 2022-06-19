package fr.venodez.brume.tileentity;

import fr.venodez.brume.Brume;
import fr.venodez.brume.block.BrumeBlocks;
import fr.venodez.brume.block.TFBossSpawnerBlock;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BrumeTileEntities {

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPE = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Brume.ID);
    public static final RegistryObject<TileEntityType<TFFinalBossSpawnerTileEntity>> TF_FINAl_BOSS_SPAWNER = TILE_ENTITY_TYPE.register("final_boss_spawner", () -> TileEntityType.Builder.create(TFFinalBossSpawnerTileEntity::new, BrumeBlocks.boss_spawner_final_boss.get()).build(null));

    public static void register(IEventBus eventBus) {
        TILE_ENTITY_TYPE.register(eventBus);
    }

}
