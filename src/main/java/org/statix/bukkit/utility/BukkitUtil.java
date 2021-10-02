package org.statix.bukkit.utility;

import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.statix.bukkit.StatixAPI;

@UtilityClass
public class BukkitUtil {

    public void callEvent(Event event) {
        Bukkit.getPluginManager().callEvent(event);
    }

    public  void runTaskLater(long delay, Runnable runnable){
        Bukkit.getScheduler().runTaskLater(StatixAPI.getInstance(), runnable, delay);
    }

    public void runTaskLaterAsync(long delay, Runnable runnable){
        Bukkit.getScheduler().runTaskLaterAsynchronously(StatixAPI.getInstance(), runnable, delay);
    }

    public void runTask(Runnable runnable){
        Bukkit.getScheduler().runTask(StatixAPI.getInstance(), runnable);
    }

    public void runTaskAsync(Runnable runnable){
        Bukkit.getScheduler().runTaskAsynchronously(StatixAPI.getInstance(), runnable);
    }

}
