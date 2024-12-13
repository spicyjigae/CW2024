package com.example.demo.levels.logic;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * PauseMenuManager class creates the pause menu overlay that allows for restarting, exiting the game
 * and handles pausing logic such as pausing the timeline and resuming the timeline.
 */
public class PauseMenuManager {
    /**
     * StackPane object for the pause menu.
     */
    private final StackPane pauseMenu;

    /**
     * Denotes if the game is currently paused.
     */
    private boolean isPaused = false;

    /**
     * TimelineManager object reference.
     */
    private final TimelineManager timelineManager;

    public PauseMenuManager(TimelineManager timelineManager) {
        this.pauseMenu = createPauseMenu();
        this.timelineManager = timelineManager;
    }

    /**
     * Creates the pause menu overlay.
     * @return Pause menu overlay.
     */
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

    /**
     * Handles logic for toggling between pausing the game and resuming the game.
     */
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

    /**
     * Restarts level is set to the Restart button on the pause menu overlay.
     */
    private void restartLevel() {
        String currentLevelName = SceneManager.getInstance(null).getCurrentLevelName();
        SceneManager.getInstance(null).onEventChange(currentLevelName);
    }

    /**
     * Retrieves the pause menu.
     * @return Pause menu.
     */
    public StackPane getPauseMenu() {
        return pauseMenu;
    }
}