# Rules of Minesweeper

The rules of Minesweeper are so simple and intuitive that it seems almost 
unnecessary to write them down. However, there can be variations among 
implementations of the game, and the familiar Windows 3.11 implementation is by 
no means canonical.

In this document, we will try to describe the game as generally as we can.

At the beginning of the game, the player is faced with a grid, usually 
rectangular, consisting of marked-off patches of land, usually square. Some of 
the patches are mined, some are not.

The player may step on a patch, and then the computer will reveal that:

* The patch is not mined, and none of the immediately neighboring patches are 
mined either.
* Or the patch is not mined but one or more neighboring patches are mined, the 
number will be indicated.
* Or the patch is mined and the player has lost the game.

The player may flag patches, according to his or her belief that the patches are 
mined. The player may even flag a patch on the first move.

Depending on the implementation, there is a timer which [FINISH WRITING]
