package com.loneliness.client.view.fxml_controller.search_data;

import com.loneliness.client.controller.CommandName;
import com.loneliness.client.controller.CommandProvider;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.view.FilledAlert;
import com.loneliness.entity.Company;
import com.loneliness.entity.Entity;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class SearchByID {

    @FXML
    private Stage dialogStage;
    @FXML
    private TextField id;
    @FXML
    private MenuButton ID;

    private String action;
    static final List<Entity> list=new ArrayList<>(1000);
    protected void setUsersIds(MenuButton usersIds, TextField usersIdField) throws ControllerException {
        Collection<Company> values=((Map<Integer,Company>) CommandProvider.getCommandProvider().getCommand(CommandName.
                RECEIVE_ALL_MANAGER).execute(new Object())).values();
        for (Entity data : values) {
            setId(data,usersIds,usersIdField);
        }
    }
    @FXML
    private void initialize() throws ControllerException {

       setUsersIds(ID,id);
    }

    protected void setId(Entity value, MenuButton menuButton, TextField field){

        MenuItem item;
        EventHandler<ActionEvent> fillIDField = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                for (Entity en :
                        list) {
                    if(en.getStringValue().toString().equals(((MenuItem) e.getSource()).getText())){
                        field.setText(String.valueOf(en.getPrimaryStringId()));
                    }
                }

                menuButton.setText(((MenuItem) e.getSource()).getText().toString());
            }
        };
        //EventHandler<ActionEvent> fillField = e -> menuButton.setText(((MenuItem)e.getSource()).getText().toString());
        //EventHandler<ActionEvent> fillNameField = e -> field.setText(((MenuItem)e.getSource()).getText());
        item=new MenuItem(String.valueOf(value.toString()));
        item.setOnAction(fillIDField);
        //menuButton.setOnAction(fillField);
        //menuButton.setText("adad");
        menuButton.getItems().add(item);
        list.add(value);
    }


    public void setData(Stage dialogStage){
        this.dialogStage=dialogStage;
    }
    @FXML
    void finishWork() {
        if(isValid()){
            goBack();
        }
    }

    private boolean isValid(){
        boolean valid=false;
        String err="";
        try {
            if (id.getText() == null || id.getText().length() == 0) {
                err += "Id данных должно быть задано";
            } else if (Integer.parseInt(id.getText()) < 0) {
                err += "Id данных болжно быть положительным";
            } else valid = true;
        } catch (NumberFormatException ex){
            err+="В поле должны быть числовые значения";
        }
        if(!valid){
            FilledAlert.getInstance().showAlert("Валидация данных",
                    "Не валидные данные", err,
                    this.dialogStage, "ERROR");
        }
        return valid;
    }

    @FXML
    void goBack() {
        dialogStage.close();
    }

    public int getId() {
        return Integer.parseInt(id.getText());
    }
}
