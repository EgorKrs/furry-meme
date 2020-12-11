package com.loneliness.client.view.fxml_controller.change_data;

import com.loneliness.client.controller.CommandName;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.view.FilledAlert;
import com.loneliness.entity.Entity;
import com.loneliness.entity.UserData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class ChangeUser extends ChangeData{

    private UserData userData;

    @FXML
    private ToggleGroup type=new ToggleGroup();

    @FXML
    private TextField loginField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField emailField;

    @FXML
    private RadioMenuItem admin;

    @FXML
    private RadioMenuItem manager;

    @FXML
    private RadioMenuItem noType;

    @FXML
    protected void initialize() {
        admin.setToggleGroup(type);
        manager.setToggleGroup(type);
        noType.setToggleGroup(type);
        deleteButton.setDisable(true);
        deleteButton.setVisible(false);
        addButton.setDisable(true);
        addButton.setVisible(false);
    }

    public void setDialogStage(Stage dialogStage, String action, UserData userData) {
        this.dialogStage = dialogStage;
        this.action = action;
        this.userData = userData;
        if (userData != null) {
            setData(userData);
        }
    }

    private void setData(UserData userData) {
        loginField.setText(userData.getLogin());
        passwordField.setText(userData.getLogin());
        emailField.setText(userData.getLogin());
        setType(userData);
    }

    @FXML
    void finishWork(ActionEvent event) {
        if (isValid()) {
            try {
                String answer = "";
                switch (action) {
                    case "UPDATE":
                        answer = (String) commandProvider.getCommand(CommandName.UPDATE_USER).execute(userData);
                        break;
                    case "ADD":
                        answer = (String) commandProvider.getCommand(CommandName.CREATE_USER).execute(userData);
                        break;
                }

                FilledAlert.getInstance().showAnswer(answer, dialogStage, "Обновления данных");
            } catch (ControllerException e) {
                FilledAlert.getInstance().showAlert("Подсчет данных",
                        "Ошибка", e.getMessage(),
                        this.dialogStage, "ERROR");
                logger.catching(e);
            }
        }
    }


    private boolean isValid() {
        try {
            UserData userData = new UserData();
            if(this.userData!=null)
            userData.setId(this.userData.getId());
            else action="ADD";
            userData.setLogin(loginField.getText());
            userData.setPassword(passwordField.getText());
            userData.setEmail(emailField.getText());
            userData.setType(getType());

            Set<ConstraintViolation<Object>> errors = (Set<ConstraintViolation<Object>>) commandProvider.
                    getCommand(CommandName.USER_DATA_VALIDATION).execute(userData);
            if (errors.size() == 0) {
                this.userData = userData;
                return true;
            } else {
                FilledAlert.getInstance().showAlert("Валидация ", "Ошибка ", errors, dialogStage, "ERROR");
                return false;
            }
        } catch (NumberFormatException e) {
            FilledAlert.getInstance().showAlert("Валидация данных",
                    "Не валидные данные", "В полях должны быть заданы числовые значения",
                    this.dialogStage, "ERROR");
            logger.catching(e);

        } catch (ControllerException e) {
            FilledAlert.getInstance().showAlert("Сбой программы", "Целостность нарушена",
                    e.getMessage(), dialogStage, "ERROR");
            logger.catching(e);
        }
        return false;
    }

    private UserData.Type getType() {
        if (admin.isSelected()) {
            return UserData.Type.ADMIN;
        } else if (manager.isSelected()) {
            return UserData.Type.MANAGER;
        } else return UserData.Type.NO_TYPE;
    }

    private void setType(UserData userData) {
        switch (userData.getType()) {
            case ADMIN:
                admin.setSelected(true);
                break;
            case MANAGER:
                manager.setSelected(true);
                break;
            default:
                noType.setSelected(true);
        }
    }
    @FXML
    private void delete(){
        String answer = null;
        if(userData!=null) {
            try {
                answer = (String) commandProvider.getCommand(CommandName.DELETE_USER).execute(userData);
                FilledAlert.getInstance().showAnswer(answer, dialogStage, "Обновления данных");
            } catch (ControllerException e) {
                FilledAlert.getInstance().showAlert("Подсчет данных",
                        "Ошибка", e.getMessage(),
                        this.dialogStage, "ERROR");
                logger.catching(e);
            }
        }
    }
    @Override
    public void setData(Entity entity) {
        if(entity!=null) {
            UserData userData = (UserData) entity;
            this.userData = userData;
            setData(userData);
            deleteButton.setDisable(false);
            deleteButton.setVisible(true);
            addButton.setDisable(false);
            addButton.setVisible(true);
        }
    }
    @FXML
    private void add(){
        if(isValid()) {
            String answer = null;
            if (userData != null) {
                try {
                    answer = (String) commandProvider.getCommand(CommandName.CREATE_USER).execute(userData);
                    FilledAlert.getInstance().showAnswer(answer, dialogStage, "Обновления данных");
                } catch (ControllerException e) {
                    FilledAlert.getInstance().showAlert("Обновления данных",
                            "Ошибка", e.getMessage(),
                            this.dialogStage, "ERROR");
                    logger.catching(e);
                }
            }
        }
    }
}
