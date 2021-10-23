package ru.universalstudio.universaljoiner;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import ru.universalstudio.universaljoiner.commands.CommandGive;
import ru.universalstudio.universaljoiner.commands.CommandManager;
import ru.universalstudio.universaljoiner.commands.CommandReload;
import ru.universalstudio.universaljoiner.commands.CommandRemove;
import ru.universalstudio.universaljoiner.commands.CommandSub;
import ru.universalstudio.universaljoiner.sql.SQLDatabase;
import ru.universalstudio.universaljoiner.utils.Utils;

public class JoinerPlugin extends JavaPlugin {
  private static JoinerPlugin instance;
  private Economy economy;
  private Chat chat;
  private JoinerManager joinerManager;
  private SQLDatabase sqlDatabase;
  private CommandManager commandManager;
  
  public static JoinerPlugin getInstance() {
    return instance;
  }
  
  public JoinerManager getJoinerManager() {
    return this.joinerManager;
  }
  
  public SQLDatabase getSQLDatabase() {
    return this.sqlDatabase;
  }
  
  public CommandManager getCommandManager() {
    return this.commandManager;
  }


  
  public void onEnable() {
    if (Utils.getConfig().getString("KEY") == null) {
      
      Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[UniversalJoiner] You are not licensed! You can buy it on our website: u-studio.su");
      
      Bukkit.getServer().shutdown();
      return;
    } 
    instance = this;
    
    getServer().getPluginManager().registerEvents(new JoinerListener(), (Plugin)this);
    
    if (!(new UGuard(Utils.getConfig().getString("KEY"), "https://uguard.u-studio.su/webpanel/verify.php", (Plugin)this)).register())
      return; 
    try {
      if (Utils.getBoolean("mysql.enable"))
      { this.sqlDatabase = new SQLDatabase(Utils.getString("mysql.host"), Utils.getString("mysql.user"), Utils.getString("mysql.password")); }
      else { this.sqlDatabase = new SQLDatabase(getDataFolder()); }
      
      this.sqlDatabase.connect();
      
      getLogger().info("[SQLDatabase] База MySQL/SQLite успешно подключена.");
    }
    catch (Exception exception) {
      exception.printStackTrace();
    } 
    
    this.joinerManager = new JoinerManager();
    
    this.commandManager = new CommandManager();
    this.commandManager.registerCommand((CommandSub)new CommandGive());
    this.commandManager.registerCommand((CommandSub)new CommandRemove());
    this.commandManager.registerCommand((CommandSub)new CommandReload());
    
    getCommand("joiner").setExecutor((CommandExecutor)this.commandManager);
    
    for (Player player : Bukkit.getOnlinePlayers()) {
      this.joinerManager.getJoinerPlayer(player);
    }
  }


  
  public void onDisable() {}

  
  public boolean withdraw(Player paramPlayer, double paramDouble) {
    if (this.economy == null) return true;
    
    if (this.economy.getBalance((OfflinePlayer)paramPlayer) < paramDouble) return false;
    
    return this.economy.withdrawPlayer((OfflinePlayer)paramPlayer, paramDouble).transactionSuccess();
  }
  
  public Chat getChat() {
    if (this.chat == null)
    {
      if (Bukkit.getPluginManager().isPluginEnabled("Vault")) {
        RegisteredServiceProvider registeredServiceProvider = Bukkit.getServicesManager().getRegistration(Chat.class);
        this.chat = (Chat)registeredServiceProvider.getProvider();
      } 
    }
    return this.chat;
  }
  
  public String getPrefix(Player paramPlayer) {
    if (getChat() == null) return "";
    
    String str = getChat().getPlayerPrefix(paramPlayer);
    return (str != null) ? str : "";
  }
  
  public String getSuffix(Player paramPlayer) {
    if (getChat() == null) return "";
    
    String str = getChat().getPlayerSuffix(paramPlayer);
    return (str != null) ? str : "";
  }
  
  public void reloadSystem() {
    this.joinerManager = new JoinerManager();
    Utils.reloadConfig();
  }
}
