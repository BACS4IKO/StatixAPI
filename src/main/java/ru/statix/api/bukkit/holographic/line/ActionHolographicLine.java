package ru.statix.api.bukkit.holographic.line;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import ru.statix.api.bukkit.holographic.ProtocolHolographic;
import ru.statix.api.bukkit.holographic.ProtocolHolographicLine;
import ru.statix.api.bukkit.protocollib.entity.impl.FakeArmorStand;
import ru.statix.api.bukkit.protocollib.entity.impl.FakeSilverfish;

import java.util.function.Consumer;
// по идее будет лучше работать, нахуй нам этот ваш сильверфиш
@Getter
public class ActionHolographicLine implements ProtocolHolographicLine {

    protected final int lineIndex;

    protected final ProtocolHolographic holographic;

    @Setter
    private Consumer<Player> clickAction;

    protected Location location;


    @Setter
    protected String lineText;


    @Setter
    private FakeArmorStand fakeArmorStand;


    public ActionHolographicLine(int lineIndex, String lineText, ProtocolHolographic holographic, Consumer<Player> clickAction) {
        this.lineIndex = lineIndex;
        this.clickAction = clickAction;
        this.holographic = holographic;
        this.lineText = lineText;

        this.location = holographic.getLocation();
    }


    @Override
    public void initialize() {
        //armor stand holographic
        setFakeArmorStand(new FakeArmorStand(getLocation().clone().add(0, -(0.25 * lineIndex), 0)));

        fakeArmorStand.setInvisible(true);
        fakeArmorStand.setBasePlate(false);
        fakeArmorStand.setCustomNameVisible(true);
        fakeArmorStand.setCustomName(lineText);
        fakeArmorStand.setClickAction(clickAction);
    }

    @Override
    public void update() {
        fakeArmorStand.setCustomName(lineText);
        fakeArmorStand.setClickAction(clickAction);
    }

    @Override
    public boolean hasReceiver(@NonNull Player player) {
        return fakeArmorStand.hasReceiver(player);
    }

    @Override
    public void addReceivers(@NonNull Player... receivers) {
        fakeArmorStand.addReceivers(receivers);
    }

    @Override
    public void removeReceivers(@NonNull Player... receivers) {
        fakeArmorStand.removeReceivers(receivers);
    }

    @Override
    public boolean hasViewer(@NonNull Player player) {
        return fakeArmorStand.hasViewer(player);
    }

    @Override
    public void addViewers(@NonNull Player... viewers) {
        fakeArmorStand.addViewers(viewers);
    }

    @Override
    public void removeViewers(@NonNull Player... viewers) {
        fakeArmorStand.removeViewers(viewers);
    }

    @Override
    public void spawn() {
        fakeArmorStand.spawn();
    }

    @Override
    public void remove() {
        fakeArmorStand.remove();
    }

    @Override
    public void teleport(Location location) {
        this.location = location.clone().add(0, -(0.25 * lineIndex), 0);

        fakeArmorStand.teleport(this.location);
    }

}
