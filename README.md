# Software Engineering's project 2022: Eryantis

Group ID: PSP16

## Components

[Lorenzo Motelli] (https://github.com/LorenzoMotelli) : focus on model

[Giovanni Giuseppe Moretti] (https://github.com/JJmoretti) : focus on network

[Manuel Ianieri] (https://github.com/manuelianieri) : focus on GUI

# Features

**BASIC**: Game with 2 or 3 players, CLI implemented and normal game.

**ADVANCED**: Game with 4 players, both CLI and GUI as user interface, the server can host multiple games.

# Starting a game
There are two different JARs, one for the clients, one for the server. The JARs can be launched by command line by using the common maven command.
**NOTE**: select exclusively one of the profile! If both or none are selected, we do not guarantee the correct behaviour.

##How to play

Server launch:
```bash
cd /path/to/server_jar

#launch server
java -jar Eriantys-server-1.0.jar
```

Client launch:
```bash
cd /path/to/server_jar

#launch server
java -jar Eriantys-client-1.0.jar
```
