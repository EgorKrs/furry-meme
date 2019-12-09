package com.loneliness.client.view.fxml_controller.search_data;

import com.loneliness.client.view.FilledAlert;
import com.loneliness.entity.Company;
import com.loneliness.entity.ReportingPeriod;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SearchByIDAndYear {
    @FXML
    private Stage dialogStage;
    @FXML
    private TextField company_id;

    @FXML
    private TextField year;

    private String action;

    private String title;

    private ReportingPeriod reportingPeriod=new ReportingPeriod();

    public ReportingPeriod getReportingPeriod(){
        return reportingPeriod;
    }

    public void setData(Stage dialogStage){
        this.dialogStage=dialogStage;
    }
    public void setData(Stage dialogStage, Company company){
        this.dialogStage=dialogStage;
        company_id.setText(String.valueOf(company.getCompanyId()));
        company_id.setEditable(false);
    }
    @FXML
    void finishWork() {
        if(isValid()){
            reportingPeriod.setCompanyId(Integer.parseInt(company_id.getText()));
            reportingPeriod.setYear(Integer.parseInt(year.getText()));
          dialogStage.close();
        }

    }

    private boolean isValid(){
        boolean valid=false;
        String err="";
        try {
            if (company_id.getText() == null || company_id.getText().length() == 0) {
                err += "Id компании должно быть задано";
            } else if (Integer.parseInt(company_id.getText()) < 0) {
                err += "Id компании болжно быть положительным";
            } else if (year.getText() == null || year.getText().length() == 0) {
                err += "Id начального отчётного периода должно быть задано";
            } else if (Integer.parseInt(year.getText()) < 0) {
                err += "Id начального отчётного периода болжно быть положительным";
            } else valid = true;
        } catch (NumberFormatException ex){
            err+="В полях должны быть числовые значения";
        }
        if(!valid){
            FilledAlert.getInstance().showAlert("Валидация данных",
                    "Не валидные данные", err,
                    this.dialogStage, "ERROR");
        }
        return valid;
    }

    @FXML
    void goBack(ActionEvent event) {
        dialogStage.close();
    }
}
