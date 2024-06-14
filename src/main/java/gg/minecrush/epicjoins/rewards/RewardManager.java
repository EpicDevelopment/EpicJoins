package gg.minecrush.epicjoins.rewards;

import gg.minecrush.epicjoins.EpicJoins;
import gg.minecrush.epicjoins.storage.Config;
import gg.minecrush.epicjoins.storage.Lang;
import gg.minecrush.epicjoins.util.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class RewardManager {

    private final EpicJoins plugin;
    private Config config;
    private Lang lang;

    public RewardManager(EpicJoins plugin) {
        this.plugin = plugin;
    }

    public void rewardPlayer(Player player) {
        String rewardName = config.getValue("reward-item");
        int amount = config.getValueInt("reward-amount");
        Material material = Material.getMaterial(rewardName.toUpperCase());

        if (material != null) {
            ItemStack reward = new ItemStack(material, amount);
            player.getInventory().addItem(reward);
            // player.sendMessage("You have been rewarded with " + amount + " " + rewardName + "(s)!");
            player.sendMessage(Color.c(lang.getReplacedMessage("reward-message")));
        } else {
            String prefix = config.getValue("prefix");
            player.sendMessage(Color.c(prefix + " &cInvalid reward item loaded in config.yml!"));
        }
    }
}
