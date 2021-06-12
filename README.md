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
