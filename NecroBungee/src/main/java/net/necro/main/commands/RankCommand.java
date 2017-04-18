package net.necro.main.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.necro.main.Necro;
import net.necro.main.SQL.Rank;
import net.necro.main.SQL.SQLRanks;
import net.necro.main.utils.Utils;

public class RankCommand extends Command{

	public RankCommand() {
		super("setrank");
	}

	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] args) {
				if(args.length == 0){
					String incompleteCommand = Utils.color(Necro.name + " &eThe command is /setrank [Player] [rankvalue]");
					String noPerms = Utils.color(Necro.name + " &eYou must have " + Rank.SRMOD.getColor() + Rank.SRMOD.getName() + " &eor above to use this!");
					String badSender = Utils.color(Necro.name + " &eYou do not have permission to send this command!");
					if(sender instanceof ProxiedPlayer){
						ProxiedPlayer ProxiedPlayer = (ProxiedPlayer)sender;
						if(SQLRanks.getRank(ProxiedPlayer).equals(Rank.SRMOD.getName())
								|| SQLRanks.getRank(ProxiedPlayer).equals(Rank.ADMIN.getName())
								|| SQLRanks.getRank(ProxiedPlayer).equals(Rank.OWNER.getName())){
							ProxiedPlayer.sendMessage(incompleteCommand);
						}else{
							ProxiedPlayer.sendMessage(noPerms);
						}
					}else if(sender instanceof CommandSender){
						sender.sendMessage(incompleteCommand);
					}else{
						sender.sendMessage(badSender);
					}
				}
			
				if(args.length == 1){
					ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);
					String noRankProvided = Utils.color(Necro.name + " &eYou must supply a rank to set!");
					String noPerms = Utils.color(Necro.name + " &eYou must have " + Rank.SRMOD.getColor() + Rank.SRMOD.getName() + " &eor above to use this!");
					String invalidProxiedPlayer = Utils.color(Necro.name + " &eYou must supply an online ProxiedPlayers name!");
					String badSender = Utils.color(Necro.name + " &eYou do not have permission to send this command!");
						if(sender instanceof ProxiedPlayer){
							ProxiedPlayer ProxiedPlayer = (ProxiedPlayer)sender;
							if(target != null){
								if(SQLRanks.getRank(ProxiedPlayer).equals(Rank.SRMOD.getName())
										|| SQLRanks.getRank(ProxiedPlayer).equals(Rank.ADMIN.getName())
										|| SQLRanks.getRank(ProxiedPlayer).equals(Rank.OWNER.getName())){
									ProxiedPlayer.sendMessage(noRankProvided);
								}else{
									ProxiedPlayer.sendMessage(noPerms);
								}
							}else{
								ProxiedPlayer.sendMessage(invalidProxiedPlayer);
							}
						}else if(sender instanceof CommandSender){
							if(target != null){
								sender.sendMessage(noRankProvided);
							}else{
								sender.sendMessage(invalidProxiedPlayer);
							}
						}else{
							sender.sendMessage(badSender);
						}
					}
			
				if(args.length == 2){
					String rank = args[1].toString(); 
					String kickMessage = Utils.color(Necro.name + "\n&cYou have been kicked by: &b" + sender.getName() + "\n&aReason: &eYour rank has been updated!\n&aInfo: &ePlease relog to see the change!\n&aChange: &eRank updated to &c" + rank.toUpperCase());
					String noPerms = Utils.color(Necro.name + " &eYou must have " + Rank.SRMOD.getColor() + Rank.SRMOD.getName() + " &eor above to use this!");
					String invalidProxiedPlayer = Utils.color(Necro.name + " &eYou must supply an online ProxiedPlayers name!");
					String invalidRank = Utils.color(Necro.name + " &eYou must supply a valid rank name!");
					String badSender = Utils.color(Necro.name + " &eYou do not have permission to send this command!");
					String noHigherPerm = Utils.color(Necro.name + " &eYou are not allowed to edit this ProxiedPlayers rank!");
					ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);
					if(sender instanceof ProxiedPlayer){
						ProxiedPlayer ProxiedPlayer = (ProxiedPlayer)sender;
						if(target != null){
							if(SQLRanks.getRank(ProxiedPlayer).equals(Rank.SRMOD.getName())){
								if(!SQLRanks.getRank(target).equals(Rank.OWNER.getName()) 
										&& !SQLRanks.getRank(target).equals(Rank.ADMIN.getName()) 
										&& !SQLRanks.getRank(target).equals(Rank.SRMOD.getName())){
									if(rank.equalsIgnoreCase(Rank.DEFAULT.getName()) 
											|| rank.equalsIgnoreCase(Rank.DONOR1.getName())
											|| rank.equalsIgnoreCase(Rank.DONOR2.getName())
											|| rank.equalsIgnoreCase(Rank.DONOR3.getName())
											|| rank.equalsIgnoreCase(Rank.BUILDER.getName())
											|| rank.equalsIgnoreCase(Rank.HELPER.getName())
											|| rank.equalsIgnoreCase(Rank.MOD.getName())){
										//UPDATE RANK THEN SAVE THEN PROVIDE KICKMESSAGE
										SQLRanks.setRank(target, rank.toUpperCase());
										if(target.isConnected()){
											target.disconnect(kickMessage);
										}
									}else{
										sender.sendMessage(invalidRank);
									}
								}else{
									sender.sendMessage(noHigherPerm);
								}
							}else if(SQLRanks.getRank(ProxiedPlayer).equals(Rank.ADMIN.getName())){
								if(!SQLRanks.getRank(target).equals(Rank.OWNER.getName()) 
										&& !SQLRanks.getRank(target).equals(Rank.ADMIN.getName())){
									if(rank.equalsIgnoreCase(Rank.DEFAULT.getName()) 
											|| rank.equalsIgnoreCase(Rank.DONOR1.getName())
											|| rank.equalsIgnoreCase(Rank.DONOR2.getName())
											|| rank.equalsIgnoreCase(Rank.DONOR3.getName())
											|| rank.equalsIgnoreCase(Rank.BUILDER.getName())
											|| rank.equalsIgnoreCase(Rank.HELPER.getName())
											|| rank.equalsIgnoreCase(Rank.MOD.getName())
											|| rank.equalsIgnoreCase(Rank.SRMOD.getName())){
										//UPDATE RANK THEN SAVE THEN PROVIDE KICKMESSAGE
										SQLRanks.setRank(target, rank.toUpperCase());
										if(target.isConnected()){
											target.disconnect(kickMessage);
										}
									}else{
										sender.sendMessage(invalidRank);
									}
								}else{
									sender.sendMessage(noHigherPerm);
								}
							}else if(SQLRanks.getRank(ProxiedPlayer).equals(Rank.OWNER.getName())){
								if(rank.equalsIgnoreCase(Rank.DEFAULT.getName()) 
										|| rank.equalsIgnoreCase(Rank.DONOR1.getName())
										|| rank.equalsIgnoreCase(Rank.DONOR2.getName())
										|| rank.equalsIgnoreCase(Rank.DONOR3.getName())
										|| rank.equalsIgnoreCase(Rank.BUILDER.getName())
										|| rank.equalsIgnoreCase(Rank.HELPER.getName())
										|| rank.equalsIgnoreCase(Rank.MOD.getName())
										|| rank.equalsIgnoreCase(Rank.SRMOD.getName())
										|| rank.equalsIgnoreCase(Rank.ADMIN.getName())
										|| rank.equalsIgnoreCase(Rank.OWNER.getName())){
									SQLRanks.setRank(target, rank.toUpperCase());
									if(target.isConnected()){
										target.disconnect(kickMessage);
									}
								}else{
									sender.sendMessage(invalidRank);
								}
							}else{
								ProxiedPlayer.sendMessage(noPerms);
							}
						}else{
							ProxiedPlayer.sendMessage(invalidProxiedPlayer);
						}
					}else if(sender instanceof CommandSender){
						if(target != null){
							if(rank.equalsIgnoreCase(Rank.DEFAULT.getName()) 
									|| rank.equalsIgnoreCase(Rank.DONOR1.getName())
									|| rank.equalsIgnoreCase(Rank.DONOR2.getName())
									|| rank.equalsIgnoreCase(Rank.DONOR3.getName())
									|| rank.equalsIgnoreCase(Rank.BUILDER.getName())
									|| rank.equalsIgnoreCase(Rank.HELPER.getName())
									|| rank.equalsIgnoreCase(Rank.MOD.getName())
									|| rank.equalsIgnoreCase(Rank.SRMOD.getName())
									|| rank.equalsIgnoreCase(Rank.ADMIN.getName())
									|| rank.equalsIgnoreCase(Rank.OWNER.getName())){
								SQLRanks.setRank(target, rank.toUpperCase());
								if(target.isConnected()){
									target.disconnect(kickMessage);
								}
							}else{
								sender.sendMessage(invalidRank);
							}
						}else{
							sender.sendMessage(invalidProxiedPlayer);
						}
					}else{
						sender.sendMessage(badSender);
					}
				}
		}
}
