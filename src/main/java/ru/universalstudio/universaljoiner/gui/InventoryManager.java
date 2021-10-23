package ru.universalstudio.universaljoiner.gui;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.plugin.Plugin;
import ru.universalstudio.universaljoiner.gui.content.InventoryContents;
import ru.universalstudio.universaljoiner.gui.opener.InventoryOpener;

public class InventoryManager {
  private Plugin plugin;
  private Map<Player, SmartInventory> inventories;
  private Map<Player, InventoryContents> contents;
  private List<InventoryOpener> defaultOpeners;
  private List<InventoryOpener> openers;
  private static InventoryManager manager;
  
  public static InventoryManager manager() {
    return (manager != null) ? manager : (manager = new InventoryManager((Plugin)JavaPlugin.getProvidingPlugin(InventoryManager.class)));
  }
  
  public InventoryManager(Plugin paramPlugin) {
    this.plugin = paramPlugin;
    
    Bukkit.getPluginManager().registerEvents(new InvListener(), paramPlugin);
    
    this.inventories = new HashMap<>();
    this.contents = new HashMap<>();
    
    this.defaultOpeners = Arrays.asList(new InventoryOpener[] { (InventoryOpener)new ChestInventoryOpener(), (InventoryOpener)new SpecialInventoryOpener() });



    
    this.openers = new ArrayList<>();
  }
  private static boolean lambda$findOpener$0(InventoryType paramInventoryType, InventoryOpener paramInventoryOpener) {
    return paramInventoryOpener.supports(paramInventoryType);
  }
  public Optional<InventoryOpener> findOpener(InventoryType paramInventoryType) {
    Optional<InventoryOpener> optional = this.openers.stream().filter(paramInventoryType::lambda$findOpener$0).findAny();
    
    if (!optional.isPresent())
    {
      
      optional = this.defaultOpeners.stream().filter(paramInventoryType::lambda$findOpener$1).findAny();
    }
    
    return optional;
  } private static boolean lambda$findOpener$1(InventoryType paramInventoryType, InventoryOpener paramInventoryOpener) {
    return paramInventoryOpener.supports(paramInventoryType);
  } public void registerOpeners(InventoryOpener... paramVarArgs) {
    this.openers.addAll(Arrays.asList(paramVarArgs));
  }
  public List<Player> getOpenedPlayers(SmartInventory paramSmartInventory) {
    // Byte code:
    //   0: goto -> 5
    //   3: nop
    //   4: athrow
    //   5: new java/util/ArrayList
    //   8: dup
    //   9: invokespecial <init> : ()V
    //   12: astore_2
    //   13: aload_0
    //   14: getfield inventories : Ljava/util/Map;
    //   17: aload_1
    //   18: aload_2
    //   19: <illegal opcode> accept : (Lru/universalstudio/universaljoiner/gui/SmartInventory;Ljava/util/List;)Ljava/util/function/BiConsumer;
    //   24: invokevirtual forEach : (Ljava/util/function/BiConsumer;)V
    //   27: aload_2
    //   28: areturn
    //   29: nop
    //   30: nop
    //   31: nop
    //   32: athrow
    // Line number table:
    //   Java source line number -> byte code offset
    //   #72	-> 5
    //   #74	-> 13
    //   #79	-> 27
  }
  
  private static void lambda$getOpenedPlayers$2(SmartInventory paramSmartInventory1, List<Player> paramList, Player paramPlayer, SmartInventory paramSmartInventory2) {
    if (paramSmartInventory1.equals(paramSmartInventory2)) {
      paramList.add(paramPlayer);
    }
  }


  
  public Optional<SmartInventory> getInventory(Player paramPlayer) {
    return Optional.ofNullable(this.inventories.get(paramPlayer));
  }
  
  protected void setInventory(Player paramPlayer, SmartInventory paramSmartInventory) {
    if (paramSmartInventory == null) {
      this.inventories.remove(paramPlayer);
    } else {
      this.inventories.put(paramPlayer, paramSmartInventory);
    } 
  }
  public Optional<InventoryContents> getContents(Player paramPlayer) {
    return Optional.ofNullable(this.contents.get(paramPlayer));
  }
  
