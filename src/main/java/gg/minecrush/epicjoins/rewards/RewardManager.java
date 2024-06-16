package gg.minecrush.epicjoins.rewards;

import gg.minecrush.epicjoins.EpicJoins;
import gg.minecrush.epicjoins.storage.Config;
import gg.minecrush.epicjoins.storage.Lang;
import gg.minecrush.epicjoins.util.Color;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class RewardManager {

    private final EpicJoins plugin;
    private final Config config;
    private final Lang lang;

    public RewardManager(EpicJoins plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfigManager();
        this.lang = plugin.getLang();
    }

    public void rewardPlayer(Player player) {
        String rewardName = config.getValue("reward-item");
        int amount = config.getValueInt("reward-amount");
        Material material = Material.getMaterial(rewardName.toUpperCase());

        if (material != null) {
            ItemStack reward = new ItemStack(material, amount);
            Bukkit.getScheduler().runTask(plugin, () -> {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), plugin.getConfig().getString("reward.command").replace("%player%", player.getName()));
            });
            player.getInventory().addItem(reward);
            player.sendMessage(Color.c(lang.getReplacedMessage("reward-message").replace("%reward%", config.getValue("reward.name"))));
        } else {
            String prefix = config.getValue("prefix");
            player.sendMessage(Color.c(prefix + " &cInvalid reward item loaded in config.yml!"));
        }
    }
}
