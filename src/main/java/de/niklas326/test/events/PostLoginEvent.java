package de.niklas326.test.events;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.proxy.ProxyServer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

/**
 * @author Niklas
 * Created on 23/05/2023 / 22:11
 */

public class PostLoginEvent {

    private final ProxyServer proxyServer;
    private final Component gradientPrefix = MiniMessage.miniMessage().deserialize("<gradient:#FF9B00:#FF0C00>Server <dark_gray>● <gray>").asComponent();

    public PostLoginEvent(ProxyServer proxyServer) {
        this.proxyServer = proxyServer;
    }
    @Subscribe
    public void onPostLogin(com.velocitypowered.api.event.connection.PostLoginEvent event) {
        proxyServer.sendMessage(gradientPrefix.append(Component.text("PostLoginEvent " + event.getPlayer().getUsername())));

    }
}
