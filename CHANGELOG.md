# 1.2.5

Changes
- Removed battle move descriptions

Bug Fixes
- Adjusted default sitting position offset slightly
- Fixed Hoopa Unbound Prison Bottle form change
- Fixed Generations Pokeloot drops
- Fixed item description tooltips


# 1.2.4

Additions
- Added an S icon to PC for Galaxy, Pastel, Shadow, Sketch, and Vintage Pokemon

Changes
- Removed Ruby Rod recipe, Lake Guardian Crystals can be obtained from Cobblemon's fishing treasure loot table
- Changed Legendary Pokemon Wing items to stack at 64
- Shifted most Legendaries to only spawn above ground

Bug Fixes
- Fixed Galarian Articuno/Moltres/Zapdos summoning as Kantoian
- Fixed Mythicals not having 3 guaranteed 31 IVs
- Fixed Arceus/Silvally types not changing
- Fixed Terapagos abilities
- Fixed external transformations on shift right click, eg. mega
- Fixed Legendary item charging overflowing into all in inventory
- Fixed Zacian and Zamazenta summon drops
- Fixed Rotom spawns
- Fixed certain modded biomes not having proper spawns
- Fixed some Pokemon not appearing as implemented for Pokedex completion
- Fixed Pokemon spawn context to spawnablePositionType


# 1.2.3

Additions
- Added Cobblemon 1.7.3 compatibility!

# 1.2.2

Additions
- Added Cobblemon 1.7.2 compatibility!


# 1.2.1

Bug Fixes
- Resolved FPS decay when using smooth models (finally!)
- Fixed Pokemon models sometimes floating way up or appearing invisible
- Fixed a Fridge crash
- Fixed a client crash that occurred with RKS Machine
- Fixed a post battle conditional crash
- Fixed Cobblemon profiles and portraits appearing a couple pixels up
- Fixed Therian form changes
- Fixed Zygarde Cube dialogue exit
- Adjusted Gible and Clefairy evolution line spawn rates in the End to account for some modded biomes and Ultra Beast rates
- Adjusted sound volumes for Elevator and Meloetta Relic Song

Resource Packs
- Fixed Unown sprites and models
- Reduced file size slightly
- Fixed Hisuian starter sprites

# 1.2.0

Additions
- Added Cobblemon 1.7 compatibility!

Known Issues
- Pokemon riding anchoring is a bit below where it should be, riding is functional and intact, bar multi-seaters. Improvements for this go hand-in-hand with how models animate on our end, so we'll be resolving this as part of our renderer rewrite already in progress


# 1.1.0

Additions
- Added Cobblemon 1.6 compatibility!
   - Please be sure to update your Generations 1-9 Resource Packs to patch up model issues!
- Added Terastallization for devs and server owners!
   - Have a Tera Orb in your inventory to Terastallizatize during battle!
   - Use 50 Tera Shards to change a Pokemon's Tera Type
   - Comes with a Tera battle overlay effect, Tera sound, and visual effect
   - We'll add a proper singleplayer obtainment method, like Tera Ores or the like, in a future update
- Added Pokemon icons for profiles and portraits
- Added Exp All functionality
- Added world and Time Capsule conversions for all Generations items
- Legendary Pokemon will now be guaranteed 3 Perfect IVs
- Added external transformations to the shift right click wheel
   - Try Mega Evolving your favorites outside of battle!
- Added Statue functionality for non-OP players
   - Up to 2 statues can be placed per chunk using a Chisel
- Added a Brazilian Portuguese translation (Thanks ednzinho!)

Changes
- Rewrote a majority of the codebase from Java to Kotlin
- Updated to Cobblemon species and spawn files for all new Cobblemon
- Adjusted Legendary spawn weights to factor in Cobblemon's Pokemon spawn weight changes
- Moved Ruby Rod to use Cobblemon 1.6's fishing loot table
- Adjusted DNA Splicer stackability to 1
- Improved Pokemon shoulder rendering

Bug Fixes
- Fixed shift clicking RKS Machine output items disappearing
- Fixed Aqua armor set having no texture on armor stands
- Fixed Zygarde Cubes disappearing when used in offhand
- Fixed Dusk Ball Displays showing as Dive Ball
- Fixed Calyrex Rider form not receiving typing, ability, or stats
- Fixed Kyurem retaining Fusion Bolt/Flare after fusing and unfusing
- Fixed Rotom being unable to learn certain moves in certain forms
- Fixed Rusty Shield not transforming into Crowned Shield upon summoning of Zacian/Zamazenta
- Add movepool to Crowned forms of Zacian/Zamazenta
   - Zacian/Zamazenta: Iron Head should transform into Behemoth Blade/Bash in battle
