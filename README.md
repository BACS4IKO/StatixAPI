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
* Переписать билдер Sideboard'ов
* Сделать что-то с GameAPI
* Небольшие изменения в голограммах
* Добавить инструкцию по созданию кликабельных инвенторей в Readme
 
***

## Основная информация
Это достаточтно маштабное апи, которое являеться форком MoonAPI, и включает в себя GameAPI, BukkitAPI, ProtocolLib API (Множество пакетных entity), и многое другое. Разработкой форка сейчас занимаеться ItzStatix, а автор огигинального MoonAPI - ItzStonlex (Который к слову сейчас имеет свою маштабную и обновляемую апишку)

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
        //Повернуть голову и тело FakePlayer в сторону того, кому отправляем NPC
        fakePlayer.look(receiver, receiver.getLocation());
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
**Здесь по идее нужно дальше писать, но мне лень**
