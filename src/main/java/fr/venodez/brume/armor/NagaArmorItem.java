package fr.venodez.brume.armor;

import net.minecraft.enchantment.Enchantments;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class NagaArmorItem extends twilightforest.item.NagaArmorItem {
    public NagaArmorItem(IArmorMaterial materialIn, EquipmentSlotType equipmentSlotIn, Properties props) {
        super(materialIn, equipmentSlotIn, props);
    }

    @Override
    public void fillItemGroup(ItemGroup tab, NonNullList<ItemStack> list) {
        if (isInGroup(tab)) {
            ItemStack istack = new ItemStack(this);
            switch (this.slot) {
                case HEAD:
                    istack.addEnchantment(Enchantments.THORNS, 3);
                    break;
                case FEET:
                    istack.addEnchantment(Enchantments.FEATHER_FALLING, 2);
                    break;
                default:
                    break;
            }
            list.add(istack);
        }
    }
}
