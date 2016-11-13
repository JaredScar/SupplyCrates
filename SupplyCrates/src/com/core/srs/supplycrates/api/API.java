package com.core.srs.supplycrates.api;

import com.core.srs.supplycrates.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: TheWolfBadger
 * Date: 8/25/15
 * Time: 10:18 PM
 */
public class API {
    public static boolean checkCrate(ItemStack item) {
        List<String> crates = Main.get().getConfig().getStringList("SupplyCrates.Crates");
        if(item !=null) {
            if(item.getType() == Material.CHEST) {
                if(item.hasItemMeta()) {
                    for(String crate : crates) {
                        if(item.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', Main.get().getConfig().getString("SupplyCrates."+crate+".DisplayName")))) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    public static String getCrate(ItemStack item) {
        List<String> crates = Main.get().getConfig().getStringList("SupplyCrates.Crates");
        if(item !=null) {
            if(item.getType() == Material.CHEST) {
                if(item.hasItemMeta()) {
                    for(String crate : crates) {
                        if(item.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', Main.get().getConfig().getString("SupplyCrates."+crate+".DisplayName")))) {
                            return crate;
                        }
                    }
                }
            }
        }
        return "";
    }
    public static boolean isInventory(Inventory inv) {
        List<String> crates = Main.get().getConfig().getStringList("SupplyCrates.Crates");
        for(String crate : crates) {
            if(inv.getTitle().equals(ChatColor.translateAlternateColorCodes('&', Main.get().getConfig().getString("SupplyCrates." + crate + ".Title")))) {
                return true;
            }
        }
        return false;
    }
    public static String getCrateFromTitle(String title) {
        List<String> crates = Main.get().getConfig().getStringList("SupplyCrates.Crates");
        for(String crate : crates) {
            if(title.equals(ChatColor.translateAlternateColorCodes('&', Main.get().getConfig().getString("SupplyCrates." + crate + ".Title")))) {
                return crate;
            }
        }
        return "";
    }
    public static void openInventory(Player p, String crate) {
        Inventory inv = Bukkit.createInventory(p, Main.get().getConfig().getInt("SupplyCrates." + crate + ".ChestSize"), ChatColor.translateAlternateColorCodes('&', Main.get().getConfig().getString("SupplyCrates."+crate+".Title")));
        String displayName = Main.get().getConfig().getString("Config.DisplayName");
        List<String> lores = Main.get().getConfig().getStringList("Config.Lores");
        Material mat = Material.valueOf(Main.get().getConfig().getString("Config.Material"));
        ItemStack stack = new ItemStack(mat, 1);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
        ArrayList<String> list = new ArrayList<String>();
        if(!lores.isEmpty()) {
            for(String lore : lores) {
                list.add(ChatColor.translateAlternateColorCodes('&', lore));
            }
            meta.setLore(list);
        }
        stack.setItemMeta(meta);
        for(int i=0; i<inv.getSize(); i++) {
            inv.setItem(i, stack);
        }
        p.openInventory(inv);
    }
    public static boolean isMysteryCrate(ItemStack stack) {
        String displayName = Main.get().getConfig().getString("Config.DisplayName");
        Material mat = Material.valueOf(Main.get().getConfig().getString("Config.Material"));
        if(stack !=null) {
            if(stack.getType() == mat) {
                if(stack.hasItemMeta()) {
                    if(stack.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', displayName))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static boolean isCrate(String s) {
        List<String> crates = Main.get().getConfig().getStringList("SupplyCrates.Crates");
        if(crates.contains(s)) {
            return true;
        }
        return false;
    }
    public static ItemStack getCrate(String s) {
        List<String> crates = Main.get().getConfig().getStringList("SupplyCrates.Crates");
        for(String crate : crates) {
            if(s.equals(crate)) {
                ItemStack item = new ItemStack(Material.CHEST, 1);
                ItemMeta im = item.getItemMeta();
                im.setDisplayName(ChatColor.translateAlternateColorCodes('&', Main.get().getConfig().getString("SupplyCrates."+crate+".DisplayName")));
                ArrayList<String> lores = new ArrayList<String>();
                List<String> loreCon = Main.get().getConfig().getStringList("SupplyCrates."+crate+".Lores");
                for(String lore : loreCon) {
                    lores.add(ChatColor.translateAlternateColorCodes('&', lore));
                }
                im.setLore(lores);
                item.setItemMeta(im);
                return item;
            }
        }
        return null;
    }
    public static void animate(final Inventory chest, final Player p, final String crate) {
        final List<String> crates = Main.get().getConfig().getStringList("SupplyCrates.Crates");
        //TODO Animate with the different glass panes and their colors
        new BukkitRunnable() {
            ItemStack glass_pane = new ItemStack(Material.STAINED_GLASS_PANE, 1);
            int timer = 0;
            @Override
        public void run() {
                String crateString = "";
                if(Main.get().getConfig().getBoolean("SupplyCrates."+crate+".Cycle")) {
                    List<Integer> crateChances = Main.get().getConfig().getIntegerList("SupplyCrates."+crate+".Chances");
                    for(Integer crateChance : crateChances) {
                        if(Main.get().getRandom().nextInt(100-1)+1 <= crateChance) {
                            String newCrate = crates.get(crateChances.indexOf(crateChance));
                            String mat = Main.get().getConfig().getString("SupplyCrates."+newCrate+".Glass_Pane");
                            if(mat.contains(":")) {
                                glass_pane = new ItemStack(Material.valueOf(mat.split(":")[0]), 1, Short.parseShort(mat.split(":")[1]));
                                crateString = newCrate;
                            } else {
                                glass_pane = new ItemStack(Material.valueOf(mat), 1);
                                crateString = newCrate;
                            }
                        }
                    }
                chest.setItem(timer, glass_pane);
                p.openInventory(chest);
                } else {
                    String mat = Main.get().getConfig().getString("SupplyCrates."+crate+".Glass_Pane");
                    if(mat.contains(":")) {
                        glass_pane = new ItemStack(Material.valueOf(mat.split(":")[0]), 1, Short.parseShort(mat.split(":")[1]));
                        crateString = crate;
                    } else {
                        glass_pane = new ItemStack(Material.valueOf(mat), 1);
                        crateString = crate;
                    }
                    chest.setItem(timer, glass_pane);
                    p.openInventory(chest);
                }
                for(String slot : Main.get().getPickedCrates().get(p.getUniqueId()).split(", ")) {
                    int sl = Integer.parseInt(slot);
                    if(sl == timer) {
                        ItemStack pane = glass_pane.clone();
                        ItemMeta meta = pane.getItemMeta();
                        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Main.get().getConfig().getString("SupplyCrates."+crateString+".GlassDisplayName")));
                        pane.setItemMeta(meta);
                        chest.setItem(sl, pane);
                    }
                }
                timer++;
                if(timer >= chest.getSize()) {
                    getItems(chest, p.getUniqueId());
                    this.cancel();
                }
            }
        }.runTaskTimer(Main.get(), 0L, 5L);
    }
    private static void getItems(final Inventory chest, UUID player) {
        final Player p = Bukkit.getPlayer(player);
        final String slotsPickedArgs[] = Main.get().getPickedCrates().get(player).split(", ");
        final List<String> crates = Main.get().getConfig().getStringList("SupplyCrates.Crates");
        final int slotsPicked = slotsPickedArgs.length;
        new BukkitRunnable() {
            int slotsDone = 0;
            @Override
        public void run() {
                //Loop through and get the items
                if(!(slotsDone == slotsPicked)) {
                    ItemStack stack = null;
                    for(String crate : crates) {
                        if(chest.getItem(Integer.valueOf(slotsPickedArgs[slotsDone])) !=null) {
                            if(chest.getItem(Integer.valueOf(slotsPickedArgs[slotsDone])).getType() == Material.valueOf(Main.get().getConfig().getString("SupplyCrates."+crate+".Glass_Pane").split(":")[0]) && chest.getItem(Integer.valueOf(slotsPickedArgs[slotsDone])).getData().getData()
                                    == Byte.valueOf(Main.get().getConfig().getString("SupplyCrates."+crate+".Glass_Pane").split(":")[1])) {
                                //TODO Check all the items and their chances
                                List<String> items = Main.get().getConfig().getStringList("SupplyCrates."+crate+".Items");
                                if(Main.get().getConfig().getBoolean("SupplyCrates."+crate+".Cycle")) {
                                    for(String item : items) {
                                        if(Main.get().getRandom().nextInt(100-1)+1 <= getChance(item)) {
                                            stack = deconfizzle(item);
                                            if(stack !=null) {
                                            chest.setItem(Integer.parseInt(slotsPickedArgs[slotsDone]), stack);
                                            p.openInventory(chest);
                                            //Play sound maybe?
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if(stack !=null) {
                    p.getInventory().addItem(stack);
                    p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1F, 8F);
                    }
                    slotsDone++;
                } else {
                    p.closeInventory();
                    Main.get().getPickedCrates().remove(p.getUniqueId());
                    Main.get().getAlreadySelected().remove(p.getUniqueId());
                    this.cancel();
                }
            }
        }.runTaskTimer(Main.get(), 0L, 20L);
    }
    public static int getChance(String s) {
        String args[] = s.split(", ");
        if(args.length >= 3) {
            int chance = Integer.parseInt(args[2]);
            return chance;
        }
        return 0;
    }
    public static ItemStack toItemStack(String material) {
        if(material.contains(":")) {
        String data[] = material.split(":");
        return new ItemStack(Material.valueOf(data[0]), 1, Short.parseShort(data[1]));
        }
        return new ItemStack(Material.valueOf(material), 1);
    }
    public static ItemStack deconfizzle(String s) {
        String args[] = s.split(", ");
        if(args.length >= 3) {
            String mat = args[0];
            int amt = Integer.parseInt(args[1]);
            int chance = Integer.parseInt(args[2]);
            ItemStack item = toItemStack(mat);
            item.setAmount(amt);
            String displayName = "";
            List<String> loreList = new ArrayList<String>();
            if(args.length == 4) {
                //Display Only
                displayName = args[3];
            } else
                if(args.length == 5) {
                    //Lore and Display
                    String lores[] = args[4].split(" // ");
                    displayName = args[3];
                    if(lores.length >= 1) {
                        for(int i=0; i<lores.length; i++) {
                            loreList.add(ChatColor.translateAlternateColorCodes('&', lores[i]));
                        }
                    }
                } else
                    if(args.length == 6) {
                        String lores[] = args[4].split(" // ");
                        String enchants[] = args[5].split(" // ");
                        displayName = args[3];
                        if(lores.length >= 1) {
                            for(int i=0; i<lores.length; i++) {
                                loreList.add(ChatColor.translateAlternateColorCodes('&', lores[i]));
                            }
                        }
                         if(enchants.length >= 2) {
                            for(int i=0; i<enchants.length; i++) {
                                item.addEnchantment(Enchantment.getByName(enchants[i].split(":")[0]), Integer.valueOf(enchants[i].split(":")[1]));
                            }
                        } else {
                            item.addUnsafeEnchantment(Enchantment.getByName(enchants[0].split(":")[0]), Integer.valueOf(enchants[0].split(":")[1]));
                        }
                    }
                if(!displayName.equals("") && loreList.isEmpty()) {
                    //Make the item with displayname
                    ItemMeta im = item.getItemMeta();
                    im.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
                    item.setItemMeta(im);
                } else
                    if(!displayName.equals("") && !loreList.isEmpty()) {
                        //Make the item with displayname and lores
                        ItemMeta im = item.getItemMeta();
                        im.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
                        im.setLore(loreList);
                        item.setItemMeta(im);
                    }
            return item;
        }
        return null;
    }
}
