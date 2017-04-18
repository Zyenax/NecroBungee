package net.necro.main.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.necro.main.Necro;
import net.necro.main.utils.Utils;

public class HubCommand extends Command{
	
	public HubCommand() {
		super("hub", null, "lobby");
	  }
	
	@SuppressWarnings("deprecation")
	public void execute(CommandSender sender, String[] args) {
		if(sender instanceof ProxiedPlayer){
            ProxiedPlayer player = (ProxiedPlayer) sender;
            if(!player.getServer().getInfo().getName().equalsIgnoreCase("Lobby")){
            	ServerInfo target = ProxyServer.getInstance().getServerInfo("Lobby");
            	player.sendMessage(Utils.color(Necro.name + " &eSending you to &c" + target.getName()));
            	player.connect(target);
            }else{
            	player.sendMessage(Utils.color(Necro.name + " &eYou are already connected to that server!"));
            }
		}else{
			sender.sendMessage(Utils.color(Necro.name + " &eYou are not a player!"));
		}
	}
}
