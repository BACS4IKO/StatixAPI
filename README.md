# StatixAPI

***
## Обратная связь

* [**Discord**](https://discord.gg/SWZ2cPGnBT)
* [**Telegram**](https://t.me/ItzStatix)
* [**ВКонтакте**](https://vk.com/ItzStatix)

## Планы на API
* Полностью изменить строение команд (Сделано)
* Переписать кривой ServerPing
* Изменения пока-что не планируються в (Билдере скорбордов и GameAPI)
 
***

## Основная информация
Это достаточтно маштабное апи, которое являеться форком MoonAPI, и включает в себя GameAPI, BukkitAPI, ProtocolLib API (Множество пакетных entity), и многое другое. Разработкой форка сейчас занимаеться ItzStatix, а автор огигинального MoonAPI - ItzStonlex (Который к слову сейчас имеет свою маштабную и обновляемую апишку)

***
## StatixBukkitApi
***
### `Vault-API:`
***
**StatixAPI** позволяет узнавать различные данные о игроке из Vault, а также делать с ними различные действия, например изменить баланс или провести какие-то действия с балансом или правами игрока.
 
Совсем недавно в классе `ru.statix.api.bukkit.vault.VaultBase` все упросил до упора:
 
Узнать баланс игрока
```java
new BaseVault().getBalance("Ник игрока");
```
Получить префикс игрока
```java
new BaseVault().getPrefix("Ник игрока");
```
Получить суффикс (Ну или титул, или вовсе тайтл, кому как удобно) игрока
```java
new BaseVault().getSuffix("Ник игрока");
```
**Совет от автора** Можно сделать еще проще и удобнее:
```java
BaseVault vault = new BaseVault();
   vault.giveMoney("Ник игрока", 3); //Добавить монет игроку
   vault.addGroup("Ник игрока", "admin"); //Установить группу игроку
   vault.removePerm("Ник игрока", "statixplayer.setup"); //Удалить право у игрока
```
* Здесь показано далеко не все возможности Vault-API, подробнее в `ru.statix.api.bukkit.vault.VaultBase`
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
            fakePlayer.removeReceiver(player); //Скрыть FakePlayer от игрока, который кликнул по нему
        });

        fakePlayer.addReceiver(receiver); //Отправить FakePlayer игроку
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
