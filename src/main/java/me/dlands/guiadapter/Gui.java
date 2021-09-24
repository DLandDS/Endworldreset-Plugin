package me.dlands.guiadapter;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public interface Gui {
    void onClick(InventoryClickEvent event);
    void close(InventoryCloseEvent event);
}
