package net.necro.main.SQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Listener;
import net.necro.main.Necro;

public class SQLRanks implements Listener{
	
	@SuppressWarnings("unused")
	private static Necro plugin;
	public SQLRanks(Necro listener) {
		SQLRanks.plugin = listener;		
	}

    public static HashMap<UUID, String> rank = new HashMap<UUID, String>();

    private static final String INSERT = "INSERT INTO Ranks VALUES(?,?,?) ON DUPLICATE KEY UPDATE NAME=?";
    private static final String SELECT = "SELECT RANK FROM Ranks WHERE UUID=?";
    private static final String SAVE = "UPDATE Ranks SET RANK=? WHERE UUID=?";
    
    private static void addProxiedPlayer(ProxiedPlayer p, String rankName) {
        removeProxiedPlayer(p);
        rank.put(p.getUniqueId(), rankName);
    }

    public static void removeProxiedPlayer(ProxiedPlayer p) {
        rank.remove(p.getUniqueId());
    }

    public static String getRank(ProxiedPlayer target) {
        if (rank.containsKey(target.getUniqueId())){
            return rank.get(target.getUniqueId());
        }else{
        	return null;
        }
        
    }

    public static void setRank(ProxiedPlayer target, String rankname) {
        if (rank.containsKey(target.getUniqueId())){
        	rank.remove(target.getUniqueId());
            rank.put(target.getUniqueId(), rankname);
        }else{
           rank.put(target.getUniqueId(), rankname);
        }
    }
    
    public static void loadProxiedPlayer(final ProxiedPlayer p) {
                try {
                    PreparedStatement statement = Necro.mysql.prepareStatement(INSERT);
                    statement.setString(1, p.getUniqueId().toString());
                    statement.setString(2, p.getName());
                    statement.setString(3, rank.get(p.getUniqueId()));
                    statement.setString(4, p.getName());
                    Necro.mysql.update(statement);

                    statement = Necro.mysql.prepareStatement(SELECT);
                    statement.setString(1, p.getUniqueId().toString());
                    ResultSet result = Necro.mysql.query(statement);
                    if (result.next())
                        addProxiedPlayer(p, result.getString("RANK"));
                    result.close();
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
    }
    
    public static void saveProxiedPlayer(final ProxiedPlayer p) {
                try {
                    PreparedStatement statement = Necro.mysql.prepareStatement(SAVE);
                    statement.setString(1, rank.get(p.getUniqueId()));
                    statement.setString(2, p.getUniqueId().toString());
                    Necro.mysql.update(statement);
                    removeProxiedPlayer(p);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
    }
    
    public static void onDisableSaveProxiedPlayer() {
            	for(ProxiedPlayer p : ProxyServer.getInstance().getPlayers()){
                try {
                	if(rank.containsKey(p.getUniqueId())){
                    PreparedStatement statement = Necro.mysql.prepareStatement(SAVE);
                    statement.setString(1, rank.get(p.getUniqueId()));
                    statement.setString(2, p.getUniqueId().toString());
                    Necro.mysql.update(statement);
                    removeProxiedPlayer(p);
                	}
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            	}
    }
}
