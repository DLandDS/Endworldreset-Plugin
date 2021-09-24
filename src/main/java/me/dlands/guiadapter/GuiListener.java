package me.dlands.guiadapter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;


public class GuiListener implements Listener {

    public GuiListener() {
        GuiAdapter.init();
    }

    //Menu
    @EventHandler
    public static void onClickGUI(InventoryClickEvent e){
        Player player = (Player) e.getView().getPlayer();
        if(GuiAdapter.has(player)){
            Gui gui = GuiAdapter.getSession(player);
            gui.onClick(e);
            e.setCancelled(true);
        }
    }

    @EventHandler
    public static void onClose(InventoryCloseEvent e){
        Player player = (Player) e.getView().getPlayer();
        if(GuiAdapter.has(player)){
            Gui gui = GuiAdapter.getSession(player);
            gui.close(e);
        }
    }
}
