package de.niklas326.test.commands;

import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Niklas
 * Created on 23/05/2023 / 22:17
 */

public class KickAllCommand implements SimpleCommand {

    private final ProxyServer proxyServer;
    private final Component gradientPrefix = MiniMessage.miniMessage().deserialize("<gradient:#FF9B00:#FF0C00>BladeMC <dark_gray>● <gray>").asComponent();

    public KickAllCommand(ProxyServer proxyServer) {
        this.proxyServer = proxyServer;
    }

    @Override
    public void execute(Invocation invocation) {
        if (invocation.arguments().length != 1) {
            proxyServer.getAllPlayers()
                    .forEach(player -> player.disconnect(gradientPrefix.append(Component.text("Du wurdest vom Server geworfen!", NamedTextColor.RED)
                    )));
            return;
        }

        Optional<RegisteredServer> server = proxyServer.getServer(invocation.arguments()[0]);

        if (server.isEmpty()) {
            invocation.source().sendMessage(gradientPrefix.append(Component.text("Der Server existiert nicht!", NamedTextColor.RED)));
            return;
        }

        server.get().getPlayersConnected()
                .forEach(player -> player.disconnect(gradientPrefix.append(Component.text("Du wurdest vom Server geworfen!", NamedTextColor.RED)
                )));


    }

    @Override
    public List<String> suggest(Invocation invocation) {
        if(invocation.arguments().length == 1) {
            return proxyServer.getAllServers().stream()
                    .map(server -> server.getServerInfo().getName())
                    .filter(serverName -> serverName.startsWith(invocation.arguments()[0]))
                    .collect(Collectors.toList());
        }

        return Collections.emptyList();
    }
}
