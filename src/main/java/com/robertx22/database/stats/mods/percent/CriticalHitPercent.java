package com.robertx22.database.stats.mods.percent;

import com.robertx22.database.stats.types.offense.CriticalHit;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;
import com.robertx22.uncommon.utilityclasses.IWeighted;

public class CriticalHitPercent extends StatMod {

	public CriticalHitPercent() {
	}

	@Override
	public String GUID() {
		return "CriticalHitPercent";
	}

	@Override
	public int Min() {
		return 2;

	}

	@Override
	public int Max() {
		return 10;
	}

	@Override
	public StatTypes Type() {
		return StatTypes.Percent;
	}

	@Override
	public Stat GetBaseStat() {
		return new CriticalHit();
	}

	@Override
	public int Weight() {
		return IWeighted.UncommonWeight;
	}

}
