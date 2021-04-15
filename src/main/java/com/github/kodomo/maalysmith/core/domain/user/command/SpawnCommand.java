package com.github.kodomo.maalysmith.core.domain.user.command;

import com.github.kodomo.maalysmith.core.MALLYSmithCore;
import com.github.leaguelugas.mcpluginframework.register.Executor;
import com.github.leaguelugas.mcpluginframework.register.command.Command;
import com.github.leaguelugas.mcpluginframework.register.command.CommandType;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

@Command(value = "spawn", permission = "*", type = CommandType.ONLY_PLAYER)
public class SpawnCommand {

    @Executor
    public void spawn(MALLYSmithCore main, Player player, String[] args) {
        FileConfiguration config = main.getConfig();
        if (args.length == 1) {
            if (config.get("spawn." + args[0]) != null) {
                player.teleport((Location) config.get("spawn." + args[0]));
                player.sendMessage("순간이동 띠롱");
            }
        }
    }
}
