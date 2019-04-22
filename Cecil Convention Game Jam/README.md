# Game title: Dude, Where's My Toilet?
The Cecil Convention held at Cecil College featured a 24 hour Game Jam requiring developers to create a game under the premise of "Strange Spaces".
In this game, the player is meant to wander through the rooms of the building in search of the toilet. If you touch the floating toilet object, you win.
To add some difficulty, we created an enemy that randomly teleports between the different rooms and will damage your health if you are too close to it.
Lose all your health, and you lose the game.

# What I worked on.
	- A command framework with Ryan Lloyd, that is meant to send player input and their interactions with other game objects to a hub, which would in turn relay the information to all
	clients that are on the network. We ran out of time to implement networking but continued to develop the game within this framework.
	- Commands for user input like walking, running, and jumping.