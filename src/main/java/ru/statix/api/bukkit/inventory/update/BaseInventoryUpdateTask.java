package ru.statix.api.bukkit.inventory.update;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.statix.api.bukkit.inventory.BaseInventory;

@RequiredArgsConstructor
@Getter
public class BaseInventoryUpdateTask {

    private final BaseInventory baseInventory;

    private final long updateTaskDelay;
    private final Runnable inventoryUpdateTask;
}
