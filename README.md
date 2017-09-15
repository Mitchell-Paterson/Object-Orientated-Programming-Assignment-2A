# Object-Orientated-Programming-Assignment-2A
A basic 2D graphical game in Java.

## Notes
MAP is made of 2d array of List<Sprite>
- When player walks into a new coord, it will look up all that are there
- Then it will figure out what happens
- Instead of walkable only, we also have a killed when walked on thing
- So instead of World.unBlocked, we have a world.applyChecks that 
- Needs to know if it's a player or not.
- If it is a player, we check if a hostile character is in the spot as well

Push goes to a map coordinate and loops through all sprites there. If pushable,
we call move using the same move that made push happen.

Move also applies Push (recursive!) and either before or after (ideally after
push) it calls an update on the switch/targets in the spots.

Update checks if there is a block in the same coordlist thing as itself and 
react accordingly.




