package net.necro.main.events;

import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.necro.main.Necro;
import net.necro.main.SQL.SQLRanks;

public class PlayerQuit implements Listener{
	
	@SuppressWarnings("unused")
	private Necro plugin;
	public PlayerQuit(Necro listener) {
		this.plugin = listener;		
	}
	
	@EventHandler
	public void onQuit(PlayerDisconnectEvent e){
		if(SQLRanks.rank.containsKey(e.getPlayer().getUniqueId())){
			SQLRanks.saveProxiedPlayer(e.getPlayer());
		}
	}
}
