package com.loneliness.client.view;

import com.loneliness.client.view.ViewException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class WorkWithFXMLLoader {
    private static final WorkWithFXMLLoader instance=new WorkWithFXMLLoader();
    private final Logger logger = LogManager.getLogger();
    private volatile static  FXMLLoader loader = new FXMLLoader();
    private volatile BorderPane page;
    private WorkWithFXMLLoader(){}

    public static WorkWithFXMLLoader getInstance() {
        return instance;
    }

    public Stage createStage(String resource,String title) throws ViewException {
        try {
            loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(resource));
            page = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle(title);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(PrimaryStage.getInstance().getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            return dialogStage;
        } catch (IOException e) {
            logger.catching(e);
            throw new ViewException("Нарушена целостность программы",e.getMessage());
        }
    }

    public FXMLLoader getLoader() {
        return loader;
    }

    public void setLoader(String resource){
        loader.setLocation(getClass().getResource(resource));
    }
    public BorderPane getPane(){
        return page;
    }
}
