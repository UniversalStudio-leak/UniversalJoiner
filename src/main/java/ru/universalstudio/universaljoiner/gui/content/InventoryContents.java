package ru.universalstudio.universaljoiner.gui.content;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.bukkit.inventory.ItemStack;
import ru.universalstudio.universaljoiner.gui.ClickableItem;
import ru.universalstudio.universaljoiner.gui.SmartInventory;

public interface InventoryContents {
  public static final boolean  ‏  ;
  
  SmartInventory inventory();
  
  Pagination pagination();
  
  Optional<SlotIterator> iterator(String paramString);
  
  SlotIterator newIterator(String paramString, SlotIterator.Type paramType, int paramInt1, int paramInt2);
  
  SlotIterator newIterator(SlotIterator.Type paramType, int paramInt1, int paramInt2);
  
  SlotIterator newIterator(String paramString, SlotIterator.Type paramType, SlotPos paramSlotPos);
  
  SlotIterator newIterator(SlotIterator.Type paramType, SlotPos paramSlotPos);
  
  Collection<ClickableItem> contents();
  
  Map<Integer, ClickableItem> contentsAll();
  
  Optional<ClickableItem> get(ItemStack paramItemStack);
  
  Optional<ClickableItem> get(int paramInt1, int paramInt2);
  
  InventoryContents set(int paramInt1, int paramInt2, ClickableItem paramClickableItem);
  
  InventoryContents set(SlotPos paramSlotPos, ClickableItem paramClickableItem);
  
  InventoryContents add(ClickableItem paramClickableItem);
  
  InventoryContents fillBorders(ClickableItem paramClickableItem);
  
  InventoryContents fillRect(int paramInt1, int paramInt2, int paramInt3, int paramInt4, ClickableItem paramClickableItem);
  
  InventoryContents fillRect(SlotPos paramSlotPos1, SlotPos paramSlotPos2, ClickableItem paramClickableItem);
  
  <T> T property(String paramString);
  
  <T> T property(String paramString, T paramT);
  
  InventoryContents setTitle(String paramString);
  
  InventoryContents setProperty(String paramString, Object paramObject);
  
  InventoryContents propertyClear();
  
  InventoryContents updateItem(int paramInt1, int paramInt2);
  
  InventoryContents updateItem(int paramInt1, int paramInt2, ItemStack paramItemStack);
  
  int getSlot(int paramInt1, int paramInt2);
  
  public static class Impl implements InventoryContents {
    private Map<Integer, ClickableItem> contents = new HashMap<>(); private SmartInventory inv;
    private Player player;
    private Pagination pagination = new Pagination.Impl();
    private Map<String, SlotIterator> iterators = new HashMap<>();
    private Map<String, Object> properties = new HashMap<>();
    
    public Impl(SmartInventory param1SmartInventory, Player param1Player) {
      this.inv = param1SmartInventory;
      this.player = param1Player;
    }
    
    public SmartInventory inventory() {
      return this.inv;
    }
    public Pagination pagination() {
      return this.pagination;
    }
    
