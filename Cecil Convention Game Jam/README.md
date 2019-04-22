# Game title: Dude, Where's My Toilet?
The Cecil Convention held at Cecil College featured a 24 hour Game Jam requiring developers to create a game under the premise of "Strange Spaces".
In this game, the player is meant to wander through the rooms of the building in search of the toilet. If you touch the floating toilet object, you win.
To add some difficulty, we created an enemy that randomly teleports between the different rooms and will damage your health if you are too close to it.
Lose all your health, and you lose the game.

# What I worked on.
	- A command framework with Ryan Lloyd, that is meant to send player input and their interactions with other game objects to a hub, which 
	would in turn relay the information to all clients that are on the network. We ran out of time to implement networking but continued to 
	develop the game within this framework.
	- Commands for user input like walking, running, and jumping.
	- More commands to add and remove objects in the playing scene and object trigger events.
	- The enemy (aka The Thing). Randomly moving it between different rooms. Causing it to damage the player (more commands). 
	- Writing the initialization code to store vectors from each room into a list for randomization.
	- Spawning in powerup objects at random places in the room as well as the Toilet (goal).
	- Integrating art assets with the code such as the enemy, powerups, and HUD.
	
Most of the judges scored us pretty fairly, and we had pleasant crowd favor with our game as silly as it was. Our team did not win unfortunately.
Time was a huge constraint and I did not sleep during the competition so that I could get more work done. 
Reflecting on the project, the networking framework Ryan and I wanted to implement is a good foundation for a bigger project with more time.