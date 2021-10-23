package ru.universalstudio.universaljoiner;

import java.util.ArrayList;
import java.util.List;
import ru.universalstudio.universaljoiner.sql.SQLDatabase;



public class JoinerPlayer
{
  private String playerName;
  private Joiner activeJoiner;
  private List<Joiner> joiners = new ArrayList<>();
  
  public JoinerPlayer(String paramString) {
    this.playerName = paramString;
  }
  
  public String getPlayerName() {
    return this.playerName;
  }
  
  public List<Joiner> getJoiners() {
    return this.joiners;
  }
  
  public Joiner getJoiner(String paramString) {
    return getJoiners().stream().filter(paramString::lambda$getJoiner$0).findAny().orElse(null); } private static boolean lambda$getJoiner$0(String paramString, Joiner paramJoiner) { return paramJoiner.getName().equalsIgnoreCase(paramString); }

  
  public void addJoiner(Joiner paramJoiner) {
    if (!this.joiners.contains(paramJoiner)) {
      this.joiners.add(paramJoiner);
      
      if (this.activeJoiner == null) {
        this.activeJoiner = paramJoiner;
      }
      update();
    } 
  }

  
  public boolean removeJoiner(String paramString) {
    if (this.joiners.removeIf(paramString::lambda$removeJoiner$1)) {
      
      if (this.activeJoiner != null && this.activeJoiner.getName().equalsIgnoreCase(paramString)) {
        this.activeJoiner = null;
      }
      
      update();
      
      return true;
    } 
    return false;
  } private static boolean lambda$removeJoiner$1(String paramString, Joiner paramJoiner) {
    return paramJoiner.getName().equalsIgnoreCase(paramString);
  } public void setJoiners(List<Joiner> paramList) {
    this.joiners = paramList;
  }
  
  public Joiner getActiveJoiner() {
    return this.activeJoiner;
  }
  
  public void setActiveJoiner(Joiner paramJoiner) {
    this.activeJoiner = paramJoiner;
    update();
  }

  
  public void update() {
    SQLDatabase sQLDatabase = JoinerPlugin.getInstance().getSQLDatabase();
    
    sQLDatabase.setActiveJoiner(this.playerName, this.activeJoiner);
    sQLDatabase.setJoiners(this.playerName, this.joiners);
  }
  
  public void loadFromDatabase() {
    SQLDatabase sQLDatabase = JoinerPlugin.getInstance().getSQLDatabase();
    
    this.activeJoiner = sQLDatabase.getActiveJoiner(this.playerName);
    this.joiners = sQLDatabase.getJoiners(this.playerName);
  }
}
