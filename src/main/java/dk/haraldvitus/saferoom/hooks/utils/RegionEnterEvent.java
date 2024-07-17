package dk.haraldvitus.saferoom.hooks.utils;

import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class RegionEnterEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();
    @Getter
    private final Player player;
    @Getter
    private final ProtectedRegion region;
    private boolean isCancelled;

    public RegionEnterEvent(Player player, ProtectedRegion region) {
        this.isCancelled = false;
        this.player = player;
        this.region = region;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public boolean isCancelled() {
        return this.isCancelled;
    }

    @Override
    public void setCancelled(boolean isCancelled) {
        this.isCancelled = isCancelled;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }
}