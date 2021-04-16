package com.github.kodomo.maalysmith.core.domain.government.listener;

import com.github.kodomo.maalysmith.core.MALLYSmithCore;
import com.github.kodomo.maalysmith.core.domain.government.command.BasicIncomeCommand;
import com.github.leaguelugas.mcpluginframework.register.Executor;
import com.github.leaguelugas.mcpluginframework.register.listener.Listener;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;

import static com.github.leaguelugas.mcpluginframework.util.Utils.color;

@Listener(BlockBreakEvent.class)
public class BasicIncomeSetBlockListener {

    @Executor
    public void setBasicIncomeBlock(MALLYSmithCore main, BlockBreakEvent event, Player player) {
        if (BasicIncomeCommand.isSetting(player)) {
            FileConfiguration config = main.getConfig();
            event.setCancelled(true);
            Location location = event.getBlock().getLocation();
            config.set("basicincome.location", location);
            main.saveConfig();
            player.sendMessage(color(String.format("&f[&6기본수당&f] &a기본수당 지급 블럭을 설정했습니다 (%s, %s, %s)",
                    location.getBlockX(),
                    location.getBlockY(),
                    location.getBlockZ()
            )));
        }
    }

}
