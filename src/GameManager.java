import java.util.Deque;

public class GameManager {
    private Snake snake;
    private FoodManager foodManager;
    private Position boardSize;
    private String direction;

    public GameManager(Position boardSize, Snake snake) {
        this.boardSize = boardSize;
        this.snake = snake;
        this.foodManager = new FoodManager();
        this.direction = "RIGHT"; // Default starting direction
        generateNewFood();
    }

    public void changeDirection(String newDirection) {
        newDirection = newDirection.toUpperCase();

        if ((direction.equals("UP") && newDirection.equals("DOWN")) ||
                (direction.equals("DOWN") && newDirection.equals("UP")) ||
                (direction.equals("LEFT") && newDirection.equals("RIGHT")) ||
                (direction.equals("RIGHT") && newDirection.equals("LEFT"))) {
            System.out.println("Invalid direction change!");
            return;
        }

        if (newDirection.equals("UP") || newDirection.equals("DOWN") ||
                newDirection.equals("LEFT") || newDirection.equals("RIGHT")) {
            direction = newDirection;
        } else {
            System.out.println("Invalid input! Use UP, DOWN, LEFT, or RIGHT.");
        }
    }

    public void moveSnake() {
        Position newHead = calculateNextPosition();

        if (isCollision(newHead)) {
            System.out.println("Game Over!");
            System.exit(0);
        }

        if (newHead.equals(foodManager.getFoodPosition())) {
            snake.move(newHead);
            snake.grow();
            generateNewFood();
        } else {
            snake.move(newHead);
        }

        printGameState();
    }

    private Position calculateNextPosition() {
        Position currentHead = snake.getHead();
        int row = currentHead.getRow();
        int col = currentHead.getCol();

        switch (direction) {
            case "UP": row--; break;
            case "DOWN": row++; break;
            case "LEFT": col--; break;
            case "RIGHT": col++; break;
        }

        return new Position(row, col);
    }

    private boolean isCollision(Position newHead) {
        if (newHead.getRow() < 0 || newHead.getRow() >= boardSize.getRow() ||
                newHead.getCol() < 0 || newHead.getCol() >= boardSize.getCol()) {
            return true;
        }

        return snake.getBody().contains(newHead);
    }

    private void generateNewFood() {
        Position food = foodManager.generateFood(boardSize, snake.getBody());
        System.out.println("New food generated at: " + food);
    }

    private void printGameState() {
        System.out.println("Snake Body: " + snake.getBody());
        System.out.println("Food Position: " + foodManager.getFoodPosition());
        drawBoard();
    }

    private void drawBoard() {
        int rows = boardSize.getRow();
        int cols = boardSize.getCol();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Position current = new Position(i, j);

                if (snake.getBody().contains(current)) {
                    System.out.print("S "); // Snake
                } else if (current.equals(foodManager.getFoodPosition())) {
                    System.out.print("F "); // Food
                } else {
                    System.out.print(". "); // Empty cell
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}