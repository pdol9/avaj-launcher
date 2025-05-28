# avaj-launcher

## Introduction

This is an introductory project in Java programming and design patterns
(Observer, Singleton and Factory design). A UML class is provided and must
be implemented. Only basic language features and libraries are allowed,
without any build systems, to help understand the internal workings of Java.

The application is a simple airport simulator that generates different flight
scenarios, combining random weather and aircrafts' properties and coordinates.
After processing the input, the program generates a ```simulation.txt``` log file.

## Installation

To compile the program, run:
```bash
	find * -name "*.java" > sources.txt
	javac -d srcs @sources.txt
```

## Usage

Run application with following command:
```bash
	java -cp srcs simulator.Main scenario.txt
```

* There needs to be a ```scenario.txt``` file located in the root of the
repository with specified aircraft(s) and coordinates. Example of such file
is present.

## Description

* provided UML class diagram

                           +---------------------+
                           |    <<interface>>	 |
                           |	Flyable		 |
                           +---------------------+
                           |	 #WeatherTower	 |
                           +---------------------+
                           | +updateConditions() |
                           | +registerTower()    |
                           +---------------------+
                                   ^
                                   |
        +--------------------------+--------------------------+
        |                          |                          |
+----------------+     +----------------+     +----------------+
|   Helicopter   |     |    JetPlane    |     |     Baloon     |
+----------------+     +----------------+     +----------------+
| +updateCond()  |     | +updateCond()  |     | +updateCond()  |
+----------------+     +----------------+     +----------------+
        ^                          ^                          ^
        |                          |                          |
        +-----------+--------------+--------------+-----------+
                    |     Aircraft (abstract)     |
                    +-----------------------------+
                    | #long id                    |
                    | #string name                |
                    | #Coordinates coordinates    |
                    +-----------------------------+
                    | ~Aircraft(...)              |
                    +-----------------------------+
				^
				|
                      +-----------------+
                      | Coordinates	|
                      +-----------------+
                      | -longitude	|
                      | -latitude	|
                      | -height		|
                      +-----------------+
                      | +getLongitude() |
                      | +getLatitude()  |
                      | +getHeight()    |
                      +-----------------+

        +-----------------------+          +---------------------+
        |	Tower		|<>--------|     WeatherTower    |
        +-----------------------+          +---------------------+
        | -observers		|          | +getWeather()       |
        +-----------------------+          | +changeWeather()    |
        | +register()		|	   +---------------------+
        | +unregister()		|
        | #conditionChanged()	|
        +-----------------------+

        +------------------------+
        |  <<Singleton>>         |
        |   WeatherProvider      |
        +------------------------+
        | -weather[]             |
        +------------------------+
        | +getCurrentWeather()   |
        +------------------------+

        +----------------------------------+
        |	<<Singleton>>		   |
        |	AircraftFactory		   |
        +----------------------------------+
        | +newAircraft(type, name, coords) |
        +----------------------------------+
