package ru.universalstudio.universaljoiner;

import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import ru.universalstudio.universaljoiner.gui.ClickableItem;
import ru.universalstudio.universaljoiner.utils.Utils;

public class Joiner {

  private String name;
  private Available available;
  private AvailableBuy availableBuy;
  private NotAvailable notAvailable;
  private String permission;
  private String permissionFree;
  private String joinMessage;
  private double money;
  
  public Joiner(String paramString) {
    this.name = paramString;
    this.available = new Available();
    this.availableBuy = new AvailableBuy();
    this.notAvailable = new NotAvailable();
  }
  
  public String getName() {
    return this.name;
  }
  
  public String getPermission() {
    return this.permission;
  }
  
  public void setPermission(String paramString) {
    this.permission = paramString;
  }
  
  public String getPermissionFree() {
    return this.permissionFree;
  }
  
  public void setJoinMessage(String paramString) {
    this.joinMessage = paramString;
  }
  
  public String getJoinMessage() {
    return this.joinMessage;
  }
  
  public String getJoinMessage(Player paramPlayer) {
    return getJoinMessage().replace("%prefix%", JoinerPlugin.getInstance().getPrefix(paramPlayer))
      .replace("%player%", paramPlayer.getName()).replace("%suffix%", JoinerPlugin.getInstance().getSuffix(paramPlayer));
  }
  
  public void setPermissionFree(String paramString) {
    this.permissionFree = paramString;
  }
  
  public double getMoney() {
    return this.money;
  }
  
  public void setMoney(double paramDouble) {
    this.money = paramDouble;
  }
  
  public Available getAvailable() {
    return this.available;
  }
  
  public AvailableBuy getAvailableBuy() {
    return this.availableBuy;
  }
  
