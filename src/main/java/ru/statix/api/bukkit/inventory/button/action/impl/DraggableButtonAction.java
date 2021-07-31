package ru.statix.api.bukkit.inventory.button.action.impl;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import ru.statix.api.bukkit.inventory.button.BaseInventoryButton;
import ru.statix.api.bukkit.inventory.button.action.EnumButtonAction;


public interface DraggableButtonAction extends BaseInventoryButton.IInventoryButtonAction {

    /**
     * Произвести действие кнопки на предмет
     *
     * @param player - игрок
     * @param event  - ивент
     */
    void buttonDrag(Player player, InventoryDragEvent event);


    @Override
    default void buttonAction(Player player, EnumButtonAction buttonAction, InventoryInteractEvent event) {
        buttonDrag(player, (InventoryDragEvent) event);
    }

}