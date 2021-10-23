package ru.universalstudio.universaljoiner.gui;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import ru.universalstudio.universaljoiner.utils.ItemBuilder;
import ru.universalstudio.universaljoiner.utils.Utils;

public class GuiLoader {
  private String title;
  private int rows;
  private int columns;
  
  public static GuiLoader guiLoad(FileConfiguration paramFileConfiguration, String paramString) {
    String str = paramFileConfiguration.getString(paramString + ".title");
    ArrayList<GuiItem> arrayList = new ArrayList();

    
    Pagination pagination = new Pagination(paramFileConfiguration.getInt(paramString + ".pagination.start-row"), paramFileConfiguration.getInt(paramString + ".pagination.start-column"), paramFileConfiguration.getInt(paramString + ".pagination.perpage"));
    
    ItemStack itemStack = null;
    
    if (paramFileConfiguration.getString(paramString + ".window") != null) {
      itemStack = ItemBuilder.loadItemBuilder(Utils.getConfig(), paramString + ".window").build();
    }
    int i = 6;
    int j = 9;
    
    try {
      i = Integer.parseInt(paramFileConfiguration.getString(paramString + ".size").split(";")[0]);
      j = Integer.parseInt(paramFileConfiguration.getString(paramString + ".size").split(";")[1]);
    } catch (Exception exception) {}
    
    for (String str1 : paramFileConfiguration.getConfigurationSection(paramString + ".items").getKeys(false)) {
      ItemStack itemStack1 = ItemBuilder.loadItemBuilder(paramFileConfiguration, paramString + ".items." + str1 + ".item").build();
      ItemStack itemStack2 = ItemBuilder.loadItemBuilder(paramFileConfiguration, paramString + ".items." + str1 + ".air-replace").build();
      
      String str2 = paramFileConfiguration.getString(paramString + ".items." + str1 + ".type");
      int k = paramFileConfiguration.getInt(paramString + ".items." + str1 + ".position-y") * 9 + paramFileConfiguration.getInt(paramString + ".items." + str1 + ".position-x");
      
      Sound sound = null;
      
      try {
        sound = Sound.valueOf(paramFileConfiguration.getString(paramString + ".sound").toUpperCase());
      }
      catch (Exception exception) {}
      
      arrayList.add(new GuiItem(str1, itemStack1, itemStack2, str2, k, sound));
    } 
    
    return new GuiLoader(str, itemStack, i, j, arrayList, pagination);
  }


  
  private ItemStack window;
  
  private List<GuiItem> items;
  
  private Pagination pagination;

  
  public GuiLoader(String paramString, ItemStack paramItemStack, int paramInt1, int paramInt2, List<GuiItem> paramList, Pagination paramPagination) {
    this.title = paramString;
    this.rows = paramInt1;
    this.columns = paramInt2;
    this.window = paramItemStack;
    this.items = paramList;
    this.pagination = paramPagination;
  }
  
  public String getTitle() {
    return this.title;
  }
  
  public int getRows() {
    return this.rows;
  }
  
  public int getColumns() {
    return this.columns;
  }
  
  public ItemStack getWindow() {
    return this.window;
  }
  
  public List<GuiItem> getItems() {
    return this.items;
  }
  
  public List<GuiItem> getItemsType(String paramString) {
    return (List<GuiItem>)getItems().stream().filter(paramString::lambda$getItemsType$0).collect(Collectors.toList()); } private static boolean lambda$getItemsType$0(String paramString, GuiItem paramGuiItem) { return paramGuiItem.getType().equalsIgnoreCase(paramString); }

  
  public GuiItem getItem(String paramString) {
    return getItems().stream().filter(paramString::lambda$getItem$1).findAny().orElse(null); } private static boolean lambda$getItem$1(String paramString, GuiItem paramGuiItem) { return paramGuiItem.getName().equalsIgnoreCase(paramString); }

  
  public Pagination pagination() {
    return this.pagination;
  }
  
  public static class GuiItem
  {
    private String name;
    private ItemStack item;
    private ItemStack airReplace;
    private String type;
    private Sound sound;
    private int slot;
    
    public GuiItem(String param1String1, ItemStack param1ItemStack1, ItemStack param1ItemStack2, String param1String2, int param1Int, Sound param1Sound) {
      this.name = param1String1;
      this.item = param1ItemStack1;
      this.airReplace = param1ItemStack2;
      this.type = param1String2;
      this.slot = param1Int;
      this.sound = param1Sound;
    }
    
    public Sound getSound() {
      return this.sound;
    }
    
    public String getName() {
      return this.name;
    }
    
    public ItemStack getItem() {
      return this.item;
    }
    
    public ItemStack getAirReplace() {
      return this.airReplace;
    }
    
    public String getType() {
      return this.type;
    }
    
    public boolean isType(String param1String) {
      return getType().equalsIgnoreCase(param1String);
    }
    
    public int getSlot() {
      return this.slot;
    }
  }
  
  public static class Pagination
  {
    private int startRow;
    private int startColumn;
    private int perPage;
    
    public Pagination(int param1Int1, int param1Int2, int param1Int3) {
      this.startRow = param1Int1;
      this.startColumn = param1Int2;
      this.perPage = param1Int3;
    }
    
    public int getStartRow() {
      return this.startRow;
    }
    
    public int getStartColumn() {
      return this.startColumn;
    }
    
    public int getPerPage() {
      return this.perPage;
    }
  }
}