- Fixed Abundant Shrine lang
- Fixed Special textures not working for statues on servers
- Fixed High Plains Vivillon texture
- Fixed Shiny Entei Doll appearing as a regular Entei
- Fixed mipmapping due to certain icon texture sizes

Known Issues
- Uncommonly, there may be a black screen on the upper right quadrant from the cursor. Sorry! It's not worth delaying updates any longer than we already have, but any leads on this are appreciated, please let us know! So far we've had this related to hotbar items overlaying when Time Capsuled Pokemon are in or out of inventory, and resource packs, sometimes F3 + T reloading resolving it
- Opening the Pokedex attempting to load non-Cobblemon sprites of certain species will cause fps strain on the client until reboot. This would be caused by a Pokemon form or species missed when mapping sprites
- If you're interested in helping beta test future updates, please apply in our Discord's #apply channel!


# 1.0.2

Changelog
- Fixed an invisibility, rendering, and crashing bug with 1.0.1— please update if you're experiencing issues!
- Fixed Megas causing HA to not revert properly
- Fixed Megas not reverting if not active in battle
- Tweaked Ogerpon details to support future Tera implementation

Next up: 1.21.1!


# 1.0.1

Changelog
- Added Ore Generation for Mega Stone, Z-Crystals, and Meteorite.
- Mega Shards are now stackable.
- Raised legend spawn rates across the board based on player feedback, and moved Diancie to spawn in mountains.
- Adjusted spawns for Ditto, Unown, Rotom, Togepi, Marill, Solosis, Gothita, and Bagon evo lines based on player feedback.
- Fixed spawns for Bronzor, Corphish, Drilbur, and Noibat evo lines.
- Added about a dozen Pokemon to ocean land spawns
- Added config option 'enableZygardeCubeOverflow' to allow Zygarde Cubes to continue collecting cells when full.
- Fixed Zygarde Cell spawn rate with the addition of the collectibles spawn bucket.
- Zygarde's abilities are no longer affected by ability changing items.
- Added Zygarde to default Caught section of the config.
- Zygarde cell spawning is now prevented by scarecrows.
- Scarecrow is now based on a configurable radius on the x, y, and z axis vs per chunk. Default is 32 in every direction.
- Fixed an issue with Lake Trio Crystals causing crashes.
- Ruby Rod should now break after getting enough Lake Trio Shards.
- Fixed an issue with Bird Shrine interaction.
- Fixed Cooking Pots not dropping their inventory.
- Fixed Pokemon changed by the RKS machine lacking moves when the new species shares no moves with the old one.
- Fixed errors in a few RKS recipes.
- Chisel statues now have upper/lower scale bounds of 5x and 0.5x, respectively.
- Fixed statues of Pokemon with aspects displaying incorrectly.
- Added Ultrite Smithing Template to Ancient Cities, End Cities, Beast Ball loot.
- Added TMs 230-237 to the loot ball pool.
- Added Ogerpon masks and Genesect drives to loot ball pool.
- Fixed X and Y Mega evolutions not working correctly in battle.
- Fixed full-set bonus buffs for armor being applied incorrectly.

Known Issues
- Client-side invisibility and log spam at times, fixed in 1.0.2.


# 1.0
Haven't been following the BETA's? Here's a quick recap:
- We are a Cobblemon sidemod that hooks in and adds extra features, including gamelike Pokémon models you're familiar with
- You can disable our models if you wish, they are just resourcepacks and use Cobblemon's blocky models instead.
- We highly recommend our modpack- there's several mods/resourcepacks you need for the full Generations experience
- Check out our wiki for a general guide on things- most mechanics have received overhauls. Our wiki is at generations.gg. You can also create an account, and edit pages to fix any issues you spot
- We said we were keeping it simple, but turns out we ended up with most of our previous features. Some missing mechanics such as Ultra Space are being worked on now. Others, such as outbreaks can often be found as Cobblemon sidemods- we're compatible with most! We'll add our own toggleable features for missing mechanics over time as well, for things such as Outbreaks in this example
- You must download the new resourcepacks if you have old ones, the ones from the previous BETA's are no longer compatible
- Yes, we have the full dex

From our last beta, here's the changes/fixes:
- Added 6,875 Special Pokémon using shaders, rather than textures for zero bloat
 - All Pokémon now have the following specials:
 - Galaxy: Darkens Pokémon and adds dark, heavy purples/blacks, while also applying an animated star-y mask
 - Sketch: Shifts Pokémon mostly black, while adding a sketch-like white outline effect
 - Shadow: Converts Pokemon to a grayscale, then Darkens Pokémon towards mostly deep, dark blues
 - Vintage: Applies a nice soft grayscale texture that looks good on pretty much everyone
 - Pastel: A brightened effect with pink & blue pastel shading. This will mostly only look good on Pokémon that are brighter to begin with
 - These won't be perfect for every Pokémon, experiment! We'll still add normal nice specials with textures over time such as cosplay's and whatnot we had before- that'll take some time
 - Creators: These are not a global configuration, they are individually configured in each of their config.jsons, so you can turn off any of these on anyone in favor of a custom one (ie if you want to have a custom textures Shadow Pikachu instead of using the Shadow shader)
