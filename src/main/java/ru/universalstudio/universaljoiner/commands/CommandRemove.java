package ru.universalstudio.universaljoiner.commands;

import java.util.List;
import java.util.stream.Collectors;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.universalstudio.universaljoiner.Joiner;
import ru.universalstudio.universaljoiner.JoinerPlayer;
import ru.universalstudio.universaljoiner.JoinerPlugin;
import ru.universalstudio.universaljoiner.utils.Utils;



public class CommandRemove
  extends CommandSub
{
  public boolean execute(CommandSender paramCommandSender, String[] paramArrayOfString) {
    if (paramArrayOfString.length < 2) return false;
    
    Player player = Bukkit.getPlayer(paramArrayOfString[0]);
    
    if (player == null) {
      
      Utils.sendMessage(paramCommandSender, Utils.getMessage("player-null"));
      
      return true;
    } 
    
    JoinerPlayer joinerPlayer = JoinerPlugin.getInstance().getJoinerManager().getJoinerPlayer(player);
    
    if (joinerPlayer.removeJoiner(paramArrayOfString[1])) {
      
      Utils.sendMessage(paramCommandSender, Utils.getMessage("remove.removed")
          .replace("%player%", player.getName()));
    }
    else {
      
      Utils.sendMessage(paramCommandSender, Utils.getMessage("remove.joiner-notfound"));
    } 
    
    return true;
  }


  
  public List<String> tab(CommandSender paramCommandSender, String[] paramArrayOfString) {
    if (paramArrayOfString.length == 2) {
      Player player = Bukkit.getPlayer(paramArrayOfString[0]);
      
      if (player != null) {
        return (List<String>)JoinerPlugin.getInstance().getJoinerManager().getJoinerPlayer(player)
          .getJoiners().stream().map(CommandRemove::lambda$tab$0).collect(Collectors.toList());
      }
    } 
    
    return null;
  }

  
  public String command() {
    return "remove";
  } private static String lambda$tab$0(Joiner paramJoiner) {
    return paramJoiner.getName();
  }
  public String permission() {
    return "remove";
  }

  
  public String description() {
    return Utils.getMessage("remove.usage");
  }

  
  public boolean onlyPlayers() {
    return false;
  }
}
