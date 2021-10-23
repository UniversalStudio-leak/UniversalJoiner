package ru.universalstudio.universaljoiner.gui.opener;

import com.google.common.collect.ImmutableList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import ru.universalstudio.universaljoiner.gui.InventoryManager;
import ru.universalstudio.universaljoiner.gui.SmartInventory;
import ru.universalstudio.universaljoiner.gui.content.InventoryContents;

public class SpecialInventoryOpener
  implements InventoryOpener
{
  private static final List<InventoryType> SUPPORTED = (List<InventoryType>)ImmutableList.of(InventoryType.FURNACE, InventoryType.WORKBENCH, InventoryType.DISPENSER, InventoryType.DROPPER, InventoryType.ENCHANTING, InventoryType.BREWING, InventoryType.ANVIL, InventoryType.BEACON, InventoryType.HOPPER);











  
  public Inventory open(SmartInventory paramSmartInventory, Player paramPlayer) {
    InventoryManager inventoryManager = paramSmartInventory.getManager();
    Inventory inventory = Bukkit.createInventory((InventoryHolder)paramPlayer, paramSmartInventory.getType(), (String)paramSmartInventory.getTitle().apply(paramPlayer));
    
    fill(inventory, ((InventoryContents)inventoryManager.getContents(paramPlayer).get()).contentsAll());
    
    paramPlayer.openInventory(inventory);
    return inventory;
  }

  
  public boolean supports(InventoryType paramInventoryType) {
    return SUPPORTED.contains(paramInventoryType);
  }
}
