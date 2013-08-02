CustomFlags
===========

####A ModJam Project by nerdboy####

This is the repository for a mod I am planning on adding to the second ModJam for 2013. The mod I will be releasing is called "CustomFlags". This name likely will change coser to the date when/if I can think of a more creative name, but for the moment that will do.

The mod will allow the player to create their own flags in game using a built in designer. On the surface this does sound similar to my heraldry system in Mine & Blade: Battlegear, but the majort difference will be that I will allow the player to edit the design pixal by pixal. The reason I have chosen this idea is because:

* It is possible (I have done a few initial tests)
* It should also be possible to implement over a weekend (With a lot of coffee to help)
* After the base system is done I can start implementing other features until the deadline.

I am also imposing another persoinal limitaion to not use a single image in the mod.

##Mod Explanation##

The mod will revolve around a new block (flag pole) and a new item (flag) being added to the game. Flags can be placed on flag poles (obviously) and will wave in the wind modeled using an exponential sine cure. It should be noted that the wind in minecraft only blows north so all falgs will always point in this direction.

Right clicking while holding a flag with no block in front will open up a flag designer. The flag designer can be used to customise the design of a flag. The designer will alow the user to draw on a 32x32 canvus using a 16bit ARGB colour platelet. In addition designes can be loaded and saved using "Load" and "Save" buttons allowing the user to select a image from their file system. JFileChoosers will be used for this functionality. Additional tools for designing the flag (flood fill, line, square etc) will be implemented depending on time.


The data for the flag image will be implemented as an array of pixals. This data will be stored in the flag nbt as an array of bytes, each byte containing 2 ARGB colour components (16 bit colour is used to reduce packet size). The size of the image data packet noyt including overhead should be
* 32 * 32 = 1024 pixels
* 4 * 4 = 16 bit colour channels
* total bits = 1024 * 16 = 16,384
* total bytes = 16,384 / 8 = 2048.

For comparison map data requires 16,384 bytes for it's data (128 x* 128 * 8) making the packet data required for the flags much less than maps.


The image data will also be stored in a ready to use "Dynamic Texture" client side in a guava Cache Loader. This will allow the mod to use the most recently used textures without having to recompute, saving on CPU cycles at slight expence to RAM.
