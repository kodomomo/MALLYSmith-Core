package com.github.kodomo.maalysmith.core.domain.user.listener;

import com.github.kodomo.maalysmith.core.domain.user.temp.UserLoginStatus;
import com.github.leaguelugas.mcpluginframework.register.Executor;
import com.github.leaguelugas.mcpluginframework.register.listener.Listener;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerQuitEvent;

@Listener(PlayerQuitEvent.class)
public class PlayerQuitListener {

    @Executor
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        UserLoginStatus.resetStatus(player);
    }
}
