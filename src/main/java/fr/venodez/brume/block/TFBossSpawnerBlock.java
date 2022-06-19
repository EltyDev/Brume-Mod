package fr.venodez.brume.block;

import fr.venodez.brume.tileentity.TFFinalBossSpawnerTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import twilightforest.block.BossSpawnerBlock;
import twilightforest.enums.BossVariant;

import javax.annotation.Nullable;

public class TFBossSpawnerBlock extends BossSpawnerBlock {

    private BossVariant boss;

    public TFBossSpawnerBlock(Properties props, BossVariant variant) {
        super(props, variant);
        boss = variant;
    }

    @Override
    @Nullable
    public TileEntity createTileEntity(BlockState state, IBlockReader reader) {
        if (boss == BossVariant.FINAL_BOSS) {
            return (new TFFinalBossSpawnerTileEntity());
        }
        return boss.getSpawner();
    }

}
