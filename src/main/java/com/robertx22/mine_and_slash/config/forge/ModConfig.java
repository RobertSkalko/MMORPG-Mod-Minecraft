package com.robertx22.mine_and_slash.config.forge;

import com.robertx22.mine_and_slash.config.forge.parts.AutoCompatibleItemConfig;
import com.robertx22.mine_and_slash.config.forge.parts.DropRatesContainer;
import com.robertx22.mine_and_slash.config.forge.parts.RarityDropratesConfig;
import com.robertx22.mine_and_slash.config.forge.parts.StatScaleContainer;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class ModConfig {

    public RarityDropratesConfig RarityWeightConfig;
    public ServerContainer Server;
    public DropRatesContainer DropRates;
    public StatScaleContainer StatScaling;
    public AutoCompatibleItemConfig autoCompatibleItems;

    public static final String NAME = "SERVER";
    public static final ForgeConfigSpec spec;
    public static final ModConfig INSTANCE;

    static {
        final Pair<ModConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ModConfig::new);
        spec = specPair.getRight();
        INSTANCE = specPair.getLeft();

    }

    ModConfig(ForgeConfigSpec.Builder builder) {

        builder.comment("Mine and Slash Config")
            .push(NAME);

        RarityWeightConfig = builder.configure(RarityDropratesConfig::new)
            .getLeft();
        Server = builder.configure(ServerContainer::new)
            .getLeft();
        DropRates = builder.configure(DropRatesContainer::new)
            .getLeft();
        StatScaling = builder.configure(StatScaleContainer::new)
            .getLeft();
        autoCompatibleItems = builder.configure(AutoCompatibleItemConfig::new)
            .getLeft();

        builder.pop();

    }

}
