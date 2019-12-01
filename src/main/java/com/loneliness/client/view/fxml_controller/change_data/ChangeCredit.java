package com.loneliness.client.view.fxml_controller.change_data;

import com.loneliness.client.controller.CommandName;
import com.loneliness.client.controller.CommandProvider;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.view.FilledAlert;
import com.loneliness.client.view.fxml_controller.ManagerStartWindowController;
import com.loneliness.entity.Credit;
import com.loneliness.entity.Dividend;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.validation.ConstraintViolation;
import java.math.BigDecimal;
import java.util.Set;

public class ChangeCredit {
    @FXML
    private Stage dialogStage;

    private String action;

    private Credit credit;

    private CommandProvider commandProvider=CommandProvider.getCommandProvider();

    @FXML
    private TextField companyIdField;

    @FXML
    private TextField loanPercentageField;

    @FXML
    private TextField loanTotalField;

    @FXML
    private TextField RField;
    @FXML
    private DatePicker dateOfCollection;
    @FXML
    private DatePicker payDate;

    public void setDialogStage(Stage dialogStage, String action,  Credit credit) {
        this.dialogStage = dialogStage;
        this.action = action;
        this.credit=credit;
        Set<ConstraintViolation<Object>> errors = null;
        try {
            errors = (Set<ConstraintViolation<Object>>)commandProvider.
                    getCommand(CommandName.CREDIT_VALIDATION).execute(credit);
            if(errors.size() == 0){
                setData(credit);
            }
        } catch (ControllerException e) {

        }

    }

    private void setData(Credit credit){
        companyIdField.setText(String.valueOf(credit.getCompanyId()));
        loanPercentageField.setText(String.valueOf(credit.getLoanPercentage()));
        loanTotalField.setText(credit.getLoanTotal().toString());
        RField.setText(credit.getR().toString());
        dateOfCollection.setValue(credit.getDateOfCollection());
        payDate.setValue(credit.getPayDate());
    }
    @FXML
    void finishWork(ActionEvent event) {
        if (isValid()) {
            try {
                String answer = "";
                switch (action) {
                    case "CREATE":
                        ManagerStartWindowController.setCredit(credit);
                        break;
                    case "UPDATE":
                        answer = (String) commandProvider.getCommand(CommandName.UPDATE_CREDIT).execute(credit);
                        break;
                    case "ADD":
                        answer = (String) commandProvider.getCommand(CommandName.CREATE_DIVIDEND).execute(credit);
                        break;
                }

                FilledAlert.getInstance().showAnswer(answer, dialogStage, "Обновления данных");
            } catch (ControllerException e) {
                FilledAlert.getInstance().showAlert("Подсчет данных",
                        "Ошибка", e.getMessage(),
                        this.dialogStage, "ERROR");
            }
        }
    }

    @FXML
    void goBack(ActionEvent event) {
    dialogStage.close();
    }

    private boolean isValid(){
        try {
            Credit credit=new Credit();
            credit.setCreditId(this.credit.getCreditId());
            credit.setCompanyId(Integer.parseInt(companyIdField.getText()));
            credit.setLoanPercentage(new BigDecimal(loanPercentageField.getText()));
            credit.setLoanTotal(new BigDecimal(loanTotalField.getText()));
            credit.setR(new BigDecimal(RField.getText()));
            credit.setDateOfCollection(dateOfCollection.getValue());
            credit.setPayDate(payDate.getValue());

            Set<ConstraintViolation<Object>> errors = (Set<ConstraintViolation<Object>>)commandProvider.
                    getCommand(CommandName.CREDIT_VALIDATION).execute(credit);
            if (errors.size() == 0) {
                this.credit=credit;
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

        } catch (ControllerException e) {
            FilledAlert.getInstance().showAlert("Сбой программы", "Целостность нарушена",
                    e.getMessage(), dialogStage, "ERROR");
        }
        return false;
    }
}