package com.robertx22.database.rarities.base;

import com.robertx22.saveclasses.gearitem.gear_bases.Rarity;

import net.minecraft.util.text.TextFormatting;

public abstract class BaseRare implements Rarity {

    @Override
    public String Name() {

	return "Rare";
    }

    @Override
    public int Rank() {

	return 2;
    }

    @Override
    public String Color() {
	return TextFormatting.YELLOW.toString();
    }

    @Override
    public int Weight() {
	return 5000;
    }

}
