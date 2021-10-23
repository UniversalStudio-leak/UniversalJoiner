package ru.universalstudio.universaljoiner.commands;

import java.util.List;
import org.bukkit.command.CommandSender;
import ru.universalstudio.universaljoiner.JoinerPlugin;
import ru.universalstudio.universaljoiner.utils.Utils;




public class CommandReload
  extends CommandSub
{
  public boolean execute(CommandSender paramCommandSender, String[] paramArrayOfString) {
    JoinerPlugin.getInstance().reloadSystem();
    Utils.sendMessage(paramCommandSender, Utils.getMessage("reload.reloaded"));
    
    return true;
  }

  
  public List<String> tab(CommandSender paramCommandSender, String[] paramArrayOfString) {
    return null;
  }

  
  public String command() {
    return "reload";
  }

  
  public String permission() {
    return "reload";
  }

  
  public String description() {
    return Utils.getMessage("reload.usage");
  }

  
  public boolean onlyPlayers() {
    return false;
  }
}
