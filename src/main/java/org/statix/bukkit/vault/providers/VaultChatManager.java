package org.statix.bukkit.vault.providers;

import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.RegisteredServiceProvider;

public final class VaultChatManager {
   private Chat vaultChat;
   private static final String NO_INIT_MESSAGE;

   public VaultChatManager() {
      RegisteredServiceProvider<Chat> serviceProvider = Bukkit.getServicesManager().getRegistration(Chat.class);
      if (serviceProvider != null && serviceProvider.getProvider() != null) {
         this.vaultChat = (Chat)serviceProvider.getProvider();
         if (this.vaultChat == null) {
            Bukkit.getConsoleSender().sendMessage(NO_INIT_MESSAGE);
         }

      } else {
         Bukkit.getConsoleSender().sendMessage(NO_INIT_MESSAGE);
      }
   }

   public Chat getVaultChat() {
      return this.vaultChat;
   }

   static {
      NO_INIT_MESSAGE = ChatColor.RED + "Не удалось инициализировать VaultChat.";
   }
}
