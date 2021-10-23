package ru.universalstudio.universaljoiner.utils;

import java.util.List;
import java.util.stream.Collectors;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import ru.universalstudio.universaljoiner.utils.actionbar.Actionbar;



public class Utils
{
  private static FileConfiguration config;
  
  public static FileConfiguration getConfig() {
    return (config != null) ? config : (config = Config.getFile("config.yml"));
  }
  
  public static void reloadConfig() {
    config = Config.getFile("config.yml");
  }
  
  public static String getMessage(String paramString) {
    return getConfig().getString("messages." + paramString);
  }
  
  public static String getString(String paramString) {
    return getConfig().getString(paramString);
  }
  
  public static List<String> getStringList(String paramString) {
    return getConfig().getStringList(paramString);
  }
  
  public static int getInt(String paramString) {
    return getConfig().getInt(paramString);
  }
  
  public static double getDouble(String paramString) {
    return getConfig().getDouble(paramString);
  }
  
  public static boolean getBoolean(String paramString) {
    return getConfig().getBoolean(paramString);
  }
  
  public static String color(String paramString) {
    return ChatColor.translateAlternateColorCodes('&', paramString);
  }
  
  public static List<String> color(List<String> paramList) {
    return (List<String>)paramList.stream().map(Utils::lambda$color$0).collect(Collectors.toList()); } private static String lambda$color$0(String paramString) { return color(paramString); }

  
  public static boolean has(CommandSender paramCommandSender, String paramString) {
    if (!paramCommandSender.hasPermission(paramString)) {
      sendMessage(paramCommandSender, getConfig().getString("messages.no-permission"));
      return false;
    } 
    return true;
  }
  
  public static String format(int paramInt) {
    int i = paramInt / 86400;
    int j = paramInt % 86400 / 3600;
    int k = paramInt % 3600 / 60;
    int m = paramInt % 60;
    
    StringBuilder stringBuilder = new StringBuilder();
    
    if (i > 0) stringBuilder.append(getString("time.days").replace("%size%", "" + i)).append(" "); 
    if (j > 0) stringBuilder.append(getString("time.hours").replace("%size%", "" + j)).append(" "); 
    if (k > 0) stringBuilder.append(getString("time.minutes").replace("%size%", "" + k)).append(" "); 
    if (m > 0) stringBuilder.append(getString("time.seconds").replace("%size%", "" + m)).append(" ");
    
    return stringBuilder.toString().trim().isEmpty() ? getString("time.now") : stringBuilder.toString().trim();
  }



  
  public static void sendMessage(CommandSender paramCommandSender, String paramString) {
    for (String str : paramString.split(";")) {
      str = str.replace("%player%", paramCommandSender.getName());
      
      if (str.startsWith("title:")) {
        if (paramCommandSender instanceof Player) {
          Title.sendTitle((Player)paramCommandSender, str.split("title:")[1]);
        }
      } else if (str.startsWith("actionbar:")) {
        if (paramCommandSender instanceof Player) {
          Actionbar.sendActionbar((Player)paramCommandSender, str.split("actionbar:")[1]);
        }
      } else {
        paramCommandSender.sendMessage(color(getMessage("prefix") + str));
      } 
    } 
  }
  
  public static Sound getSound(String paramString) {
    try {
      return Sound.valueOf(paramString.toUpperCase());
    }
    catch (Exception exception) {
      return null;
    } 
  }
}
