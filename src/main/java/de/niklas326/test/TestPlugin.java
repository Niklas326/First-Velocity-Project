package de.niklas326.test;

import com.google.inject.Inject;
import com.velocitypowered.api.command.CommandManager;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import de.niklas326.test.commands.KickAllCommand;
import de.niklas326.test.events.DisconnectEvent;
import de.niklas326.test.events.PostLoginEvent;
import org.slf4j.Logger;

/**
 * @author Niklas
 * Created on 23/05/2023 / 22:07
 */

@Plugin(id = "testplugin", name = "TestPlugin", version = "1.0")
public class TestPlugin {

    private final ProxyServer proxyServer;
    private final Logger logger;

    @Inject
    public TestPlugin(ProxyServer proxyServer, Logger logger) {
        this.proxyServer = proxyServer;
        this.logger = logger;

        logger.info("TestPlugin loaded");
    }

    @Subscribe
    public void onProxyInitialize(ProxyInitializeEvent event) {
        logger.info("TestPlugin initialized");

        proxyServer.getEventManager().register(this, new PostLoginEvent(proxyServer));
        proxyServer.getEventManager().register(this, new DisconnectEvent(proxyServer));

        CommandManager commandManager = proxyServer.getCommandManager();
        commandManager.register(commandManager.metaBuilder("kickall").build(), new KickAllCommand(proxyServer));

    }
}
