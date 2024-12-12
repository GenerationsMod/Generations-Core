# 5.0.0
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



# 4.1.1
- Fix Server Load Crash
- Fix Wooden Fence Gates missing from Forge fence_gates/wooden tag
- Fix Gates ItemTag missing
- Make Snow Layer not survival on Ball Loots, Display Blocks and Pokedolls

# 4.1.0
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

# 4.0.2
- Fix Crash when trying to render the Celestial Altar

# 4.0.1
- Fix Rendering not displaying on MacOS and Linux
- Update Glowing Charge Cobblestone texture