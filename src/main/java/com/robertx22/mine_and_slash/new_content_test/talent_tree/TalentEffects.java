package com.robertx22.mine_and_slash.new_content_test.talent_tree;

import com.robertx22.mine_and_slash.database.stats.stat_types.offense.CriticalHit;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class TalentEffects {

    public static TalentPointEffect SMALL_CRIT_HIT = new TalentPointEffect(new ExactStatData(2, StatTypes.Flat, CriticalHit.GUID));
    public static TalentPointEffect MEDIUM_CRIT_HIT = new TalentPointEffect(new ExactStatData(4, StatTypes.Flat, CriticalHit.GUID));
    public static TalentPointEffect BIG_CRIT_HIT = new TalentPointEffect(new ExactStatData(10, StatTypes.Flat, CriticalHit.GUID));

}
