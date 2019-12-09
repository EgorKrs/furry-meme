package com.loneliness.client.view.fxml_controller.change_data;

import com.loneliness.client.controller.CommandName;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.view.FilledAlert;
import com.loneliness.client.view.fxml_controller.ManagerStartWindowController;
import com.loneliness.entity.Entity;
import com.loneliness.entity.ROE;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.validation.ConstraintViolation;
import java.math.BigDecimal;
import java.util.Set;

public class ChangeRoe extends ChangeData{

    private ROE roe;

    @FXML
    private MenuButton companyIds;

    @FXML
    private MenuButton initialDataIDs;

    @FXML
    private MenuButton creditIDs;

    @FXML
    private MenuButton dividendIds;

    @FXML
    private TextField companyIdField;

    @FXML
    private TextField initialDataIDField;

    @FXML
    private TextField creditIDField;

    @FXML
    private TextField dividendIDField;

    @FXML
    private TextField EBITField;

    @FXML
    private Text roeField;

    @FXML
    private Text profitabilityField;

    @FXML
    private Text RonaField;

    @FXML
    private Text FlField;

    public void setDialogStage(Stage dialogStage, String action,ROE roe,int id) {
        this.dialogStage = dialogStage;
        this.action = action;
        this.roe=roe;
        companyIdField.setText(String.valueOf(id));
        companyIdField.setEditable(false);
        Set<ConstraintViolation<Object>> errors = null;
        try {
            errors = (Set<ConstraintViolation<Object>>)commandProvider.
                    getCommand(CommandName.ROE_VALIDATION).execute(roe);
            setAllIds();
            if (errors.size() == 0) {
                setData(roe);
            }
        } catch (ControllerException e) {
            logger.catching(e);
        }

    }

    public void setAllIds() throws ControllerException {
        setCompanyIds(companyIds,companyIdField);
        setInitialDataIDs(initialDataIDs,initialDataIDField);
        setCreditIDs(creditIDs,creditIDField);
        setDividendIds(dividendIds,dividendIDField);
    }

    private void setData(ROE roe){
        companyIdField.setText(String.valueOf(roe.getCompanyId()));
        initialDataIDField.setText(String.valueOf(roe.getInitialDataId()));
        creditIDField.setText(String.valueOf(roe.getCreditId()));
        dividendIDField.setText(String.valueOf(roe.getDividendID()));
        EBITField.setText(roe.getEBIT().toString());
        roeField.setText(roe.getROE().toString());
        profitabilityField.setText(roe.getProfR().toString());
        RonaField.setText(roe.getRONA().toString());
        FlField.setText(roe.getFL().toString());
    }

    @FXML
    void calculate(MouseEvent event) {
        if(isValid()){
            try {
                roe=(ROE)commandProvider.getCommand(CommandName.CALCULATE_ALL_ROE_DATA).execute(roe);

                roeField.setText(roe.getROE().toString());
                profitabilityField.setText(roe.getProfR().toString());
                RonaField.setText(roe.getRONA().toString());
                FlField.setText(roe.getFL().toString());
            } catch (ControllerException e) {
                FilledAlert.getInstance().showAlert("Подсчет данных",
                        "Ошибка", e.getMessage(),
                        this.dialogStage, "ERROR");
                logger.catching(e);
            }
        }
    }

    @FXML
    void finishWork() {
        if (isValid()) {
            try {
                String answer = "";
                switch (action) {
                    case "CREATE":
                        ManagerStartWindowController.setRoe(roe);
                        break;
                    case "UPDATE":
                        answer = (String) commandProvider.getCommand(CommandName.UPDATE_ROE).execute(roe);
                        break;
                    case "ADD":
                        answer = (String) commandProvider.getCommand(CommandName.CREATE_ROE).execute(roe);
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

    private boolean isValid(){
        try {
            ROE roe=new ROE();
            if(this.roe!=null)
            roe.setROEId(this.roe.getROEId());
            roe.setCompanyId(Integer.parseInt(companyIdField.getText()));
            roe.setInitialDataId(Integer.parseInt(initialDataIDField.getText()));
            roe.setCreditId(Integer.parseInt(creditIDField.getText()));
            roe.setDividendID(Integer.parseInt(dividendIDField.getText()));
            roe.setEBIT(new BigDecimal(EBITField.getText()));
            Set<ConstraintViolation<Object>> errors = (Set<ConstraintViolation<Object>>)commandProvider.
                    getCommand(CommandName.ROE_VALIDATION).execute(roe);
            if (errors.size() == 0) {
                this.roe=roe;
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
        if(roe!=null) {
            try {
                answer = (String) commandProvider.getCommand(CommandName.DELETE_ROE).execute(roe);
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
            ROE roe = (ROE) entity;
            this.roe = roe;
            setData(roe);
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
            if (roe != null) {
                try {
                    answer = (String) commandProvider.getCommand(CommandName.CREATE_ROE).execute(roe);
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
