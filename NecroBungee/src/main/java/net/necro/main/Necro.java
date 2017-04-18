package net.necro.main;

import java.sql.PreparedStatement;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.necro.main.SQL.SQL;
import net.necro.main.SQL.SQLRanks;
import net.necro.main.commands.FindCommand;
import net.necro.main.commands.HubCommand;
import net.necro.main.commands.ListCommand;
import net.necro.main.commands.MSGCommand;
import net.necro.main.commands.RankCommand;
import net.necro.main.commands.SendToServerCommand;
import net.necro.main.events.PlayerJoin;
import net.necro.main.events.PlayerQuit;
import net.necro.main.events.ProxyPingHandler;
import net.necro.main.utils.Utils;

public class Necro extends Plugin implements Listener {
	
	public static Integer verNum = 314;
	public static String name = "&8&l[&c&lNECRO&8&l]";
	public static SQL mysql;
	
	@SuppressWarnings("deprecation")
	public void onEnable() {
		CommandSender console = getProxy().getConsole();
		console.sendMessage(Utils.color(name + " &aPlugin Enabled!"));
		registerClasses();
		registerCommands();
		connectMySQL();
		for(ProxiedPlayer p : ProxyServer.getInstance().getPlayers()){
			SQLRanks.loadProxiedPlayer(p);
		}
	}
	
	public void registerClasses(){
		getProxy().getPluginManager().registerListener(this, new PlayerJoin(this));
		getProxy().getPluginManager().registerListener(this, new PlayerQuit(this));
		getProxy().getPluginManager().registerListener(this, new SQLRanks(this));
		getProxy().getPluginManager().registerListener(this, new Utils(this));
		getProxy().getPluginManager().registerListener(this, new ProxyPingHandler(this));
	}
	
	public void registerCommands(){
		getProxy().getPluginManager().registerCommand(this, new ListCommand());
		getProxy().getPluginManager().registerCommand(this, new HubCommand());
		getProxy().getPluginManager().registerCommand(this, new MSGCommand());
		getProxy().getPluginManager().registerCommand(this, new RankCommand());
		getProxy().getPluginManager().registerCommand(this, new SendToServerCommand());
		getProxy().getPluginManager().registerCommand(this, new FindCommand());
	}
	
	@SuppressWarnings("deprecation")
	public void onDisable() {
		CommandSender console = getProxy().getConsole();
		console.sendMessage(Utils.color(name + " &cPlugin Disabled!"));
		SQLRanks.onDisableSaveProxiedPlayer();
	}
	
	public SQL getMySQL(){
	     return mysql;
	}
	
	public void connectMySQL() {
		// IPADDRESS, PORT, DATABASE, USERNAME, PASSWORD
	     mysql = new SQL("127.0.0.1", "3306", "NecroStorage", "NecroDevs", "qRtVf2u6xqzGshbg");
	     PreparedStatement ranks = mysql.prepareStatement("CREATE TABLE IF NOT EXISTS Ranks(UUID varchar(36) NOT NULL, NAME VARCHAR(16) NOT NULL, RANK VARCHAR(45) NOT NULL, PRIMARY KEY(UUID))");
	     
	     //USED TO CREATE TABLES FOR INFORMATION
	     mysql.update(ranks);
	}
}
