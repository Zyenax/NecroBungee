package net.necro.main.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Command;
import net.necro.main.Necro;
import net.necro.main.utils.Utils;

public class ListCommand extends Command{
	
	public ListCommand() {
		super("glist", null, "list");
	  }
	
	@SuppressWarnings("deprecation")
	public void execute(CommandSender sender, String[] args) {
			if(args.length >= 0){
				sender.sendMessage(Utils.color(Necro.name + " &eThere are &c" + ProxyServer.getInstance().getOnlineCount() + " &eplayers across &c" + ProxyServer.getInstance().getServers().size() + " &eservers!"));
			}
	}
}
