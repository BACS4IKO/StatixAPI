package ru.statix.api.bukkit.vault;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import ru.statix.api.base.types.AbstractCacheManager;
import ru.statix.api.bukkit.vault.providers.VaultChatManager;
import ru.statix.api.bukkit.vault.providers.VaultEconomyManager;
import ru.statix.api.bukkit.vault.providers.VaultPermissionManager;

public final class VaultManager extends AbstractCacheManager<VaultPlayer> {
   private final VaultEconomyManager economyManager = new VaultEconomyManager();
   private final VaultPermissionManager permissionManager = new VaultPermissionManager();
   private final VaultChatManager chatManager = new VaultChatManager();

   public VaultPlayer getVaultPlayer(String playerName) {
      return (VaultPlayer)this.getComputeCache(playerName.toLowerCase(), (x$0) -> {
         return new VaultManager.VaultPlayerImpl(x$0);
      });
   }

   public VaultPlayer getVaultPlayer(Player player) {
      return this.getVaultPlayer(player.getName());
   }

   public VaultEconomyManager getEconomyManager() {
      return this.economyManager;
   }

   public VaultPermissionManager getPermissionManager() {
      return this.permissionManager;
   }

   public VaultChatManager getChatManager() {
      return this.chatManager;
   }

   private class VaultPlayerImpl implements VaultPlayer {
      private final String playerName;
      private final OfflinePlayer offlinePlayer;

      public VaultPlayerImpl(String playerName) {
         this.playerName = playerName;
         this.offlinePlayer = Bukkit.getOfflinePlayer(playerName);
      }

      public String getName() {
         return this.playerName;
      }

      public String getPrefix() {
         return VaultManager.this.chatManager.getVaultChat().getPlayerPrefix((String)null, this.playerName);
      }

      public String getSuffix() {
         return VaultManager.this.chatManager.getVaultChat().getPlayerSuffix((String)null, this.playerName);
      }

      public String getGroupPrefix() {
         return VaultManager.this.chatManager.getVaultChat().getGroupPrefix((String)null, this.getPrimaryGroup());
      }

      public String getGroupSuffix() {
         return VaultManager.this.chatManager.getVaultChat().getGroupSuffix((String)null, this.getPrimaryGroup());
      }

      public String getPrimaryGroup() {
         return VaultManager.this.chatManager.getVaultChat().getPrimaryGroup((String)null, this.playerName);
      }

      public double getBalance() {
         return VaultManager.this.economyManager.getVaultEconomy().getBalance(this.offlinePlayer);
      }

      public void setBalance(double balance) {
         if (balance > this.getBalance()) {
            this.giveMoney(balance - this.getBalance());
         } else if (balance < this.getBalance()) {
            this.takeMoney(this.getBalance() - balance);
         }

      }

      public void giveMoney(double moneyCount) {
         VaultManager.this.economyManager.getVaultEconomy().depositPlayer(this.offlinePlayer, moneyCount);
      }

      public void takeMoney(double moneyCount) {
         VaultManager.this.economyManager.getVaultEconomy().withdrawPlayer(this.offlinePlayer, moneyCount);
      }

      public String[] getGroups() {
         return VaultManager.this.chatManager.getVaultChat().getPlayerGroups((String)null, this.offlinePlayer);
      }

      public void addPermission(String permission) {
         VaultManager.this.permissionManager.getVaultPermission().playerAdd((String)null, this.offlinePlayer, permission);
      }

      public void removePermission(String permission) {
         VaultManager.this.permissionManager.getVaultPermission().playerRemove((String)null, this.offlinePlayer, permission);
      }

      public void addGroup(String group) {
         VaultManager.this.permissionManager.getVaultPermission().groupAdd((String)null, this.playerName, group);
      }

      public void removeGroup(String group) {
         VaultManager.this.permissionManager.getVaultPermission().groupRemove((String)null, this.playerName, group);
      }

      public boolean hasGroup(String group) {
         return VaultManager.this.permissionManager.getVaultPermission().groupHas((String)null, this.playerName, group);
      }

      public boolean hasPermission(String permission) {
         return VaultManager.this.permissionManager.getVaultPermission().playerHas((String)null, this.offlinePlayer, permission);
      }
   }
}
