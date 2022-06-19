package fr.venodez.brume.block;

import fr.venodez.brume.fluid.BrumeFluids;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;

public class PurifiedWaterBlock extends FlowingFluidBlock {
    public static IntegerProperty QUANTITY = IntegerProperty.create("quantity",0, 4);

    public PurifiedWaterBlock() {
        super(() -> BrumeFluids.PURIFIED_WATER_FLUID.get(), AbstractBlock.Properties.create(Material.WATER)
                .doesNotBlockMovement().hardnessAndResistance(100f).noDrops());
        this.setDefaultState(this.getStateContainer().getBaseState().with(QUANTITY, 3));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(QUANTITY);
        super.fillStateContainer(builder);
    }
}
