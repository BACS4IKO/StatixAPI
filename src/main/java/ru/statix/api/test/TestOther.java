package ru.statix.api.test;

import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import ru.statix.api.bukkit.modules.protocol.entity.impl.FakePlayer;
import ru.statix.api.bukkit.particle.ParticleEffect;
import ru.statix.api.bukkit.StatixAPI;
import ru.statix.api.test.commands.TestCommand;

import java.util.concurrent.ThreadLocalRandom;

@RequiredArgsConstructor
public class TestOther {

    private final StatixAPI statixAPI;

    /**
     * В данном методе мы создаем Sidebar, или же Scoreboard, или
     * даже просто Board, кому как удобнее это называть :)
     */
    public void setScoreboard(Player player) {

        //Создание и инициализация нужных переменн
        ChatColor[] displayNameColors = new ChatColor[]{ChatColor.AQUA, ChatColor.BLUE};
        ThreadLocalRandom random = ThreadLocalRandom.current();

        //Непосредственно создание самого скорборда
        StatixAPI.getSidebarManager().newBuilder()
                .setDisplayName("§b§lMoonStudio")

                .setLine(2, "§aТестовая строка 1")
                .setLine(1, "§aТестовая строка 2")
                .setLine(0, "§aТестовая строка 3")

                .newUpdater(update -> {
                    ChatColor chatColor = displayNameColors[random.nextInt(displayNameColors.length)];

                    update.setDisplayName(chatColor + "MoonStudio");
                    update.setLine(2, chatColor + "Тестовая строка 1");
                }, 5)

                .showToPlayer(player);
    }


    /**
     * В этом простом методе мы очень легко создаем
     * голограмму, которая может быть видима только 1 игроку, а может и всем.
     */
    public void spawnHologram(Player receiver, Location location, boolean showForAll) {
        StatixAPI.getHologramManager().createHologram("testHologram", location, hologram -> {
            hologram.addLine("§6§lStatixAPI");
            hologram.addLine("");
            hologram.addLine("§7Уникальная разработка, которая являеться форком");
            hologram.addLine("§7ранее популярной StatixAPI");
            hologram.addCleanLine(); //пустая строка
            hologram.addLine("§c§lx §cНажмите, чтобы удалить голограмму.");


            hologram.setClickAction(player -> { //Действие при клике на голограмму
                player.sendMessage("§cКлик по голограмме прошел, удаляем для вас голограмму З:"); //Отправитб сообщение игроку

                hologram.removeReceiver(player);
            });

            //hologram.spawn(); //Спавн голограммы для всех

            hologram.addReceiver(receiver); //Отправляем голграмму игроку
        });
    }


    /**
     * А в этом простеньком методе мы узнаем глобальный онлайн сервера, через
     * пинг
     *
     * UPD: Эта хуйня очень криво работает, и у меня в планах её переписать
     */

    public int getGlobalOnline() {
        return StatixAPI.getMessagingManager()
                .getServer("127.0.0.1", 25565).getPlayersOnline();
    }

    /**
     * Данный метод отправляет игроку сообщение, даже если
     * он находится на другом сервере.
     */
    public void sendMessage(Player player, String text) {
        StatixAPI.getMessagingManager().sendMessage(player.getName(),
                                                     ChatColor.translateAlternateColorCodes('&', text));
    }

    /**
     * MessagingManager - очень интересный и уникальный класс, которому
     * подвласно многое, и даже больше, чем кажется.
     *
     * Теперь без помощи самого BungeeCord и без его API,
     * мы можем спокойно телепортировать игроков по разным
     * серверм Proxy
     */
    public void redirectPlayer(Player player, String serverName) {
        StatixAPI.getMessagingManager().redirectPlayer(player, serverName);
    }

    /**
     * ParticleEffect - Удобный enum-класс с удобной работой
     *  с партиклами и эффектами, с их редактированием и видоизменением.
     */
    public void spawnCircle(Plugin plugin, Player player, Location location) {
        new BukkitRunnable() {
            double t = 0;

            @Override
            public void run() {
                t += Math.PI / 16;

                double x = 2 * Math.cos(t);
                double z = 2 * Math.sin(t);

                ParticleEffect.SPELL.display(0, 0, 0, 0, 3,
                        location.clone().add(x, 0, z), player);
            }
        }.runTaskTimer(plugin, 0, 1);
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
            player.sendMessage("Удаляю для вас голограмму, не надо было кликать с:"); //Отправить сообщение игроку, который кликнул по FakePlayer
            fakePlayer.removeReceiver(player); //Скрыть FakePlayer от игрока, который кликнул по нему
        });

        fakePlayer.addReceiver(receiver); //Отправить FakePlayer игроку
    }

}
