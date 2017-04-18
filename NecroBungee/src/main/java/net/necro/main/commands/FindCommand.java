package net.necro.main.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.necro.main.Necro;
import net.necro.main.utils.Utils;

public class FindCommand extends Command{
	
	public FindCommand() {
	      super("find", null, "locate");
	  }
	
	@SuppressWarnings("deprecation")
	public void execute(final CommandSender sender, String[] args) {
		if(sender instanceof ProxiedPlayer){
			final ProxiedPlayer player = (ProxiedPlayer)sender;
			if(args.length == 0){
				sender.sendMessage(Utils.color(Necro.name + " &e/find [playername]"));
			}
		
			
			
			if(args.length == 1){
				final ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);
					if(target == null){
						sender.sendMessage(Utils.color(Necro.name + " &eThat player is not online!"));
					}else{
						player.sendMessage(Utils.color(Necro.name + " &c" + target + " &eis on the server named &c" + target.getServer().getInfo().getName()));
					}
			}
		}else{
			sender.sendMessage(Utils.color(Necro.name + " &eYou are not allowed to type this command"));
		}
	}
}
