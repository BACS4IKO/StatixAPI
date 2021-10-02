package org.statix.bukkit.vault.providers;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.RegisteredServiceProvider;

public final class VaultEconomyManager {
   private Economy vaultEconomy;
   private static final String NO_INIT_MESSAGE;

   public VaultEconomyManager() {
      RegisteredServiceProvider<Economy> serviceProvider = Bukkit.getServicesManager().getRegistration(Economy.class);
      if (serviceProvider != null && serviceProvider.getProvider() != null) {
         this.vaultEconomy = (Economy)serviceProvider.getProvider();
         if (this.vaultEconomy == null) {
            Bukkit.getConsoleSender().sendMessage(NO_INIT_MESSAGE);
         }

      } else {
         Bukkit.getConsoleSender().sendMessage(NO_INIT_MESSAGE);
      }
   }

   public Economy getVaultEconomy() {
      return this.vaultEconomy;
   }

   static {
      NO_INIT_MESSAGE = ChatColor.RED + "Не удалось инициализировать VaultEconomy.";
   }
}
