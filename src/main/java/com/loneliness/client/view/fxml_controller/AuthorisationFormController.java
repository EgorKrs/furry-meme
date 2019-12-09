package com.loneliness.client.view.fxml_controller;


import com.loneliness.client.controller.CommandName;
import com.loneliness.client.controller.CommandProvider;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.launcher.Reconnect;
import com.loneliness.client.view.FilledAlert;
import com.loneliness.client.view.PathManager;
import com.loneliness.client.view.PrimaryStage;
import com.loneliness.entity.CompanyRepresentatives;
import com.loneliness.entity.UserData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AuthorisationFormController {
        @FXML
        private Stage dialogStage;
        private UserData userData = new UserData();
        @FXML
        private PasswordField passwordField;

        @FXML
        private TextField loginField;

        @FXML
        void finishWork() {
                if (isValid()) {
                        UserData userData = new UserData();
                        getAllData(userData);
                        try {
                                userData = (UserData) CommandProvider.getCommandProvider().getCommand(
                                        CommandName.AUTHORISE_USER).execute(userData);

                                switch (userData.getType()) {
                                        case ADMIN:
                                                PrimaryStage.getInstance().changeStage(FXMLLoader.load(getClass().getResource(PathManager.
                                                        getInstance().getAdminStartWindow())));
                                                break;
                                        case MANAGER:
                                                PrimaryStage.getInstance().changeStage(FXMLLoader.load(getClass().getResource(PathManager.
                                                        getInstance().getManagerStartWindow())));
                                                CompanyRepresentatives companyRepresentatives=new CompanyRepresentatives();
                                                companyRepresentatives.setManagerId(userData.getId());
                                                companyRepresentatives=(CompanyRepresentatives)
                                                        CommandProvider.getCommandProvider().getCommand(
                                                        CommandName.RECEIVE_COMPANY_REPRESENTATIVES).execute(companyRepresentatives);
                                                ManagerStartWindowController.getCompany().setCompanyId(companyRepresentatives.getCompanyId());
                                                break;
                                        case NO_TYPE:
                                                FilledAlert.getInstance().showAlert("Авторизация",
                                                        "Ошибка авторизации", "Нет такого логина и пароля или ваш статус еще не подтвержден",
                                                        dialogStage, "ERROR");
                                                break;
                                        default:
                                                FilledAlert.getInstance().showAlert("Ошибка авторизации",
                                                        "Неизвестная ошибка", "авторизуйтесь позже", dialogStage, "ERROR");
                                }
                        } catch (ControllerException e) {
                                FilledAlert.getInstance().showAlert("Ошибка авторизации",
                                        e.getMessage(), "авторизуйтесь позже", dialogStage, "ERROR");
                        } catch (IOException e) {
                                FilledAlert.getInstance().showAlert("Ошибка регистрации",
                                        "Неизвестная ошибка", "Попробуйте повторить действие позже",
                                        dialogStage, "ERROR");
                        }
                }
        }

        private void getAllData(UserData userData) {
                userData.setPassword(passwordField.getText());
                userData.setLogin(loginField.getText());
        }

        private boolean isValid() {
                String errorMessage = "";

                if (loginField.getText() == null || loginField.getText().length() == 0) {
                        errorMessage += "Не допустимый логин!\n";
                } else if (passwordField.getText() == null || passwordField.getText().length() == 0) {
                        errorMessage += "Не допустимый пароль!\n";
                }

                if (errorMessage.length() == 0) {
                        return true;
                } else {
                        FilledAlert.getInstance().showAlert("Неверный ввод",
                                "Исправьте недопустимые поля", errorMessage, dialogStage, "ERROR");
                        return false;
                }
        }

        @FXML
        public void initialize() {

                FilledAlert.getInstance().showAlert("furry-meme", "Добро пожаловать в furry-meme",
                        "Сдесь должна быть заставка, но ее съели мыши", dialogStage, "INFORMATION");
                while (!Reconnect.getInstance().getCancel().get()) {
                        if (!FilledAlert.getInstance().showAlert("Ошибка подключения",
                                "Подключение к серверу отсутствует",
                                "Нажмите ок для переподключения", dialogStage, "ERROR")) {
                                System.exit(0);
                        }
                }
        }
}

