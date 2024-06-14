package gg.minecrush.epicjoins;

import gg.minecrush.epicjoins.listener.JoinListener;
import gg.minecrush.epicjoins.rewards.RewardManager;
import gg.minecrush.epicjoins.storage.Config;
import gg.minecrush.epicjoins.storage.Lang;
import gg.minecrush.epicjoins.util.Color;
import org.bukkit.plugin.java.JavaPlugin;

public class EpicJoins extends JavaPlugin {

    private Config config;
    private Color color;
    private Lang lang;
    private RewardManager rewardManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        lang = new Lang(this, config);
        rewardManager = new RewardManager(this);
        getServer().getPluginManager().registerEvents(new JoinListener(this), this);
    }

    public Config config() {
        return config;
    }

    public Lang lang() {
        return lang;
    }

    public RewardManager getRewardManager() {
        return rewardManager;
    }
}
