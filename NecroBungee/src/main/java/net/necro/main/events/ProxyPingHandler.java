package net.necro.main.events;

import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.necro.main.Necro;
import net.necro.main.utils.Utils;

public class ProxyPingHandler implements Listener{
	
	@SuppressWarnings("unused")
	private Necro plugin;
	public ProxyPingHandler(Necro listener) {
		this.plugin = listener;		
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler(priority = 64)
	public void onPing(ProxyPingEvent ev) {
		ServerPing r = ev.getResponse();
		ServerPing.Players p = r.getPlayers();
		p = new ServerPing.Players(p.getOnline() + 1, p.getOnline(),
				p.getSample());
		Integer num = Utils.randomNum(1, 5);
		ServerPing ping = null;
		
		if (ev.getConnection().getVersion() < Necro.verNum) {
			ev.getResponse().setDescription(Utils.color("&c&lWARNING: &eYou are not on a valid &c&lNECRO &eVersion please use these versions: &c&l1.11"));
			ev.getResponse().setVersion(new ServerPing.Protocol(Utils.color("&c&lNecroBungee: 1.11"), Short.MAX_VALUE));
        }else if (ev.getConnection().getVersion() >= Necro.verNum) {
			if(num == 1){
				ping = new ServerPing(r.getVersion(), p,
						Utils.color("&c&lNECROBUNGEE &b&l- &e&lMOTD 1"),
						r.getFaviconObject());
			}else if(num == 2){
				ping = new ServerPing(r.getVersion(), p,
						Utils.color("&c&lNECROBUNGEE &b&l- &e&lMOTD 2"),
						r.getFaviconObject());
			}else if(num == 3){
				ping = new ServerPing(r.getVersion(), p,
						Utils.color("&c&lNECROBUNGEE &b&l- &e&lMOTD 3"),
						r.getFaviconObject());
			}else if(num == 4){
				ping = new ServerPing(r.getVersion(), p,
						Utils.color("&c&lNECROBUNGEE &b&l- &e&lMOTD 4"),
						r.getFaviconObject());
			}else if(num == 5){
				ping = new ServerPing(r.getVersion(), p,
						Utils.color("&c&lNECROBUNGEE &b&l- &e&lMOTD 5"),
						r.getFavicon());
			}
			ev.setResponse(ping);
        }
	}

}
