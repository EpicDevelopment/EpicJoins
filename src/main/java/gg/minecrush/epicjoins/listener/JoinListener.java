package gg.minecrush.epicjoins.listener;

import gg.minecrush.epicjoins.EpicJoins;
import gg.minecrush.epicjoins.runnable.WelcomeTask;
import gg.minecrush.epicjoins.storage.Lang;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    private final EpicJoins plugin;
    private final Lang lang;

    public JoinListener(EpicJoins plugin, Lang lang) {
        this.plugin = plugin;
        this.lang = lang;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage(lang.getReplacedMessage("join-message").replace("%player%", event.getPlayer().getName()));

        if (!event.getPlayer().hasPlayedBefore()) {
            Bukkit.broadcastMessage(lang.getReplacedMessage("welcome-player-broadcast").replace("%player%", event.getPlayer().getName()));
            new WelcomeTask(plugin, event.getPlayer().getName()).runTaskTimer(plugin, 0, 20);
        }
    }
}
