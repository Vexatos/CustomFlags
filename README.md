CustomFlags
===========

####A ModJam Project by nerdboy####

This is the repository for a mod I am planning on adding to the second ModJam for 2013. The mod I will be releasing is called "CustomFlags". This name likely will change coser to the date when/if I can think of a more creative name, but for the moment that will do.


The mod will allow the player to create their own flags in game using a built in designer. On the surface this does sound similar to my heraldry system in Mine & Blade: Battlegear, but the majort difference will be that I will allow the player to edit the design pixal by pixal. The reason I have chosen this idea is because:

* It is possible (I have done a few initial tests)
* It should also be possible to implement over a weekend (With a lot of coffee to help)
* After the base system is done I can start implementing other features until the deadline.

##Mod Explanation##
The mod will focus around a new block (A flag) being added to the game. Initially, flags will be able to be placed on top of other "normal solid" blocks. When right clicked in the air a flag designer gui will appear. This will allow the user to design the flag in a pixal by pixal basis. The specifications of the design are as follows

* Flags will be either 16x16 or 32x32 pixels in size (I haven't decided yet)
* Flag pixels will be in 12 bit colour (4 bits for each red, green and blue)
    + This should give users more than enough colour depth while reducing the packet size by 2/3 from using 32 bit ARGB
    + See https://en.wikipedia.org/wiki/List_of_monochrome_and_RGB_palettes#12-bit_RGB for a short description
    + See RGB_12bits_palette.png for the colour test pallet (in the "plan images" folder)
* If I have time I will also include a 1 bit alpha channel (i.e. full transparency or none)

This will mean the total packet size will be (not including overhead)

* 16*16*12 / 8 = 384 bytes for 16 x 16 without alpha bit
* 16*16*13 / 8 = 416 bytes for 16 x 16 with an alpha bit
* 32*32*12 / 8 = 1536 bytes for 32 x 32 without an alpha bit
* 32*32*13 / 8 = 1664 bytes for 32 x 32 with an alpha bit

For comparison the default map data uses 16,384 bytes for it's data (128x128*8) making the packet data required for the flags much less than maps.


A mock-up for the GUI is available in the file GUI Mockup.png (in the "plan images" folder). This is show in a much higher resolution than the final version (for ease of viweing). This is also if i get everything planned working.



The only things that have been added to this repo are the explination/plan files as well as my scripts that I will use to aut-commit every 15 minutes (and some test files)