  public NotAvailable getNotAvailable() {
    return this.notAvailable;
  }





  
  public ClickableItem collectClickableItem(Player paramPlayer) {
    // Byte code:
    //   0: goto -> 5
    //   3: nop
    //   4: athrow
    //   5: invokestatic getInstance : ()Lru/universalstudio/universaljoiner/JoinerPlugin;
    //   8: invokevirtual getJoinerManager : ()Lru/universalstudio/universaljoiner/JoinerManager;
    //   11: aload_1
    //   12: invokevirtual getJoinerPlayer : (Lorg/bukkit/entity/Player;)Lru/universalstudio/universaljoiner/JoinerPlayer;
    //   15: astore_2
    //   16: aload_0
    //   17: getfield permissionFree : Ljava/lang/String;
    //   20: ifnull -> 34
    //   23: aload_1
    //   24: aload_0
    //   25: getfield permissionFree : Ljava/lang/String;
    //   28: invokevirtual hasPermission : (Ljava/lang/String;)Z
    //   31: ifne -> 45
    //   34: aload_2
    //   35: invokevirtual getJoiners : ()Ljava/util/List;
    //   38: aload_0
    //   39: invokevirtual contains : (Ljava/lang/Object;)Z
    //   42: ifeq -> 177
    //   45: aload_0
    //   46: getfield available : Lru/universalstudio/universaljoiner/Joiner$Available;
    //   49: invokevirtual getItem : ()Lorg/bukkit/inventory/ItemStack;
    //   52: invokestatic of : (Lorg/bukkit/inventory/ItemStack;)Lru/universalstudio/universaljoiner/utils/ItemBuilder;
    //   55: ldc '%money%'
    //   57: aload_0
    //   58: getfield money : D
    //   61: invokestatic toString : (D)Ljava/lang/String;
    //   64: invokevirtual replace : (Ljava/lang/String;Ljava/lang/String;)Lru/universalstudio/universaljoiner/utils/ItemBuilder;
    //   67: ldc '%joinMessage%'
    //   69: aload_0
    //   70: aload_1
    //   71: invokevirtual getJoinMessage : (Lorg/bukkit/entity/Player;)Ljava/lang/String;
    //   74: invokestatic color : (Ljava/lang/String;)Ljava/lang/String;
    //   77: invokevirtual replace : (Ljava/lang/String;Ljava/lang/String;)Lru/universalstudio/universaljoiner/utils/ItemBuilder;
    //   80: astore_3
    //   81: aload_2
    //   82: invokevirtual getActiveJoiner : ()Lru/universalstudio/universaljoiner/Joiner;
    //   85: ifnull -> 141
    //   88: aload_2
    //   89: invokevirtual getActiveJoiner : ()Lru/universalstudio/universaljoiner/Joiner;
    //   92: aload_0
    //   93: invokevirtual equals : (Ljava/lang/Object;)Z
    //   96: ifeq -> 141
    //   99: aload_3
    //   100: getstatic org/bukkit/enchantments/Enchantment.LUCK : Lorg/bukkit/enchantments/Enchantment;
    //   103: iconst_1
    //   104: invokevirtual enchant : (Lorg/bukkit/enchantments/Enchantment;I)Lru/universalstudio/universaljoiner/utils/ItemBuilder;
    //   107: ldc '%selected%'
    //   109: aload_0
    //   110: getfield available : Lru/universalstudio/universaljoiner/Joiner$Available;
    //   113: invokevirtual getSelected : ()Ljava/lang/String;
    //   116: invokestatic color : (Ljava/lang/String;)Ljava/lang/String;
    //   119: invokevirtual replace : (Ljava/lang/String;Ljava/lang/String;)Lru/universalstudio/universaljoiner/utils/ItemBuilder;
    //   122: invokevirtual build : ()Lorg/bukkit/inventory/ItemStack;
    //   125: aload_0
    //   126: aload_1
    //   127: <illegal opcode> accept : (Lru/universalstudio/universaljoiner/Joiner;Lorg/bukkit/entity/Player;)Ljava/util/function/Consumer;
    //   132: invokestatic of : (Lorg/bukkit/inventory/ItemStack;Ljava/util/function/Consumer;)Lru/universalstudio/universaljoiner/gui/ClickableItem;
    //   135: areturn
    //   136: nop
    //   137: nop
    //   138: nop
    //   139: nop
    //   140: athrow
    //   141: aload_3
    //   142: ldc '%selected%'
    //   144: aload_0
    //   145: getfield available : Lru/universalstudio/universaljoiner/Joiner$Available;
    //   148: invokevirtual getNoSelected : ()Ljava/lang/String;
    //   151: invokestatic color : (Ljava/lang/String;)Ljava/lang/String;
    //   154: invokevirtual replace : (Ljava/lang/String;Ljava/lang/String;)Lru/universalstudio/universaljoiner/utils/ItemBuilder;
    //   157: invokevirtual build : ()Lorg/bukkit/inventory/ItemStack;
    //   160: aload_0
    //   161: aload_1
    //   162: aload_2
    //   163: <illegal opcode> accept : (Lru/universalstudio/universaljoiner/Joiner;Lorg/bukkit/entity/Player;Lru/universalstudio/universaljoiner/JoinerPlayer;)Ljava/util/function/Consumer;
    //   168: invokestatic of : (Lorg/bukkit/inventory/ItemStack;Ljava/util/function/Consumer;)Lru/universalstudio/universaljoiner/gui/ClickableItem;
    //   171: areturn
    //   172: nop
    //   173: nop
    //   174: nop
    //   175: nop
    //   176: athrow
    //   177: aload_0
    //   178: getfield permission : Ljava/lang/String;
    //   181: ifnull -> 252
    //   184: aload_1
    //   185: aload_0
    //   186: getfield permission : Ljava/lang/String;
    //   189: invokevirtual hasPermission : (Ljava/lang/String;)Z
    //   192: ifeq -> 252
    //   195: aload_0
    //   196: getfield availableBuy : Lru/universalstudio/universaljoiner/Joiner$AvailableBuy;
    //   199: invokevirtual getItem : ()Lorg/bukkit/inventory/ItemStack;
    //   202: invokestatic of : (Lorg/bukkit/inventory/ItemStack;)Lru/universalstudio/universaljoiner/utils/ItemBuilder;
    //   205: ldc '%money%'
    //   207: aload_0
    //   208: getfield money : D
    //   211: invokestatic toString : (D)Ljava/lang/String;
    //   214: invokevirtual replace : (Ljava/lang/String;Ljava/lang/String;)Lru/universalstudio/universaljoiner/utils/ItemBuilder;
    //   217: ldc '%joinMessage%'
    //   219: aload_0
    //   220: aload_1
    //   221: invokevirtual getJoinMessage : (Lorg/bukkit/entity/Player;)Ljava/lang/String;
    //   224: invokestatic color : (Ljava/lang/String;)Ljava/lang/String;
    //   227: invokevirtual replace : (Ljava/lang/String;Ljava/lang/String;)Lru/universalstudio/universaljoiner/utils/ItemBuilder;
    //   230: astore_3
    //   231: aload_3
    //   232: invokevirtual build : ()Lorg/bukkit/inventory/ItemStack;
    //   235: aload_0
    //   236: aload_1
    //   237: aload_2
    //   238: <illegal opcode> accept : (Lru/universalstudio/universaljoiner/Joiner;Lorg/bukkit/entity/Player;Lru/universalstudio/universaljoiner/JoinerPlayer;)Ljava/util/function/Consumer;
    //   243: invokestatic of : (Lorg/bukkit/inventory/ItemStack;Ljava/util/function/Consumer;)Lru/universalstudio/universaljoiner/gui/ClickableItem;
    //   246: areturn
    //   247: nop
    //   248: nop
    //   249: nop
    //   250: nop
    //   251: athrow
    //   252: aload_0
    //   253: getfield notAvailable : Lru/universalstudio/universaljoiner/Joiner$NotAvailable;
    //   256: invokevirtual getItem : ()Lorg/bukkit/inventory/ItemStack;
    //   259: invokestatic of : (Lorg/bukkit/inventory/ItemStack;)Lru/universalstudio/universaljoiner/utils/ItemBuilder;
    //   262: ldc '%money%'
    //   264: aload_0
    //   265: getfield money : D
    //   268: invokestatic toString : (D)Ljava/lang/String;
    //   271: invokevirtual replace : (Ljava/lang/String;Ljava/lang/String;)Lru/universalstudio/universaljoiner/utils/ItemBuilder;
    //   274: ldc '%joinMessage%'
    //   276: aload_0
    //   277: aload_1
    //   278: invokevirtual getJoinMessage : (Lorg/bukkit/entity/Player;)Ljava/lang/String;
    //   281: invokestatic color : (Ljava/lang/String;)Ljava/lang/String;
    //   284: invokevirtual replace : (Ljava/lang/String;Ljava/lang/String;)Lru/universalstudio/universaljoiner/utils/ItemBuilder;
    //   287: astore_3
    //   288: aload_3
    //   289: invokevirtual build : ()Lorg/bukkit/inventory/ItemStack;
    //   292: aload_0
    //   293: aload_1
    //   294: <illegal opcode> accept : (Lru/universalstudio/universaljoiner/Joiner;Lorg/bukkit/entity/Player;)Ljava/util/function/Consumer;
    //   299: invokestatic of : (Lorg/bukkit/inventory/ItemStack;Ljava/util/function/Consumer;)Lru/universalstudio/universaljoiner/gui/ClickableItem;
    //   302: areturn
    //   303: nop
    //   304: nop
    //   305: nop
    //   306: nop
    //   307: athrow
    // Line number table:
    //   Java source line number -> byte code offset
    //   #88	-> 5
    //   #90	-> 16
    //   #91	-> 35
    //   #93	-> 45
    //   #94	-> 61
    //   #95	-> 71
    //   #97	-> 81
    //   #98	-> 99
    //   #99	-> 113
    //   #98	-> 132
    //   #108	-> 141
    //   #118	-> 177
    //   #119	-> 195
    //   #120	-> 211
    //   #121	-> 221
    //   #123	-> 231
    //   #143	-> 252
    //   #144	-> 268
    //   #145	-> 278
    //   #147	-> 288
  }




  
  private void lambda$collectClickableItem$0(Player paramPlayer, InventoryClickEvent paramInventoryClickEvent) {
    if (this.available.getSoundAlreadySelected() != null) {
      paramPlayer.playSound(paramPlayer.getLocation(), this.available.getSoundAlreadySelected(), 10.0F, 1.0F);
    }
    Utils.sendMessage((CommandSender)paramPlayer, Utils.getMessage("selected-already"));
  }


  
  private void lambda$collectClickableItem$1(Player paramPlayer, JoinerPlayer paramJoinerPlayer, InventoryClickEvent paramInventoryClickEvent) {
    if (this.available.getSoundSelected() != null) {
      paramPlayer.playSound(paramPlayer.getLocation(), this.available.getSoundSelected(), 10.0F, 1.0F);
    }
    paramJoinerPlayer.setActiveJoiner(this);
    JoinerGui.open(paramPlayer);
  }







  
  private void lambda$collectClickableItem$2(Player paramPlayer, JoinerPlayer paramJoinerPlayer, InventoryClickEvent paramInventoryClickEvent) {
    if (JoinerPlugin.getInstance().withdraw(paramPlayer, this.money)) {
      
      if (this.availableBuy.getSoundBuy() != null) {
        paramPlayer.playSound(paramPlayer.getLocation(), this.availableBuy.getSoundBuy(), 10.0F, 1.0F);
      }
      paramJoinerPlayer.addJoiner(this);
      paramPlayer.closeInventory();
      
      Utils.sendMessage((CommandSender)paramPlayer, Utils.getMessage("joiner-buy").replace("%joinMessage%", getJoinMessage(paramPlayer)));

    
    }
    else if (this.availableBuy.getSoundMoneyNotEnough() != null) {
      paramPlayer.playSound(paramPlayer.getLocation(), this.availableBuy.getSoundMoneyNotEnough(), 10.0F, 1.0F);
    } 
  }






  
  private void lambda$collectClickableItem$3(Player paramPlayer, InventoryClickEvent paramInventoryClickEvent) {
    if (this.notAvailable.getSound() != null) {
      paramPlayer.playSound(paramPlayer.getLocation(), this.notAvailable.getSound(), 10.0F, 1.0F);
    }
    Utils.sendMessage((CommandSender)paramPlayer, Utils.getMessage("not-available"));
  }

  
  public class Available
  {
    private ItemStack item;
    
