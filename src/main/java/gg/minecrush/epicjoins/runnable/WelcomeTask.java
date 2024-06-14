package gg.minecrush.epicjoins.runnable;

import gg.minecrush.epicjoins.EpicJoins;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import gg.minecrush.epicjoins.storage.Lang;

public class WelcomeTask extends BukkitRunnable {

    private final EpicJoins plugin;
    private final String playerName;
    private int countdown;
    private Lang lang;

    public WelcomeTask(EpicJoins plugin, String playerName) {
        this.plugin = plugin;
        this.playerName = playerName;
        this.countdown = 10;
    }

    @Override
    public void run() {
        if (countdown <= 0) {
            this.cancel();
            return;
        }

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getName().equalsIgnoreCase("Welcome " + playerName)) {
                Bukkit.broadcastMessage(lang.getReplacedMessage("welcomed-player-broadcast"));
                player.sendMessage(lang.getReplacedMessage("welcomed-player-reward-individual"));
                plugin.getRewardManager().rewardPlayer(player);
                this.cancel();
                return;
            }
        }

        countdown--;
    }
}
