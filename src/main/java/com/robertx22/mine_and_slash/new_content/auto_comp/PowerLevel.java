package com.robertx22.mine_and_slash.new_content.auto_comp;

import com.google.common.collect.Multimap;
import com.robertx22.mine_and_slash.config.forge.ModConfig;
import com.robertx22.mine_and_slash.config.forge.parts.AutoCompatibleItemConfig;
import com.robertx22.mine_and_slash.config.forge.parts.AutoConfigItemType;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.Item;
import net.minecraft.util.math.MathHelper;

import java.util.List;
import java.util.stream.Collectors;

public class PowerLevel {

    public static float MAX_SINGLE_STAT_VALUE = 50;
    public static float MAX_TOTAL_STATS = 200;

    public PowerLevel(Item item, GearItemSlot slot) {

        this.item = item;

        Multimap<String, AttributeModifier> stats = item.getAttributeModifiers(slot.getVanillaSlotType());

        this.statAmount = stats.size();

        this.totalStatNumbers = stats.values()
            .stream()
            .mapToInt(x -> (int) MathHelper.clamp(x.getAmount(), -MAX_SINGLE_STAT_VALUE, MAX_SINGLE_STAT_VALUE))
            .sum();

        totalStatNumbers = MathHelper.clamp(totalStatNumbers, -MAX_TOTAL_STATS, MAX_TOTAL_STATS);

    }

    public static float getFloatValueOf(Item item) {
        List<GearItemSlot> slots = SlashRegistry.GearTypes()
            .getList()
            .stream()
            .filter(x -> x.isGearOfThisType(item))
            .collect(Collectors.toList());

        float val = 0;

        for (GearItemSlot slot : slots) {
            PowerLevel power = new PowerLevel(item, slot);

            PowerLevel best = DeterminePowerLevels.STRONGEST.get(slot);

            val += power.divideBy(best);

        }

        val *= slots.size();

        return val;
    }

    public enum Types {
        TRASH() {
            @Override
            public AutoConfigItemType getConfig() {
                return ModConfig.INSTANCE.autoCompatibleItems.TRASH;
            }
        }, NORMAL() {
            @Override
            public AutoConfigItemType getConfig() {
                return ModConfig.INSTANCE.autoCompatibleItems.NORMAL;
            }
        }, BEST() {
            @Override
            public AutoConfigItemType getConfig() {
                return ModConfig.INSTANCE.autoCompatibleItems.BEST;
            }
        }, NONE() {
            @Override
            public AutoConfigItemType getConfig() {
                return null;
            }
        };

        Types() {

        }

        public abstract AutoConfigItemType getConfig();

    }

    public static Types getPowerClassification(Item item) {

        float val = getFloatValueOf(item);

        AutoCompatibleItemConfig config = ModConfig.INSTANCE.autoCompatibleItems;

        if (val > config.BEST.POWER_REQ.get()) {
            return Types.BEST;
        }
        if (val > config.NORMAL.POWER_REQ.get()) {
            return Types.NORMAL;
        }
        if (val > config.TRASH.POWER_REQ.get()) {
            return Types.TRASH;
        }

        return Types.NONE;
    }

    public boolean isStrongerThan(PowerLevel other) {
        return totalStatNumbers > other.totalStatNumbers;
    }

    public float divideBy(PowerLevel other) {
        return totalStatNumbers / other.totalStatNumbers;
    }

    public Item item;
    public int statAmount = 0;
    public float totalStatNumbers = 0;

}
