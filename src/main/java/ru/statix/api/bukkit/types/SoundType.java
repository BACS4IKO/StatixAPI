package ru.statix.api.bukkit.types;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * А этот код я вовсе взял с инета
 */
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public enum SoundType {
    CLICK("UI_BUTTON_CLICK", 1.0F, 10.0F),
    DESTROY("ENTITY_ELDER_GUARDIAN_CURSE"),
    NOTE_BASS("BLOCK_NOTE_BASS", 1.0F, 10.F),
    POP("ENTITY_CHICKEN_EGG"),
    NO("ENTITY_VILLAGER_NO"),
    YES("ENTITY_VILLAGER_YES"),
    CHEST_OPEN("BLOCK_CHEST_OPEN"),
    TELEPORT("ENTITY_ENDERMEN_TELEPORT"),
    LEVEL_UP("ENTITY_PLAYER_LEVELUP"),
    SELECTED("BLOCK_ANVIL_USE"),
    POSITIVE("ENTITY_EXPERIENCE_ORB_PICKUP"),
    PICKUP("ENTITY_EXPERIENCE_ORB_PICKUP", 1.0F, 10.F),
    NEGATIVE("ENTITY_BAT_HURT"),
    BREAK("ENTITY_ITEM_BREAK"),
    SLIME("BLOCK_SLIME_STEP"),
    DIG_GRASS("BLOCK_GRASS_BREAK"),
    DIG_STONE("BLOCK_STONE_BREAK"),
    DIG_WOOD("BLOCK_WOOD_BREAK"),
    DIG_GLASS("BLOCK_GLASS_BREAK"),
    DIG_GRAVEL("BLOCK_GRAVEL_BREAK"),
    DIG_SAND("BLOCK_SAND_BREAK"),
    DIG_SNOW("BLOCK_SNOW_BREAK"),
    DIG_WOOL("BLOCK_CLOTH_BREAK"),
    AFK_SOUND("BLOCK_NOTE_PLING", 10.0F, 1.0F),
    CREEPER_DEATH("ENTITY_CREEPER_DEATH"),
    BLAZE_DEATH("ENTITY_BLAZE_DEATH"),
    ENDERMEN_DEATH("ENTITY_ENDERMEN_DEATH"),
    GHAST_DEATH("ENTITY_GHAST_DEATH"),
    IRONGOLEM_DEATH("ENTITY_IRONGOLEM_DEATH"),
    SKELETON_DEATH("ENTITY_SKELETON_DEATH"),
    ZOMBIE_DEATH("ENTITY_ZOMBIE_DEATH"),
    DONKEY_DEATH("ENTITY_DONKEY_DEATH"),
    HORSE_ZOMBIE_DEATH("ENTITY_ZOMBIE_HORSE_DEATH"),
    WITHER_DEATH("ENTITY_WITHER_DEATH"),
    WITHER_SHOOT("ENTITY_WITHER_SHOOT"),
    ENTITY_SHULKER_BULLET_HURT("ENTITY_SHULKER_BULLET_HURT"),
    ZOMBIE_PIG_ANGRY("ENTITY_ZOMBIE_PIG_ANGRY", 10.0f, 1.0f),
    EXPLODE("ENTITY_GENERIC_EXPLODE"),
    ZOMBIE_WOOD("ENTITY_ZOMBIE_ATTACK_DOOR_WOOD"),
    FIREWORK_LAUNCH("ENTITY_FIREWORK_LAUNCH"),
    SPIDER_IDLE("ENTITY_SPIDER_AMBIENT"),
    WOLF_GROWL("ENTITY_WOLF_GROWL"),
    SHOOT_ARROW("ENTITY_ARROW_SHOOT"),
    VILLAGER_HIT("ENTITY_VILLAGER_HURT"),
    BAT_DEATH("ENTITY_BAT_DEATH"),
    EAT("ENTITY_GENERIC_EAT"),
    HORSE_ARMOR("ENTITY_HORSE_ARMOR"),
    ITEM_PICKUP("ENTITY_ITEM_PICKUP"),
    FIREWORK_BLAST("ENTITY_FIREWORK_BLAST"),
    CREEPER_HISS("ENTITY_CREEPER_HURT"),
    STEP_GRASS("BLOCK_GRASS_STEP"),
    PIG_IDLE("ENTITY_PIG_AMBIENT"),
    FIREWORK_BLAST2("ENTITY_FIREWORK_BLAST_FAR"),
    LAVA("BLOCK_LAVA_POP"),
    TOTEM_OF_UNDYING("ITEM_TOTEM_USE"),
    ENTITY_FIREWORK_TWINKLE("ENTITY_FIREWORK_TWINKLE"),
    ENTITY_ILLUSION_VILLAGER_PREPARE_BLINDNESS("ENTITY_ILLUSION_VILLAGER_PREPARE_BLINDNESS"),
    ENTITY_CHICKEN_HURT("ENTITY_CHICKEN_HURT"),
    ;

    private final String soundName;

    private float volume = 1.0F;
    private float pitch = 1.0F;
}