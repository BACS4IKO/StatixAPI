package org.statix.example;

import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.statix.base.localization.Localization;
import org.statix.base.utility.DateUtil;
import org.statix.bukkit.StatixAPI;
import org.statix.bukkit.protocollib.entity.impl.FakePlayer;
import org.statix.bukkit.scoreboard.BaseScoreboardBuilder;
import org.statix.bukkit.scoreboard.BaseScoreboardScope;
import org.statix.bukkit.utility.ItemUtil;
import org.statix.bukkit.utility.LocationUtil;

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
     * Создание NPC при помощи пакетов
     */
    public void spawnNPC(Player receiver, Location location) {
        FakePlayer fakePlayer = new FakePlayer("ItzStatix", location);

        fakePlayer.look(receiver, receiver.getLocation()); //Повернуть голову и тело FakePlayer в сторону того, кому отправляем NPC
        fakePlayer.setGlowingColor(ChatColor.AQUA); //Создать подсветку вокруг FakePlayer определенного цвета
        fakePlayer.setSneaking(true); //Встать на SHIFT
        fakePlayer.setBurning(true); //Зажечь NPC
        fakePlayer.setInvisible(false); //Сделать видимым
        fakePlayer.enableAutoLooking(10.5); // Сделать чтобы наблюдал за всеми в радиусе 10.5 блоков
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