- Added Mew DNA Fiber extraction using DNA Splicers
- Added DNA Extraction information on Mew Summary screen
- Added Ultrite tier of tools and armors, end game content which can also have trimming on
- Added tooltips to moves in battle when you hover over them to display their description
- Added Syrupy Apple
- Added Ball Loot messages to inform you what you've received
- Added Timed PokeBall Loots
- Added Silver Knowledge Symbol
- Added JEI/REI compatibility support for RKS Machine
- Added JourneyMap and Xaero's compatibility support for entity icons
- Added some support for Mr Crayfish's catalogue
- Changed Curry to be a bit more basic for now, due to the constant issues, we'll tackle this in a future update
- Reworked RKS recipes to allow for shapeless recipes instead of specific placements
- Reworked Iceroot/Shaderoot so they vanish on use instead of enchanted/unenchanted versions
- Fixed crash with mail
- Fixed statue bounding box
- Fixed Chisel menu not updating
- Fixed crash when flavor is null on Curry
- Fixed Compressed Stone recipe due to conflict with Deeplsate Tiles
- Fixed Elevator sound being too loud
- Fixed Tao and Regigigas Shrine not rendering
- Fixed sprite shading in GUI's
- Fixed MultiBlocks replacing other blocks when placed nearby
- Fixed some misc blocks missing tags, such as being breakable
- Fixed Time Capsules ignoring untradeable tag
- Fixed Scarecrows being per chunk, now 32 block radius
- Fixed Lighting Lantern missing data
- Fixed Megas losing their abilities
- Fixed Shaderoot and Iceroot not dropping from carrots
- Fixed Rotom Catalog not working
- Fixed Psychic TMs displaying as Bug type
- Fixed Reveal Glass not toggling Therian Forms
- Fixed Iron Valiant unable to be made from Gallade
- Fixed Krabby PokeDoll texture
- Fixed Shadow Lugia's eyes
- Fixed HDTV's lang entry
- Fixed Ditto being stretchy in animations
- Fixed Terapagos Stellar being a bit wonky
- Fixed Vivillon being a bit wonky
- Fixed Zubat glowing
- Fixed Shoulder Mounts being invisible
- Fixed an error with Heartflame Mask
- Fixed couch sitting positions and heights
- Fixed Pastel Bean Bags
- Fixed Box's sprite being too basic
- Fixed Female Pikachu missing sprite
- Fixed Park Ball texture being wrong color
- Fixed a handful of blocks not dropping when broken such as Double Street Lamps
- Fixed Defog, Mystical Fire, Roost showing as Blank TMs
- Fixed Pokémon able to have moves they aren't supposed to when converted to another species in an RSK Machine
- Fixed Plasma Armor textures missing
- Fixed RKS Machine giving you a blank Pokémon sprite item if you click the Time Capsule before it is finished the process of making a Pokemon
- Fixed BiomesOPlenty Dryland, Wasteland, Wasteland Steppe having no spawns
- Fixed Corsola, Clauncher, Clawitzer, Mareanie, Toxapex, Bruxish, Pincurchin not spawning due to invalid is_reef spawn set, now spawn in is_warm_ocean
- Fixed Silicon tools being better than Netherite
- Fixed Solgaleo's face
- Fixed Hisuian Electrode missing its mouth
- Fixed Genesect form change to match the drive it holds
- Changed Curry to be a bit more basic curry-ntly, due to the constant issues. Curry will default to Milcery Rating for now until Curry Dex is implemented in full. We'll tackle this in a future update.
- Fixed Yet Another Chance Booster in the modpack conflicting with shiny rate config edits
- Fixed armors not having armor values
- Fixed Zygarde Cell spawnrates
- Fixed Zygarde 50% being able to be created from only 10 Cells
- Fixed Zygarde Cube opening menus when collecting
- Decreased Zygarde Cell collection noise
- Fixed Zygarde Cell collection text firing off twice
- Fixed lang typos
- Fixed and added new lang to Chinese and French
- Fixed Arceus's eyes
- Fixed RKS Machine causing multiplayer networking issues
- Fixed recipe for crafting Unown Block letters
- Fixed Dacite Shore no spawns
- Fixed a few dozen Pokemon spawns
- Fixed Swift, Substitute, Grassy Terrain, Supercell Slam showing as Blank TMs
- Fixed Supercell Slam lang
- Fixed an error with Heartflame Mask
- Fixed Tentacool/Tentacruel having gray instead of red orbs
- Fixed Kyruem fusion and defusion with DNA Splicer
- Fixed Shaderoot and Iceroot not dropping from carrots
- Reworked Iceroots and Shaderoots so they vanish on use instead of enchanted and unenchanted versions
- Pillars should now connect properly
- Fixed Pokeball chests being 1 pixel up
- Fixed overwriting Time Capsule Pokemon
- Fixed Ruby, Sapphire, Silicon, Crystal, Evolution Stone Blocks un-craft recipe
- Fixed and reogranized symbol loading order
- Fixed Kantoian Pikachu not being able to evolve
- Fixed Arceus/Silvally type changes when holding their respective item
- Fixed Silvally/Arceus being able to have any type without having to hold the respective plate
- Fixed Pokémon able to have moves they aren't supposed to when converted to another species in an RKS Machine
- Fixed Chisel menu not updating. Statue GUI picture should now update when you change things
- Fixed Time Capsules ignoring untradeable tags


