import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Position boardSize = new Position(10, 10); // 10x10 board
        Position initialPosition = new Position(5, 5);
        Snake snake = new Snake(initialPosition);
        GameManager gameManager = new GameManager(boardSize, snake);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Game Started! Use UP, DOWN, LEFT, RIGHT to control the snake.");

        while (true) {
            System.out.print("Enter direction: ");
            String input = scanner.nextLine();
            gameManager.changeDirection(input);
            gameManager.moveSnake();
        }
    }
}