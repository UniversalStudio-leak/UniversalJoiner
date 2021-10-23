package ru.universalstudio.universaljoiner.gui.opener;

import com.google.common.base.Preconditions;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import ru.universalstudio.universaljoiner.gui.InventoryManager;
import ru.universalstudio.universaljoiner.gui.SmartInventory;
import ru.universalstudio.universaljoiner.gui.content.InventoryContents;

public class ChestInventoryOpener
  implements InventoryOpener
{
  public Inventory open(SmartInventory paramSmartInventory, Player paramPlayer) {
    Preconditions.checkArgument((paramSmartInventory.getColumns() == 9), "The column count for the chest inventory must be 9, found: %s.", paramSmartInventory
        .getColumns());
    Preconditions.checkArgument((paramSmartInventory.getRows() >= 1 && paramSmartInventory.getRows() <= 6), "The row count for the chest inventory must be between 1 and 6, found: %s", paramSmartInventory
        .getRows());
    
    InventoryManager inventoryManager = paramSmartInventory.getManager();
    Inventory inventory = Bukkit.createInventory((InventoryHolder)paramPlayer, paramSmartInventory.getRows() * paramSmartInventory.getColumns(), (String)paramSmartInventory.getTitle().apply(paramPlayer));
    
    fill(inventory, ((InventoryContents)inventoryManager.getContents(paramPlayer).get()).contentsAll());
    
    paramPlayer.openInventory(inventory);
    return inventory;
  }

  
  public boolean supports(InventoryType paramInventoryType) {
    return (paramInventoryType == InventoryType.CHEST || paramInventoryType == InventoryType.ENDER_CHEST);
  }
}
