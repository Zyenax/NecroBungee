package net.necro.main.SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.necro.main.Necro;
import net.necro.main.utils.Utils;

public class SQL {
    private final String host, port, database, user, password;

    private Connection con;

    public SQL(String host, String port, String database, String user, String password) {
    	this.host= host;
    	this.port= port;
        this.database= database;
        this.user= user;
        this.password= password;

        connect();
    }

    @SuppressWarnings("deprecation")
	public void connect() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true",user, password);
            CommandSender console = ProxyServer.getInstance().getConsole();
    		console.sendMessage(Utils.color(Necro.name + " &aDATABASE CONNECT SUCCESSFUL!"));
        } catch (SQLException e) {
        	CommandSender console = ProxyServer.getInstance().getConsole();
    		console.sendMessage(Utils.color(Necro.name + " &cDATABASE CONNECT UNSUCCESSFUL! &4REASON: &e" + e.getMessage()));
        }
    }
    
    @SuppressWarnings("deprecation")
	public void close() {
        try {
            if (con != null) {
                con.close();
                CommandSender console = ProxyServer.getInstance().getConsole();
        		console.sendMessage(Utils.color(Necro.name + " &aThe connection to the SQL database has ended successfully!"));
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public PreparedStatement prepareStatement(String qry) {
        PreparedStatement st = null;
        try {
            st = con.prepareStatement(qry);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return st;
    }

    public void update(PreparedStatement statement) {
        try {
            statement.executeUpdate();    
        } catch (SQLException e) {
            connect();
            e.printStackTrace();
        }finally{
           try {
             statement.close();
           } catch (SQLException e) {
             e.printStackTrace();
           }
    }
    }

    public boolean hasConnection() {
        return con != null;
    }

    public ResultSet query(PreparedStatement statement) {
        try {
            return statement.executeQuery();
        } catch (SQLException e) {
            connect();
            e.printStackTrace();
        }
        return null;
    }
}
