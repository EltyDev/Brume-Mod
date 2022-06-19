package fr.venodez.brume.tag;

import fr.venodez.brume.Brume;
import net.minecraft.fluid.Fluid;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

public class BrumeTags {

    public static class Fluid {

        public static final Tags.IOptionalNamedTag<net.minecraft.fluid.Fluid> PURIFIED_WATER = FluidTags.createOptional(new ResourceLocation("forge", "purified_water"));

    }

}
