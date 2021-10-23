package ru.universalstudio.universaljoiner;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.entity.Player;
import ru.universalstudio.universaljoiner.utils.ItemBuilder;
import ru.universalstudio.universaljoiner.utils.Utils;



public class JoinerManager
{
  private List<Joiner> joiners = new ArrayList<>();
  private List<JoinerPlayer> players = new ArrayList<>();
  
  public JoinerManager() {
    for (String str : Utils.getConfig().getConfigurationSection("joiners").getKeys(false)) {
      
      Joiner joiner = new Joiner(str);
      
      joiner.setJoinMessage(Utils.getString("joiners." + str + ".join-message"));
      joiner.setPermission(Utils.getString("joiners." + str + ".permission"));
      joiner.setPermissionFree(Utils.getString("joiners." + str + ".permission-free"));
      joiner.setMoney(Utils.getDouble("joiners." + str + ".money"));
      
      joiner.getAvailable().setItem(ItemBuilder.loadItemBuilder(Utils.getConfig(), "joiners." + str + ".available.item").build());
      joiner.getAvailable().setSoundSelected(Utils.getSound(Utils.getString("joiners." + str + ".available.sound-selected")));
      joiner.getAvailable().setSoundAlreadySelected(Utils.getSound(Utils.getString("joiners." + str + ".available.sound-already-selected")));
      joiner.getAvailable().setSelected(Utils.getString("joiners." + str + ".available.selected"));
      joiner.getAvailable().setNoSelected(Utils.getString("joiners." + str + ".available.no-selected"));
      
      joiner.getAvailableBuy().setItem(ItemBuilder.loadItemBuilder(Utils.getConfig(), "joiners." + str + ".available-buy.item").build());
      joiner.getAvailableBuy().setSoundBuy(Utils.getSound(Utils.getString("joiners." + str + ".available-buy.sound-buy")));
      joiner.getAvailableBuy().setSoundMoneyNotEnough(Utils.getSound(Utils.getString("joiners." + str + ".available-buy.sound-money-notenough")));
      
      joiner.getNotAvailable().setItem(ItemBuilder.loadItemBuilder(Utils.getConfig(), "joiners." + str + ".not-available.item").build());
      joiner.getNotAvailable().setSound(Utils.getSound(Utils.getString("joiners." + str + ".not-available.sound")));
      
      this.joiners.add(joiner);
    } 
  }
  
  public List<Joiner> getJoiners() {
    return this.joiners;
  }
  
  public JoinerPlayer getJoinerPlayer(Player paramPlayer) {
    return getJoinerPlayer(paramPlayer.getName());
  } private static boolean lambda$getJoinerPlayer$0(String paramString, JoinerPlayer paramJoinerPlayer) {
    return paramJoinerPlayer.getPlayerName().equalsIgnoreCase(paramString);
  }
  public JoinerPlayer getJoinerPlayer(String paramString) {
    JoinerPlayer joinerPlayer = this.players.stream().filter(paramString::lambda$getJoinerPlayer$0).findAny().orElse(null);
    
    if (joinerPlayer == null) {
      joinerPlayer = new JoinerPlayer(paramString);
      joinerPlayer.loadFromDatabase();
      
      this.players.add(joinerPlayer);
    } 
    return joinerPlayer;
  }
  
  public Joiner getJoiner(String paramString) {
    return this.joiners.stream().filter(paramString::lambda$getJoiner$1).findAny().orElse(null); } private static boolean lambda$getJoiner$1(String paramString, Joiner paramJoiner) { return paramJoiner.getName().equalsIgnoreCase(paramString); }

}
