package com.loneliness.client.view.fxml_controller.search_data;

import com.loneliness.client.controller.CommandName;
import com.loneliness.client.controller.CommandProvider;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.view.FilledAlert;
import com.loneliness.entity.Company;
import com.loneliness.entity.Entity;
import com.loneliness.entity.ReportingPeriod;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class SearchByIDAndYear {
    @FXML
    private Stage dialogStage;
    @FXML
    private TextField company_id;
    @FXML
    private MenuButton company;
    @FXML
    private TextField year;
//    @FXML
//    private MenuButton year;

    protected void setCompanyIds(MenuButton company, TextField companyIdField) throws ControllerException {
        Collection<Company> values = ((Map<Integer, Company>) CommandProvider.getCommandProvider().getCommand(CommandName.
                RECEIVE_ALL_COMPANY).execute(new Object())).values();
        for (Entity data : values) {
            setId(data, company, companyIdField);
        }
    }


    static final List<Entity> list = new ArrayList<>(1000);

    protected void setId(Entity value, MenuButton menuButton, TextField field) {

        MenuItem item;
        EventHandler<ActionEvent> fillIDField = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                for (Entity en :
                        list) {
                    if (en.getStringValue().toString().equals(((MenuItem) e.getSource()).getText())) {
                        field.setText(String.valueOf(en.getPrimaryStringId()));
                    }
                }

                menuButton.setText(((MenuItem) e.getSource()).getText().toString());
            }
        };
        //EventHandler<ActionEvent> fillField = e -> menuButton.setText(((MenuItem)e.getSource()).getText().toString());
        //EventHandler<ActionEvent> fillNameField = e -> field.setText(((MenuItem)e.getSource()).getText());
        item = new MenuItem(String.valueOf(value.toString()));
        item.setOnAction(fillIDField);
        //menuButton.setOnAction(fillField);
        //menuButton.setText("adad");
        menuButton.getItems().add(item);
        list.add(value);
    }

    @FXML
    private void initialize() {
        try {
            setCompanyIds(company,company_id);
        } catch (ControllerException e) {
            e.printStackTrace();
        }
    }


    private String action;

    private String title;

    private ReportingPeriod reportingPeriod = new ReportingPeriod();

    public ReportingPeriod getReportingPeriod() {
        return reportingPeriod;
    }

    public void setData(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setData(Stage dialogStage, Company company) {
        this.dialogStage = dialogStage;
        company_id.setText(String.valueOf(company.getCompanyId()));
        company_id.setEditable(false);
    }

    @FXML
    void finishWork() {
        if (isValid()) {
            reportingPeriod.setCompanyId(Integer.parseInt(company_id.getText()));
            reportingPeriod.setYear(Integer.parseInt(year.getText()));
            dialogStage.close();
        }

    }

    private boolean isValid() {
        boolean valid = false;
        String err = "";
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
        } catch (NumberFormatException ex) {
            err += "В полях должны быть числовые значения";
        }
        if (!valid) {
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