    private Sound soundSelected;
    private Sound soundAlreadySelected;
    private String selected;
    private String noSelected;
    final Joiner this$0;
    
    public ItemStack getItem() {
      return this.item.clone();
    }
    
    public void setItem(ItemStack param1ItemStack) {
      this.item = param1ItemStack;
    }
    
    public void setSoundSelected(Sound param1Sound) {
      this.soundSelected = param1Sound;
    }
    
    public Sound getSoundSelected() {
      return this.soundSelected;
    }
    
    public void setSoundAlreadySelected(Sound param1Sound) {
      this.soundAlreadySelected = param1Sound;
    }
    
    public Sound getSoundAlreadySelected() {
      return this.soundAlreadySelected;
    }
    
    public void setSelected(String param1String) {
      this.selected = param1String;
    }
    
    public String getSelected() {
      return (this.selected != null) ? this.selected : "";
    }
    
    public void setNoSelected(String param1String) {
      this.noSelected = param1String;
    }
    
    public String getNoSelected() {
      return (this.noSelected != null) ? this.noSelected : "";
    }
  }
  
  public class AvailableBuy
  {
    private ItemStack item;
    private Sound soundMoneyNotEnough;
    private Sound soundBuy;
    final Joiner this$0;
    
    public ItemStack getItem() {
      return this.item.clone();
    }
    
    public void setItem(ItemStack param1ItemStack) {
      this.item = param1ItemStack;
    }
    
    public Sound getSoundMoneyNotEnough() {
      return this.soundMoneyNotEnough;
    }
    public void setSoundMoneyNotEnough(Sound param1Sound) {
      this.soundMoneyNotEnough = param1Sound;
    }
    
    public Sound getSoundBuy() {
      return this.soundBuy;
    }
    
    public void setSoundBuy(Sound param1Sound) {
      this.soundBuy = param1Sound;
    }
  }
  
  public class NotAvailable {
    private ItemStack item;
    private Sound sound;
    final Joiner this$0;
    
    public ItemStack getItem() {
      return this.item.clone();
    }
    
    public void setItem(ItemStack param1ItemStack) {
      this.item = param1ItemStack;
    }
    
    public Sound getSound() {
      return this.sound;
    }
    
    public void setSound(Sound param1Sound) {
      this.sound = param1Sound;
    }
  }
}
