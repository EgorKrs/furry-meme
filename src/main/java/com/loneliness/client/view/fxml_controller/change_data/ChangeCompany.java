package com.loneliness.client.view.fxml_controller.change_data;

import com.loneliness.client.controller.CommandName;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.view.FilledAlert;
import com.loneliness.client.view.fxml_controller.ManagerStartWindowController;
import com.loneliness.entity.Company;
import com.loneliness.entity.Entity;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class ChangeCompany extends ChangeData {

    private Company company;



    @FXML
    private TextField companyNameField;

    public void setDialogStage(Stage dialogStage, String action, Company company)  {
        this.dialogStage = dialogStage;
        this.action = action;
        this.company = company;
        Set<ConstraintViolation<Object>> errors = null;
        try {
            errors = (Set<ConstraintViolation<Object>>) commandProvider.
                    getCommand(CommandName.COMPANY_VALIDATION).execute(company);
            if (errors.size() == 0){
                setData(company);
                logger.info("валидация COMPANY успешна");
            }
        } catch (ControllerException e) {
            logger.catching(e);
        }

    }

    @FXML
    void finishWork() {
        if (isValid()) {
            try {
                String answer = "";
                switch (action) {
                    case "CREATE":
                        ManagerStartWindowController.setCompany(company);
                        break;
                    case "UPDATE":
                        answer = (String) commandProvider.getCommand(CommandName.UPDATE_COMPANY).execute(company);
                        break;
                    case "ADD":
                        answer = (String) commandProvider.getCommand(CommandName.CREATE_COMPANY).execute(company);
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
            Company company = new Company();
            if(this.company!=null)
            company.setCompanyId(this.company.getCompanyId());
            company.setCompanyName(companyNameField.getText());


            Set<ConstraintViolation<Object>> errors = (Set<ConstraintViolation<Object>>) commandProvider.
                    getCommand(CommandName.COMPANY_VALIDATION).execute(company);
            if (errors.size() == 0) {
                this.company = company;
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

    @Override
    public void setData(Entity entity) {
            if(entity!=null) {
                Company company=(Company)entity;
                this.company = company;
                companyNameField.setText(company.getCompanyName());
                deleteButton.setDisable(false);
                deleteButton.setVisible(true);
                addButton.setDisable(false);
                addButton.setVisible(true);
            }
    }

    @FXML
    private void delete(){
        String answer = null;
        if(company!=null) {
            try {
                answer = (String) commandProvider.getCommand(CommandName.DELETE_COMPANY).execute(company);
                FilledAlert.getInstance().showAnswer(answer, dialogStage, "Обновления данных");
            } catch (ControllerException e) {
                FilledAlert.getInstance().showAlert("Обновления данных",
                        "Ошибка", e.getMessage(),
                        this.dialogStage, "ERROR");
                logger.catching(e);
            }
        }
    }
    @FXML
    private void add(){
        if(isValid()) {
            String answer = null;
            if (company != null) {
                try {
                    answer = (String) commandProvider.getCommand(CommandName.CREATE_COMPANY).execute(company);
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

