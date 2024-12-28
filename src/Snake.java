import java.util.Set;

public class Snake {
    SnakeNode head, tail;
    int snakeLength;
    Snake(int i, int j) {
        head = new SnakeNode(i, j, null, null);
        tail = head;
        this.snakeLength = 0;
        snakeMatrix[i][j] = 1;
    }

    public boolean isValid(int i, int j) {
        if (i >=0 && i < 20 || j>=0 && j <20 && snakeMatrix[i][j] == 0){
        return true;
        }
        return false;
    }

    public void Move(String direction) {
        int i = head.i, j = head.j;
        SnakeFood snakeFood = new SnakeFood(i ,j);
        if (direction == "left") {
            if (!isValid(i, j-1)) {
                System.out.println("game over");
                return;
            }
            snakeMatrix[i][j-1] = 1;
            head.right = new SnakeNode(i, j-1, null, null);

        } else if(direction == "right") {
            if (!isValid(i, j + 1)) {
                System.out.println("game over");
                return;
            }
            snakeMatrix[i][j + 1] = 1;
            head.right = new SnakeNode(i, j + 1, null, null);
        } else if (direction == "up") {
            if (!isValid(i - 1, j)) {
                System.out.println("game over");
                return;
            }
            snakeMatrix[i - 1][j] = 1;
            head.right = new SnakeNode(i - 1, j, null, null);

        } else {
            if (!isValid(i + 1, j)) {
                System.out.println("game over");
                return;
            }
            snakeMatrix[i + 1][j] = 1;
            head.right = new SnakeNode(i + 1, j, null, null);
        }
        SnakeNode temp = head.right;
        head = head.right;
        head.left = temp;

//        shift snake:
        int xCoordinate = tail.i;
        int yCoordinate = tail.j;
        snakeMatrix[xCoordinate][yCoordinate] = 0;
        tail = tail.right;
        tail.left = null;
    }

    public void foodIntake(SnakeFood snakeFood) {
        if (isValid(snakeFood.i, snakeFood.j)) {
            snakeLength++;
        }
    }
}

// Entitty diagram how the entities should look like:
//
// Driver CLass for managing
// foodd Manager class for managing the all food related things.SnakeFood not a task of snake class as per say.

