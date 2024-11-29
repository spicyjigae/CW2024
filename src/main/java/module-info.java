module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.demo to javafx.fxml;
    exports com.example.demo.controller;
    opens com.example.demo.game.actors to javafx.fxml;
    opens com.example.demo.game.projectiles to javafx.fxml;
    opens com.example.demo.images to javafx.fxml;
    opens com.example.demo.game.levels to javafx.fxml;
    opens com.example.demo.game.ui to javafx.fxml;
    opens com.example.demo.game.listeners to javafx.fxml;
    opens com.example.demo.game to javafx.fxml;
}