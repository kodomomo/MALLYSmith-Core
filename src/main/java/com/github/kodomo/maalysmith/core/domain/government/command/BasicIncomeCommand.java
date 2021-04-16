package com.github.kodomo.maalysmith.core.domain.government.command;

import com.github.kodomo.maalysmith.core.MALLYSmithCore;
import com.github.leaguelugas.mcpluginframework.register.Executor;
import com.github.leaguelugas.mcpluginframework.register.command.Command;
import com.github.leaguelugas.mcpluginframework.register.command.CommandType;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

import static com.github.leaguelugas.mcpluginframework.util.Utils.color;

@Command(value = "basicincome", aliases = "기본수당", type = CommandType.ONLY_PLAYER, permission = "*")
public class BasicIncomeCommand {

    private static final String PREFIX = "&f[&6기본수당&f]&r";
    private static final Set<Player> SETTING = new HashSet<>();

    @Executor
    public void basicIncome(MALLYSmithCore main, String cmd, String[] args, Player player) {
        FileConfiguration config = main.getConfig();
        int value = config.getInt("basicincome.amount", 0);
        Location location = (Location) config.get("basicincome.location");
        if (args.length == 1) {
            switch (args[0]) {
                case "config":
                    player.sendMessage(color(PREFIX + " &6기본수당: &c" + value + "원"));
                    if (location != null) {
                        player.sendMessage(color(String.format(PREFIX + " &6지급 블럭: &f%s, %s, %s",
                                location.getBlockX(),
                                location.getBlockY(),
                                location.getBlockZ()
                        )));
                    }
                    return;
                case "setloc":
                    if (isSetting(player)) {
                        SETTING.remove(player);
                        player.sendMessage(color(PREFIX + " 기본수당 지급 블럭 설정을 취소했습니다"));
                    } else {
                        SETTING.add(player);
                        player.sendMessage(color(PREFIX + " 기본수당 지급 블럭 설정을 시작했습니다"));
                    }
                    return;
                case "help":
                    player.sendMessage(color(PREFIX + " &6명령어 도움말"));
                    player.sendMessage(color("/" + cmd + " config &7- &f기본수당 관련 설정값을 확인합니다"));
                    player.sendMessage(color("/" + cmd + " setloc &7- &f클릭시 기본수당을 지급할 블럭을 좌클릭하여 선택합니다 " +
                            "(명령어를 한번 더 실행하면 선택상태가 취소됩니다)"));
                    player.sendMessage(color("/" + cmd + " setamount <숫자> &7- &f기본수당을 설정합니다"));
                    break;
            }
        } else if (args.length == 2) {
            if (args[0].equals("setamount")) {
                try {
                    int amount = Integer.parseInt(args[1]);
                    config.set("basicincome.amount", amount);
                    main.saveConfig();
                    player.sendMessage(color(PREFIX + " &a기본수당을 &c" + amount + "&a원으로 설정했습니다"));
                    return;
                } catch (Exception ignored) {}
            }
        }
        player.sendMessage(color("&c[기본수당] 잘못된 명령어입니다. /" + cmd + " help 로 도움말을 확인하세요"));
    }

    public static boolean isSetting(Player player) {
        return SETTING.contains(player);
    }

}
