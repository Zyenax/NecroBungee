package net.necro.main;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.necro.main.commands.FindCommand;
import net.necro.main.commands.HubCommand;
import net.necro.main.commands.ListCommand;
import net.necro.main.commands.MSGCommand;
import net.necro.main.commands.SendToServerCommand;
import net.necro.main.events.PlayerJoin;
import net.necro.main.events.ProxyPingHandler;
import net.necro.main.utils.Utils;

public class Necro extends Plugin implements Listener {
	
	public static Integer verNum = 314;
	public static String name = "&8&l[&c&lNECRO&8&l]";
	
	@SuppressWarnings("deprecation")
	public void onEnable() {
		CommandSender console = getProxy().getConsole();
		console.sendMessage(Utils.color(name + " &aPlugin Enabled!"));
		registerClasses();
		registerCommands();
	}
	
	public void registerClasses(){
		getProxy().getPluginManager().registerListener(this, new PlayerJoin(this));
		getProxy().getPluginManager().registerListener(this, new Utils(this));
		getProxy().getPluginManager().registerListener(this, new ProxyPingHandler(this));
	}
	
	public void registerCommands(){
		getProxy().getPluginManager().registerCommand(this, new ListCommand());
		getProxy().getPluginManager().registerCommand(this, new HubCommand());
		getProxy().getPluginManager().registerCommand(this, new MSGCommand());
		getProxy().getPluginManager().registerCommand(this, new SendToServerCommand());
		getProxy().getPluginManager().registerCommand(this, new FindCommand());
	}
	
	@SuppressWarnings("deprecation")
	public void onDisable() {
		CommandSender console = getProxy().getConsole();
		console.sendMessage(Utils.color(name + " &cPlugin Disabled!"));
	}
}
