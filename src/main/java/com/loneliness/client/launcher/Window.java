package com.loneliness.client.launcher;

import com.loneliness.client.view.PathManager;
import com.loneliness.client.view.PrimaryStage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class Window extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            Reconnect.getInstance().reconnect();
            //Parent root = FXMLLoader.load(getClass().getResource(PathManager.getInstance().getAuthorisationForm()));
            //Parent root = FXMLLoader.load(getClass().getResource(PathManager.getInstance().getManagerStartWindow()));
            Parent root = FXMLLoader.load(getClass().getResource(PathManager.getInstance().getAdminStartWindow()));
            PrimaryStage.getInstance().changeStage(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void begin(){
        launch();
    }

}
