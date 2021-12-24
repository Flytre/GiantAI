package net.flytre.giant_ai;

import net.fabricmc.api.ModInitializer;
import net.flytre.flytre_lib.api.config.ConfigHandler;
import net.flytre.flytre_lib.api.config.ConfigRegistry;
import net.flytre.flytre_lib.api.config.GsonHelper;

public class GiantAI implements ModInitializer {

    public static final ConfigHandler<Config> CONFIG_HANDLER = new ConfigHandler<>(new Config(), "giant_ai", GsonHelper.GSON);

    @Override
    public void onInitialize() {
        ConfigRegistry.registerServerConfig(CONFIG_HANDLER);
    }
}
