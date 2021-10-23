package ru.universalstudio.universaljoiner.sql;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import ru.universalstudio.universaljoiner.Joiner;
import ru.universalstudio.universaljoiner.JoinerPlugin;



public class SQLDatabase
{
  private String host;
  private String user;
  private String password;
  private Connection connection;
  private Statement statement;
  
  public SQLDatabase(File paramFile) {
    this.host = "jdbc:sqlite:" + paramFile + File.separator + "data.db";
  }
  
  public SQLDatabase(String paramString1, String paramString2, String paramString3) {
    this.host = paramString1;
    this.user = paramString2;
    this.password = paramString3;
  }
  
  private Connection connection() throws Exception {
    if (this.connection == null || this.connection.isClosed()) {
      connect();
    }
    return this.connection;
  }
  
  private Statement statement() throws Exception {
    if (this.statement == null || this.statement.isClosed()) {
      this.statement = connection().createStatement();
    }
    return this.statement;
  }
  
  public void close() throws Exception {
    if (this.statement != null && !this.statement.isClosed()) {
      this.statement.close();
    }
    if (this.connection != null && !this.connection.isClosed())
      this.connection.close(); 
  }
  
  public void connect() throws Exception {
    if (this.user != null) { this.connection = DriverManager.getConnection(this.host, this.user, this.password); }
    else { this.connection = DriverManager.getConnection(this.host); }
    
    statement().executeUpdate("CREATE TABLE IF NOT EXISTS `ujoiner` (`player` varchar(255), `joiners` varchar(255), `activeJoiner` varchar(255))");
  }

  
  public void setActiveJoiner(String paramString, Joiner paramJoiner) {
    try {
      String str = (paramJoiner != null) ? paramJoiner.getName() : null;
      
      if (statement().executeUpdate("UPDATE `ujoiner` set `activeJoiner`='" + str + "' WHERE `player`='" + paramString + "'") <= 0) {
        statement().executeUpdate("INSERT INTO `ujoiner` (`player`, `activeJoiner`) VALUES ('" + paramString + "', '" + str + "')");
      }
    }
    catch (Exception exception) {
      exception.printStackTrace();
    } 
  }
  
  public Joiner getActiveJoiner(String paramString) {
    try {
      ResultSet resultSet = statement().executeQuery("SELECT `player`, `activeJoiner` FROM `ujoiner` WHERE `player`='" + paramString + "'");
      
      if (resultSet.next()) {
        return JoinerPlugin.getInstance().getJoinerManager().getJoiner(resultSet.getString("activeJoiner"));
      }
      return null;
    }
    catch (Exception exception) {
      return null;
    } 
  }
  
  public List<Joiner> getJoiners(String paramString) {
    ArrayList<Joiner> arrayList = new ArrayList();
    
    try {
      ResultSet resultSet = statement().executeQuery("SELECT `player`, `joiners` FROM `ujoiner` WHERE `player`='" + paramString + "'");
      
      while (resultSet.next()) {
        
        for (String str : resultSet.getString("joiners").split(",")) {
          Joiner joiner = JoinerPlugin.getInstance().getJoinerManager().getJoiner(str);
          
          if (joiner != null) {
            arrayList.add(joiner);
          }
        } 
      } 
      
      return arrayList;
    }
    catch (Exception exception) {
      return arrayList;
    } 
  }
  
  public void setJoiners(String paramString, List<Joiner> paramList) {
    try {
      StringBuilder stringBuilder = new StringBuilder();
      
      for (Joiner joiner : paramList) {
        stringBuilder.append(joiner.getName()).append(",");
      }
      if (stringBuilder.length() > 0) {
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
      }
      if (statement().executeUpdate("UPDATE `ujoiner` set `joiners`='" + stringBuilder.toString() + "' WHERE `player`='" + paramString + "'") <= 0) {
        statement().executeUpdate("INSERT INTO `ujoiner`(`player`, `joiners`) VALUES ('" + paramString + "', '" + stringBuilder.toString() + "')");
      }
    }
    catch (Exception exception) {
      exception.printStackTrace();
    } 
  }
}
