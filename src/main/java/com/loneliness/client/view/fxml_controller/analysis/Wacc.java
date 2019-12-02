package com.loneliness.client.view.fxml_controller.analysis;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Wacc {
    @FXML
    private Text mark;
    @FXML
    private BorderPane pane;

    @FXML
    private Text wacc;

    public void setData(String state){
        // TODO: 02.12.2019 допустим это работает
        mark.setText(state);
        switch (state){
            case "ХОРОШО":
                wacc.setText("WACC(1-T)<RONA");
                pane.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
                break;
            case "ПЛОХО":
                wacc.setText("WACC(1-T)>RONA");
                pane.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
                break;
            case "НОРМ":
                wacc.setText("WACC(1-T)=RONA");
                pane.setBackground(new Background(new BackgroundFill(Color.ORANGE, CornerRadii.EMPTY, Insets.EMPTY)));
                break;
        }
    }
}
