package com.github.kodomo.maalysmith.core.domain.user.listener;

import com.github.kodomo.maalysmith.core.domain.user.temp.UserLoginStatus;
import com.github.kodomo.maalysmith.core.domain.user.temp.UserStatus;
import com.github.leaguelugas.mcpluginframework.register.Executor;
import com.github.leaguelugas.mcpluginframework.register.listener.Listener;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;

@Listener(PlayerMoveEvent.class)
public class PlayerMoveListener {

    @Executor
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (UserLoginStatus.getStatus(player) != UserStatus.LOGIN) {
//            event.setCancelled(true);
        }
    }
}
