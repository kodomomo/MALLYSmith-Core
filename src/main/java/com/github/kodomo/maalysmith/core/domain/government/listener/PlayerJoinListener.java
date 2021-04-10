package com.github.kodomo.maalysmith.core.domain.government.listener;

import com.github.kodomo.maalysmith.core.MALLYSmithCore;
import com.github.kodomo.maalysmith.core.global.stub.UserGrpcStub;
import com.github.leaguelugas.mcpluginframework.register.Executor;
import com.github.leaguelugas.mcpluginframework.register.listener.Listener;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;

@Listener(PlayerJoinEvent.class)
public class PlayerJoinListener {

    private final UserGrpcStub userGrpcStub = MALLYSmithCore.getInstance().getBean(UserGrpcStub.class);

    @Executor
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String uuid = player.getUniqueId().toString();
        if (userGrpcStub.isExistsUser(uuid)) {
            player.sendMessage("등록된 유저입니다");
        } else {
            player.sendMessage("등록되지 않은 유저입니다");
            if (signUpUser(player)) {
                player.sendMessage("등록되었습니다");
            }
        }
    }

    private boolean signUpUser(Player player) {
        String uuid = player.getUniqueId().toString();
        String name = player.getName();
        return userGrpcStub.signUp(uuid, name) != null;
    }

}
