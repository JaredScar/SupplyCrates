package com.core.srs.supplycrates.main;

import com.core.srs.supplycrates.api.API;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: TheWolfBadger
 * Date: 8/25/15
 * Time: 10:17 PM
 */
public class Main extends JavaPlugin implements Listener {
    private static Main main;
    private HashMap<UUID, String> pickedCrates = new HashMap<UUID, String>();
    private Random random = new Random();
    private ArrayList<UUID> alreadySelected = new ArrayList<UUID>();
    @Override
    public void onEnable() {
        this.main = this;
        this.getServer().getPluginManager().registerEvents(this, this);
        this.saveDefaultConfig();
    }
    public Random getRandom() {
        return random;
    }
    public HashMap<UUID, String> getPickedCrates() {
        return pickedCrates;
    }
    @Override
    public void onDisable() {}
    public static Main get() {
        return main;
    }
    public ArrayList<UUID> getAlreadySelected() {
        return alreadySelected;
    }
    @EventHandler
    public void onPlayerPlace(BlockPlaceEvent evt) {
        ItemStack placed = evt.getItemInHand();
        if(placed.getType() == Material.CHEST) {
            if(API.checkCrate(placed)) {
                //It's a crate
                evt.setCancelled(true);
                if(placed.getAmount() > 1) {
                    ItemStack stack = placed.clone();
                    stack.setAmount(placed.getAmount()-1);
                    evt.getPlayer().setItemInHand(stack);
                } else {
                    evt.getPlayer().getInventory().remove(placed);
                }
                API.openInventory(evt.getPlayer(), API.getCrate(placed));
            }
        }
    }
    @EventHandler
    public void InventoryClick(InventoryClickEvent evt) {
        Player p = (Player) evt.getWhoClicked();
        Inventory inv = evt.getInventory();
        if(API.isInventory(inv)) {
            evt.setCancelled(true);
            if(!alreadySelected.contains(p.getUniqueId())) {
                if(API.isMysteryCrate(evt.getCurrentItem())) {
                    //Is Mystery Crate, he's selecting one
                    if(pickedCrates.containsKey(p.getUniqueId())) {
                        //Add to existing String
                        String existing = pickedCrates.get(p.getUniqueId());
                        if(!(Main.get().getConfig().getInt("SupplyCrates."+API.getCrateFromTitle(inv.getTitle())+".Picks") == pickedCrates.get(p.getUniqueId()).split(", ").length+1)) {
                        pickedCrates.put(p.getUniqueId(), existing+", "+evt.getSlot());
                        inv.setItem(evt.getSlot(), API.toItemStack(getConfig().getString("Config.RegularGlassPane")));
                            evt.setCancelled(true);
                        } else {
                            pickedCrates.put(p.getUniqueId(), existing+", "+evt.getSlot());
                            inv.setItem(evt.getSlot(), API.toItemStack(getConfig().getString("Config.RegularGlassPane")));
                            evt.setCancelled(true);
                        }
                    } else {
                        pickedCrates.put(p.getUniqueId(), evt.getSlot()+"");
                        inv.setItem(evt.getSlot(), API.toItemStack(getConfig().getString("Config.RegularGlassPane")));
                        evt.setCancelled(true);
                    }
                    if(Main.get().getConfig().getInt("SupplyCrates."+API.getCrateFromTitle(inv.getTitle())+".Picks") == pickedCrates.get(p.getUniqueId()).split(", ").length) {
                        API.animate(inv, p, API.getCrateFromTitle(inv.getTitle()));
                        alreadySelected.add(p.getUniqueId());
                    }
                }
            }
        }
    }
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[]) {
        if(cmd.getName().equalsIgnoreCase("giveCrate") || cmd.getName().equalsIgnoreCase("gc")) {
            if(sender.hasPermission("SupplyCrates.giveCrate")) {
                if(args.length == 2) {
                    if(Bukkit.getPlayer(args[0]) !=null) {
                        if(API.isCrate(args[1])) {
                            ItemStack stack = API.getCrate(args[1]);
                            Bukkit.getPlayer(args[0]).getInventory().addItem(stack);
                            Bukkit.getPlayer(args[0]).sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("Messages.ReceivedCrate").replace("{CRATE}", args[1])));
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou have given the player a crate!"));
                        }
                    }
                }
            }
        }
        return true;
    }
}
