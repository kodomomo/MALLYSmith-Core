package com.github.kodomo.maalysmith.core.domain.user.listener;

import com.github.kodomo.maalysmith.core.MALLYSmithCore;
import com.github.kodomo.maalysmith.core.domain.user.temp.UserLoginStatus;
import com.github.kodomo.maalysmith.core.domain.user.temp.UserStatus;
import com.github.kodomo.maalysmith.core.global.stub.UserGrpcStub;
import com.github.leaguelugas.mcpluginframework.register.Executor;
import com.github.leaguelugas.mcpluginframework.register.listener.Listener;
import io.grpc.StatusRuntimeException;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Listener(AsyncPlayerChatEvent.class)
public class PlayerChatListener {

    private final UserGrpcStub userGrpcStub = MALLYSmithCore.getInstance().getBean(UserGrpcStub.class);

    @Executor
    public void chatPassword(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String uuid = player.getUniqueId().toString();
        String password = event.getMessage();
        Pattern pattern = Pattern.compile("[~!@#$%^&*()_+`\\-=\\\\|{}\\[\\]:;\"'<>?,./ ]");
        Matcher matcher = pattern.matcher(password);

        if (UserLoginStatus.getStatus(player) == UserStatus.UNLOGIN) {
            try {
                userGrpcStub.signIn(uuid, password);
                player.sendMessage("로그인에 성공하였습니다");
                UserLoginStatus.setPlayerStatus(player, UserStatus.LOGIN);
                Bukkit.broadcastMessage(player.getName() + "님이 접속하였습니다");
            } catch (StatusRuntimeException e) {
                player.sendMessage("비밀번호가 일치하지 않습니다");
            }
        } else if (UserLoginStatus.getStatus(player) == UserStatus.SIGNUP) {
            if (!userGrpcStub.isExistsUser(uuid)) {
                if (matcher.find()) {
                    player.sendMessage("=========================");
                    player.sendMessage("특수문자는 허용할 수 없습니다");
                    player.sendMessage("회원가입을 다시 진행해 주세요");
                    player.sendMessage("=========================");
                } else {
                    UserLoginStatus.setTempPassword(player, password);

                    player.sendMessage("비밀번호를 한번 더 입력해주세요");
                    UserLoginStatus.setPlayerStatus(player, UserStatus.RESIGNUP);
                }
            }
        } else if (UserLoginStatus.getStatus(player) == UserStatus.RESIGNUP) {
            if (!userGrpcStub.isExistsUser(uuid)) {
                String tempPassword = UserLoginStatus.getTempPassword(player);
                if (tempPassword.equals(password)) {
                    userGrpcStub.signUp(uuid, player.getName(), tempPassword);
                    UserLoginStatus.setPlayerStatus(player, UserStatus.LOGIN);
                    player.sendMessage("회원가입이 완료되었습니다");
                } else {
                    player.sendMessage("=========================");
                    player.sendMessage("비밀번호가 일치하지 않습니다");
                    player.sendMessage("회원가입을 다시 진행해 주세요");
                    player.sendMessage("=========================");
                    UserLoginStatus.setPlayerStatus(player, UserStatus.SIGNUP);
                }
            }
        } else {
            return;
        }
        event.setCancelled(true);
    }
}
