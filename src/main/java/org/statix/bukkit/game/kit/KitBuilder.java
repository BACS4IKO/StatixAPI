package org.statix.bukkit.game.kit;

import lombok.AllArgsConstructor;
import org.bukkit.inventory.ItemStack;
import org.statix.base.interfaces.Builder;
import org.statix.bukkit.StatixAPI;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public final class KitBuilder implements Builder<StatixKit> {

    private String kitName;

    private KitInfo kitInfo;

    /**
     * Установить имя набору
     */
    public KitBuilder setKitName(String kitName) {
        this.kitName = kitName;

        return this;
    }

    /**
     * Установить набору предмет шлема
     */
    public KitBuilder setHelmetItem(ItemStack itemStack) {
        kitInfo.setHelmetItem(itemStack);
        
        return this;
    }

    /**
     * Установить набору предмет нагрудника
     */
    public KitBuilder setChestplateItem(ItemStack itemStack) {
        kitInfo.setChestplateItem(itemStack);

        return this;
    }

    /**
     * Установить набору предмет штанов
     */
    public KitBuilder setLeggingsItem(ItemStack itemStack) {
        kitInfo.setLeggingsItem(itemStack);

        return this;
    }

    /**
     * Установить набору предмет ботинок
     */
    public KitBuilder setBootsItem(ItemStack itemStack) {
        kitInfo.setBootsItem(itemStack);

        return this;
    }

    /**
     * Установка права для набора
     */
    public KitBuilder setPermission(String permission) {
        kitInfo.setPermission(permission);

        return this;
    }

    /**
     * Установка цены набора
     */
    public KitBuilder setPrice(int price) {
        kitInfo.setPrice(price);

        return this;
    }

    /**
     * Добаление прелмета в набор
     */
    public KitBuilder addItem(ItemStack itemStack) {
        if (kitInfo.getItemList() == null) {
            kitInfo.setItemList(new ArrayList<>());
        }
        
        kitInfo.getItemList().add(itemStack);

        return this;
    }

    /**
     * Установка списка предметов в наборе
     */
    public KitBuilder setItemList(List<ItemStack> itemStackList) {
        if (kitInfo.getItemList() == null) {
            kitInfo.setItemList(new ArrayList<>());
        }
        
        kitInfo.setItemList(itemStackList);

        return this;
    }

    /**
     * Создание и кеширование набора
     */
    @Override
    public StatixKit build() {
        StatixKit statixKit = new StatixKit(kitName, kitInfo);

        StatixAPI.getGameAPI().getKitManager()
                .cacheKit(kitName.toLowerCase(), statixKit);

        return statixKit;
    }
}
