# JayGames_Spring_MVC
This version of the JayGames website will be built on the Spring MVC framework. The core purpose of the website is to allow users to sign
up, download games, and discuss their experiences on a forum. This project utilizes a Tomcat 9 server and MySQL database featured in 
XAMPP.

As a side project, after having completed an interesting tutorial with Java's Remote Method Invocation library, I saw a quick and 
convenient way to handle networking that User's might be able to enjoy. For now, I am going to work on completing the RMIMessenger, an 
Instant Messaging prototype system that let's users who know about port forwarding or hamachi, host a private group chat that their 
friends can connect to if they know the host's ip.

The RMIMessenger will be the foundation for rebuilding the PlotFour game I wrote so that players can host private games and play with
as well as chat with their friends. To move a step further, I'm thinking about creating a new table in the JAY Games database to allow
game hosts to make their game public if they like. Essentially, the database will allow me to interface a Lobby Browser within the game
client. Players who host their game publicly only need to provide their desired port and insure that their local ip address is forwarded
on the same desired port so that outside connections can reach it. Players who are looking for games can use the lobby browser and its 
provided filters to look for favorable games. Some example columns for the table could be UserID, Username, GameID, HostIP, HostPort,
PlayerLimit, HostRegion(So that players can find better connections. Maybe try pinging selection of servers after performing database
prepared statement lobby search query with http request? Could be slow and unfriendly for user. The thought of seeing ping snapshot 
to server seems nice.)

I can put in client code in the game to store the ip address and port behind the scenes so that the typical player does not need to
see this information and provide some privacy for the host. TODO: Get OpenSSL integrated with Java RMI socket factory for more secure
network.
