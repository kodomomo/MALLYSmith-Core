package com.github.kodomo.maalysmith.core.domain.user.command;

import com.github.leaguelugas.mcpluginframework.register.Executor;
import com.github.leaguelugas.mcpluginframework.register.command.Command;
import com.github.leaguelugas.mcpluginframework.register.command.CommandType;
import org.bukkit.entity.Player;

@Command(value = "speed", permission = "*", type = CommandType.ONLY_PLAYER)
public class SpeedCommand {

    @Executor
    public void onSpeed(String[] args, Player player) {
        Player players = player.getPlayer();

        if (args.length == 1) {
            if (Float.parseFloat(args[0]) > 1.1) {
                players.sendMessage("1.1 이상은 등록할 수 없습니다");
                players.sendMessage("0에서 1사이 숫자로 다시 입력해주세요 ex)0.5");
            }
                players.setFlySpeed(Float.parseFloat(args[0]));
                players.sendMessage("속도가 " + args[0] + " 만큼 증가하였습니다");
        }
    }
}
