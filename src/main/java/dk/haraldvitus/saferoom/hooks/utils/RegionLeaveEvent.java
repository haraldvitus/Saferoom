package dk.haraldvitus.saferoom.hooks.utils;

import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class RegionLeaveEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();

    @Getter
    private final Player player;

    @Getter
    private final ProtectedRegion region;

    private boolean cancelled;

    public RegionLeaveEvent(Player player, ProtectedRegion region) {
        this.player = player;
        this.region = region;
        this.cancelled = false;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }
}
