package ru.universalstudio.universaljoiner.gui.content;

import org.bukkit.entity.Player;

public interface InventoryProvider
{
  void init(Player paramPlayer, InventoryContents paramInventoryContents);
  
  default boolean refresh(Player paramPlayer, InventoryContents paramInventoryContents) {
    return false;
  }
  
  @Deprecated
  default void update(Player paramPlayer, InventoryContents paramInventoryContents) {}
}
