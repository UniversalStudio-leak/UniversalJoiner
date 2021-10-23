package ru.universalstudio.universaljoiner.gui;

import com.google.common.base.Function;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;
import ru.universalstudio.universaljoiner.gui.content.InventoryContents;
import ru.universalstudio.universaljoiner.gui.content.InventoryProvider;









public class SmartInventory
{
  private String id;
  private Function<Player, String> title;
  private InventoryType type;
  private int rows;
  private int columns;
  private boolean closeable;
  private InventoryProvider provider;
  private SmartInventory parent;
  private List<InventoryListener<? extends Event>> listeners;
  private InventoryManager manager;
  
  private SmartInventory(InventoryManager paramInventoryManager) {
    this.manager = paramInventoryManager;
  }
  public Inventory open(Player paramPlayer) {
    return open(paramPlayer, 0);
  }
  public Inventory open(Player paramPlayer, int paramInt) {
    // Byte code:
    //   0: goto -> 5
    //   3: nop
    //   4: athrow
    //   5: aload_0
    //   6: getfield manager : Lru/universalstudio/universaljoiner/gui/InventoryManager;
    //   9: aload_1
    //   10: invokevirtual getInventory : (Lorg/bukkit/entity/Player;)Ljava/util/Optional;
    //   13: astore_3
    //   14: aload_3
    //   15: aload_0
    //   16: aload_1
    //   17: <illegal opcode> accept : (Lru/universalstudio/universaljoiner/gui/SmartInventory;Lorg/bukkit/entity/Player;)Ljava/util/function/Consumer;
    //   22: invokevirtual ifPresent : (Ljava/util/function/Consumer;)V
    //   25: new ru/universalstudio/universaljoiner/gui/content/InventoryContents$Impl
    //   28: dup
    //   29: aload_0
    //   30: aload_1
    //   31: invokespecial <init> : (Lru/universalstudio/universaljoiner/gui/SmartInventory;Lorg/bukkit/entity/Player;)V
    //   34: astore #4
    //   36: aload #4
    //   38: invokevirtual pagination : ()Lru/universalstudio/universaljoiner/gui/content/Pagination;
    //   41: iload_2
    //   42: invokevirtual page : (I)Lru/universalstudio/universaljoiner/gui/content/Pagination;
    //   45: pop
    //   46: aload_0
    //   47: getfield manager : Lru/universalstudio/universaljoiner/gui/InventoryManager;
    //   50: aload_1
    //   51: aload #4
    //   53: invokevirtual setContents : (Lorg/bukkit/entity/Player;Lru/universalstudio/universaljoiner/gui/content/InventoryContents;)V
    //   56: aload_0
    //   57: getfield provider : Lru/universalstudio/universaljoiner/gui/content/InventoryProvider;
    //   60: aload_1
    //   61: aload #4
    //   63: invokevirtual init : (Lorg/bukkit/entity/Player;Lru/universalstudio/universaljoiner/gui/content/InventoryContents;)V
    //   66: aload_0
    //   67: getfield manager : Lru/universalstudio/universaljoiner/gui/InventoryManager;
    //   70: aload_0
    //   71: getfield type : Lorg/bukkit/event/inventory/InventoryType;
    //   74: invokevirtual findOpener : (Lorg/bukkit/event/inventory/InventoryType;)Ljava/util/Optional;
    //   77: aload_0
    //   78: <illegal opcode> get : (Lru/universalstudio/universaljoiner/gui/SmartInventory;)Ljava/util/function/Supplier;
    //   83: invokevirtual orElseThrow : (Ljava/util/function/Supplier;)Ljava/lang/Object;
    //   86: checkcast ru/universalstudio/universaljoiner/gui/opener/InventoryOpener
    //   89: astore #5
    //   91: aload #5
    //   93: aload_0
    //   94: aload_1
    //   95: invokevirtual open : (Lru/universalstudio/universaljoiner/gui/SmartInventory;Lorg/bukkit/entity/Player;)Lorg/bukkit/inventory/Inventory;
    //   98: astore #6
    //   100: aload_0
    //   101: getfield manager : Lru/universalstudio/universaljoiner/gui/InventoryManager;
    //   104: aload_1
    //   105: aload_0
    //   106: invokevirtual setInventory : (Lorg/bukkit/entity/Player;Lru/universalstudio/universaljoiner/gui/SmartInventory;)V
    //   109: new ru/universalstudio/universaljoiner/gui/SmartInventory$1
    //   112: dup
    //   113: aload_0
    //   114: aload_1
    //   115: aload #4
    //   117: invokespecial <init> : (Lru/universalstudio/universaljoiner/gui/SmartInventory;Lorg/bukkit/entity/Player;Lru/universalstudio/universaljoiner/gui/content/InventoryContents;)V
    //   120: aload_0
    //   121: invokevirtual getClass : ()Ljava/lang/Class;
    //   124: invokestatic getProvidingPlugin : (Ljava/lang/Class;)Lorg/bukkit/plugin/java/JavaPlugin;
    //   127: ldc2_w 1
    //   130: ldc2_w 1
    //   133: invokevirtual runTaskTimer : (Lorg/bukkit/plugin/Plugin;JJ)Lorg/bukkit/scheduler/BukkitTask;
    //   136: pop
    //   137: aload #6
    //   139: areturn
    //   140: nop
    //   141: nop
    //   142: nop
    //   143: nop
    //   144: athrow
    // Line number table:
    //   Java source line number -> byte code offset
    //   #44	-> 5
    //   #46	-> 14
    //   #55	-> 25
    //   #56	-> 36
    //   #58	-> 46
    //   #59	-> 56
    //   #61	-> 66
    //   #62	-> 83
    //   #63	-> 91
    //   #65	-> 100
    //   #67	-> 109
    //   #76	-> 121
    //   #78	-> 137
  }
  private void lambda$open$2(Player paramPlayer, SmartInventory paramSmartInventory) {
    paramSmartInventory.getListeners().stream()
      .filter(SmartInventory::lambda$open$0)
      .forEach(paramPlayer::lambda$open$1);

    
    this.manager.setInventory(paramPlayer, null);
  }
  private static boolean lambda$open$0(InventoryListener<InventoryCloseEvent> paramInventoryListener) {
    return (paramInventoryListener.getType() == InventoryCloseEvent.class);
  }
  private static void lambda$open$1(Player paramPlayer, InventoryListener<InventoryCloseEvent> paramInventoryListener) {
    paramInventoryListener.accept(new InventoryCloseEvent(paramPlayer.getOpenInventory()));
  }
  
