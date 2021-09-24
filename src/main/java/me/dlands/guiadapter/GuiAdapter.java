package me.dlands.guiadapter;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

public abstract class GuiAdapter implements Gui {
    private String name;
    private Inventory gui;
    private Player player;
    private static int size;
    private static HashMap<Player, GuiAdapter> session;

    public GuiAdapter(Player player) {
        if(session.containsKey(player)){
            session.replace(player, this);
        } else {
            session.put(player, this);
        }
        this.player = player;
    }

    public static void init(){
        if(session == null){
            session = new HashMap<>();
        }
    }

    public static GuiAdapter getSession(Player player){
        return session.get(player);
    }

    public void open(){
        gui = Bukkit.createInventory(player, size, name);
        build();
        player.openInventory(gui);
    }

    @Override
    public void close(InventoryCloseEvent event) {
        session.remove(event.getPlayer());
    }

    static public boolean has(Player player){
        return session.containsKey(player);
    }

    public abstract void build();

    public void setSize(int size) {
        GuiAdapter.size = size;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Inventory getGui() {
        return gui;
    }
    public Player getPlayer() {
        return player;
    }

    public abstract static class Elements {

        public static ItemStack createItem(String name, Material material){
            ItemStack item = new ItemStack(material);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(name);
            item.setItemMeta(meta);
            return item;
        }

        public static ItemStack getPlayerHead(String playerName){
            boolean isNewVersion = Arrays.stream(Material.values()).map(Material::name)
                    .collect(Collectors.toList()).contains("PLAYER_HEAD");
            Material type = Material.matchMaterial(isNewVersion? "PLAYER_HEAD" : "SKULL_ITEM");
            ItemStack item = new ItemStack(type, 1);
            if(!isNewVersion){
                item.setDurability((short) 3);
            }

            SkullMeta meta = (SkullMeta) item.getItemMeta();
            meta.setOwner(playerName);
            meta.setDisplayName(ChatColor.WHITE + playerName);
            String[] lore = {"Teleport to this player"};
            meta.setLore(Arrays.asList(lore));
            item.setItemMeta(meta);
            return item;
        }

    }

}
