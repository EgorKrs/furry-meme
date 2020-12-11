package com.loneliness.client.view.fxml_controller.change_data;

import com.loneliness.client.controller.CommandName;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.view.FilledAlert;
import com.loneliness.client.view.fxml_controller.ManagerStartWindowController;
import com.loneliness.entity.Dividend;
import com.loneliness.entity.Entity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.validation.ConstraintViolation;
import java.math.BigDecimal;
import java.util.Set;

public class ChangeDividend extends ChangeData {

    private Dividend dividend;

    @FXML
    private TextField companyIdField;

    @FXML
    private TextField reportingPeriodIdField;

    @FXML
    private TextField dividendPercentageField;

    @FXML
    private TextField recipientField;

    @FXML
    void finishWork(ActionEvent event) {
        if (isValid()) {
            try {
                String answer = "";
                switch (action) {
                    case "CREATE":
                        ManagerStartWindowController.setDividend(dividend);
                        break;
                    case "UPDATE":
                        answer = (String) commandProvider.getCommand(CommandName.UPDATE_DIVIDEND).execute(dividend);
                        break;
                    case "ADD":
                        answer = (String) commandProvider.getCommand(CommandName.CREATE_DIVIDEND).execute(dividend);
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

    public void setDialogStage(Stage dialogStage, String action,  Dividend dividend,int id) {
        this.dialogStage = dialogStage;
        this.action = action;
        this.dividend=dividend;
        companyIdField.setText(String.valueOf(id));
        companyIdField.setEditable(false);
        Set<ConstraintViolation<Object>> errors = null;
        try {
            errors = (Set<ConstraintViolation<Object>>)commandProvider.
                    getCommand(CommandName.DIVIDEND_VALIDATION).execute(dividend);
            setAllIds();
            if(errors.size() == 0){
                setData(dividend);
                setAllIds();
            }
        } catch (ControllerException e) {
            logger.catching(e);
        }
    }
    public void setAllIds() throws ControllerException {
        setCompanyIds(companyIds,companyIdField);
        setReportingPeriodIDs(reportingPeriodIDs,reportingPeriodIdField);
    }
    private void setData(Dividend dividend){
        companyIdField.setText(String.valueOf(dividend.getCompanyId()));
        reportingPeriodIdField.setText(String.valueOf(dividend.getReportingPeriodId()));
        dividendPercentageField.setText(dividend.getDividendPercentage().toString());
        recipientField.setText(dividend.getRecipient());
    }

    private boolean isValid(){
        try {
            Dividend dividend=new Dividend();
            if(this.dividend!=null)
            dividend.setDividendId(this.dividend.getDividendId());
            else action="ADD";
            dividend.setCompanyId(Integer.parseInt(companyIdField.getText()));
            dividend.setReportingPeriodId(Integer.parseInt(reportingPeriodIdField.getText()));
            dividend.setDividendPercentage(new BigDecimal(dividendPercentageField.getText()));
            dividend.setRecipient(recipientField.getText());

            Set<ConstraintViolation<Object>> errors = (Set<ConstraintViolation<Object>>)commandProvider.
                    getCommand(CommandName.DIVIDEND_VALIDATION).execute(dividend);
            if (errors.size() == 0) {
                this.dividend=dividend;
                return true;
            } else {
                FilledAlert.getInstance().showAlert("Валидация ", "Ошибка ", errors, dialogStage, "ERROR");
                return false;
            }
        }
        catch (NumberFormatException e){
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
    @FXML
    private void delete(){
        String answer = null;
        if(dividend!=null) {
            try {
                answer = (String) commandProvider.getCommand(CommandName.DELETE_DIVIDEND).execute(dividend);
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
            Dividend dividend = (Dividend) entity;
            this.dividend = dividend;
            setData(dividend);
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
            if (dividend != null) {
                try {
                    answer = (String) commandProvider.getCommand(CommandName.CREATE_DIVIDEND).execute(dividend);
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