  private IllegalStateException lambda$open$3() {
    return new IllegalStateException("No opener found for the inventory type " + this.type.name());
  }



  
  private static boolean lambda$close$4(InventoryListener<InventoryCloseEvent> paramInventoryListener) {
    return (paramInventoryListener.getType() == InventoryCloseEvent.class);
  }



  
  private static void lambda$close$5(Player paramPlayer, InventoryListener<InventoryCloseEvent> paramInventoryListener) {
    paramInventoryListener.accept(new InventoryCloseEvent(paramPlayer.getOpenInventory()));
  }


  
  public void close(Player paramPlayer) {
    this.listeners.stream()
      .filter(SmartInventory::lambda$close$4)
      .forEach(paramPlayer::lambda$close$5);

    
    this.manager.setInventory(paramPlayer, null);
    paramPlayer.closeInventory();
    
    this.manager.setContents(paramPlayer, null);
  }
  public String getId() {
    return this.id; } public Function<Player, String> getTitle() {
    return this.title;
  }
  public void title(Function<Player, String> paramFunction) { this.title = paramFunction; }
  public void title(String paramString) { this.title = paramString::lambda$title$6; } private static String lambda$title$6(String paramString, Player paramPlayer) { return paramString; }
  
  public InventoryType getType() { return this.type; }
  public int getRows() { return this.rows; } public int getColumns() {
    return this.columns;
  }
  public boolean isCloseable() { return this.closeable; } public void setCloseable(boolean paramBoolean) {
    this.closeable = paramBoolean;
  }
  public InventoryProvider getProvider() { return this.provider; } public Optional<SmartInventory> getParent() {
    return Optional.ofNullable(this.parent);
  } public InventoryManager getManager() {
    return this.manager;
  } List<InventoryListener<? extends Event>> getListeners() {
    return this.listeners;
  } public static Builder builder() {
    return new Builder();
  }
  
  public static final class Builder {
    private String id = "unknown";
    
    private Function<Player, String> title;
    private InventoryType type = InventoryType.CHEST;
    private int rows = 6; private int columns = 9;
    
    private boolean closeable = true;
    
    private InventoryManager manager;
    private InventoryProvider provider;
    private SmartInventory parent;
    private List<InventoryListener<? extends Event>> listeners = new ArrayList<>();


    
    public Builder id(String param1String) {
      this.id = param1String;
      return this;
    }
    
    public Builder title(Function<Player, String> param1Function) {
      this.title = param1Function;
      
      return this;
    }
    
    public Builder title(String param1String) {
      this.title = param1String::lambda$title$0;
      return this;
    } private static String lambda$title$0(String param1String, Player param1Player) {
      return param1String;
    } public Builder type(InventoryType param1InventoryType) {
      this.type = param1InventoryType;
      return this;
    }
    
    public Builder size(int param1Int1, int param1Int2) {
      this.rows = param1Int1;
      this.columns = param1Int2;
      return this;
    }
    
    public Builder closeable(boolean param1Boolean) {
      this.closeable = param1Boolean;
      return this;
    }
    
    public Builder provider(InventoryProvider param1InventoryProvider) {
      this.provider = param1InventoryProvider;
      return this;
    }
    
    public Builder parent(SmartInventory param1SmartInventory) {
      this.parent = param1SmartInventory;
      return this;
    }
    
    public Builder listener(InventoryListener<? extends Event> param1InventoryListener) {
      this.listeners.add(param1InventoryListener);
      return this;
    }
    
    public Builder manager(InventoryManager param1InventoryManager) {
      this.manager = param1InventoryManager;
      return this;
    }
    
    public SmartInventory build() {
      if (this.provider == null) {
        throw new IllegalStateException("The provider of the SmartInventory.Builder must be set.");
      }
      InventoryManager inventoryManager = (this.manager != null) ? this.manager : InventoryManager.manager();
      
      if (inventoryManager == null) {
        throw new IllegalStateException("The manager of the SmartInventory.Builder must be set, or the SmartInvs should be loaded as a plugin.");
      }
      
      SmartInventory smartInventory = new SmartInventory(inventoryManager);
      smartInventory.id = this.id;
      smartInventory.title = this.title;
      smartInventory.type = this.type;
      smartInventory.rows = this.rows;
      smartInventory.columns = this.columns;
      smartInventory.closeable = this.closeable;
      smartInventory.provider = this.provider;
      smartInventory.parent = this.parent;
      smartInventory.listeners = this.listeners;
      
      return smartInventory;
    }
    
    private Builder() {}
  }
}
