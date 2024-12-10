package com.example.demo.scenes;
import com.example.demo.interfaces.SceneState;
import javafx.scene.image.ImageView;
import com.example.demo.levels.logic.SceneManager;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import java.util.Objects;

public class MainMenuScene implements SceneState {

    private static final String LEVEL_ONE = "com.example.demo.levels.LevelOne";
    private static final String BACKGROUND_IMAGE = "/com/example/demo/images/background/background3.jpg";

    private final SceneManager sceneManager;
    private Scene scene;

    public MainMenuScene(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @Override
    public void loadScene() {
        ImageView background = new ImageView(Objects.requireNonNull(getClass().getResource(BACKGROUND_IMAGE)).toExternalForm());
        background.setFitWidth(sceneManager.getStage().getWidth());
        background.setFitHeight(sceneManager.getStage().getHeight());
        background.setPreserveRatio(false);

        Rectangle dimLayer = new Rectangle(sceneManager.getStage().getWidth(), sceneManager.getStage().getHeight());
        dimLayer.setFill(new Color(0,0,0,0.5));

        VBox root = new VBox(50);
        root.setAlignment(Pos.CENTER);

        Text title = new Text("Sky Battle");
        title.setFont(Font.font("Verdana", 64));

        Button startButton = new Button("Start Game");
        startButton.setStyle("-fx-font-size: 18px; -fx-background-color: #4caf93; -fx-text-fill: white;");
        startButton.setOnAction(event -> sceneManager.onEventChange(LEVEL_ONE));

        Button exitButton = new Button("Exit");
        exitButton.setStyle("-fx-font-size: 18px; -fx-background-color: #4caf93; -fx-text-fill: white;");
        exitButton.setOnAction(event -> System.exit(0));

        root.getChildren().addAll(title, startButton, exitButton);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(background, dimLayer, root);

        this.scene = new Scene(stackPane, sceneManager.getStage().getWidth(), sceneManager.getStage().getHeight());
    }

    @Override
    public void exitScene() {
        this.scene = null;
    }

    @Override
    public Scene getScene() {
        return this.scene;
    }
}