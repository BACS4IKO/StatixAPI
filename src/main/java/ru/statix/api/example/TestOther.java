package ru.statix.api.example;

import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import ru.statix.api.base.localization.Localization;
import ru.statix.api.base.utility.DateUtil;
import ru.statix.api.base.utility.NumberUtil;
import ru.statix.api.bukkit.holographic.ProtocolHolographic;
import ru.statix.api.bukkit.protocollib.entity.impl.FakePlayer;
import ru.statix.api.bukkit.StatixAPI;
import ru.statix.api.bukkit.scoreboard.BaseScoreboardBuilder;
import ru.statix.api.bukkit.scoreboard.BaseScoreboardScope;
import ru.statix.api.bukkit.utility.LocationUtil;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;

@RequiredArgsConstructor
public class TestOther {

    private final StatixAPI statixAPI;
    /**
     * В данном методе мы создаем Sidebar, или же Scoreboard, или
     * даже просто Board, кому как удобнее это называть :)
     */
    public void setScoreboard(Player player) {
        BaseScoreboardBuilder scoreboardBuilder = StatixAPI.newScoreboardBuilder();
        scoreboardBuilder.scoreboardScope(BaseScoreboardScope.PRIVATE); // PRIVATE, PUBLIC

        scoreboardBuilder.scoreboardDisplay("§6§lItzStatix");
        scoreboardBuilder.scoreboardLine(6, ChatColor.GRAY + DateUtil.formatPattern(DateUtil.DEFAULT_DATE_PATTERN));
        scoreboardBuilder.scoreboardLine(5, "");
        scoreboardBuilder.scoreboardLine(4, "§fРазработчики: §eStatix, Stonlex");
        scoreboardBuilder.scoreboardLine(3, "§fОнлайн: §e" + Bukkit.getOnlinePlayers().size());
        scoreboardBuilder.scoreboardLine(2, "");
        scoreboardBuilder.scoreboardLine(1, "§evk.com/itzstatix");
        scoreboardBuilder.scoreboardUpdater((baseScoreboard, player1) -> {

            baseScoreboard.updateScoreboardLine(3, player1, "§fОнлайн: §e" + Bukkit.getOnlinePlayers().size());

        }, 20);
        scoreboardBuilder.build().setScoreboardToPlayer(player);

    }


    /**
     * В этом простом методе мы очень легко создаем
     * голограмму, которая может быть видима только 1 игроку, а может и всем.
     */
    public void spawnHologram(Player receiver, Location location, boolean showForAll) {
        ProtocolHolographic protocolHolographic
                = StatixAPI.createHologram(location);
// Создание кликабельных голограмм
        Consumer<Player> playerConsumer = player -> { //player = игрок, который кликнул

            player.sendMessage(ChatColor.GOLD + "Клик по голограмме прошел успешно!");
            player.sendMessage(ChatColor.GOLD + "Локация: " + LocationUtil.locationToString(protocolHolographic.getLocation()));
        };

// Добавление строк в голограмму
        protocolHolographic.addClickLine(ChatColor.YELLOW + "WOW! THIS IS PROTOCOL HOLO", playerConsumer);
        protocolHolographic.setUpdater(10, protocolHolographic.getHolographicUpdater());
    }


    /**
     * Создание NPC при помощи пакетов
     */
    public void spawnNPC(Player receiver, Location location) {
        FakePlayer fakePlayer = new FakePlayer("ItzStatix", location);

        fakePlayer.look(receiver, receiver.getLocation()); //Повернуть голову и тело FakePlayer в сторону того, кому отправляем NPC
        fakePlayer.setGlowingColor(ChatColor.AQUA); //Создать подсветку вокруг FakePlayer определенного цвета
        fakePlayer.setSneaking(true); //Встать на SHIFT
        fakePlayer.setBurning(true); //Зажечь NPC
        fakePlayer.setInvisible(false); //Сделать видимым

        fakePlayer.setClickAction(player -> { //Действие при клике на FakePlayer
            player.sendMessage("Удаляю для вас NPC, не надо было кликать с:"); //Отправить сообщение игроку, который кликнул по FakePlayer
            fakePlayer.removeReceivers(player); //Скрыть FakePlayer от игрока, который кликнул по нему
        });

        fakePlayer.addReceivers(receiver); //Отправить FakePlayer игроку
        //fakePlayer.spawn(); - Заспавнить для всех
    }

    /**
     * Пример использования локализации
     *
     * @param p - игрок
     * @param key - ключ локализации
     */
    public void sendLangMsg(Player p, String key) {
        p.sendMessage(Localization.getString(0, key));
    }

}
