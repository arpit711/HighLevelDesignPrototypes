
# **Entities and Their Responsibilities**

### Position

* `Represents a cell on the board (row, col).
* Used to track the snake's body segments and the food's location.`

### Snake

* Represents the snake's body as a collection of Position objects.
* Handles movement, growth, and direction changes.

### FoodManager

* Manages the generation of food on the board.
* Ensures food doesn’t overlap with the snake's body.
* Singleton ensures there’s only one food item in the game at a time.

### SnakeGame

* The central controller of the game.
* Manages the interaction between the Snake and FoodManager.
* Handles user inputs and game loop logic.

### Direction (Enum)

* Represents possible directions the snake can move: UP, DOWN, LEFT, RIGHT.

### // Entitty diagram how the entities should look like:

Driver CLass for managing
foodd Manager class for managing the all food related things.SnakeFood not a task of snake class as per say.

# Sample Game running example:
* Enter direction: Down
* Snake Body: [(6, 8), (5, 8), (4, 8), (4, 7)]
* Food Position: (8, 8)
* . . . . . . . . . .
* . . . . . . . . . .
* . . . . . . . . . .
* . . . . . . . . . .
* . . . . . . . S S .
* . . . . . . . . S .
* . . . . . . . . S .
* . . . . . . . . . .
* . . . . . . . . F .
* . . . . . . . . . .
* 
* Enter direction: 

