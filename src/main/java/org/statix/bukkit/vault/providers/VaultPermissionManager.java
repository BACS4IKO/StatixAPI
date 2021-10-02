package org.statix.bukkit.vault.providers;

import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.RegisteredServiceProvider;

public final class VaultPermissionManager {
   private Permission vaultPermission;
   private static final String NO_INIT_MESSAGE;

   public VaultPermissionManager() {
      RegisteredServiceProvider<Permission> serviceProvider = Bukkit.getServicesManager().getRegistration(Permission.class);
      if (serviceProvider != null && serviceProvider.getProvider() != null) {
         this.vaultPermission = (Permission)serviceProvider.getProvider();
         if (this.vaultPermission == null) {
            Bukkit.getConsoleSender().sendMessage(NO_INIT_MESSAGE);
         }

      } else {
         Bukkit.getConsoleSender().sendMessage(NO_INIT_MESSAGE);
      }
   }

   public Permission getVaultPermission() {
      return this.vaultPermission;
   }

   static {
      NO_INIT_MESSAGE = ChatColor.RED + "Не удалось инициализировать VaultPermission.";
   }
}
