package com.robertx22.mine_and_slash.database.spells.synergies.ocean_mystic;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ocean_mystic.WhirpoolSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.Synergy;
import com.robertx22.mine_and_slash.database.spells.synergies.ctx.DamageContext;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalSpellDamage;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.ocean_mystic.FrostEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class WhirlpoolFrostDmgSynergy extends Synergy<DamageContext> {

    @Override
    public String GUID() {
        return "whirlpool_frost_extra_dmg_synergy";
    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent("Consumes Frost for extra damage"));

        list.addAll(CALC.GetTooltipString(info));

        return list;
    }

    public static SpellCalcData CALC = SpellCalcData.one(new ElementalSpellDamage(Elements.Water), 0.25F, 5);

    @Override

    public BaseSpell spellAffected() {
        return new WhirpoolSpell();
    }

    @Override
    public void tryActivate(DamageContext ctx) {

        if (PotionEffectUtils.has(ctx.target, FrostEffect.INSTANCE)) {

            PotionEffectUtils.reduceStacks(ctx.target, FrostEffect.INSTANCE);

            ctx.dmg.number += CALC.getCalculatedValue(Load.Unit(ctx.caster));
        }
    }
}