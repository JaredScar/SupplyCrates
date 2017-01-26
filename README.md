# SupplyCrates

<h2>Purchase</h2>

[Buy it here] (http://www.mc-market.org/threads/34246/)

![alt text] (https://i.gyazo.com/ad90ace59d0f0dec5c5e17ef0be2ff3d.png "SupplyCrates")

This plugin is basically like the crates you can buy on Desteria and/or CosmicPvP. It is a crate plugin, in which they have certain chances to win different items that are specified in the config. Here is a video of what it is modeled after: 

[Video] (https://www.youtube.com/watch?v=m3F2gLyszWk)

The plugin works very well and is well-worth the buy! It's a great donator option and it can boost your donation sales! You can sell these crates on your online store and make tons of money! Contact me if you are interested, thebadgerwolf via Skype. I will have the plugin posted via Spigot soon too.

![alt text] (https://i.gyazo.com/5ecd96db6d93a1eaa9284a98a600bfbe.png "Commands")
/gc (player) (crate)

/givecrate (player) (crate)

/gc all (crate) - Give everyone a crate on the server

/givecrate all (crate) - Give everyone a crate on the server

![alt text] (https://i.gyazo.com/b2077504e031bb6e8f1259ed763e2053.png "Permissions")

SupplyCrates.giveCrate - Permission to use /gc or /givecrate

![alt text] (https://i.gyazo.com/4fa319d7442909e107769c8130c5b4dc.png "Configuration")

```YAML
##############
### Config ###
##############
Config:
    Material: CHEST
    DisplayName: '&7&l???'
    Lores: {}
    RegularGlassPane: STAINED_GLASS_PANE
    Sound: ANVIL_BREAK
################
### Messages ###
################
Messages:
    ReceivedCrate: '&7You have received a &a{CRATE} &7crate!'
#####################
### Supply Crates ###
#####################
SupplyCrates:
    Crates:
    - Vote
    - Common
    - Ultra
    - Rare
    - Legendary
    Vote:
        Glass_Pane: STAINED_GLASS_PANE:5
        GlassDisplayName: '&aVote'
        Cycle: true                     ## Do you want it to use other crates' chances of winning or do you want them to just win items from this crate?
        Chances:
        - 100
        - 40
        - 25
        - 20
        - 5
        ChestSize: 27                   ## Multiples of 9
        DisplayName: '&aVote'
        Title: '&aVote'
        Picks: 3
        Lores:
        - '&cLores'
        Items:                          ## Material, Amount, Chance, DisplayName, Lores, Enchants
        - 'GOLD_INGOT, 8, 100, &fGold Ingot, <COMMAND> eco give <USER> 100'
        - 'TNT, 16, 5, &fTNT'
        - 'OBSIDIAN, 32, 5, &fObsidian'
        - 'IRON_INGOT, 16, 5, &fIron Ingot'
        - 'EXP_BOTTLE, 32, 5, &fBottle o Enchanting'
        - 'DIAMOND, 10, 5, &fDiamond, &41st Lore // &22nd Lore'
        - 'DIAMOND_CHESTPLATE, 1, 5, &aVote Chest, &9Lucky, PROTECTION_ENVIRONMENTAL:1'
        - 'DIAMOND_SWORD, 1, 5, &aVote Sword, &4Vote Sword, DAMAGE_ALL:3 // FIRE_ASPECT:2'
        - 'BEACON, 1, 3, &fBeacon'
    Common:
        Glass_Pane: STAINED_GLASS_PANE:0
        GlassDisplayName: '&7Common'
        Cycle: true                     ## Do you want it to use other crates' chances of winning or do you want them to just win items from this crate?
        Chances:
        - 1
        - 100
        - 1
        - 1
        - 1
        ChestSize: 27                   ## Multiples of 9
        DisplayName: '&7Common'
        Title: '&7Common'
        Picks: 3
        Lores:
        - '&cLores'
        Items:                          ## Material, Amount, Chance, DisplayName, Lores, Enchants
        - 'MONSTER_EGG:50, 1, 100'
    Ultra:
        Glass_Pane: STAINED_GLASS_PANE:11
        GlassDisplayName: '&dUltra'
        Cycle: true                     ## Do you want it to use other crates' chances of winning (true) or do you want them to just win items from this crate? (false)
        Chances:
        - 100
        - 40
        - 25
        - 20
        - 5
        ChestSize: 54                   ## Multiples of 9
        DisplayName: '&dUltra'
        Title: '&dUltra'
        Picks: 2
        Lores:
        - '&cLores'
        Items:                          ## Material, Amount, Chance, DisplayName, Lores, Enchants
        - 'DIAMOND_SWORD, 1, 100, &6DisplayName, &41st Lore // &22nd Lore, DAMAGE_ALL:3 // FIRE_ASPECT:2'
        - 'DIAMOND, 1, 70, &6DisplayName, &41st Lore // &22nd Lore'
        - 'DIAMOND, 1, 60, &6DisplayName, &41st Lore // &22nd Lore'
        - 'DIAMOND, 1, 40, &6DisplayName, &41st Lore // &22nd Lore'
    Rare:
        Glass_Pane: STAINED_GLASS_PANE:2
        GlassDisplayName: '&cRare'
        Cycle: true                     ## Do you want it to use other crates' chances of winning or do you want them to just win items from this crate?
        Chances:
        - 100
        - 40
        - 25
        - 20
        - 5
        ChestSize: 54                   ## Multiples of 9
        DisplayName: '&cRare'
        Title: '&cRare'
        Picks: 4
        Lores:
        - '&cLores'
        Items:                          ## Material, Amount, Chance, DisplayName, Lores, Enchants
        - 'DIAMOND_SWORD, 1, 100, &6DisplayName, &41st Lore // &22nd Lore, DAMAGE_ALL:3 // FIRE_ASPECT:2'
        - 'DIAMOND, 1, 100, &6DisplayName, &41st Lore // &22nd Lore'
        - 'DIAMOND, 1, 100, &6DisplayName, &41st Lore // &22nd Lore'
        - 'DIAMOND, 1, 100, &6DisplayName, &41st Lore // &22nd Lore'
    Legendary:
        Glass_Pane: STAINED_GLASS_PANE:14
        GlassDisplayName: '&bLegendary'
        Cycle: true                     ## Do you want it to use other crates' chances of winning or do you want them to just win items from this crate?
        Chances:
        - 100
        - 40
        - 25
        - 20
        - 5
        ChestSize: 54                   ## Multiples of 9
        DisplayName: '&bLegendary'
        Title: '&bLegendary'
        Picks: 8
        Lores:
        - '&cLores'
        Items:                          ## Material, Amount, Chance, DisplayName, Lores, Enchants
        - 'DIAMOND_SWORD, 100, 5, &6DisplayName, &41st Lore // &22nd Lore, DAMAGE_ALL:3 // FIRE_ASPECT:2'
        - 'DIAMOND, 1, 100, &6DisplayName, &41st Lore // &22nd Lore'
        - 'DIAMOND, 1, 100, &6DisplayName, &41st Lore // &22nd Lore'
        - 'DIAMOND, 1, 100, &6DisplayName, &41st Lore // &22nd Lore'
```        

<h2>Give the user a crate:</h2>

![alt text] (https://i.gyazo.com/7b7cdd94b6db2232044f3a1223f13d33.gif "Give the user a crate")

<h2>Place down the crate:</h2>

![alt text] (https://i.gyazo.com/8517b5445cb96ae6174ed3ff79f7c436.gif "Place down the crate")

<h2>Select your chances:</h2>

![alt text] (https://i.gyazo.com/1ddde54c7a08cab2abcf1599958cafdb.gif "Select your chances")

The items will be added to your inventory as it reveals the items you won at the end.


<h2>Price</h2>
The price of this plugin is $15.00 USD

<h2>Terms and Conditions:</h2>
You may not redistribute this plugin in any way.

This plugin can only be used on 1 server.

No refunds are to be given. (I have no idea what you are going to do with the plugin once you get that refund). Could easily be a scam.
