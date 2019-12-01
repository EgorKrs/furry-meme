package com.loneliness.client.view.fxml_controller.change_data;

import com.loneliness.client.controller.CommandName;
import com.loneliness.client.controller.CommandProvider;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.view.FilledAlert;
import com.loneliness.client.view.fxml_controller.ManagerStartWindowController;
import com.loneliness.entity.SG;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.validation.ConstraintViolation;
import java.math.BigDecimal;
import java.util.Set;

public class ChangeSG {
    private CommandProvider commandProvider=CommandProvider.getCommandProvider();

    @FXML
    private Stage dialogStage;

    private String action;

    private SG sg;

    @FXML
    private TextField companyIdField;

    @FXML
    private TextField initialDataIdField;

    @FXML
    private TextField creditIdField;

    @FXML
    private TextField dividendIDField;

    @FXML
    private TextField roeIdField;

    @FXML
    private TextField reinvestmentProfitField;

    @FXML
    private TextField reinvestmentRatioField;

    @FXML
    private Text sgField;

    @FXML
    void calculate(MouseEvent event) {
        if(isValid()){
            try {
                sg=(SG)commandProvider.getCommand(CommandName.CALCULATE_SG).execute(sg);
                sgField.setText(sg.getSG().toString());
            } catch (ControllerException e) {
                FilledAlert.getInstance().showAlert("Подсчет данных",
                        "Ошибка", e.getMessage(),
                        this.dialogStage, "ERROR");
            }
        }
    }

    @FXML
    void finishWork(ActionEvent event) {
        if(isValid()){
            try {
                String answer = "";
                switch (action) {
                    case "CREATE":
                        ManagerStartWindowController.setSg(sg);
                        break;
                    case "UPDATE":
                        answer = (String) commandProvider.getCommand(CommandName.UPDATE_ROE).execute(sg);
                        break;
                    case "ADD":
                        answer = (String) commandProvider.getCommand(CommandName.CREATE_ROE).execute(sg);
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

    public void setDialogStage(Stage dialogStage, String action, SG sg) {
        this.dialogStage = dialogStage;
        this.action = action;
        this.sg = sg;
        Set<ConstraintViolation<Object>> errors = null;
        try {
            errors = (Set<ConstraintViolation<Object>>)commandProvider.
                    getCommand(CommandName.SG_VALIDATION).execute(sg);
            if (errors.size() == 0) {
                setData(sg);
            }
        } catch (ControllerException e) {
            e.printStackTrace();
        }

    }

    private void setData(SG sg){
        companyIdField.setText(String.valueOf(sg.getCompanyId()));
        initialDataIdField.setText(String.valueOf(sg.getInitialDataId()));
        creditIdField.setText(String.valueOf(sg.getCreditId()));
        dividendIDField.setText(String.valueOf(sg.getDividendID()));
        roeIdField.setText(String.valueOf(sg.getRoeId()));
        reinvestmentProfitField.setText(sg.getReinvestmentProfit().toString());
        reinvestmentRatioField.setText(sg.getReinvestmentRatio().toString());
        sgField.setText(sg.getSG().toString());
    }
    private boolean isValid(){
        try {
            SG sg=new SG();
            sg.setSGId(this.sg.getSGId());
            sg.setCompanyId(Integer.parseInt(companyIdField.getText()));
            sg.setInitialDataId(Integer.parseInt(initialDataIdField.getText()));
            sg.setCreditId(Integer.parseInt(creditIdField.getText()));
            sg.setDividendID(Integer.parseInt(dividendIDField.getText()));
            sg.setRoeId(Integer.parseInt(roeIdField.getText()));
            sg.setReinvestmentProfit(new BigDecimal(reinvestmentProfitField.getText()));
            sg.setReinvestmentRatio(new BigDecimal(reinvestmentRatioField.getText()));
            Set<ConstraintViolation<Object>> errors = (Set<ConstraintViolation<Object>>)commandProvider.
                    getCommand(CommandName.SG_VALIDATION).execute(sg);
            if (errors.size() == 0) {
                this.sg=sg;
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