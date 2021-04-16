package com.github.kodomo.maalysmith.core.domain.core.command;

import com.github.kodomo.maalysmith.core.MALLYSmithCore;
import com.github.leaguelugas.mcpluginframework.register.Executor;
import com.github.leaguelugas.mcpluginframework.register.command.Command;
import org.bukkit.command.CommandSender;

@Command(value = "permission", aliases = "per", permission = "*")
public class PermissionCommand {

    @Executor
    public void permission(MALLYSmithCore main, CommandSender sender, String cmd, String[] args, org.bukkit.command.Command command) {
        if (args.length == 1) {
            // /per <player>
        }
    }

}
