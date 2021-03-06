package com.robertx22.mine_and_slash.uncommon.interfaces.data_items;

import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.ITooltip;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.saveclasses.item_classes.JewelData;
import com.robertx22.mine_and_slash.saveclasses.item_classes.SkillGemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.datasaving.Jewel;
import com.robertx22.mine_and_slash.uncommon.datasaving.SkillGem;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

public interface ICommonDataItem<R extends Rarity> extends ITiered, ISalvagable, ITooltip, IRarity {

    DataItemType getDataType();

    @Override
    default boolean isSalvagable(SalvageContext context) {
        return true;
    }

    void saveToStack(ItemStack stack);

    @Nullable
    static ICommonDataItem load(ItemStack stack) {

        GearItemData gear = Gear.Load(stack);
        if (gear != null) {
            return gear;
        }
        SkillGemData gem = SkillGem.Load(stack);
        if (gem != null) {
            return gem;
        }
        JewelData jewel = Jewel.Load(stack);
        if (jewel != null) {
            return jewel;
        }

        return null;
    }

}
