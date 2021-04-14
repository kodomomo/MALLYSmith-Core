package com.github.kodomo.maalysmith.core.domain.user.listener;

import com.github.kodomo.maalysmith.core.MALLYSmithCore;
import com.github.kodomo.maalysmith.core.domain.user.temp.UserLoginStatus;
import com.github.kodomo.maalysmith.core.domain.user.temp.UserStatus;
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

        event.setJoinMessage(null);
        if (userGrpcStub.isExistsUser(uuid)) {
            player.sendMessage("등록된 유저입니다");
            player.sendMessage("비밀번호를 입력하세요");
            UserLoginStatus.setPlayerStatus(player, UserStatus.UNLOGIN);
        } else {
            player.sendMessage("첫 방문을 환영합니다.");
            player.sendMessage("사용하실 비밀번호를 입력해주세요");
            UserLoginStatus.setPlayerStatus(player, UserStatus.SIGNUP);
        }
    }
}
