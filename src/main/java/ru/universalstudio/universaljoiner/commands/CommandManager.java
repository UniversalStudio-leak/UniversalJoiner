package ru.universalstudio.universaljoiner.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import ru.universalstudio.universaljoiner.JoinerGui;
import ru.universalstudio.universaljoiner.utils.Utils;


public class CommandManager
  implements CommandExecutor, TabCompleter
{
  private List<CommandSub> commands = new ArrayList<>();
  
  public void registerCommand(CommandSub paramCommandSub) {
    unregisterCommand(paramCommandSub.command());
    this.commands.add(paramCommandSub);
  }
  
  public boolean unregisterCommand(String paramString) {
    return this.commands.removeIf(paramString::lambda$unregisterCommand$0); } private static boolean lambda$unregisterCommand$0(String paramString, CommandSub paramCommandSub) { return paramCommandSub.command().equals(paramString); }



  
  public boolean onCommand(CommandSender paramCommandSender, Command paramCommand, String paramString, String[] paramArrayOfString) {
    if (paramArrayOfString.length == 0) {
      
      if (!Utils.has(paramCommandSender, "ujoiner.joiner")) {
        return true;
      }
      if (!(paramCommandSender instanceof Player)) {
        
        Utils.sendMessage(paramCommandSender, Utils.getMessage("only-players"));
        
        return true;
      } 
      
      Player player = (Player)paramCommandSender;
      
      JoinerGui.open(player);
      
      return true;
    } 
    
    CommandSub commandSub = getCommand(paramArrayOfString[0]);
    
    if (commandSub != null) {
      
      if (commandSub.permission() != null && !Utils.has(paramCommandSender, "ujoiner." + commandSub.permission())) return true;
      
      if (commandSub.onlyPlayers() && !(paramCommandSender instanceof Player)) {
        Utils.sendMessage(paramCommandSender, Utils.getMessage("only-players"));
        
        return true;
      } 
      
      ArrayList arrayList = new ArrayList(Arrays.asList((Object[])paramArrayOfString));
      arrayList.remove(0);
      
      try {
        if (!commandSub.execute(paramCommandSender, (String[])arrayList.toArray((Object[])new String[arrayList.size()]))) {
          Utils.sendMessage(paramCommandSender, commandSub.description());
        }
      }
      catch (Exception exception) {
        Utils.sendMessage(paramCommandSender, "��������� ������ ��� ���������� ������ �������, ���������� � ��������������.");
        
        exception.printStackTrace();
      } 
    } else {
      
      Utils.sendMessage(paramCommandSender, Utils.getMessage("unknown"));
    } 
    return true;
  }


  
  public List<String> onTabComplete(CommandSender paramCommandSender, Command paramCommand, String paramString, String[] paramArrayOfString) {
    if (paramArrayOfString.length == 1) {
      return (List<String>)allowedCommands(paramCommandSender).stream().map(CommandManager::lambda$onTabComplete$1).collect(Collectors.toList());
    }
    
    if (paramArrayOfString.length >= 2) {
      
      CommandSub commandSub = getCommand(paramArrayOfString[0]);
      
      if (commandSub != null) {
        
        if (commandSub.permission() != null && !paramCommandSender.hasPermission("usuffix." + commandSub.permission())) return null;
        
        if (commandSub.onlyPlayers() && !(paramCommandSender instanceof Player)) return null;
        
        ArrayList arrayList = new ArrayList(Arrays.asList((Object[])paramArrayOfString));
        arrayList.remove(0);
        
        String[] arrayOfString = (String[])arrayList.toArray((Object[])new String[arrayList.size()]);
        
        return filter(commandSub.tab(paramCommandSender, arrayOfString), arrayOfString);
      } 
    } 
    return null;
  } private static String lambda$onTabComplete$1(CommandSub paramCommandSub) {
    return paramCommandSub.command();
  } private List<String> filter(List<String> paramList, String[] paramArrayOfString) {
    if (paramList == null) return null;
    
    String str = paramArrayOfString[paramArrayOfString.length - 1].toLowerCase();
    ArrayList<String> arrayList = new ArrayList();
    
    for (String str1 : paramList) {
      if (str1.startsWith(str))
        arrayList.add(str1); 
    } 
    return arrayList;
  }
  
  private List<CommandSub> allowedCommands(CommandSender paramCommandSender) {
    return (List<CommandSub>)this.commands.stream()
      .filter(paramCommandSender::lambda$allowedCommands$2)
      .collect(Collectors.toList());
  }
  private static boolean lambda$allowedCommands$2(CommandSender paramCommandSender, CommandSub paramCommandSub) {
    return (paramCommandSub.permission() != null) ? paramCommandSender.hasPermission(paramCommandSub.permission()) : true;
  } public CommandSub getCommand(String paramString) { return this.commands.stream().filter(paramString::lambda$getCommand$3).findAny().orElse(null); } private static boolean lambda$getCommand$3(String paramString, CommandSub paramCommandSub) { return paramCommandSub.command().equalsIgnoreCase(paramString); }

}
