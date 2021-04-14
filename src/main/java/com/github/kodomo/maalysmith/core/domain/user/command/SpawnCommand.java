package com.github.kodomo.maalysmith.core.domain.user.command;

import com.github.kodomo.maalysmith.core.MALLYSmithCore;
import com.github.leaguelugas.mcpluginframework.register.Executor;
import com.github.leaguelugas.mcpluginframework.register.command.Command;
import com.github.leaguelugas.mcpluginframework.register.command.CommandType;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

@Command(value = "spawn", type = CommandType.ONLY_PLAYER, permission = "*")
public class SpawnCommand {

    @Executor
    public void spawn(MALLYSmithCore main, Player player) {
        FileConfiguration config = main.getConfig();
        player.teleport((Location) config.get("spawn"));
        player.sendMessage("순간이동 띠롱");
    }

}
