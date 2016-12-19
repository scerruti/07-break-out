import javafx.geometry.Bounds;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**
 * Created by league on 12/18/16.
 */
public class Ball extends Circle {
    private double deltaX = 2;
    private double deltaY = 2;

    public Ball(double centerX, double centerY, double radius, Paint fill) {
        super(centerX, centerY, radius, fill);
    }

    void update() {
        this.setCenterX(getCenterX() + deltaX);
        this.setCenterY(getCenterY() + deltaY);

        final Bounds bounds = getParent().getLayoutBounds();
        final boolean atRightBorder = getCenterX() >= (bounds.getMaxX() - getRadius());
        final boolean atLeftBorder = getCenterX() <= (bounds.getMinX() + getRadius());
        final boolean atBottomBorder = getCenterY() >= (bounds.getMaxY() - getRadius());
        final boolean atTopBorder = getCenterY() <= (bounds.getMinY() + getRadius());

        if (atRightBorder || atLeftBorder) {
            deltaX *= -1;
        }
        if (atBottomBorder || atTopBorder) {
            deltaY *= -1;
        }
    }

    public void bounce() {
        deltaY *= -1;
    }
}
