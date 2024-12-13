package com.example.demo.scenes;
import com.example.demo.interfaces.SceneState;
import javafx.scene.image.ImageView;
import com.example.demo.levels.logic.SceneManager;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import java.util.Objects;

/**
 * MainMenuScene handles the creation of the Main Menu scene.
 */
public class MainMenuScene implements SceneState {

    private static final String LEVEL_ONE = "com.example.demo.levels.LevelOne";
    private static final String BACKGROUND_IMAGE = "/com/example/demo/images/background/background3.jpg";

    /**
     * SceneManager object reference.
     */
    private final SceneManager sceneManager;

    /**
     * JavaFX scene object for storing the scene.
     */
    private Scene scene;

    public MainMenuScene(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    /**
     * Creates the Main Menu scene at stores it in {@code scene}.
     */
    @Override
    public void loadScene() {
        ImageView backgroundImage = new ImageView(Objects.requireNonNull(getClass().getResource(BACKGROUND_IMAGE)).toExternalForm());
        backgroundImage.setFitWidth(SceneManager.getStage().getWidth());
        backgroundImage.setFitHeight(SceneManager.getStage().getHeight());
        backgroundImage.setPreserveRatio(false);

        Rectangle dimLayer = new Rectangle(SceneManager.getStage().getWidth(), SceneManager.getStage().getHeight());
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
        stackPane.getChildren().addAll(backgroundImage, dimLayer, root);

        this.scene = new Scene(stackPane, SceneManager.getStage().getWidth(), SceneManager.getStage().getHeight());

        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.SPACE) {
                event.consume();
            }
        });
    }

    /**
     * Properly exits the scene before switching to another scene.
     */
    @Override
    public void exitScene() {
        this.scene = null;
    }

    /**
     * Retrieves the current Main Menu scene.
     * @return Main Menu scene.
     */
    @Override
    public Scene getScene() {
        return this.scene;
    }
}