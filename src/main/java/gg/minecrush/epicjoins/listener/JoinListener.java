package gg.minecrush.epicjoins.listener;

import gg.minecrush.epicjoins.EpicJoins;
import gg.minecrush.epicjoins.runnable.WelcomeTask;
import gg.minecrush.epicjoins.storage.Lang;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class JoinListener implements Listener {

    private final EpicJoins plugin;
    private Lang lang;

    public JoinListener(EpicJoins plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        String joinMessage = lang.getReplacedMessage("join-message");
        Bukkit.broadcastMessage(lang.getReplacedMessage("welcome-player-broadcast"));
        event.setJoinMessage(joinMessage);

        if (!event.getPlayer().hasPlayedBefore()) {
            new WelcomeTask(plugin, event.getPlayer().getName()).runTaskTimer(plugin, 0, 20);
        }
    }
}
