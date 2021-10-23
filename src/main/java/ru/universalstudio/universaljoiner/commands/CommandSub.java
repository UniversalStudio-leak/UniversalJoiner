package ru.universalstudio.universaljoiner.commands;

import java.util.List;
import org.bukkit.command.CommandSender;

public abstract class CommandSub {
  public abstract boolean execute(CommandSender paramCommandSender, String[] paramArrayOfString);
  
  public abstract List<String> tab(CommandSender paramCommandSender, String[] paramArrayOfString);
  
  public abstract String command();
  
  public abstract String permission();
  
  public abstract String description();
  
  public abstract boolean onlyPlayers();
}
