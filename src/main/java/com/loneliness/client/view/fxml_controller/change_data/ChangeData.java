package com.loneliness.client.view.fxml_controller.change_data;

import com.loneliness.client.controller.CommandName;
import com.loneliness.client.controller.CommandProvider;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.entity.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public abstract class ChangeData implements SetData {
    protected CommandProvider commandProvider=CommandProvider.getCommandProvider();
    protected final Logger logger = LogManager.getLogger();
    protected String action;
    @FXML
    protected Stage dialogStage;
    @FXML
    protected Button deleteButton;
    @FXML
    protected MenuButton companyIds;

    @FXML
    protected MenuButton initialDataIDs;

    @FXML
    protected MenuButton usersIds;

    @FXML
    protected MenuButton creditIDs;

    @FXML
    protected MenuButton dividendIds;

    @FXML
    protected MenuButton idROE;

    @FXML
    protected MenuButton reportingPeriodIDs;

    @FXML
    protected Button addButton;

    @FXML
    protected void initialize(){
        deleteButton.setDisable(true);
        deleteButton.setVisible(false);
        addButton.setDisable(true);
        addButton.setVisible(false);
    }
    @FXML
    void goBack(ActionEvent event) {
        dialogStage.close();
    }

    public void setDialogStage(Stage dialogStage, String action)  {
        this.dialogStage = dialogStage;
        this.action = action;
    }

    protected void setUsersIds(MenuButton usersIds, TextField usersIdField) throws ControllerException {
        Collection<Company> values=((Map<Integer,Company>)commandProvider.getCommand(CommandName.
                RECEIVE_ALL_MANAGER).execute(new Object())).values();
        for (Entity data : values) {
            setId(data,usersIds,usersIdField);
        }
    }

    protected void setCompanyIds(MenuButton company, TextField companyIdField) throws ControllerException {
        Collection<Company> values=((Map<Integer,Company>)commandProvider.getCommand(CommandName.
                RECEIVE_ALL_COMPANY).execute(new Object())).values();
        for (Entity data : values) {
            setId(data,company,companyIdField);
        }
    }
    protected void setInitialDataIDs(MenuButton initialDataIDs,TextField initialDataIDField) throws ControllerException {
        Collection<InitialData> values=((Map<Integer,InitialData>)commandProvider.getCommand(CommandName.
                RECEIVE_ALL_INITIAL_DATA).execute(new Object())).values();
        for (Entity data : values) {
            setId(data,initialDataIDs,initialDataIDField);
        }
    }
    protected void setCreditIDs(MenuButton creditIDs,TextField creditIDField) throws ControllerException {
        Collection<Credit> values=((Map<Integer,Credit>)commandProvider.getCommand(CommandName.
                RECEIVE_ALL_CREDIT).execute(new Object())).values();
        for (Entity data : values) {
            setId(data,creditIDs,creditIDField);
        }
    }
    protected void setDividendIds(MenuButton dividendIds,TextField dividendIDField) throws ControllerException {
        Collection<Dividend> values=((Map<Integer,Dividend>)commandProvider.getCommand(CommandName.
                RECEIVE_ALL_DIVIDEND).execute(new Object())).values();
        for (Entity data : values) {
            setId(data,dividendIds,dividendIDField);
        }
    }

    protected void setIdROE( MenuButton idROE,TextField roeIdField) throws ControllerException {
        Collection<ROE> values=((Map<Integer,ROE>)commandProvider.getCommand(CommandName.
                RECEIVE_ALL_ROE).execute(new Object())).values();
        for (Entity data : values) {
            setId(data,idROE,roeIdField);
        }
    }

    protected void setReportingPeriodIDs(MenuButton reportingPeriodIDs, TextField reportingPeriodIdField) throws ControllerException {
        Collection<ReportingPeriod> values=((Map<Integer,ReportingPeriod>)commandProvider.getCommand(CommandName.
                RECEIVE_ALL_REPORTING_PERIOD).execute(new Object())).values();
        for (Entity data : values) {
            setId(data,reportingPeriodIDs,reportingPeriodIdField);
        }
    }




  protected void setId(Entity value,MenuButton menuButton,TextField field){

      MenuItem item;
      if (value.getClass().getName().equals("con.loneliness.entity.Client")){
          EventHandler<ActionEvent> fillIDField = new EventHandler<ActionEvent>() {
              @Override
              public void handle(ActionEvent e) {
                  for (Entity entity :
                          en) {
                      if (entity.getStringValue().get().equals(((MenuItem) e.getSource()).getText())) {
                          field.setText(entity.getPrimaryStringId());
                          menuButton.setText(entity.getStringValue().get());
                      }

                  }
              }};
          item=new MenuItem(value.getStringValue().get());
         // item=new MenuItem(value.toString());
         // item=new MenuItem(value.getStringValue().get());
          item.setOnAction(fillIDField);
      }
      else {
          EventHandler<ActionEvent> fillIDField = new EventHandler<ActionEvent>() {
              @Override
              public void handle(ActionEvent e) {
                  for (Entity entity :
                          en) {
                      if (entity.getStringValue().get().equals(((MenuItem) e.getSource()).getText())) {
                          field.setText(entity.getPrimaryStringId());
                          menuButton.setText(entity.getStringValue().get());
                      }

                  }
              }};
          item=new MenuItem(value.getStringValue().get());
          //item=new MenuItem(value.toString());
        //  item=new MenuItem(value.toString());
          item.setOnAction(fillIDField);
      }

      menuButton.getItems().add(item);
      en.add(value);



        /*
        MenuItem item;
        EventHandler<ActionEvent> fillIDField = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                field.setText(((MenuItem) e.getSource()).getText());
                for (Entity ent :
                        en) {

                    if (ent.getStringValue().get().equals(((MenuItem) e.getSource()).getText())) {
                        field.setText(ent.getPrimaryStringId());
                        menuButton.setText(ent.getStringValue().get());
                    }

                    //if(((MenuItem) e.getSource()).getText().equals(String.valueOf(ent.getPrimaryStringId())))

                        //  if(((MenuItem) e.getSource()).getText().equals(ent.getPrimaryStringId()))
                   //     menuButton.setText(ent.getStringValue().toString());
                }
            }
        };


      item=new MenuItem(value.getStringValue().get());

      //   item=new MenuItem(String.valueOf(value.getStringValue()));

     // item=new MenuItem(value.toString());

        //item=new MenuItem(String.valueOf(value.getPrimaryStringId()));
        item.setOnAction(fillIDField);
        menuButton.getItems().add(item);
        en.add(value);*/
}
 public static ArrayList<Entity> en=new ArrayList<>();


}


