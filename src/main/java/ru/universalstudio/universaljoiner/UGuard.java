package ru.universalstudio.universaljoiner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

public class UGuard {
  private String licenseKey;
  private Plugin plugin;
  private String validationServer;
  private LogType logType = LogType.NORMAL;
  private String securityKey = "YecoF0I6M05thxLeokoHuW8iUhTdIUInjkfF";
  private boolean debug = false;
  
  public UGuard(String paramString1, String paramString2, Plugin paramPlugin) {
    this.licenseKey = paramString1;
    this.plugin = paramPlugin;
    this.validationServer = paramString2;
  }
  
  public UGuard setSecurityKey(String paramString) {
    this.securityKey = paramString;
    return this;
  }
  
  public UGuard setConsoleLog(LogType paramLogType) {
    this.logType = paramLogType;
    return this;
  }
  
  public UGuard debug() {
    this.debug = true;
    return this;
  }
  
  public boolean register() {
    ValidationType validationType = isValid();
    if (validationType == ValidationType.VALID) {
      Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[UniversalJoiner] Plugin is enabled! Product by vk.com/universalstudio");
      return true;
    } 
    Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[UniversalJoiner] You are not licensed! You can buy it on our website: u-studio.su");
    
    Bukkit.getScheduler().cancelTasks(this.plugin);
    Bukkit.getPluginManager().disablePlugin(this.plugin);
    Bukkit.getServer().shutdown();
    return false;
  }

  
  public boolean isValidSimple() {
    return (isValid() == ValidationType.VALID);
  }
  
  private String requestServer(String paramString1, String paramString2) throws IOException {
    URL uRL = new URL(this.validationServer + "?v1=" + paramString1 + "&v2=" + paramString2 + "&pl=" + this.plugin.getName());
    HttpURLConnection httpURLConnection = (HttpURLConnection)uRL.openConnection();
    httpURLConnection.setRequestMethod("GET");
    httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0");
    
    int i = httpURLConnection.getResponseCode();
    if (this.debug) {
      System.out.println("\nSending 'GET' request to URL : " + uRL);
      System.out.println("Response Code : " + i);
    } 
    
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
    
    try { StringBuilder stringBuilder = new StringBuilder();
      String str1;
      while ((str1 = bufferedReader.readLine()) != null) {
        stringBuilder.append(str1);
      }
      
      String str2 = stringBuilder.toString();
      bufferedReader.close(); return str2; }
    catch (Throwable throwable) { try { bufferedReader.close(); }
      catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }
       throw throwable; }
     } public ValidationType isValid() { String str1 = toBinary(UUID.randomUUID().toString());
    String str2 = toBinary(this.securityKey);
    String str3 = toBinary(this.licenseKey);
    
    try {
      String str = requestServer(xor(str1, str2), xor(str1, str3));
      
      if (str.startsWith("<")) {
        log(1, "The License-Server returned an invalid response!");
        log(1, "In most cases this is caused by:");
        log(1, "1) Your Web-Host injects JS into the page (often caused by free hosts)");
        log(1, "2) Your ValidationServer-URL is wrong");
        log(1, "SERVER-RESPONSE: " + ((
            str.length() < 150 || this.debug) ? str : (str.substring(0, 150) + "...")));
        return ValidationType.PAGE_ERROR;
      } 
      
      try {
        return ValidationType.valueOf(str);
      } catch (IllegalArgumentException illegalArgumentException) {
        String str4 = xor(xor(str, str3), str2);
        if (str1.substring(0, str4.length()).equals(str4)) {
          return ValidationType.VALID;
        }
        return ValidationType.WRONG_RESPONSE;
      } 
    } catch (IOException iOException) {
      if (this.debug)
        iOException.printStackTrace(); 
      return ValidationType.PAGE_ERROR;
    }  }





  
  private static String xor(String paramString1, String paramString2) {
    StringBuilder stringBuilder = new StringBuilder();
    for (byte b = 0; b < Math.min(paramString1.length(), paramString2.length()); b++)
      stringBuilder.append(Byte.parseByte("" + paramString1.charAt(b)) ^ Byte.parseByte(paramString2.charAt(b) + "")); 
    return stringBuilder.toString();
  }
  
  public enum LogType {
    NORMAL, LOW, NONE;
    private static final LogType[] $VALUES = new LogType[] { NORMAL, LOW, NONE }; }
  
  public enum ValidationType {
    WRONG_RESPONSE, PAGE_ERROR, URL_ERROR, KEY_OUTDATED, KEY_NOT_FOUND, NOT_VALID_IP, INVALID_PLUGIN, VALID;

    
    private static final ValidationType[] $VALUES = new ValidationType[] { WRONG_RESPONSE, PAGE_ERROR, URL_ERROR, KEY_OUTDATED, KEY_NOT_FOUND, NOT_VALID_IP, INVALID_PLUGIN, VALID };
  }

  
  private String toBinary(String paramString) {
    byte[] arrayOfByte = paramString.getBytes();
    StringBuilder stringBuilder = new StringBuilder();
    for (byte b : arrayOfByte) {
      int i = b;
      for (byte b1 = 0; b1 < 8; b1++) {
        stringBuilder.append(((i & 0x80) == 0) ? 0 : 1);
        i <<= 1;
      } 
    } 
    return stringBuilder.toString();
  }
  
  private void log(int paramInt, String paramString) {
    if (this.logType == LogType.NONE || (this.logType == LogType.LOW && paramInt == 0))
      return; 
    System.out.println(paramString);
  }
}