    public Optional<SlotIterator> iterator(String param1String) {
      return Optional.ofNullable(this.iterators.get(param1String));
    }

    
    public SlotIterator newIterator(String param1String, SlotIterator.Type param1Type, int param1Int1, int param1Int2) {
      SlotIterator.Impl impl = new SlotIterator.Impl(this, this.inv, param1Type, param1Int1, param1Int2);

      
      this.iterators.put(param1String, impl);
      return impl;
    }

    
    public SlotIterator newIterator(String param1String, SlotIterator.Type param1Type, SlotPos param1SlotPos) {
      return newIterator(param1String, param1Type, param1SlotPos.getRow(), param1SlotPos.getColumn());
    }

    
    public SlotIterator newIterator(SlotIterator.Type param1Type, int param1Int1, int param1Int2) {
      return new SlotIterator.Impl(this, this.inv, param1Type, param1Int1, param1Int2);
    }

    
    public SlotIterator newIterator(SlotIterator.Type param1Type, SlotPos param1SlotPos) {
      return newIterator(param1Type, param1SlotPos.getRow(), param1SlotPos.getColumn());
    }

    
    public Collection<ClickableItem> contents() {
      return this.contents.values();
    }

    
    public Map<Integer, ClickableItem> contentsAll() {
      return this.contents;
    }

    
    public Optional<ClickableItem> get(ItemStack param1ItemStack) {
      return Optional.ofNullable(contents()
          .stream().filter(param1ItemStack::lambda$get$0)
          .findAny().orElse(null));
    } private static boolean lambda$get$0(ItemStack param1ItemStack, ClickableItem param1ClickableItem) {
      return param1ClickableItem.equalsItemStack(param1ItemStack);
    }
    public Optional<ClickableItem> get(int param1Int1, int param1Int2) {
      return Optional.ofNullable(this.contents.get(Integer.valueOf(getSlot(param1Int1, param1Int2))));
    }

    
    public InventoryContents set(int param1Int1, int param1Int2, ClickableItem param1ClickableItem) {
      if (param1ClickableItem == null) return this;
      
      this.contents.put(Integer.valueOf(getSlot(param1Int1, param1Int2)), param1ClickableItem);
      
      updateItem(param1Int1, param1Int2, (param1ClickableItem != null) ? param1ClickableItem.getItem() : null);
      return this;
    }

    
    public InventoryContents set(SlotPos param1SlotPos, ClickableItem param1ClickableItem) {
      return set(param1SlotPos.getRow(), param1SlotPos.getColumn(), param1ClickableItem);
    }

    
    public InventoryContents add(ClickableItem param1ClickableItem) {
      for (byte b = 0; b < this.contents.size(); b++) {
        if (this.contents.get(Integer.valueOf(b)) == null) {
          this.contents.put(Integer.valueOf(b), param1ClickableItem);
          return this;
        } 
      } 
      
      this.contents.put(Integer.valueOf(this.contents.size() + 1), param1ClickableItem);
      
      return this;
    }

    
    public InventoryContents fillBorders(ClickableItem param1ClickableItem) {
      fillRect(0, 0, this.inv.getRows() - 1, this.inv.getColumns() - 1, param1ClickableItem);
      return this;
    }

    
    public InventoryContents fillRect(int param1Int1, int param1Int2, int param1Int3, int param1Int4, ClickableItem param1ClickableItem) {
      for (int i = param1Int1; i <= param1Int3; i++) {
        for (int j = param1Int2; j <= param1Int4; j++) {
          if (i == param1Int1 || i == param1Int3 || j == param1Int2 || j == param1Int4)
          {
            
            set(i, j, param1ClickableItem);
          }
        } 
      } 
      return this;
    }

    
    public InventoryContents fillRect(SlotPos param1SlotPos1, SlotPos param1SlotPos2, ClickableItem param1ClickableItem) {
      return fillRect(param1SlotPos1.getRow(), param1SlotPos1.getColumn(), param1SlotPos2.getRow(), param1SlotPos2.getColumn(), param1ClickableItem);
    }


    
    public <T> T property(String param1String) {
      return (T)this.properties.get(param1String);
    }


    
    public <T> T property(String param1String, T param1T) {
      return this.properties.containsKey(param1String) ? (T)this.properties.get(param1String) : param1T;
    }

    
    public InventoryContents setProperty(String param1String, Object param1Object) {
      this.properties.put(param1String, param1Object);
      return this;
    }

    
    public InventoryContents propertyClear() {
      this.properties.clear();
      return this;
    }

    
    public InventoryContents updateItem(int param1Int1, int param1Int2) {
      updateItem(param1Int1, param1Int2, ((ClickableItem)this.contents.get(Integer.valueOf(getSlot(param1Int1, param1Int2)))).getItem());
      return this;
    }

    
    public InventoryContents updateItem(int param1Int1, int param1Int2, ItemStack param1ItemStack) {
      if (!this.inv.getManager().getOpenedPlayers(this.inv).contains(this.player)) {
        return this;
      }
      Inventory inventory = this.player.getOpenInventory().getTopInventory();
      inventory.setItem(getSlot(param1Int1, param1Int2), param1ItemStack);
      return this;
    }

    
    public InventoryContents setTitle(String param1String) {
      this.inv.title(param1String);
      
      return this;
    }

    
    public int getSlot(int param1Int1, int param1Int2) {
      return param1Int1 * 9 + param1Int2;
    }
  }
}
