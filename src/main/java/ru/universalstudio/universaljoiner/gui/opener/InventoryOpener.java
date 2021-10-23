package ru.universalstudio.universaljoiner.gui.opener;

import java.util.Map;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import ru.universalstudio.universaljoiner.gui.ClickableItem;
import ru.universalstudio.universaljoiner.gui.SmartInventory;



public interface InventoryOpener
{
  Inventory open(SmartInventory paramSmartInventory, Player paramPlayer);
  
  boolean supports(InventoryType paramInventoryType);
  
  default void fill(Inventory paramInventory, Map<Integer, ClickableItem> paramMap) {
    for (Map.Entry<Integer, ClickableItem> entry : paramMap.entrySet()) {
      if (entry.getValue() != null)
        paramInventory.setItem(((Integer)entry.getKey()).intValue(), ((ClickableItem)entry.getValue()).getItem()); 
    } 
  }
}
