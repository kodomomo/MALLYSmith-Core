package com.github.kodomo.maalysmith.core.global.stub;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import proto.bank.Bank;
import proto.bank.BankServiceGrpc;

public final class BankGrpcStub {

    private static final String host = "25.112.64.235";
    private static final int port = 10002;

    private final ManagedChannel channel = ManagedChannelBuilder.forTarget(host + ":" + port).usePlaintext().build();
    private final BankServiceGrpc.BankServiceBlockingStub bankStub = BankServiceGrpc.newBlockingStub(channel);

    public Bank.OpenAccountResponse openAccount(String uuid) {
        Bank.OpenAccountRequest request = Bank.OpenAccountRequest.newBuilder()
                .setUserId(uuid)
                .build();

        return bankStub.openAccount(request);
    }

    public Bank.GetAccountResponse getAccount(String uuid) {
        Bank.GetAccountRequest request = Bank.GetAccountRequest.newBuilder()
                .setUserId(uuid)
                .build();

        return bankStub.getAccount(request);
    }

    public Bank.DepositResponse deposit(String fromUuid, String toUuid, long money) {
        Bank.DepositRequest request = Bank.DepositRequest.newBuilder()
                .setFromUserId(fromUuid)
                .setToUserId(toUuid)
                .setMoney(money)
                .build();

        return bankStub.deposit(request);
    }

}
