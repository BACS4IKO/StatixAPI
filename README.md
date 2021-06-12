# StatixAPI

***
## Обратная связь

* [**Discord**](https://discord.gg/SWZ2cPGnBT)
* [**Telegram**](https://t.me/ItzStatix)
* [**ВКонтакте**](https://vk.com/ItzStatix)


***

## Основная информация
Это достаточтно маштабное апи, которое являеться форком MoonAPI, и включает в себя GameAPI, BukkitAPI, ProtocolLib API (Множество пакетных entity), и многое другое. Разработкой форка сейчас занимаеться ItzStatix, а автор огигинального MoonAPI - ItzStonlex (Который к слову сейчас имеет свою маштабную и обновляемую апишку)

***
## StatixBukkitApi
***
### `Vault-API:`

**StatixAPI** позволяет узнавать различные данные о игроке из Vault, а также делать с ними различные действия, например изменить баланс или провести какие-то действия с балансом или правами игрока.
 
Совсем недавно в классе `ru.statix.api.bukkit.modules.vault.VaultBase` все упросил до упора:
 
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
* Здесь показано далеко не все возможности Vault-API, подробнее в `ru.statix.api.bukkit.modules.vault.VaultBase`
### `ProtocolLib-API:`

***
- `FakeEntity`:

**StatixAPI** позволяет создавать различных entity на пакетах:

Сейчас на примере `ru.statix.api.bukkit.modules.protocol.entity.impl.FakePlayer` мы попытаемся в этом убедиться:
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
**Здесь по идее нужно дальше писать, но мне лень**