# BETA 5.0.0
Our FINAL BETA is here!
Please report bugs- new and old in case we missed something important. We hope to have our official release in a couple weeks, we just want some time to collect new bug report and have some time to fix any that arise.

- Added lang for fr_fr and zh_cn
- Added lang for missing moves
- Added missing alolans: pichu, pikachu, exeggcute, cubone, coffing, mimejr
- Added all missing sprites
- Added stack data for unimplemented items so they stack
- Added spawn data for Pecharunt & Tarapagos
- Added Ogerpon masks
- Various pokemon size adjustments
- Reworked Time Capsules to make them look nicer and fix log spam errors
- Fixed Pokémon textures flickering black on Forge
- Fixed all Galarian forms not working
- Fixed Psychic type missing in our species features
- Fixed Chandelure, Centiskorch flames
- Fixed Cursola pulsing
- Fixed varoom, revavroom anims
- Fixed Bellibolt missing yellow Orb
- Fixed Mewostic & Indeedee M/F forms
- Fixed Hoopa Unbound
- Fixed a handful of swimming pokemon
- Fixed a handful of flying pokemon
- Fixed BaseScale being applied to our models, so models are now their intended sizes again
- Fixed poster lang missing
- Fixed Sparkling Stone having the wrong type requirement to charge
- Fixed all form change items
- Fixed a crash with red chain in anvils
- Fixed a crash with mail items
- Fixed an exploit with a handful of legendary mechanics
- Fixed an exploit with PokeLoot being used infinitely (old loots can be fixed with a sidemod we're publishing in #plugins-mods)
- Fixed a crash on Fabric with vending machines (temporarily disabled shops)
- Temporarily hard coded Manaphy/Phione to be 10,000 walk steps (so clients can connect to vanilla servers aka server hubs)
- Removed Paradox spawns since they're crafted in RKS Machine now

Server Owners:
We believe it is safe enough to use in production now. If you've generated a world, use our sidemod to fix old PokeLoots in the plugins-mods channel. This will convert old infinite use PokeLoots to one time per player loots. We'll add more modes soon. Not all structures are implemented yet, but most are. We recommend either starting with a smaller world, or make a resource world that you will regenerate periodically

Note: This is BETA 5.0. When we release, we're going back down to 1.0 and adjust any improperly labeled versions and/or remove old/confusing ones on our Curse/Modrinth pages.


# BETA 4.1.1
- Fix Server Load Crash
- Fix Wooden Fence Gates missing from Forge fence_gates/wooden tag
- Fix Gates ItemTag missing
- Make Snow Layer not survival on Ball Loots, Display Blocks and Pokedolls


# BETA 4.1.0
Model Loading: 
- Adjusted model loading to ensure proper unloading over time; added config triggered logging for loading/unloading processes.
Spawns: 
- Fixed spawn issues related to the removed is_frigid tag; tweaked legendary spawn mechanics.
Items/Blocks & Mechanics:
- Added lighting lantern and z-block.
- Fixed statue levels from legendary statue spawners (pending testing).
- Adjusted drops on capture and modified Weather Trio orbs to 250 durability.
- Make Curse Carved Pumpkin Equipable
- Add Cursed Pumpkins to EnderMan Holdable and Sword Efficient Tags
Armors & Drops:
- Applied armor effects, using 1.12.2 values.
- Fixed various armor dupes and general handling tweaks.
- Manaphy Mechanic: Adjusted mechanics to scale with egg cycles, based on configurable distance.
Shrines: 
- Updated Regi shrines to not require pillars, fixed ONCE_PER_PLAYER option with BallLoots.
Overlays: 
- Revised overlay code to support hotbar display in the camera and accommodate pumpkin overlays.


# BETA 4.0.2
- Fix Crash when trying to render the Celestial Altar


# BETA 4.0.1
- Fix Rendering not displaying on MacOS and Linux
- Update Glowing Charge Cobblestone texture