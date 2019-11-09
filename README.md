# Maze Solver

Takes an image of a maze and outputs an image of the maze with a highlighted solution path.

# Solving

Uses the [A* pathfinding algorithm](https://en.wikipedia.org/wiki/A*_search_algorithm) to solve the puzzle.

# Input

Input image must

- be a black and white image. 
- have walls that are black and paths that are white.
- start in the top row and finish in the bottom row

# How to run

1. Clone repo
2. Move to where ever you want to run it form
3. Compile package from **directory above** file containing MazeSolver folder, such as `javac MazeSolver/*.java`
4. Run using `java MazeSolver.MazeSolver "path/to/image"`
5. Program outputs an image named `solved.png` in the same directory as a input image
