# StatsExporter
A simple Spigot plugin to allow access to ingame player data via a locally hosted REST API.<br>
This is a plugin that I develop for fun and it may not receive many updates

## Setup
Download the compiled jar file and place it in your plugins folder.<br>
If needed start and stop the server once to generate a `config.yml`
In the congig.yml you can customise the hostname and port as well as wether the webserver should
start automatically.

## Usage
Access the api via http://hostname:port

Currently you can access the information as follows:
+ `/player/{uuid}` 
<br> Shows information on the player if he has ever been on the server while the plugin was enabled. 
<br> Otherwise an error is displayed.
<br> The information avialable is the following:
  > + name: the real name of the player
  > + uuid: the uuid of the player
  > + displayName: the name displayed by Minecraft
  > + online: wether the player is currently online
  > + lastSeen: the date the player was last on the server. "now" if they are currently online
  > + op: wether the player has op rights on the server
  > + canFly: wether the player is allowed to fly (e.g. they are in creative mode)
  
 + Errors
 <br> e.g. Errors occur if the given path does not exist or a uuid is invalid
 <br> They hold following information:
   > + timestamp: the current timestamp in the format yyyy-mm-dd hh:mm:ss
   > + status: a html code corresponding to the error (e.g. 404)
   > + error: the type of error that occured
   > + message: a short description with further information on the error
   > + path: the path that was entered in the url


