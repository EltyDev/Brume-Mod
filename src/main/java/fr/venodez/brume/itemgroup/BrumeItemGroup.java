package fr.venodez.brume.itemgroup;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.stats.Stats;
import net.minecraft.util.DrinkHelper;

public class BrumeItemGroup {

    public static final ItemGroup BRUME_GROUP = new ItemGroup("brume") {
        @Override
        public ItemStack createIcon() {
            return Items.CHORUS_FRUIT.getDefaultInstance();
        }
    };
}
