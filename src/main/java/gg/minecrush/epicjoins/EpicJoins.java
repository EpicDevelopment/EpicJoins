package gg.minecrush.epicjoins;

import gg.minecrush.epicjoins.listener.JoinListener;
import gg.minecrush.epicjoins.rewards.RewardManager;
import gg.minecrush.epicjoins.storage.Config;
import gg.minecrush.epicjoins.storage.Lang;
import org.bukkit.plugin.java.JavaPlugin;

public class EpicJoins extends JavaPlugin {

    private Config configManager;
    private Lang lang;
    private RewardManager rewardManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        configManager = new Config(this); // Initialize Config
        lang = new Lang(this, configManager); // Pass Config to Lang
        rewardManager = new RewardManager(this);

        // Register the JoinListener and pass both EpicJoins and Lang instances
        getServer().getPluginManager().registerEvents(new JoinListener(this, lang), this);

        getLogger().info("EpicJoins has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("EpicJoins has been disabled.");
    }

    public Config getConfigManager() {
        return configManager;
    }

    public Lang getLang() {
        return lang;
    }

    public RewardManager getRewardManager() {
        return rewardManager;
    }
}
