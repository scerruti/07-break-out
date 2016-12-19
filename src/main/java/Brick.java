import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by league on 12/18/16.
 */
public class Brick extends Rectangle{
    public Brick(double x, double y, double width, double height) {
        super(x, y, width, height);
        setFill(Color.GREEN);
    }

    public boolean isHit(Ball ball) {
        return intersects(ball.getLayoutBounds());
    }
}
