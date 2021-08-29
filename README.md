# StatixAPI

***
## Обратная связь

* [**Discord**](https://discord.gg/SWZ2cPGnBT)
* [**Telegram**](https://t.me/ItzStatix)
* [**ВКонтакте**](https://vk.com/ItzStatix)

## Инструкция по использованию API / How to use this API
* [**Wiki (EN)**](https://github.com/ItzStatix/StatixAPI/wiki)
* [**README.md (RU)**](https://github.com/ItzStatix/StatixAPI/blob/master/README.md)

## Планы на API
* Переписать кривой ServerPing
* Сделать что-то с GameAPI
* Переписать голограммы, т.к. сейчас они меня не особо устраивают
* Добавить инструкцию по созданию кликабельных инвенторей в Readme
 
***

## Основная информация
Это апи прекрасно подойдет как основа для апи вашей сети..

Это достаточтно маштабное апи, которое являеться форком MoonAPI, и включает в себя GameAPI, BukkitAPI, ProtocolLib API (Множество пакетных entity), и многое другое. Разработкой форка сейчас занимаеться ItzStatix, а автор огигинального MoonAPI - ItzStonlex (Который к слову сейчас имеет свою маштабную и обновляемую апишку).

Множество фич я как раз и взял у Миши, с его позволения, ссылка на его API - https://github.com/ItzStonlex/StonlexAPI

***
## StatixBukkitApi
***
### `Vault-API:`
***
Больше не нужно в каждом своем плагине инициализировать VaultEconomy и VaultChat, StatixAPI это может вам упростить! Сейчас я вам продемонстрирую что можно сделать с игроком через это API
 
Доступ к VaultAPI (VaultPlayer) можно получить тремя способами, сейчас мы рассмотрим их все:

### 1 | Через класс StatixAPI
**StatixAPI** имеет главный класс, через который происходит большая часть менеджмента над StatixBukkitAPI - `ru.statix.api.bukkit.StatixAPI`, и обратиться к VaultManager можно тоже через него, например:
```java
StatixAPI.getVaultManager().getVaultPlayer(player.getName()).getPrefix();
```
Вы не хотите каждый раз писать это большое нагромождение? - Можно сделать ЕЩЁ ПРОЩЕ:
```java
VaultPlayer vp = StatixAPI.getVaultManager().getVaultPlayer(player.getName()); // Получаем VaultPlayer через главный класс API
vp.giveMoney(10); // Выдать 10 монет игроку
vp.addPermission("statixapi.test"); // Добавить право игроку
// Это далеко не все функции VaultPlayer, подробнее в классе: ru.statix.api.bukkit.vault.VaultPlayer
```
### 2 | Напрямую через VaultManager
It is not necessary to refer to the StatixAPI class, you can do this directly through the VaultManager:
```java
VaultPlayer vp = new VaultManager().getVaultPlayer(player); // Получаем VaultPlayer через главный VaultManager
vp.addGroup("default"); // Добавляем игроку группу default
// Это далеко не все функции VaultPlayer, подробнее в классе: ru.statix.api.bukkit.vault.VaultPlayer
```
### 3 | Самый простой, через BaseVault
You don't have to go to the VaultManager and get the VaultPlayer type, you can make it even easier!:
```java
BaseVault bv = new BaseVault(); // Получам BaseVault
bv.addGroup(player.getName(), "default"); // Добавляем игроку группу default
// Это далеко не все функции VaultPlayer, подробнее в классе: ru.statix.api.bukkit.vault.BaseVault
```

***
### `Commands:`

Теперь создавать, регистрировать и использовать команды стало куда проще! В данной разработке доступна реализации как для мелких, так и для больших команд, которые содержат огромное количество данных, алиасов и подкоманд.

Для начала создадим обычную команду при помощи `BaseCommand`, использовать которую может **ТОЛЬКО** игрок:
```java
public class TestCommand extends BaseCommand<Player> {

    public TestCommand() {
        super("example", "testing");
    }
    
    @Override
    protected void onExecute(Player player, String[] args) {
        player.sendMessage(ChatColor.GREEN + "Лол, это работает, открываю инвентарь :)");
        new TestPagedMenu().openInventory(player); //Открыть инвентарь игроку
    }

}
```

Ранее я говорил о создании больших команд, которые помогут сэкономить множество проверок и большого количества кода. Речь шла о `BaseMegaCommand`:
```java
public class MegaTestCommand
        extends BaseMegaCommand<Player> {

    public MegaTestCommand() {
        super("megatest", "megatesting");
    }

    @Override
    protected void onUsage(Player player) {
        player.sendMessage("Список доступных подкоманд:");
        player.sendMessage(" - /megatest online");
        player.sendMessage(" - /megatest broadcast <сообщение>");
    }

    @CommandArgument(aliases = "players")
    protected void online(Player player, String[] args) {
        int playersCount = Bukkit.getOnlinePlayers().size();

        String onlinePlayers = Joiner.on(", ").join(Bukkit.getOnlinePlayers().stream().map(Player::getDisplayName).collect(Collectors.toSet()));

        player.sendMessage(String.format("Сейчас на сервере (%s): %s", playersCount, onlinePlayers));
    }

    @CommandArgument
    protected void broadcast(Player player, String[] args) {

        String broadcastMessage = ChatColor.translateAlternateColorCodes('&', Joiner.on(" ").join(args));

        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            onlinePlayer.sendMessage("MegaTest Broadcast > " + broadcastMessage);
        }
    }

}
```
Каждый метод - это отдельная подкоманда, а аннотация `@CommandArgument` обозначает нужный метод подкомандой и избавит Вас от костылей, duplicated-кода, создав для определенной подкоманды указанные алиасы.

Весь менеджмент над Bukkit API происходит через один класс - `ru.statix.api.bukkit.StatixAPI`

Исходя из этого, регистрация команд происходит тоже через этот класс:
```javascript
StatixAPI.registerCommand(new ExamplePlayerCommand());
```

***
### `ProtocolLib-API:`

***
- `FakeEntity`:

**StatixAPI** позволяет создавать различных entity на пакетах:

Сейчас на примере `ru.statix.api.bukkit.protocollib.entity.impl.FakePlayer` мы попытаемся в этом убедиться:
```java
FakePlayer fakePlayer = new FakePlayer("ItzStatix", location);
        //Следить за игроком
        fakePlayer.look(receiver);
        //Встать на SHIFT
        fakePlayer.setSneaking(true);
        //Зажечь NPC
        fakePlayer.setBurning(true);
        //Сделать видимым
        fakePlayer.setInvisible(false);
        

        fakePlayer.setClickAction(player -> { //Действие при клике на FakePlayer
            player.sendMessage("Удаляю для вас npc, не надо было кликать с:"); //Отправить сообщение игроку, который кликнул по FakePlayer
            fakePlayer.removeReceivers(player); //Скрыть FakePlayer от игрока, который кликнул по нему
        });

        //fakePlayer.spawn(); - Заспавнить NPC для всех (Даже тех кто еще не зашел)
        fakePlayer.addReceivers(receiver); //Отправить FakePlayer игроку(-ам)
```
***
- `Holograms`:

### У нас уже давно другая система голограмм, но переписывать это лень
**StatixAPI** позволяет создавать пакетные голограммы, как для всех игроков, так и только для определенных, сейчас на примере тестовой голограммы я это вам продемонструрую:
```java
        StatixAPI.getHologramManager().createHologram("DonateHologram", LocationUtil.stringToLocation(Main.getInstance().getConfig().getString("holo")), hologram -> {
            //Создаем саму голограмму
            hologram.addLine("§f§lCloute§b§lPlay");
            hologram.addCleanLine(); //Пустая строка
            hologram.addLine("§fЭто простая голограмма, созданная");
            hologram.addLine("§fпри помощи §eStatixAPI§f, и призванная");
            hologram.addLine("§fуговорить тебя подписаться на нашу");
            hologram.addLine("§fгруппу §bВКонтакте");
            hologram.addCleanLine(); //Пустая строка
            hologram.addLine("§a§l> §aНажмите, для получения ссылки");

            hologram.setClickAction(player -> { //Действие при клике на голограмму
                player.sendMessage("§7§l(§b§li§7§l) §fНаша группа - §bhttps://vk.com/clouteplay"); //Отправить сообщение игроку
            });
            hologram.spawn(); //Спавним голограмму для всех в онлайне
        });
```
![image](https://user-images.githubusercontent.com/81032650/127160796-487264ad-8436-4f51-8c41-077032529e8b.png)


Все голограммы кешируються, и если вы вдруг хотите сделать какое-то действие с голограммой за пределами метода с ней, можно воспользоваться **getCachedHologram**, для примера сейчас мы заспавним голограмму игроку, у которого её нету:
```java
//Вместо "DonateHologram" вы можете поставить своё название
StatixAPI.getHologramManager().getCachedHologram("DonateHologram").addReceiver(p);
```
***
- `FakeTeams-API`:

**StonlexAPI** также позволяет создавать пакетные Team'ы с полной кастомизацией и настройкой.

Рассмотрим простейший пример, где при заходе игрока в TabList и над головой будем создавать префикс и суффикс:
```java
@EventHandler
public void onPlayerJoin(PlayerJoinEvent event) {
    Player player = event.getPlayer();

    ProtocolTeam protocolTeam = ProtocolTeam.get("Team_" + player.getName());
    
    protocolTeam.setPrefix("§e§lPREFIX §f");
    protocolTeam.setSuffix(" §6§lSUFFIX");
    
    // ...
}
```

После создания Team'ы, в нее необходимо добавить игроков, которым она будет принадлежать. Делается это через следующий метод:
```java
protocolTeam.addPlayerEntry(player);
```

После чего можно будет получать ProtocolTeam игрока по следующему методу:
```java
ProtocolTeam protocolTeam = ProtocolTeam.findEntry(player);
```

Так как ProtocolTeam является пакетным классом, следовательно, просто так он никому не будет виден (Кроме тех игроков, которым принадлежит Team). Это можно исправить двумя способами:

**Способ 1**: Добавление ProtocolTeam в список авто-отрисовки всем игрокам онлайн, а также только зашедшим:
```java
protocolTeam.addAutoReceived();
```
**Способ 2**: Отдельное добавление игроков в список тех, кто может видеть ProtocolTeam:
```java
protocolTeam.addReceiver(player);
```

**Дополнение к Способу 2**: После чего можно будет получать ProtocolTeam игрока по следующему методу:
```java
ProtocolTeam protocolTeam = ProtocolTeam.findReceiver(player);
```

![ProtocolTeam](https://sun9-22.userapi.com/impg/rrg-k64fGpj-D3jCToMFrFTCdws2Q7hiOI3oqQ/W_tJlT2Uicg.jpg?size=559x283&quality=96&sign=e89e25a39621f59022fe4cbece4ee4d2&type=album)

***
### `Protocol Packets-API`:

Создание пакетов происходит через фабрику `ru.statix.api.bukkit.protocollib.packet.ProtocolPacketFactory`

Рассмотрим на примере пакета воспроизведения анимации "маха" рукой игрока

```java
WrapperPlayServerAnimation animationPacket 
                = ProtocolPacketFactory.createAnimationPacket(player.getEntityId(), FakeEntityAnimation.SWING_MAIN_HAND.ordinal());
```

Пакет можно отправить как одному игроку отдельно:
```java
animationPacket.sendPacket(player);
```

Так и всем игрокам, что сейчас находятся на сервере:
```java
animationPacket.broadcastPacket();
```


***
### `Scoreboards:`

Теперь буквально в пару строчек можно написать пакетные скорборды, которые не будут конфликтовать с тегами, или другими барами, созданными через **Scoreboard**.

Для начала необходимо создать Builder, по которому будут выстраиваться необходимые данные для Scoreboard:

```java
BaseScoreboardBuilder scoreboardBuilder = StatixAPI.newScoreboardBuilder();
```

Установим прототипную видимость нашему Scoreboard`у:

**Что такое видимость Scoreboard?** - Это категория, по которой определяется, кому и как будет показываться этот интерфейс.

- **PRIVATE** - Для каждого игрока Scoreboard выставляется отдельно. Если позже для игрока был выставлен новый Scoreboard, то предыдущий полностью для него очиститься, предоставив место для нового.
- **PUBLIC** - Scoreboard автоматически выставляется для всех текущих игроков онлайн и для только зашедших. Если позже для игрока будет выставлен Scoreboard типа PROTOTYPE, то PUBLIC на время скроется до того момента, пока PROTOTYPE не будет удален для игрока. Но если еще позже для игрока будет выставлен Scoreboard типа PUBLIC, то предыдущий Scoreboard с типом PUBLIC будет полностью удален и очищен для того же игрока.
```java
scoreboardBuilder.scoreboardScope(BaseScoreboardScope.PRIVATE);
```

Затем, создадим одну из шаблонных анимаций для заголовка Scoreboard - **Flick Animation**:
```java
ScoreboardDisplayFlickAnimation displayFlickAnimation = new ScoreboardDisplayFlickAnimation();

// Поочередно добавляем цвета, которые будут переливаться
displayFlickAnimation.addColor(ChatColor.RED);
displayFlickAnimation.addColor(ChatColor.GOLD);
displayFlickAnimation.addColor(ChatColor.YELLOW);
displayFlickAnimation.addColor(ChatColor.WHITE);

// Устанавливаем текст для анимации
displayFlickAnimation.addTextToAnimation("§lStonlexoBoard");
```

После чего можно установить эту анимацию в заголовок Scoreboard:
```java
scoreboardBuilder.scoreboardDisplay(displayFlickAnimation);
```

Далее, выставляем индексы линий и текст к каждой из них:
```java
scoreboardBuilder.scoreboardLine(6, ChatColor.GRAY + DateUtil.formatPattern(DateUtil.DEFAULT_DATE_PATTERN));
scoreboardBuilder.scoreboardLine(5, "");
scoreboardBuilder.scoreboardLine(4, "Ник: §c...");
scoreboardBuilder.scoreboardLine(3, "Прыжков: §c...");
scoreboardBuilder.scoreboardLine(2, "");
scoreboardBuilder.scoreboardLine(1, "§eLol, ril is work ^)");
```

Вот и все, основные настройки для Scoreboard выставлены!

Теперь для такого скорборда необходимо выставить автообновляющийся таймер, который будет каждые, например, 5 тиков, обновлять текст определенных строчек:
```java
scoreboardBuilder.scoreboardUpdater((baseScoreboard, player1) -> {
    
    baseScoreboard.updateScoreboardLine(4, player1, "Ник: §7" + player1.getName());
    baseScoreboard.updateScoreboardLine(3, player1, "Прыжков: §a" + NumberUtil.spaced(player1.getStatistic(Statistic.JUMP)));

}, 20);
```

Так как наш Scoreboard имеет видимость типа PROTOTYPE, то необходимо будет каждому игроку устанавливать его отдельно.

Делается это следующим образом:
```java
scoreboardBuilder.build().setScoreboardToPlayer(player);
```
Scoreboard создан, полностью настроен и установлен игроку, что еще может быть лучше?
***
**Здесь по идее нужно дальше писать, но мне лень**
