package net.necro.main.events;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.necro.main.Necro;
import net.necro.main.SQL.Rank;
import net.necro.main.SQL.SQLRanks;
import net.necro.main.utils.Utils;

public class PlayerJoin implements Listener{

	@SuppressWarnings("unused")
	private Necro plugin;
	public PlayerJoin(Necro Necro) {
		plugin = Necro;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onJoin(PreLoginEvent e){
		if(e.getConnection().getVersion() < Necro.verNum){
			e.setCancelReason(Utils.color("&c&lWARNING \n &cERROR: &eDisconnected \n &cREASON: &eInvalid Version \n &cFIX: &eUpdate Client \n &cYOUR VERSION: &e" + e.getConnection().getVersion() + "\n &cSUPPORTED VERSIONS: &e" + Necro.verNum + " and up"));
			e.setCancelled(true);
			e.getConnection().disconnect(Utils.color("&c&lWARNING \n &cERROR: &eDisconnected \n &cREASON: &eInvalid Version \n &cFIX: &eUpdate Client \n &cYOUR VERSION: &e" + e.getConnection().getVersion() + "\n &cSUPPORTED VERSIONS: &e" + Necro.verNum + " and up"));
		}
	}
	
	@EventHandler
	public void onJoin(PostLoginEvent e){
		final ProxiedPlayer p = (ProxiedPlayer) e.getPlayer();
		if(SQLRanks.getRank(p) == null){
			SQLRanks.setRank(p, Rank.DEFAULT.getName());
		}
		SQLRanks.loadProxiedPlayer(p);
		
	}

}
