import java.util.Deque;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class FoodManager {
    private Position foodPosition;

    public Position getFoodPosition() {
        return foodPosition;
    }

    public Position generateFood(Position boardSize, Deque<Position> snakeBody) {
        Random random = new Random();
        Set<Position> occupiedPositions = new HashSet<>(snakeBody);

        while (true) {
            int row = random.nextInt(boardSize.getRow());
            int col = random.nextInt(boardSize.getCol());
            Position newFood = new Position(row, col);

            if (!occupiedPositions.contains(newFood)) {
                foodPosition = newFood;
                return newFood;
            }
        }
    }
}