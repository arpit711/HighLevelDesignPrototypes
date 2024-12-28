/*Snake
 Move()
 Directions() (left, right ,up , down) 
 SnakeLength()
*/
public class SnakeNode {
    int i, j;
    SnakeNode left, right;
    SnakeNode(int i, int j, SnakeNode left, SnakeNode right) {
        this.i = i;
        this.j = j;
        this.left = left;
        this.right = right;
    }
}
