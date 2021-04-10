package com.github.kodomo.maalysmith.core;

import com.github.kodomo.maalysmith.core.global.bean.BeanContainer;
import com.github.kodomo.maalysmith.core.global.stub.BankGrpcStub;
import com.github.kodomo.maalysmith.core.global.stub.UserGrpcStub;
import com.github.leaguelugas.mcpluginframework.MCPluginApplication;
import kr.entree.spigradle.annotations.PluginMain;
import org.bukkit.plugin.java.JavaPlugin;

@PluginMain
public class MALLYSmithCore extends JavaPlugin {

    private static MALLYSmithCore instance;
    private final BeanContainer beanContainer = new BeanContainer();

    @Override
    public void onEnable() {
        instance = this;
        setup();
        MCPluginApplication<MALLYSmithCore> application = new MCPluginApplication<>(this);
        application.autoConfiguration();
    }

    @Override
    public void onDisable() {
        beanContainer.clearBeans();
    }

    private void setup() {
        beanContainer.registerBean(UserGrpcStub.class);
        beanContainer.registerBean(BankGrpcStub.class);
    }

    public static MALLYSmithCore getInstance() {
        return instance;
    }

    public <T> T getBean(Class<T> tClass) {
        return beanContainer.getBean(tClass);
    }

}
