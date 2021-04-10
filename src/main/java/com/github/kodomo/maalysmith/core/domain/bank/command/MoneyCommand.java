package com.github.kodomo.maalysmith.core.domain.bank.command;

import com.github.kodomo.maalysmith.core.MALLYSmithCore;
import com.github.kodomo.maalysmith.core.global.stub.BankGrpcStub;
import com.github.leaguelugas.mcpluginframework.register.Executor;
import com.github.leaguelugas.mcpluginframework.register.command.Command;
import com.github.leaguelugas.mcpluginframework.register.command.CommandType;
import org.bukkit.entity.Player;
import proto.bank.Bank;

@Command(value = "money", aliases = "돈", type = CommandType.ONLY_PLAYER)
public class MoneyCommand {

    private final BankGrpcStub bankGrpcStub = MALLYSmithCore.getInstance().getBean(BankGrpcStub.class);

    @Executor
    public void getMoney(Player player) {
        String uuid = player.getUniqueId().toString();
        Bank.GetAccountResponse response = bankGrpcStub.getAccount(uuid);
        player.sendMessage("잔고: " + response.getMoney());
    }

}
