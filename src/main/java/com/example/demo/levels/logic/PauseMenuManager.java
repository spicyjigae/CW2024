package com.example.demo.levels.logic;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PauseMenuManager {
    private final StackPane pauseMenu;
    private boolean isPaused = false;
    private final TimelineManager timelineManager;

    public PauseMenuManager(TimelineManager timelineManager) {
        this.pauseMenu = createPauseMenu();
        this.timelineManager = timelineManager;
    }

    private StackPane createPauseMenu() {

        StackPane menu = new StackPane();
        menu.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");

        VBox content = new VBox(20);
        content.setAlignment(Pos.CENTER);

        Text pauseText = new Text("Game is paused.");
        pauseText.setFont(Font.font("Verdana", 64));

        Text resumeText = new Text("Press ESC to resume.");
        resumeText.setFont(Font.font("Verdana", 34));

        Button restartButton = new Button("Restart");
        restartButton.setStyle("-fx-font-size: 18px; -fx-background-color: #4caf93; -fx-text-fill: white;");
        restartButton.setOnAction(e -> restartLevel());

        Button exitButton = new Button("Exit");
        exitButton.setStyle("-fx-font-size: 18px; -fx-background-color: #4caf93; -fx-text-fill: white;");
        exitButton.setOnAction(e -> System.exit(0));

        content.getChildren().addAll(pauseText, resumeText, restartButton, exitButton);

        menu.getChildren().add(content);

        menu.prefWidthProperty().bind(SceneManager.getStage().widthProperty());
        menu.prefHeightProperty().bind(SceneManager.getStage().heightProperty());

        return menu;
    }

    public void togglePause() {
        isPaused = !isPaused;
        if (isPaused) {
            timelineManager.pauseGame();
            pauseMenu.toFront();
        } else {
            timelineManager.startGame();
            pauseMenu.toBack();
        }
    }

    private void restartLevel() {
        String currentLevelName = SceneManager.getInstance(null).getCurrentLevelName();
        SceneManager.getInstance(null).onEventChange(currentLevelName);
    }

    public StackPane getPauseMenu() {
        return pauseMenu;
    }
}