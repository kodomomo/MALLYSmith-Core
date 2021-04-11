package com.github.kodomo.maalysmith.core.domain.economy.command;

import com.github.leaguelugas.mcpluginframework.register.Executor;
import com.github.leaguelugas.mcpluginframework.register.command.Command;
import com.github.leaguelugas.mcpluginframework.register.command.CommandType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import proto.bank.Bank;

import java.util.Arrays;

@Command(value = "trade", aliases = "교환", type = CommandType.ONLY_PLAYER)
public class TradeCommand {
    @Executor
    public void trade(Player player) {
        Inventory inv = Bukkit.createInventory(null, 36, "Trade");
        inv.addItem(createGuiItem(
                Material.BEDROCK, "line", "§aFirst line of the lore", "§bSecond line of the lore"));
        player.openInventory(inv);
    }

    protected ItemStack createGuiItem(final Material material, final String name, final String... lore) {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();

        // Set the name of the item
        meta.setDisplayName(name);

        // Set the lore of the item
        meta.setLore(Arrays.asList(lore));

        item.setItemMeta(meta);

        return item;
    }
}
