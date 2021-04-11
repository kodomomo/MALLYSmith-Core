package com.github.kodomo.maalysmith.core.domain.economy.command;

import com.github.kodomo.maalysmith.core.MALLYSmithCore;
import com.github.kodomo.maalysmith.core.global.stub.BankGrpcStub;
import com.github.leaguelugas.mcpluginframework.register.Executor;
import com.github.leaguelugas.mcpluginframework.register.command.Command;
import com.github.leaguelugas.mcpluginframework.register.command.CommandType;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import proto.bank.Bank;

import java.util.Optional;

@Command(value = "deposit", aliases = "입금", type = CommandType.ONLY_PLAYER)
public class DepositCommand {

    private final BankGrpcStub bankGrpcStub = MALLYSmithCore.getInstance().getBean(BankGrpcStub.class);

    @Executor
    public void deposit(Player player, String[] args) {
        if (checkCommandLength(player, args)) {
            getTargetUserUuid(player, args[0]).ifPresent(
                    targetUuid -> depositToUserUuid(player, args[0], targetUuid, args[1])
            );
        }
    }

    private Boolean checkCommandLength(Player player, String[] commands) {
        if (commands.length != 2) {
            player.sendMessage("잘못된 명령어 입니다. 명령어를 다시 확인해주세요.");
            return false;
        } return true;
    }

    private Optional<String> getTargetUserUuid(Player player, String targetUserNickname) {
        return Optional.of("9d56b248-d939-4a57-b120-92e9e3e9f345");
    }

    private void depositToUserUuid(Player player, String targetName, String targetUuid, String money) {
        try {
            bankGrpcStub.deposit(player.getUniqueId().toString(), targetUuid, Long.parseLong(money));
            player.sendMessage(String.format("%s에게 %s원을 보냈습니다.", targetName, money));
        } catch (StatusRuntimeException e) {
            Status.Code code = e.getStatus().getCode();
            if (code == Status.Code.NOT_FOUND) {
                player.sendMessage("없는 유저 입니다.");
            } else if (code == Status.Code.OUT_OF_RANGE) {
                player.sendMessage("잔액을 확인해주세요.");
            } else if (code == Status.Code.INTERNAL) {
                player.kickPlayer("거래 Hash가 조작되었습니다. 어드민에게 문의하세요");
            }
        }
    }
}
