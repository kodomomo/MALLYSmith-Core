package com.github.kodomo.maalysmith.core.domain.user.temp;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class UserLoginStatus {

    private static HashMap<Player, UserStatus> status = new HashMap<>();
    private static HashMap<Player, String> tempPassword = new HashMap<>();

    public static void setPlayerStatus(Player player, UserStatus status) {
        UserLoginStatus.status.put(player, status);
    }

    public static void setTempPassword(Player player, String password) {
        tempPassword.put(player, password);
    }

    public static UserStatus getStatus(Player player) {
        return status.get(player);
    }

    public static String getTempPassword(Player player) {
        return tempPassword.get(player);
    }

    public static void resetStatus(Player player) {
        status.remove(player);
    }

    public static void registerUsers() {
        Bukkit.getServer().getOnlinePlayers().stream()
                .filter(player -> !status.containsKey(player))
                .forEach(player -> setPlayerStatus(player, UserStatus.LOGIN));
    }

    public static void kickUnLoginUsers() {
        status.keySet().stream()
                .filter(player -> getStatus(player) != UserStatus.LOGIN)
                .forEach(player -> player.kickPlayer("서버 리로드 중입니다. 잠시 후 접속 해주세요"));
    }

}
