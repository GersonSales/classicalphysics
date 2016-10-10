package javafx.physics3;


import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.animation.PathTransitionBuilder;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathBuilder;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ArcToDemo extends Application {

    private PathTransition pathTransitionEllipse;
    private PathTransition pathTransitionCircle;

    private void init(Stage primaryStage) {
        Group root = new Group();
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 600, 460));

        // Ellipse path example
        Polygon rect = new Polygon();
        rect.getPoints().addAll(new Double[]{
            0.0, 0.0,
            20.0, 10.0,
            10.0, 20.0 });
        
        //==================================
        Polygon rect2 = new Polygon();
        rect2.getPoints().addAll(new Double[]{
            0.0, 0.0,
            20.0, 10.0,
            10.0, 20.0 });
        
        rect2.maxHeight(10);
        rect2.setStrokeWidth(10);
        rect2.setFill(Color.RED);
        root.getChildren().add(rect2);
        
        //==================================
        
        //Circle rect = new Circle(); 
        //Rectangle rect = new Rectangle(0, 0, 40, 40);
        rect.maxHeight(10);
        rect.setStrokeWidth(10);
        rect.setFill(Color.ORANGE);
        root.getChildren().add(rect);
        							// x   y    inclination size    degree 
        Path path = createEllipsePath(300, 300, 20, 200, 90);
        root.getChildren().add(path);
        
        pathTransitionEllipse = PathTransitionBuilder.create()
                .duration(Duration.seconds(4))
                .path(path)
                .node(rect)
                .orientation(OrientationType.ORTHOGONAL_TO_TANGENT)
                .cycleCount(Timeline.INDEFINITE)
                .autoReverse(false)
                .build();

        /*  pathTransitionEllipse = PathTransitionBuilder.create()
                .duration(Duration.seconds(4))
                .path(path)
                .node(rect2)
                .orientation(OrientationType.ORTHOGONAL_TO_TANGENT)
                .cycleCount(Timeline.INDEFINITE)
                .autoReverse(false)
                .build();*/


    }

    private Path createEllipsePath(double centerX, double centerY, double radiusX, double radiusY, double rotate) {
        ArcTo arcTo = new ArcTo();
        arcTo.setX(centerX - radiusX + 1); // to simulate a full 360 degree celcius circle.
        arcTo.setY(centerY - radiusY);
        arcTo.setSweepFlag(false);
        arcTo.setLargeArcFlag(true);
        arcTo.setRadiusX(radiusX);
        arcTo.setRadiusY(radiusY);
        arcTo.setXAxisRotation(rotate);

        Path path = PathBuilder.create()
                .elements(
                new MoveTo(centerX - radiusX, centerY - radiusY),
                arcTo,
                new ClosePath()) // close 1 px gap.
                .build();
        path.setStroke(Color.DODGERBLUE);
        path.getStrokeDashArray().setAll(5d, 5d);
        return path;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        init(primaryStage);
        primaryStage.show();
        pathTransitionEllipse.play();
       // pathTransitionCircle.play();
    }

    public static void main(String[] args) {
        launch(args);
    }


}