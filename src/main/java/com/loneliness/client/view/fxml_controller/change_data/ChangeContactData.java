package com.loneliness.client.view.fxml_controller.change_data;

import com.loneliness.client.controller.CommandName;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.view.FilledAlert;
import com.loneliness.entity.ContactDetail;
import com.loneliness.entity.Entity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class ChangeContactData  extends ChangeData {

    private ContactDetail contactDetail;

    @FXML
    private TextField companyIdField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private TextArea info;


    public void setDialogStage(Stage dialogStage, String action, ContactDetail contactDetail) {
        this.dialogStage = dialogStage;
        this.action = action;
        this.contactDetail = contactDetail;
        Set<ConstraintViolation<Object>> errors = null;
        try {
            errors = (Set<ConstraintViolation<Object>>) commandProvider.
                    getCommand(CommandName.CONTACT_DETAIL_VALIDATION).execute(contactDetail);
            setAllIds();
            if (errors.size() == 0) {
                setData(contactDetail);
            }
        } catch (ControllerException e) {
            logger.catching(e);
        }

    }

    public void setAllIds() throws ControllerException {
        setCompanyIds(companyIds,companyIdField);
    }

    private void setData(ContactDetail contactDetail) {
        companyIdField.setText(String.valueOf(contactDetail.getCompanyId()));
        emailField.setText(String.valueOf(contactDetail.getEmail()));
        phoneNumberField.setText(contactDetail.getPhoneNumber());
        info.setText(contactDetail.getInfo());

    }

    @FXML
    void finishWork(ActionEvent event) {
        if (isValid()) {
            try {
                String answer = "";
                switch (action) {
                    case "UPDATE":
                        answer = (String) commandProvider.getCommand(CommandName.UPDATE_CONTACT_DETAIL).execute(contactDetail);
                        break;
                    case "ADD":
                        answer = (String) commandProvider.getCommand(CommandName.CREATE_CONTACT_DETAIL).execute(contactDetail);
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
            ContactDetail contactDetail = new ContactDetail();
            if(this.contactDetail!=null)
            contactDetail.setContactDetailId(this.contactDetail.getContactDetailId());
            else action="ADD";
            contactDetail.setCompanyId(Integer.parseInt(companyIdField.getText()));
            contactDetail.setEmail(emailField.getText());
            contactDetail.setPhoneNumber(phoneNumberField.getText());
            contactDetail.setInfo(info.getText());


            Set<ConstraintViolation<Object>> errors = (Set<ConstraintViolation<Object>>) commandProvider.
                    getCommand(CommandName.CONTACT_DETAIL_VALIDATION).execute(contactDetail);
            if (errors.size() == 0) {
                this.contactDetail = contactDetail;
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

    @FXML
    private void delete(){
        String answer = null;
        if(contactDetail!=null) {
            try {
                answer = (String) commandProvider.getCommand(CommandName.DELETE_CONTACT_DETAIL).execute(contactDetail);
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
            ContactDetail data = (ContactDetail) entity;
            contactDetail = data;
            setData(data);
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
            if (contactDetail != null) {
                try {
                    answer = (String) commandProvider.getCommand(CommandName.CREATE_CONTACT_DETAIL).execute(contactDetail);
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
