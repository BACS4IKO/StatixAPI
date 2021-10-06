package org.statix.bukkit.utility;

import lombok.experimental.UtilityClass;
import org.statix.bukkit.StatixAPI;
import org.statix.bukkit.vault.VaultPlayer;

/**
 * ну а мало-ли, может кому-то нужно
 */
@UtilityClass
public class VaultUtil {
   public String getPrefix(String name) {
      return StatixAPI.getVaultManager().getVaultPlayer(name).getPrefix();
   }

   public String getGroupPrefix(String name) {
      return StatixAPI.getVaultManager().getVaultPlayer(name).getGroupPrefix();
   }

   public String getGroupSuffix(String name) {
      return StatixAPI.getVaultManager().getVaultPlayer(name).getGroupSuffix();
   }

   public String getSuffix(String name) {
      return StatixAPI.getVaultManager().getVaultPlayer(name).getSuffix();
   }

   public int getBalance(String name) {
      return (int)StatixAPI.getVaultManager().getVaultPlayer(name).getBalance();
   }

   public void setBalance(String name, int i) {
      StatixAPI.getVaultManager().getVaultPlayer(name).setBalance((double)i);
   }

   public void giveMoney(String name, int i) {
      StatixAPI.getVaultManager().getVaultPlayer(name).giveMoney((double)i);
   }

   public void takeMoney(String name, int i) {
      StatixAPI.getVaultManager().getVaultPlayer(name).takeMoney((double)i);
   }

   public boolean hasGroup(String name, String group) {
      return StatixAPI.getVaultManager().getVaultPlayer(name).hasGroup(group);
   }

   public boolean hasPermission(String name, String perm) {
      return StatixAPI.getVaultManager().getVaultPlayer(name).hasPermission(perm);
   }

   public boolean hasBalance(String name, int i) {
      return getBalance(name) >= i;
   }

   public void removePermission(String name, String perm) {
      StatixAPI.getVaultManager().getVaultPlayer(name).removePermission(perm);
   }

   public VaultPlayer getVaultPlayer(String name){
      return StatixAPI.getVaultManager().getVaultPlayer(name);
   }
   
   public void removeGroup(String name, String group) {
      StatixAPI.getVaultManager().getVaultPlayer(name).removeGroup(group);
   }

   public void addPermission(String name, String perm) {
      StatixAPI.getVaultManager().getVaultPlayer(name).addPermission(perm);
   }

   public void addGroup(String name, String group) {
      StatixAPI.getVaultManager().getVaultPlayer(name).addGroup(group);
   }
}