  protected void setContents(Player paramPlayer, InventoryContents paramInventoryContents) {
    if (paramInventoryContents == null) {
      this.contents.remove(paramPlayer);
    } else {
      this.contents.put(paramPlayer, paramInventoryContents);
    } 
  } class InvListener implements Listener { final InventoryManager this$0;
    private static boolean lambda$onInventoryClick$0(InventoryListener<InventoryClickEvent> param1InventoryListener) {
      return (param1InventoryListener.getType() == InventoryClickEvent.class);
    }
    @EventHandler(priority = EventPriority.LOW)
    public void onInventoryClick(InventoryClickEvent param1InventoryClickEvent) {
      Player player = (Player)param1InventoryClickEvent.getWhoClicked();
      
      if (!InventoryManager.this.inventories.containsKey(player)) {
        return;
      }
      if (param1InventoryClickEvent.getAction() == InventoryAction.COLLECT_TO_CURSOR || param1InventoryClickEvent.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY) {
        param1InventoryClickEvent.setCancelled(true);
        
        return;
      } 
      if (param1InventoryClickEvent.getAction() == InventoryAction.NOTHING && param1InventoryClickEvent.getClick() != ClickType.MIDDLE) {
        param1InventoryClickEvent.setCancelled(true);
        
        return;
      } 
      if (param1InventoryClickEvent.getClickedInventory() == player.getOpenInventory().getTopInventory()) {
        
        SmartInventory smartInventory = (SmartInventory)InventoryManager.this.inventories.get(player);
        
        smartInventory.getListeners().stream()
          .filter(InvListener::lambda$onInventoryClick$0);
        
        ((InventoryContents)InventoryManager.this.contents.get(player)).get(param1InventoryClickEvent.getCurrentItem()).ifPresent(param1InventoryClickEvent::lambda$onInventoryClick$1);
        
        player.updateInventory();
      } 
      
      param1InventoryClickEvent.setCancelled(true);
    } private static void lambda$onInventoryClick$1(InventoryClickEvent param1InventoryClickEvent, ClickableItem param1ClickableItem) {
      param1ClickableItem.getAction().accept(param1InventoryClickEvent);
    } @EventHandler(priority = EventPriority.LOW)
    public void onInventoryDrag(InventoryDragEvent param1InventoryDragEvent) {
      Player player = (Player)param1InventoryDragEvent.getWhoClicked();
      
      if (!InventoryManager.this.inventories.containsKey(player)) {
        return;
      }
      SmartInventory smartInventory = (SmartInventory)InventoryManager.this.inventories.get(player);
      
      for (Iterator<Integer> iterator = param1InventoryDragEvent.getRawSlots().iterator(); iterator.hasNext(); ) { int i = ((Integer)iterator.next()).intValue();
        if (i >= player.getOpenInventory().getTopInventory().getSize()) {
          continue;
        }
        param1InventoryDragEvent.setCancelled(true); }


      
      smartInventory.getListeners().stream()
        .filter(InvListener::lambda$onInventoryDrag$2)
        .forEach(param1InventoryDragEvent::lambda$onInventoryDrag$3); } private static boolean lambda$onInventoryDrag$2(InventoryListener<InventoryDragEvent> param1InventoryListener) { return (param1InventoryListener.getType() == InventoryDragEvent.class); } private static void lambda$onInventoryDrag$3(InventoryDragEvent param1InventoryDragEvent, InventoryListener<InventoryDragEvent> param1InventoryListener) { param1InventoryListener.accept(param1InventoryDragEvent); }

    
    @EventHandler(priority = EventPriority.LOW)
    public void onInventoryOpen(InventoryOpenEvent param1InventoryOpenEvent) {
      Player player = (Player)param1InventoryOpenEvent.getPlayer();
      
      if (!InventoryManager.this.inventories.containsKey(player)) {
        return;
      }
      SmartInventory smartInventory = (SmartInventory)InventoryManager.this.inventories.get(player);
      
      smartInventory.getListeners().stream()
        .filter(InvListener::lambda$onInventoryOpen$4)
        .forEach(param1InventoryOpenEvent::lambda$onInventoryOpen$5); } private static boolean lambda$onInventoryOpen$4(InventoryListener<InventoryOpenEvent> param1InventoryListener) { return (param1InventoryListener.getType() == InventoryOpenEvent.class); } private static void lambda$onInventoryOpen$5(InventoryOpenEvent param1InventoryOpenEvent, InventoryListener<InventoryOpenEvent> param1InventoryListener) { param1InventoryListener.accept(param1InventoryOpenEvent); }



    
    @EventHandler(priority = EventPriority.LOW)
    public void onInventoryClose(InventoryCloseEvent param1InventoryCloseEvent) {
      // Byte code:
      //   0: goto -> 4
      //   3: athrow
      //   4: aload_1
      //   5: invokevirtual getPlayer : ()Lorg/bukkit/entity/HumanEntity;
      //   8: checkcast org/bukkit/entity/Player
      //   11: astore_2
      //   12: aload_0
      //   13: getfield this$0 : Lru/universalstudio/universaljoiner/gui/InventoryManager;
      //   16: invokestatic access$000 : (Lru/universalstudio/universaljoiner/gui/InventoryManager;)Ljava/util/Map;
      //   19: aload_2
      //   20: invokevirtual containsKey : (Ljava/lang/Object;)Z
      //   23: ifne -> 32
      //   26: return
      //   27: nop
      //   28: nop
      //   29: nop
      //   30: nop
      //   31: athrow
      //   32: aload_0
      //   33: getfield this$0 : Lru/universalstudio/universaljoiner/gui/InventoryManager;
      //   36: invokestatic access$000 : (Lru/universalstudio/universaljoiner/gui/InventoryManager;)Ljava/util/Map;
      //   39: aload_2
      //   40: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
      //   43: checkcast ru/universalstudio/universaljoiner/gui/SmartInventory
      //   46: astore_3
      //   47: aload_3
      //   48: invokevirtual getListeners : ()Ljava/util/List;
      //   51: invokevirtual stream : ()Ljava/util/stream/Stream;
      //   54: <illegal opcode> test : ()Ljava/util/function/Predicate;
      //   59: invokevirtual filter : (Ljava/util/function/Predicate;)Ljava/util/stream/Stream;
      //   62: aload_1
      //   63: <illegal opcode> accept : (Lorg/bukkit/event/inventory/InventoryCloseEvent;)Ljava/util/function/Consumer;
      //   68: invokevirtual forEach : (Ljava/util/function/Consumer;)V
      //   71: aload_3
      //   72: invokevirtual isCloseable : ()Z
      //   75: ifeq -> 117
      //   78: aload_1
      //   79: invokevirtual getInventory : ()Lorg/bukkit/inventory/Inventory;
      //   82: invokevirtual clear : ()V
      //   85: aload_0
      //   86: getfield this$0 : Lru/universalstudio/universaljoiner/gui/InventoryManager;
      //   89: invokestatic access$000 : (Lru/universalstudio/universaljoiner/gui/InventoryManager;)Ljava/util/Map;
      //   92: aload_2
      //   93: invokevirtual remove : (Ljava/lang/Object;)Ljava/lang/Object;
      //   96: pop
      //   97: aload_0
      //   98: getfield this$0 : Lru/universalstudio/universaljoiner/gui/InventoryManager;
      //   101: invokestatic access$100 : (Lru/universalstudio/universaljoiner/gui/InventoryManager;)Ljava/util/Map;
      //   104: aload_2
      //   105: invokevirtual remove : (Ljava/lang/Object;)Ljava/lang/Object;
      //   108: pop
      //   109: goto -> 138
      //   112: nop
      //   113: nop
      //   114: nop
      //   115: nop
      //   116: athrow
      //   117: invokestatic getScheduler : ()Lorg/bukkit/scheduler/BukkitScheduler;
      //   120: aload_0
      //   121: getfield this$0 : Lru/universalstudio/universaljoiner/gui/InventoryManager;
      //   124: invokestatic access$200 : (Lru/universalstudio/universaljoiner/gui/InventoryManager;)Lorg/bukkit/plugin/Plugin;
      //   127: aload_2
      //   128: aload_1
      //   129: <illegal opcode> run : (Lorg/bukkit/entity/Player;Lorg/bukkit/event/inventory/InventoryCloseEvent;)Ljava/lang/Runnable;
      //   134: invokevirtual runTask : (Lorg/bukkit/plugin/Plugin;Ljava/lang/Runnable;)Lorg/bukkit/scheduler/BukkitTask;
      //   137: pop
      //   138: return
      //   139: nop
      //   140: nop
      //   141: nop
      //   142: nop
      //   143: athrow
      // Line number table:
      //   Java source line number -> byte code offset
      //   #177	-> 4
      //   #179	-> 12
      //   #180	-> 26
      //   #182	-> 32
      //   #184	-> 47
      //   #185	-> 59
      //   #186	-> 68
      //   #188	-> 71
      //   #189	-> 78
      //   #191	-> 85
      //   #192	-> 97
      //   #195	-> 117
      //   #196	-> 138
    }

    
    private static boolean lambda$onInventoryClose$6(InventoryListener<InventoryCloseEvent> param1InventoryListener)
    {
      return (param1InventoryListener.getType() == InventoryCloseEvent.class); } private static void lambda$onInventoryClose$7(InventoryCloseEvent param1InventoryCloseEvent, InventoryListener<InventoryCloseEvent> param1InventoryListener) {
      param1InventoryListener.accept(param1InventoryCloseEvent);
    }





    
    private static void lambda$onInventoryClose$8(Player param1Player, InventoryCloseEvent param1InventoryCloseEvent) {
      param1Player.openInventory(param1InventoryCloseEvent.getInventory());
    }
    
    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerQuit(PlayerQuitEvent param1PlayerQuitEvent) {
      Player player = param1PlayerQuitEvent.getPlayer();
      
      if (!InventoryManager.this.inventories.containsKey(player)) {
        return;
      }
      SmartInventory smartInventory = (SmartInventory)InventoryManager.this.inventories.get(player);
      
      smartInventory.getListeners().stream()
        .filter(InvListener::lambda$onPlayerQuit$9)
        .forEach(param1PlayerQuitEvent::lambda$onPlayerQuit$10);
      
      InventoryManager.this.inventories.remove(player);
      InventoryManager.this.contents.remove(player);
    } private static boolean lambda$onPlayerQuit$9(InventoryListener<PlayerQuitEvent> param1InventoryListener) { return (param1InventoryListener.getType() == PlayerQuitEvent.class); } private static void lambda$onPlayerQuit$10(PlayerQuitEvent param1PlayerQuitEvent, InventoryListener<PlayerQuitEvent> param1InventoryListener) {
      param1InventoryListener.accept(param1PlayerQuitEvent);
    } @EventHandler
    public void onPluginDisable(PluginDisableEvent param1PluginDisableEvent) {
      if (!param1PluginDisableEvent.getPlugin().equals(InventoryManager.this.plugin))
        return; 
      (new HashMap<>(InventoryManager.this.inventories)).forEach(param1PluginDisableEvent::lambda$onPluginDisable$13);






      
      InventoryManager.this.inventories.clear();
      InventoryManager.this.contents.clear();
    }
    
    private static void lambda$onPluginDisable$13(PluginDisableEvent param1PluginDisableEvent, Player param1Player, SmartInventory param1SmartInventory) {
      param1SmartInventory.getListeners().stream().filter(InvListener::lambda$onPluginDisable$11).forEach(param1PluginDisableEvent::lambda$onPluginDisable$12);
      param1SmartInventory.close(param1Player);
    }
    
    private static boolean lambda$onPluginDisable$11(InventoryListener<PluginDisableEvent> param1InventoryListener) {
      return (param1InventoryListener.getType() == PluginDisableEvent.class);
    }
    
    private static void lambda$onPluginDisable$12(PluginDisableEvent param1PluginDisableEvent, InventoryListener<PluginDisableEvent> param1InventoryListener) {
      param1InventoryListener.accept(param1PluginDisableEvent);
    } }

}
