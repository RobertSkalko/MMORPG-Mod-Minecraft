package com.robertx22.mine_and_slash.database.gearitemslots.bases.armor;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.BaseArmor;
import com.robertx22.mine_and_slash.database.unique_items.bases.BaseUniqueBoots;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;

public abstract class BaseBoots extends BaseArmor {
    @Override
    public EquipmentSlotType getVanillaSlotType() {
        return EquipmentSlotType.FEET;
    }

    @Override
    public String resourceID() {
        return "boots";
    }

    @Override
    public boolean isGearOfThisType(Item item) {
        return item instanceof ArmorItem && ((ArmorItem) item).getEquipmentSlot()
            .equals(EquipmentSlotType.FEET);
    }

    @Override
    public Item getBaseUniqueItem() {
        return new BaseUniqueBoots();
    }

}
