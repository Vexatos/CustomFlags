CustomFlags
===========

####A ModJam Project by nerdboy####

Custom adds flags to minecraft that allow full customisation by the user. Flags can be attached to a flag pole and be put on display for everyone to see.

##Mod Mechanics##
The mod revolves around 1 new item (Flag) and 1 new block (Flag pole)

###Flag poles###
Flag poles are created by placing 3 wooden planks or 3 iron ingots in a vertical line in the crafting recipe
[ ][P][ ]
[ ][P][ ] = Flag Pole
[ ][P][ ]

Flag poles can be placed on any block. All flag ploes will be placed vertically and they can be stacked

###Flags###
Flags are created by arranging 4 wool blocks (of any colour) in a 2 x 2 square in the crafting grid  
[ ][W][W]
[ ][W][W] = Flag
[ ][ ][ ]

The wool may be any colour (or any combination of colours) and the resulting flag will take the colours of the wool blocks used.

Flags may be attached to any flag pole by right clicking a flag pole while holding a flag. In survival/hardcore modes, this will consure the flag, in creative mode, no flag is consumed.
Each flag pole block can have a maximum of 4 flags attached to it. Each flag placed after the first will be placed after the previous flag. Right clicking on an flag pole with empty hands will remove the most recently attached flag and place it in the players inventory.

Flags attached to a flag pole will blow in the wind. Due to the fact minecraft wind only blows in the north direction, flags will ALWAYS blow north. The speed of the animation can be changed in the config file, (valid between 1 & 10) higher numbers will speed up the animation, lower nmumbers will slow it down.

###Flag Designer###
Right clicking while holding a flag (with no usable block in front of the player) will open the flag designer gui. The GUI supports a 32x32 12 bit ARGB image. A colour chooser is included on the right of the screen and drawing tools on the left The tools include
* **Pen Tool:** Draws a single pixel at the current mouse location when the left mouse button is down
	+ Holding shift will draw a straight line from the last location clicked to the current mouse location
* **Flood Fill:** Clicking the left mouse button will change all of the connected pixals of the same colour to the currently selected colour
	+ Holding shift while clicking will fill the whole 32x32 area with the colour selected

In addition there are 3 buttons at the top of the canvas area
* **Save:** Opens a save dialog to allow the user to save the current design to their filesystem for later use
* **Load:** Opens a load dialog to allow the user to use an image on their current file system as their current flag. Images will be scaled to 32x32 and converted to 12bit ARGB before being drawn on the canvus
* **Load Section:** Opens a load dialog to allow the user to use a section of an image on their current file system as their current flag. After selecting the desired file, a user will be asked how many sections they desire in the X and Y directions (between 1 & 4) and which section they want to use for the current flag. The resulting image will be scaled to 32x32 and converted to 12 bit ARGB colour space.

The OK button will save the design to the currently held flag, pressing escape will close the flag designer without changing the design.

###Configuration File###
The configuration file for the mod is located in the configs directory under the name custom_flags.cfg
The fields correspond to
* **Animation Detail Level** The detail level for the waving animation. 0 will turn the animation off, higher values will produce higher levels of detail (generally 16ish is way more than needed)
* **Cache Size** Not currently in use
* **Flag Speed** The speed of the animation (between 1 & 10). Higher values will speed up the animation
* **Use System L&F** Mimic the system look and feel for swing dialogs. Set to false to use the default look and feel (likely the cross platform metal L&F)

* **Flag Pole Id** The Id of the Flag Pole Block (defaults to 2700)
* **Flag Id** The Id of the Flag Item (defaults to 24532)

##Instalation Instructions##
The mod is compiled for Minecraft 1.6.2

1. Download and Install Minecraft Forge (http://files.minecraftforge.net/)
	+ The mod was compiled using Minecraft Forge version 9.10.0.804
2. Download Custom Flags
3. Place the downloaded Custom Flags file into the /mods directory in the .minecraft directory (for Client) or the root of the server directory (for Server)
4. Run the client or server as usual

##Build Environment Instructions##
Clone the git project into an already installed src version of minecraft forge.
The project assumes that the it will be cloned with the minecraft src installation folder as the root.

##Compilation Instructions##
1. Clone the git project into an already installed src version of minecraft forge
	+ It assumes that the mcp folder is inside the forge folder (i.e forge/mcp/src.... etc)
2. run the build script by issuing 'ant clean compile build'

Alternatively the mod can be compiled by running 'compile.bat'/'compile.sh' followed by 'reobfuscate_srg.bat'/'reobfuscate_srg.sh' then manually copying/compressing the required files in the src/minecrtaft/assets and reob/minecraft/mods into a zip file (keeping the mods and assets folders in the root of the zip). This however will not generate the mcmod.info file.

##Special Thanks##
Special thanks to 
* Everyone involved in the organisation of ModJam 2
* The friendly people of the #ModJam irc channel
* Those who tested and gave feedback on my development server (despite essentially trashing the map :p)
