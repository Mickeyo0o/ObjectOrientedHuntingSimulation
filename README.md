# Object Oriented Hunting Simulation in Java

This project is inspired by the Friday the 13th and Saw movies, simulating a survival scenario where Prey agents must navigate a forest, find resources, and evade Predators. Prey agents aim to stay alive by seeking food, water, and shelter in hideouts, while Predators roam the forest, hunting for prey.

## Features

* Agent Behavior: Prey agents perform actions such as running, eating, drinking, and reproducing, while Predators hunt for prey and rest after a successful catch.
* Resource Management: Hideouts, water sources, and food sources have limited capacities, requiring agents to manage resources efficiently.
* Movement Restrictions: Prey agents can only travel along roads, while Predators have the freedom to move off-road. Additionally, only one Prey agent can occupy an intersection at a time.
* Health Management: Predators reduce the health of prey upon catching them, leading to eventual death if health runs out. Lack of food or water also causes damage to Prey agents.
* Dynamic Speed: Agents move at speeds determined by randomly generated values upon spawning.
* Technical Aspects: The project employs sound streaming, graphical representation using Java Swing, A* algorithm for pathfinding, and semaphores for managing intersections.

## Demo

A demo video showcasing the simulation is included in the repository. You can watch it to get a visual understanding of how the simulation works. Or it can be viewed below:


https://github.com/Mickeyo0o/ObjectOrientedHuntingSimulation/assets/119634889/3aac90be-3a35-4c06-9040-b8e5a9a9801f



## Usage

To run the simulation on your machine, ensure you have JDK 19 installed. Clone the repository and build the project. The main function is located in the Hunt.java class.

## Note

This project was developed as part of a course on Object-Oriented Programming. 

## Creator 

This project was created by Mikołaj Marmurowicz (Mickeyo0o).
