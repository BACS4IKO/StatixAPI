package org.statix.bukkit.protocollib.entity;

import lombok.NonNull;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.statix.bukkit.protocollib.entity.animation.FakeEntityAnimation;

// по моему это со старой стонлекс апи, ну ладно, хоть работает - и то хорошо =)
public interface FakeEntityLiving
        extends FakeEntity {


    void playAnimationAll(@NonNull FakeEntityAnimation fakeEntityAnimation);

    void playAnimation(@NonNull FakeEntityAnimation fakeEntityAnimation, @NonNull Player player);


    void setArrowCount(int arrowCount);

    void setHealthScale(float healthScale);

    void setAmbientPotionEffect(boolean ambientPotionEffect);

    void setPotionEffectColor(@NonNull ChatColor potionEffectColor);


    ChatColor getPotionEffectColor();

    boolean isAmbientPotionEffect();

    float getHealthScale();

    int getArrowCount();
}
