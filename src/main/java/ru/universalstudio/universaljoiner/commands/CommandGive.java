package ru.universalstudio.universaljoiner.commands;

import java.util.List;
import java.util.stream.Collectors;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.universalstudio.universaljoiner.Joiner;
import ru.universalstudio.universaljoiner.JoinerPlugin;
import ru.universalstudio.universaljoiner.utils.Utils;




public class CommandGive
  extends CommandSub
{
  public boolean execute(CommandSender paramCommandSender, String[] paramArrayOfString) {
    if (paramArrayOfString.length < 2) return false;
    
    Player player = Bukkit.getPlayer(paramArrayOfString[0]);
    
    if (player == null) {
      
      Utils.sendMessage(paramCommandSender, Utils.getMessage("player-null"));
      
      return true;
    } 
    
    Joiner joiner = JoinerPlugin.getInstance().getJoinerManager().getJoiner(paramArrayOfString[1]);
    
    if (joiner == null) {
      
      Utils.sendMessage(paramCommandSender, Utils.getMessage("joiner-null"));
      
      return true;
    } 
    
    JoinerPlugin.getInstance().getJoinerManager().getJoinerPlayer(player).addJoiner(joiner);
    
    Utils.sendMessage(paramCommandSender, Utils.getMessage("give.gived")
        .replace("%player%", player.getName()));
    
    return true;
  }


  
  public List<String> tab(CommandSender paramCommandSender, String[] paramArrayOfString) {
    if (paramArrayOfString.length == 2) {
      return (List<String>)JoinerPlugin.getInstance().getJoinerManager().getJoiners()
        .stream().map(CommandGive::lambda$tab$0)
        .collect(Collectors.toList());
    }
    return null;
  }

  
  public String command() {
    return "give";
  } private static String lambda$tab$0(Joiner paramJoiner) {
    return paramJoiner.getName();
  }
  public String permission() {
    return "give";
  }

  
  public String description() {
    return Utils.getMessage("give.usage");
  }

  
  public boolean onlyPlayers() {
    return false;
  }
}
