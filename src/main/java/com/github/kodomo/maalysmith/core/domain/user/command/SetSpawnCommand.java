package com.github.kodomo.maalysmith.core.domain.user.command;

import com.github.kodomo.maalysmith.core.MALLYSmithCore;
import com.github.leaguelugas.mcpluginframework.register.Executor;
import com.github.leaguelugas.mcpluginframework.register.command.Command;
import com.github.leaguelugas.mcpluginframework.register.command.CommandType;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;


@Command(value = "setspawn", permission = "*", type = CommandType.ONLY_PLAYER)
public class SetSpawnCommand {

    @Executor
    public void setSpawnCommand(MALLYSmithCore main,Player player) {
        FileConfiguration config = main.getConfig();
        config.set("spawn", player.getLocation());
        main.saveConfig();
        player.sendMessage("위치를 저장");
    }
}
