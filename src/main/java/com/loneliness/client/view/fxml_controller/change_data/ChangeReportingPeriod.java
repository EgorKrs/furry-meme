package com.loneliness.client.view.fxml_controller.change_data;

import com.loneliness.client.controller.CommandName;
import com.loneliness.client.controller.CommandProvider;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.view.FilledAlert;
import com.loneliness.client.view.fxml_controller.ManagerStartWindowController;
import com.loneliness.entity.Quarter;
import com.loneliness.entity.ROE;
import com.loneliness.entity.ReportingPeriod;
import javafx.fxml.FXML;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import javax.validation.ConstraintViolation;
import java.math.BigDecimal;
import java.util.Set;

public class ChangeReportingPeriod {
    private ToggleGroup quarter;
    @FXML
    private TextField companyIdField;

    @FXML
    private Stage dialogStage;

    private String action;

    private ReportingPeriod period;

    @FXML
    private TextField yearField;

    @FXML
    private RadioMenuItem Q1;

    @FXML
    private RadioMenuItem Q2;

    @FXML
    private RadioMenuItem Q3;

    @FXML
    private RadioMenuItem Q4;

    private CommandProvider commandProvider=CommandProvider.getCommandProvider();
    @FXML
    void initialize() {
        Q1.setToggleGroup(quarter);
        Q2.setToggleGroup(quarter);
        Q3.setToggleGroup(quarter);
        Q4.setToggleGroup(quarter);
    }
    public void setDialogStage(Stage dialogStage, String action, ReportingPeriod period)  {
        this.dialogStage = dialogStage;
        this.action = action;
        this.period=period;
        Set<ConstraintViolation<Object>> errors = null;
        try {
            errors = (Set<ConstraintViolation<Object>>)commandProvider.
                    getCommand(CommandName.REPORTING_PERIOD_VALIDATION).execute(period);
            if(errors.size() == 0){
                setData(period);
            }
        } catch (ControllerException e) {


        }

    }
    private void setData(ReportingPeriod period){
        companyIdField.setText(String.valueOf(period.getCompanyId()));
        yearField.setText(String.valueOf(period.getYear()));
        setQuarter(period);
    }
    private boolean isValid(){
        try {
            ReportingPeriod period=new ReportingPeriod();
            period.setReportingPeriodId(this.period.getReportingPeriodId());

            period.setYear(Integer.parseInt(yearField.getText()));
            period.setQuarter(getQuarter());
            period.setCompanyId(Integer.parseInt(companyIdField.getText()));

            Set<ConstraintViolation<Object>> errors = (Set<ConstraintViolation<Object>>)commandProvider.
                    getCommand(CommandName.REPORTING_PERIOD_VALIDATION).execute(period);
            if (errors.size() == 0) {
                this.period=period;
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
    private Quarter getQuarter(){
        if(Q1.isSelected()){
            return Quarter.Q1;
        }
        else if(Q2.isSelected()){
            return Quarter.Q2;
        }
        else if(Q3.isSelected()){
            return Quarter.Q3;
        }
        else return Quarter.Q4;
    }
    private void setQuarter(ReportingPeriod reportingPeriod){
        switch (reportingPeriod.getQuarter()){
            case Q1:
                Q1.setSelected(true);
                break;
            case Q2:
                Q2.setSelected(true);
                break;
            case Q3:
                Q3.setSelected(true);
                break;
            default:
                Q4.setSelected(true);
        }
    }
    @FXML private void finishWork(){
        if (isValid()) {
            try {
                String answer = "";
                switch (action) {
                    case "CREATE":
                        ManagerStartWindowController.setReportingPeriod(period);
                        break;
                    case "UPDATE":
                        answer = (String) commandProvider.getCommand(CommandName.UPDATE_REPORTING_PERIOD).execute(period);
                        break;
                    case "ADD":
                        answer = (String) commandProvider.getCommand(CommandName.CREATE_REPORTING_PERIOD).execute(period);
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
    @FXML private void goBack(){dialogStage.close();}
}