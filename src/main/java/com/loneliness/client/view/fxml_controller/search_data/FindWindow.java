package com.loneliness.client.view.fxml_controller.search_data;

import com.loneliness.client.controller.CommandName;
import com.loneliness.client.controller.CommandProvider;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.view.FilledAlert;
import com.loneliness.client.view.PathManager;
import com.loneliness.client.view.ViewException;
import com.loneliness.client.view.WorkWithFXMLLoader;
import com.loneliness.client.view.fxml_controller.ManagerStartWindowController;
import com.loneliness.client.view.fxml_controller.analysis.AnalysisROE;
import com.loneliness.client.view.fxml_controller.analysis.AnalysisSG;
import com.loneliness.entity.ROE;
import com.loneliness.entity.SG;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class FindWindow {
    private CommandProvider commandProvider=CommandProvider.getCommandProvider();
    private PathManager pathManager=PathManager.getInstance();
    private WorkWithFXMLLoader loader=WorkWithFXMLLoader.getInstance();
    private String action;
    @FXML
    private Stage dialogStage;
    @FXML
    private TextField leftID;

    @FXML
    private TextField rightID;

    @FXML
    private TextField companyIdField;

    @FXML
    private void goBack() {
        dialogStage.close();
    }

    public void setDialogStage(Stage dialogStage,String action) {
        this.dialogStage = dialogStage;
        this.action=action;
        companyIdField.setText(String.valueOf(ManagerStartWindowController.getCompany().getCompanyId()));
        companyIdField.setEditable(false);
    }

    @FXML
    private void search(MouseEvent event) {
        if (isValid()) {
            boolean finish=false;
            Stage dialogStage = null;
            try {
                switch (action) {
                    case "ANALYSIS_ROE":
                        dialogStage = loader.createStage(pathManager.getAnalysisROE(), "Сравнение данных");
                        ROE leftROE = (ROE) commandProvider.getCommand(CommandName.FIND_ROE_BY_REPORTING_PERIOD_ID).execute(Integer.parseInt(leftID.getText()));
                        ROE rightROE = (ROE) commandProvider.getCommand(CommandName.FIND_ROE_BY_REPORTING_PERIOD_ID).execute(Integer.parseInt(rightID.getText()));
                        AnalysisROE roeController = loader.getLoader().getController();
                        roeController.setAllROE(leftROE, rightROE);
                        roeController.setDialogStage(dialogStage);
                        finish=true;
                        break;
                    case "ANALYSIS_SG":
                        dialogStage = loader.createStage(pathManager.getAnalysisSG(), "Сравнение данных");
                        SG leftSG = (SG) commandProvider.getCommand(CommandName.FIND_SG_BY_REPORTING_PERIOD_ID).execute(Integer.parseInt(leftID.getText()));
                        SG rightSG = (SG) commandProvider.getCommand(CommandName.FIND_SG_BY_REPORTING_PERIOD_ID).execute(Integer.parseInt(rightID.getText()));
                        AnalysisSG sgController = loader.getLoader().getController();
                        sgController.setAllSg(leftSG, rightSG);
                        sgController.setDialogStage(dialogStage);
                        finish=true;
                        break;
                }
                assert dialogStage != null;
                dialogStage.show();
                if(finish){
                    goBack();
                }
            } catch (ControllerException | ViewException e) {
                FilledAlert.getInstance().showAlert("Поиск данных",
                        "Поиск невозможен", e.getMessage(),
                        this.dialogStage, "ERROR");
            }
        }

    }
    private boolean isValid(){
        boolean valid=false;
        String err="";
        try {
            if (companyIdField.getText() == null || companyIdField.getText().length() == 0) {
                err += "Id компании должно быть задано";
            } else if (Integer.parseInt(companyIdField.getText()) < 0) {
                err += "Id компании болжно быть положительным";
            } else if (leftID.getText() == null || leftID.getText().length() == 0) {
                err += "Id начального отчётного периода должно быть задано";
            } else if (Integer.parseInt(leftID.getText()) < 0) {
                err += "Id начального отчётного периода болжно быть положительным";
            } else if (rightID.getText() == null || rightID.getText().length() == 0) {
                err += "Id конечного отчётного периода должно быть задано";
            } else if (Integer.parseInt(rightID.getText()) < 0) {
                err += "Id конечного отчётного периода болжно быть положительным";
            } else if(rightID.getText().equals(leftID.getText())){
                err+="Должны быть заданы различные отчётные периоды";
            }else valid = true;
        }
        catch (NumberFormatException ex){
            err+="В полях должны быть числовые значения";
        }
         if(!valid){
             FilledAlert.getInstance().showAlert("Валидация данных",
                     "Не валидные данные", err,
                     this.dialogStage, "ERROR");
         }
         return valid;
    }

}
