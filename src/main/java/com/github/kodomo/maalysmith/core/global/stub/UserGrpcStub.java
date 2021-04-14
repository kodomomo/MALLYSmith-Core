package com.github.kodomo.maalysmith.core.global.stub;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import proto.user.User;
import proto.user.UserServiceGrpc;

public class UserGrpcStub {

    private static final String host = "25.112.155.55";
    private static final int port = 8000;

    public boolean isExistsUser(String uuid) {
        User.isExistsRequest request = User.isExistsRequest.newBuilder()
                .setUuid(uuid)
                .build();

        User.isExistsResponse response = getUserStub().isExists(request);
        return response.getExists();
    }

    public User.SignUpResponse signUp(String uuid, String name, String password) {
        User.SignUpRequest request = User.SignUpRequest.newBuilder()
                .setUuid(uuid)
                .setName(name)
                .setPassword(password)
                .build();

        if (!isExistsUser(uuid)) {
            return getUserStub().signUp(request);
        }
        return null;
    }

    public User.SignInResponse signIn(String uuid, String password) {
        User.SignInRequest request = User.SignInRequest.newBuilder()
                .setUuid(uuid)
                .setPassword(password)
                .build();

        return getUserStub().signIn(request);
    }

    private UserServiceGrpc.UserServiceBlockingStub getUserStub() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
        return UserServiceGrpc.newBlockingStub(channel);
    }

}
