import java.util.Deque;
import java.util.LinkedList;

public class Snake {
    private Deque<Position> body;
    private boolean growing;

    public Snake(Position initialPosition) {
        this.body = new LinkedList<>();
        this.body.add(initialPosition);
        this.growing = false;
    }

    public Position getHead() {
        if (body.isEmpty()) {
            throw new IllegalStateException("The snake's body is empty. Game initialization failed!");
        }
        return body.getFirst();
    }

    public Deque<Position> getBody() {
        return body;
    }

    public void grow() {
        this.growing = true;
    }

    public void move(Position newHead) {
        body.addFirst(newHead);

        if (!growing) {
            body.removeLast();
        } else {
            growing = false;
        }
    }
}