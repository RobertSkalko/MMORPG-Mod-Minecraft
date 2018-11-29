package com.robertx22.unique_items.bases;

import com.robertx22.customitems.gearitems.armor.ItemPants;
import com.robertx22.database.gearitemslots.Pants;
import com.robertx22.database.rarities.items.UniqueItem;
import com.robertx22.unique_items.IUnique;

public abstract class BaseUniquePants extends ItemPants implements IUnique {

    public BaseUniquePants() {
	super(new UniqueItem().Rank());
	IUnique.ITEMS.put(GUID(), this);

    }

    @Override
    public String slot() {
	return new Pants().Name();
    }

}
