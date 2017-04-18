package net.necro.main.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.necro.main.Necro;
import net.necro.main.utils.Utils;

public class MSGCommand extends Command{
	
	public MSGCommand() {
	      super("msg", null, "tell", "message");
	  }
	
	@SuppressWarnings("deprecation")
	public void execute(final CommandSender sender, String[] args) {
		if(args.length == 0){
			String incompleteCommand = Utils.color(Necro.name + " &eThe command is /msg [Player] [message]");
			String badSender = Utils.color(Necro.name + " &eYou do not have permission to send this command!");
			if(sender instanceof ProxiedPlayer){
				ProxiedPlayer ProxiedPlayer = (ProxiedPlayer)sender;
					ProxiedPlayer.sendMessage(incompleteCommand);
			}else if(sender instanceof CommandSender){
				sender.sendMessage(incompleteCommand);
			}else{
				sender.sendMessage(badSender);
			}
		}
		
		if(args.length == 1){
			ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);
			String noMessageProvided = Utils.color(Necro.name + " &eYou must supply a message to send!");
			String invalidProxiedPlayer = Utils.color(Necro.name + " &eYou must supply an online Players name!");
			String badSender = Utils.color(Necro.name + " &eYou do not have permission to send this command!");
				if(sender instanceof ProxiedPlayer){
					ProxiedPlayer ProxiedPlayer = (ProxiedPlayer)sender;
					if(target != null){
							ProxiedPlayer.sendMessage(noMessageProvided);
					}else{
						ProxiedPlayer.sendMessage(invalidProxiedPlayer);
					}
				}else if(sender instanceof CommandSender){
					if(target != null){
						sender.sendMessage(noMessageProvided);
					}else{
						sender.sendMessage(invalidProxiedPlayer);
					}
				}else{
					sender.sendMessage(badSender);
				}
			}
		
		
		
		
			if(args.length >= 2){
				ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);
				String msg = "";
	            String[] arrayOfString;
	            int j = (arrayOfString = args).length;
	            for (int i = 1; i < j; i++)
	            {
	              String str = arrayOfString[i];
	              msg = msg + str + " ";
	            }
				if(sender instanceof ProxiedPlayer){
					ProxiedPlayer p = (ProxiedPlayer) sender;
					target.sendMessage(Utils.color("&c&l" + p.getName() + " &b> " + " &e&lYou &6= &a" + msg));
				}else if(sender instanceof CommandSender){
					target.sendMessage(Utils.color("&c&l" + sender.getName() + " &b> " + " &e&lYou &6= &a" + msg));
				}
			}
	}
}
