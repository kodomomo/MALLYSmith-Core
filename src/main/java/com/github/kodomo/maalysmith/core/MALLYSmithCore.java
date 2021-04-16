package com.github.kodomo.maalysmith.core;

import com.github.kodomo.maalysmith.core.domain.user.temp.UserLoginStatus;
import com.github.kodomo.maalysmith.core.global.bean.GRPCContainer;
import com.github.kodomo.maalysmith.core.global.stub.BankGrpcStub;
import com.github.kodomo.maalysmith.core.global.stub.GrpcStub;
import com.github.kodomo.maalysmith.core.global.stub.UserGrpcStub;
import com.github.leaguelugas.mcpluginframework.MCPluginApplication;
import kr.entree.spigradle.annotations.PluginMain;
import org.bukkit.plugin.java.JavaPlugin;

@PluginMain
public class MALLYSmithCore extends JavaPlugin {

    private static MALLYSmithCore instance;
    private final GRPCContainer grpcContainer = new GRPCContainer();

    @Override
    public void onEnable() {
        instance = this;
        setup();
        MCPluginApplication<MALLYSmithCore> application = new MCPluginApplication<>(this);
        application.autoConfiguration();
        application.createConfigFile("config.yml");
    }

    @Override
    public void onDisable() {
        UserLoginStatus.kickUnLoginUsers();
    }

    private void setup() {
        UserLoginStatus.registerUsers();
        grpcContainer.registerStub(UserGrpcStub.class);
        grpcContainer.registerStub(BankGrpcStub.class);
    }

    public static MALLYSmithCore getInstance() {
        return instance;
    }

    public <T extends GrpcStub> T getBean(Class<T> tClass) {
        return grpcContainer.getBean(tClass);
    }

    public void saveConfig() {
        super.saveConfig();
    }

}
