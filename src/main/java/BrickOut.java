/**
 * Created by league on 12/18/16.
 */

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;

public class BrickOut extends Application implements Initializable {

    public Pane gamePane;
    public Set<Brick> bricks = new HashSet<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("brick-out.fxml"));

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    public void initialize(URL location, ResourceBundle resources) {
        final Ball ball = new Ball(100.0, 100.0, 5.0, Color.BLUE);
        gamePane.getChildren().add(ball);

        final Rectangle paddle = new Rectangle(300, 320, 40, 10);
        paddle.setArcWidth(10);
        paddle.setArcHeight(10);
        paddle.setFill(Color.RED);
        gamePane.getChildren().add(paddle);
        gamePane.setOnMouseMoved(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                paddle.setX(event.getX());
            }
        });

        createBricks();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                ball.update();
                if (ball.intersects(paddle.getLayoutBounds())) {
                    ball.bounce();
                }
                Set<Brick> hit = bricks.stream().filter(i -> i.isHit(ball)).collect(Collectors.toSet());
                gamePane.getChildren().removeAll(hit);
                bricks.removeAll(hit);
            }
        };
        timer.start();
    }

    private void createBricks() {
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 12; col++) {
                bricks.add(new Brick(col*50.0, row*10.0 + 20.0, 50, 10));
            }
        }
        gamePane.getChildren().addAll(bricks);
    }
}
