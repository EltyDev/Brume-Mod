package fr.venodez.brume.tileentity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.CreeperEntity;
import twilightforest.tileentity.spawner.BossSpawnerTileEntity;

public class TFFinalBossSpawnerTileEntity extends BossSpawnerTileEntity<CreeperEntity> {

    public TFFinalBossSpawnerTileEntity() {
            super(BrumeTileEntities.TF_FINAl_BOSS_SPAWNER.get(), EntityType.CREEPER);
        }
}
