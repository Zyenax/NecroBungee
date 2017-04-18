package net.necro.main.utils;

import java.util.Random;

import net.md_5.bungee.api.plugin.Listener;
import net.necro.main.Necro;

public class Utils implements Listener{

	@SuppressWarnings("unused")
	private Necro plugin;
	public Utils(Necro riot) {
		plugin = riot;
	}
	
	public static String color(String string) {
		return net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&',
				string);
	}
	
	public static int randomNum(int Low, int High){
		Random r = new Random();
		int R = r.nextInt(High-Low) + Low;
		return R;
	}

}
