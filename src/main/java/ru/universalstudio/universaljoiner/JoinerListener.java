package ru.universalstudio.universaljoiner;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import ru.universalstudio.universaljoiner.utils.Utils;

public class JoinerListener
  implements Listener
{
  @EventHandler
  public void onJoin(PlayerJoinEvent paramPlayerJoinEvent) {
    JoinerPlayer joinerPlayer = JoinerPlugin.getInstance().getJoinerManager().getJoinerPlayer(paramPlayerJoinEvent.getPlayer());
    
    if (joinerPlayer.getActiveJoiner() != null) {
      Joiner joiner = joinerPlayer.getActiveJoiner();
      
      paramPlayerJoinEvent.setJoinMessage(Utils.color(joiner.getJoinMessage(paramPlayerJoinEvent.getPlayer())));
    } else {
      
      paramPlayerJoinEvent.setJoinMessage(null);
    } 
  }
}
