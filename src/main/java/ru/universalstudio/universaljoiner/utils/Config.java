package ru.universalstudio.universaljoiner.utils;

import java.io.File;
import java.io.IOException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;


public class Config
{
  private static JavaPlugin instance = JavaPlugin.getProvidingPlugin(Config.class);
  
  public static FileConfiguration getFile(String paramString) {
    File file = new File(instance.getDataFolder(), paramString);
    
    if (instance.getResource(paramString) == null) {
      return save((FileConfiguration)YamlConfiguration.loadConfiguration(file), paramString);
    }
    if (!file.exists()) {
      instance.saveResource(paramString, false);
    }
    return (FileConfiguration)YamlConfiguration.loadConfiguration(file);
  }
  
  public static FileConfiguration save(FileConfiguration paramFileConfiguration, String paramString) {
    try {
      paramFileConfiguration.save(new File(instance.getDataFolder(), paramString));
    }
    catch (IOException iOException) {
      iOException.printStackTrace();
    } 
    return paramFileConfiguration;
  }
}
