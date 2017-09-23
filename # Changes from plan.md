# Changes from plan

 - Update in Sprite changed to Update(Input input), so we can loop through all sprites and update
 - Does nothing in Sprite implementation

 - Added constant TRAVERSABLE = true to floor
 - Each sprite also contains a source string to load it

 - Add sprite redone to be like Eleanors example

 - Update coordinate class with equals, copy, set.
 - Update unBlocked to use just Coordinates

 - Push is now in world and requires distance, direction and location.

 - Blocks can no longer push other blocks

 - Player and block now have overrides of move, instead of the Pusher interface

 - Methods hasPressurePad, hasBlock, getSritesAt added to world.
 - Pressure pads are now linked to a block while the block is on them. This happens through World.linkPad(location).
 - The block then tells the pad to activate or deactivate when arriving or leaving

 -`targetCount` and targetsNeeded added to `World` class, with `targetCount` getting `updateTargets(int increment)` called from target `deactivate()` and `activate()`.

 -`targetsNeeded` counted at `World` constructor by looping through sprites and counting targets.