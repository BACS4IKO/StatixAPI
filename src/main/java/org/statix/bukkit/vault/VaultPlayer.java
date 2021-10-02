package org.statix.bukkit.vault;

public interface VaultPlayer {
   String getName();

   String getPrefix();

   String getSuffix();

   String getGroupPrefix();

   String getGroupSuffix();

   double getBalance();

   void setBalance(double var1);

   void giveMoney(double var1);

   void takeMoney(double var1);

   String[] getGroups();

   String getPrimaryGroup();

   void addPermission(String var1);

   void removePermission(String var1);

   void addGroup(String var1);

   void removeGroup(String var1);

   boolean hasGroup(String var1);

   boolean hasPermission(String var1);
}
