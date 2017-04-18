package net.necro.main.commands;

import net.md_5.bungee.api.Callback;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.necro.main.Necro;
import net.necro.main.utils.Utils;

public class SendToServerCommand extends Command{
	
	public SendToServerCommand() {
	      super("join", null, "server");
	  }
	
	@SuppressWarnings("deprecation")
	public void execute(final CommandSender sender, String[] args) {
		if(sender instanceof ProxiedPlayer){
			final ProxiedPlayer player = (ProxiedPlayer)sender;
			if(args.length == 0){
				sender.sendMessage(Utils.color(Necro.name + " &e/join [name]"));
			}
		
			
			
			if(args.length == 1){
				final String target = args[0];
					if(!ProxyServer.getInstance().getServers().containsKey(target)){
						sender.sendMessage(Utils.color(Necro.name + " &eYou must supply a valid server name."));
					}else{
						if(!player.getServer().getInfo().getName().equalsIgnoreCase(target)){
							final ServerInfo targetserver = ProxyServer.getInstance().getServerInfo(target);
								targetserver.ping(new Callback<ServerPing>() {
								    public void done(ServerPing result, Throwable error) {
								        if(result != null){
								        	sender.sendMessage(Utils.color(Necro.name + " &eSending you to &c" + target));
											player.connect(targetserver);
								        }else{
								        	sender.sendMessage(Utils.color(Necro.name + " &c" + target + " &eis currently not online!"));
								        }
								    }
								});
						}else{
							sender.sendMessage(Utils.color(Necro.name + " &eYou are already connected to that server."));
						}
						
					}
			}
		}else{
			sender.sendMessage(Utils.color(Necro.name + " &eYou are not allowed to type this command"));
		}
	}
}
