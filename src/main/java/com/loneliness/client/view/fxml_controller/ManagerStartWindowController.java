package com.loneliness.client.view.fxml_controller;

import com.loneliness.client.controller.CommandName;
import com.loneliness.client.controller.CommandProvider;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.view.FilledAlert;
import com.loneliness.client.view.PathManager;
import com.loneliness.client.view.ViewException;
import com.loneliness.client.view.WorkWithFXMLLoader;
import com.loneliness.client.view.fxml_controller.analysis.Wacc;
import com.loneliness.client.view.fxml_controller.change_data.*;
import com.loneliness.client.view.fxml_controller.chart.ROEChart;
import com.loneliness.client.view.fxml_controller.search_data.FindWindow;
import com.loneliness.client.view.fxml_controller.search_data.SearchByID;
import com.loneliness.client.view.fxml_controller.search_data.SearchByIDAndYear;
import com.loneliness.entity.*;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class ManagerStartWindowController {
        protected final Logger logger = LogManager.getLogger();
        private PathManager pathManager = PathManager.getInstance();
        private WorkWithFXMLLoader loader = WorkWithFXMLLoader.getInstance();
        private static Company company;
        private static Credit credit;
        private static Dividend dividend;
        private static InitialData initialData;
        private static ReportingPeriod reportingPeriod;
        private static ROE roe;
        private static SG sg;
        private static CompanyRepresentatives companyRepresentatives;
        @FXML
        private Stage dialogStage;
        @FXML
        private AnchorPane mainPane;

        public static void setCompany(Company company) {
                ManagerStartWindowController.company = company;
        }

        public static void setCompanyRepresentatives(CompanyRepresentatives companyRepresentatives) {
                ManagerStartWindowController.companyRepresentatives = companyRepresentatives;
        }

        public static CompanyRepresentatives getCompanyRepresentatives() {
                return companyRepresentatives;
        }

        public static Company getCompany() {
                return company;
        }

        public static Credit getCredit() {
                return credit;
        }

        public static Dividend getDividend() {
                return dividend;
        }

        public static InitialData getInitialData() {
                return initialData;
        }

        public static ReportingPeriod getReportingPeriod() {
                return reportingPeriod;
        }

        public static ROE getRoe() {
                return roe;
        }

        public static SG getSg() {
                return sg;
        }

        public static void setCredit(Credit credit) {
                ManagerStartWindowController.credit = credit;
        }

        public static void setDividend(Dividend dividend) {
                ManagerStartWindowController.dividend = dividend;
        }

        public static void setInitialData(InitialData initialData) {
                ManagerStartWindowController.initialData = initialData;
        }

        public static void setReportingPeriod(ReportingPeriod reportingPeriod) {
                ManagerStartWindowController.reportingPeriod = reportingPeriod;
        }

        public static void setRoe(ROE roe) {
                ManagerStartWindowController.roe = roe;
        }

        public static void setSg(SG sg) {
                ManagerStartWindowController.sg = sg;
        }


        @FXML
        void calculateSG() {
                try {

                        if (roe == null) {
                                FilledAlert.getInstance().showAlert("Подсчёт данных",
                                        "Недостаточность данных", "сначала заполните ROE",
                                        this.dialogStage, "WARNING");
                                calculateRoe();
                        }
                        dialogStage = WorkWithFXMLLoader.getInstance().createStage(PathManager.getInstance().
                                getChangeSg(), "Изменение данных");
                        ChangeSG changeSg = WorkWithFXMLLoader.getInstance().getLoader().getController();
                        changeSg.setDialogStage(dialogStage, "CREATE", sg,company.getCompanyId());
                        mainPane.getChildren().add(WorkWithFXMLLoader.getInstance().getPane());
                } catch (ViewException e) {
                        FilledAlert.getInstance().showAlert("Подсчёт данных",
                                "Подсчёт невозможен", "Что то пошло не так",
                                this.dialogStage, "ERROR");
                        logger.catching(e);
                }
        }

        @FXML
        private void calculateRoe() {
                try {
                        dialogStage = WorkWithFXMLLoader.getInstance().createStage(PathManager.getInstance().
                                getChangeRoe(), "Изменение данных");
                        ChangeRoe changeRoe = WorkWithFXMLLoader.getInstance().getLoader().getController();
                        changeRoe.setDialogStage(dialogStage, "CREATE", roe,company.getCompanyId());
                        mainPane.getChildren().add(WorkWithFXMLLoader.getInstance().getPane());
                } catch (ViewException e) {
                        FilledAlert.getInstance().showAlert("Подсчёт данных",
                                "Подсчёт невозможен", "Что то пошло не так",
                                this.dialogStage, "ERROR");
                        logger.catching(e);
                }
        }

        @FXML
        void initialize() {
                AnchorPane.setTopAnchor(mainPane, 5.0);
                AnchorPane.setLeftAnchor(mainPane, 5.0);
                AnchorPane.setRightAnchor(mainPane, 5.0);
                AnchorPane.setBottomAnchor(mainPane, 5.0);
                company = new Company();
                company.setCompanyId(5);
                credit = new Credit();
                dividend = new Dividend();
                initialData = new InitialData();
                reportingPeriod = new ReportingPeriod();
                roe = new ROE();
                sg = new SG();
        }

        @FXML
        private void addReportingPeriod() {
                try {
                        dialogStage = loader.createStage(pathManager.
                                getChangeReportingPeriod(), "Добавление данных");

                        ChangeReportingPeriod changeReportingPeriod = loader.getLoader().getController();
                        changeReportingPeriod.setDialogStage(dialogStage, "CREATE", reportingPeriod,company.getCompanyId());
                        mainPane.getChildren().add(loader.getPane());
                } catch (ViewException e) {
                        FilledAlert.getInstance().showAlert("Добавление данных",
                                "Добавление невозможно", e.getMessage(),
                                this.dialogStage, "ERROR");
                        logger.catching(e);
                }
        }

        @FXML
        private void addROE() {
                try {
                        dialogStage = loader.createStage(pathManager.getChangeRoe(), "Добавление данных");
                        ChangeRoe changeRoe = loader.getLoader().getController();
                        changeRoe.setDialogStage(dialogStage, "CREATE", roe,company.getCompanyId());
                        mainPane.getChildren().add(loader.getPane());
                } catch (ViewException e) {
                        FilledAlert.getInstance().showAlert("Добавление данных",
                                "Добавление невозможно", e.getMessage(),
                                this.dialogStage, "ERROR");
                        logger.catching(e);
                }
        }

        @FXML
        private void addSG() {
                try {
                        dialogStage = loader.createStage(pathManager.getChangeSg(), "Добавление данных");
                        ChangeSG changeSG = loader.getLoader().getController();
                        changeSG.setDialogStage(dialogStage, "CREATE", sg,company.getCompanyId());
                        mainPane.getChildren().add(loader.getPane());
                } catch (ViewException e) {
                        FilledAlert.getInstance().showAlert("Добавление данных",
                                "Добавление невозможно", e.getMessage(),
                                this.dialogStage, "ERROR");
                        logger.catching(e);
                }
        }

        @FXML
        private void addDividend() {
                try {
                        dialogStage = loader.createStage(pathManager.getChangeDividend(), "Добавление данных");
                        ChangeDividend changeDividend = loader.getLoader().getController();
                        changeDividend.setDialogStage(dialogStage, "CREATE", dividend,company.getCompanyId());
                        mainPane.getChildren().add(loader.getPane());
                } catch (ViewException e) {
                        FilledAlert.getInstance().showAlert("Добавление данных",
                                "Добавление невозможно", e.getMessage(),
                                this.dialogStage, "ERROR");
                        logger.catching(e);
                }
        }

        @FXML
        private void addInitialData() {
                try {
                        dialogStage = loader.createStage(pathManager.getChangeInitialData(), "Добавление данных");
                        ChangeInitialData controller = loader.getLoader().getController();
                        controller.setDialogStage(dialogStage, "CREATE", initialData,company.getCompanyId());
                        mainPane.getChildren().add(loader.getPane());
                } catch (ViewException e) {
                        FilledAlert.getInstance().showAlert("Добавление данных",
                                "Добавление невозможно", e.getMessage(),
                                this.dialogStage, "ERROR");
                        logger.catching(e);
                }
        }

        @FXML
        private void addCredit() {
                try {
                        dialogStage = loader.createStage(pathManager.getChangeCredit(), "Добавление данных");
                        ChangeCredit controller = loader.getLoader().getController();
                        controller.setDialogStage(dialogStage, "CREATE", credit,company.getCompanyId());
                        mainPane.getChildren().add(loader.getPane());
                } catch (ViewException e) {
                        FilledAlert.getInstance().showAlert("Добавление данных",
                                "Добавление невозможно", e.getMessage(),
                                this.dialogStage, "ERROR");
                        logger.catching(e);
                }
        }

        @FXML
        private void saveRoe() {
                try {
                        dialogStage = loader.createStage(pathManager.getChangeRoe(), "Сохранение данных");
                        ChangeRoe controller = loader.getLoader().getController();
                        controller.setDialogStage(dialogStage, "ADD", roe,company.getCompanyId());
                        mainPane.getChildren().add(loader.getPane());
                } catch (ViewException e) {
                        FilledAlert.getInstance().showAlert("Добавление данных",
                                "Добавление невозможно", e.getMessage(),
                                this.dialogStage, "ERROR");
                        logger.catching(e);
                }
        }

        @FXML
        private void saveSG() {
                try {
                        dialogStage = loader.createStage(pathManager.getChangeSg(), "Сохранение данных");
                        ChangeSG controller = loader.getLoader().getController();
                        controller.setDialogStage(dialogStage, "ADD", sg,company.getCompanyId());
                        mainPane.getChildren().add(loader.getPane());
                } catch (ViewException e) {
                        FilledAlert.getInstance().showAlert("Добавление данных",
                                "Добавление невозможно", e.getMessage(),
                                this.dialogStage, "ERROR");
                        logger.catching(e);
                }
        }

        @FXML
        private void saveReportingPeriod() {
                try {
                        dialogStage = loader.createStage(pathManager.getChangeReportingPeriod(), "Сохранение данных");
                        ChangeReportingPeriod controller = loader.getLoader().getController();
                        controller.setDialogStage(dialogStage, "ADD", reportingPeriod,company.getCompanyId());
                        mainPane.getChildren().add(loader.getPane());
                } catch (ViewException e) {
                        FilledAlert.getInstance().showAlert("Добавление данных",
                                "Добавление невозможно", e.getMessage(),
                                this.dialogStage, "ERROR");
                        logger.catching(e);
                }
        }

        @FXML
        private void saveROE() {
                try {
                        dialogStage = loader.createStage(pathManager.getChangeRoe(), "Сохранение данных");
                        ChangeRoe controller = loader.getLoader().getController();
                        controller.setDialogStage(dialogStage, "ADD", roe,company.getCompanyId());
                        mainPane.getChildren().add(loader.getPane());
                } catch (ViewException e) {
                        FilledAlert.getInstance().showAlert("Добавление данных",
                                "Добавление невозможно", e.getMessage(),
                                this.dialogStage, "ERROR");
                        logger.catching(e);
                }
        }

        @FXML
        private void saveDividend() {
                try {
                        dialogStage = loader.createStage(pathManager.getChangeDividend(), "Сохранение данных");
                        ChangeDividend controller = loader.getLoader().getController();
                        controller.setDialogStage(dialogStage, "ADD", dividend,company.getCompanyId());
                        mainPane.getChildren().add(loader.getPane());
                } catch (ViewException e) {
                        FilledAlert.getInstance().showAlert("Добавление данных",
                                "Добавление невозможно", e.getMessage(),
                                this.dialogStage, "ERROR");
                        logger.catching(e);
                }
        }

        @FXML
        private void saveInitialData() {
                try {
                        dialogStage = loader.createStage(pathManager.getChangeInitialData(), "Сохранение данных");
                        ChangeInitialData controller = loader.getLoader().getController();
                        controller.setDialogStage(dialogStage, "ADD", initialData,company.getCompanyId());
                        mainPane.getChildren().add(loader.getPane());
                } catch (ViewException e) {
                        FilledAlert.getInstance().showAlert("Добавление данных",
                                "Добавление невозможно", e.getMessage(),
                                this.dialogStage, "ERROR");
                        logger.catching(e);
                }
        }

        @FXML
        private void saveCredit() {
                try {
                        dialogStage = loader.createStage(pathManager.getChangeCredit(), "Сохранение данных");
                        ChangeCredit controller = loader.getLoader().getController();
                        controller.setDialogStage(dialogStage, "ADD", credit,company.getCompanyId());
                        mainPane.getChildren().add(loader.getPane());
                } catch (ViewException e) {
                        FilledAlert.getInstance().showAlert("Добавление данных",
                                "Добавление невозможно", e.getMessage(),
                                this.dialogStage, "ERROR");
                        logger.catching(e);
                }
        }

        @FXML
        private void loadCredit() {
                try {

                        int id = getID();
                        if (id > 0) {
                                dialogStage = loader.createStage(pathManager.getChangeCredit(), "Изменение данных");
                                ChangeCredit controller = loader.getLoader().getController();
                                credit.setCreditId(id);
                                credit = (Credit) CommandProvider.getCommandProvider().getCommand(CommandName.RECEIVE_CREDIT).execute(credit);
                                controller.setDialogStage(dialogStage, "UPDATE", credit,company.getCompanyId());
                                mainPane.getChildren().add(loader.getPane());
                        }
                } catch (ViewException | ControllerException e) {
                        FilledAlert.getInstance().showAlert("Изменение данных",
                                "Изменение невозможно", e.getMessage(),
                                this.dialogStage, "ERROR");
                        logger.catching(e);
                }
        }

        @FXML
        private void loadInitialData() {
                try {

                        int id = getID();
                        if (id > 0) {
                                dialogStage = loader.createStage(pathManager.getChangeInitialData(), "Изменение данных");
                                ChangeInitialData controller = loader.getLoader().getController();
                                initialData.setInitialDataId(id);
                                initialData = (InitialData) CommandProvider.getCommandProvider().getCommand(CommandName.RECEIVE_INITIAL_DATA).execute(initialData);
                                controller.setDialogStage(dialogStage, "UPDATE", initialData,company.getCompanyId());
                                mainPane.getChildren().add(loader.getPane());

                        }
                } catch (ViewException | ControllerException e) {
                        FilledAlert.getInstance().showAlert("Изменение данных",
                                "Изменение невозможно", e.getMessage(),
                                this.dialogStage, "ERROR");
                        logger.catching(e);
                }
        }

        @FXML
        private void loadDividend() {
                try {

                        int id = getID();
                        if (id > 0) {
                                dialogStage = loader.createStage(pathManager.getChangeDividend(), "Изменение данных");
                                ChangeDividend controller = loader.getLoader().getController();
                                dividend.setDividendId(id);
                                dividend = (Dividend) CommandProvider.getCommandProvider().getCommand(CommandName.RECEIVE_DIVIDEND).execute(dividend);
                                controller.setDialogStage(dialogStage, "UPDATE", dividend,company.getCompanyId());
                                mainPane.getChildren().add(loader.getPane());
                        }
                } catch (ViewException | ControllerException e) {
                        FilledAlert.getInstance().showAlert("Изменение данных",
                                "Изменение невозможно", e.getMessage(),
                                this.dialogStage, "ERROR");
                        logger.catching(e);
                }
        }

        @FXML
        private void loadROE() {
                try {

                        int id = getID();
                        if (id > 0) {
                                dialogStage = loader.createStage(pathManager.getChangeRoe(), "Изменение данных");
                                ChangeRoe controller = loader.getLoader().getController();
                                roe.setROEId(id);
                                roe = (ROE) CommandProvider.getCommandProvider().getCommand(CommandName.RECEIVE_ROE).execute(roe);
                                controller.setDialogStage(dialogStage, "UPDATE", roe,company.getCompanyId());
                                mainPane.getChildren().add(loader.getPane());
                        }
                } catch (ViewException | ControllerException e) {
                        FilledAlert.getInstance().showAlert("Изменение данных",
                                "Изменение невозможно", e.getMessage(),
                                this.dialogStage, "ERROR");
                        logger.catching(e);
                }
        }

        @FXML
        private void loadReportingPeriod() {
                try {

                        int id = getID();
                        if (id > 0) {
                                dialogStage = loader.createStage(pathManager.getChangeReportingPeriod(), "Изменение данных");
                                ChangeReportingPeriod controller = loader.getLoader().getController();
                                reportingPeriod.setReportingPeriodId(id);
                                reportingPeriod = (ReportingPeriod) CommandProvider.getCommandProvider().getCommand(CommandName.RECEIVE_REPORTING_PERIOD).execute(reportingPeriod);
                                controller.setDialogStage(dialogStage, "UPDATE", reportingPeriod,company.getCompanyId());
                                mainPane.getChildren().add(loader.getPane());
                        }
                } catch (ViewException | ControllerException e) {
                        FilledAlert.getInstance().showAlert("Изменение данных",
                                "Изменение невозможно", e.getMessage(),
                                this.dialogStage, "ERROR");
                        logger.catching(e);
                }
        }

        @FXML
        private void loadSG() {
                try {

                        int id = getID();
                        if (id > 0) {
                                dialogStage = loader.createStage(pathManager.getChangeSg(), "Изменение данных");
                                ChangeSG controller = loader.getLoader().getController();
                                sg.setSGId(id);
                                sg = (SG) CommandProvider.getCommandProvider().getCommand(CommandName.RECEIVE_SG).execute(sg);
                                controller.setDialogStage(dialogStage, "UPDATE", sg,company.getCompanyId());
                                mainPane.getChildren().add(loader.getPane());
                        }
                } catch (ViewException | ControllerException e) {
                        FilledAlert.getInstance().showAlert("Изменение данных",
                                "Изменение невозможно", e.getMessage(),
                                this.dialogStage, "ERROR");
                        logger.catching(e);
                }
        }

        @FXML
        private void loadRoe() {
                try {

                        int id = getID();
                        if (id > 0) {
                                dialogStage = loader.createStage(pathManager.getChangeRoe(), "Изменение данных");
                                ChangeRoe controller = loader.getLoader().getController();
                                roe.setROEId(id);
                                roe = (ROE) CommandProvider.getCommandProvider().getCommand(CommandName.RECEIVE_ROE).execute(roe);
                                controller.setDialogStage(dialogStage, "UPDATE", roe,company.getCompanyId());
                                mainPane.getChildren().add(loader.getPane());
                        }
                } catch (ViewException | ControllerException e) {
                        FilledAlert.getInstance().showAlert("Изменение данных",
                                "Изменение невозможно", e.getMessage(),
                                this.dialogStage, "ERROR");
                        logger.catching(e);
                }
        }

        private int getID() {
                try {
                        dialogStage = WorkWithFXMLLoader.getInstance().createStage(PathManager.getInstance().
                                getSearchByID(), "Загрузка данных");
                        SearchByID controller = WorkWithFXMLLoader.getInstance().getLoader().getController();
                        controller.setData(dialogStage);
                        dialogStage.showAndWait();
                        return controller.getId();
                } catch (ViewException e) {
                        FilledAlert.getInstance().showAlert("Поиск данных",
                                "Поиск невозможен", e.getMessage(),
                                this.dialogStage, "ERROR");
                        logger.catching(e);
                }
                return -1;
        }

        @FXML
        private void getState() {
                try {
                        int id = getID();
                        if (id > 0) {
                                dialogStage = WorkWithFXMLLoader.getInstance().createStage(PathManager.getInstance().
                                        getWacc(), "Анализ данных");
                                Wacc controller = WorkWithFXMLLoader.getInstance().getLoader().getController();
                                roe.setROEId(id);
                                String rez = (String) CommandProvider.getCommandProvider().getCommand(CommandName.GET_STATE).execute(roe);
                                controller.setData(rez);
                                mainPane.getChildren().add(WorkWithFXMLLoader.getInstance().getPane());
                        }
                } catch (ViewException | ControllerException e) {
                        FilledAlert.getInstance().showAlert("Поиск данных",
                                "Поиск невозможен", e.getMessage(),
                                this.dialogStage, "ERROR");
                        logger.catching(e);
                }
        }


        @FXML
        private void analysisROE() {
                analysis("ANALYSIS_ROE");
        }

        @FXML
        private void analysisSG() {
                analysis("ANALYSIS_SG");
        }

        private void analysis(String action) {
                try {
                        Stage dialogStage = loader.createStage(PathManager.getInstance().getFindWindow(), "Поиск данных");
                        FindWindow controller = loader.getLoader().getController();
                        switch (action) {
                                case "ANALYSIS_ROE":
                                        controller.setDialogStage(dialogStage, "ANALYSIS_ROE");
                                        break;
                                case "ANALYSIS_SG":
                                        controller.setDialogStage(dialogStage, "ANALYSIS_SG");
                                        break;
                        }
                        dialogStage.showAndWait();
                } catch (ViewException e) {
                        FilledAlert.getInstance().showAlert("Поиск данных",
                                "Поиск невозможен", e.getMessage(),
                                this.dialogStage, "ERROR");
                        logger.catching(e);
                }

        }

        @FXML
        private void FlChart() {
                String chart = "FL";
                createChart(chart);
        }

        @FXML
        private void ROEChart() {
                String chart = "ROE";
                createChart(chart);
        }

        @FXML
        private void RONAChart() {
                String chart = "RONA";
                createChart(chart);
        }

        @FXML
        private void profitabilityChart() {
                String chart = "PROFITABILITY";
                createChart(chart);
        }
        @FXML
        private void SGChart() {
                String chart = "SG";
                createChart(chart);
        }



        private void createChart(String chart) {
                Stage dialogStage = null;
                try {
                        dialogStage = WorkWithFXMLLoader.getInstance().createStage(PathManager.getInstance().
                                getSearchByIDAndYear(), "Поиск данных");
                        SearchByIDAndYear controller = WorkWithFXMLLoader.getInstance().getLoader().getController();
                        controller.setData(dialogStage,company);
                        dialogStage.showAndWait();
                        ReportingPeriod reportingPeriod=controller.getReportingPeriod();

                        dialogStage = WorkWithFXMLLoader.getInstance().createStage(PathManager.getInstance().
                                getROEChart(), "График экономических показателйе");
                        ROEChart roeChart=WorkWithFXMLLoader.getInstance().getLoader().getController();
                        if(chart.equals("SG")){
                                roeChart.setSgData((Map< Quarter, SG >)CommandProvider.getCommandProvider().
                                        getCommand(CommandName.FIND_SG_BY_REPORTING_PERIOD_YEAR).execute(reportingPeriod),reportingPeriod.getYear(),chart);

                        }
                        else
                        roeChart.setROeData((Map< Quarter, ROE >)CommandProvider.getCommandProvider().
                                getCommand(CommandName.FIND_ROE_BY_REPORTING_PERIOD_YEAR).execute(reportingPeriod),reportingPeriod.getYear(),chart);
                        dialogStage.show();


                } catch (ViewException | ControllerException e) {
                        FilledAlert.getInstance().showAlert("Поиск данных",
                                "Поиск невозможен", e.getMessage(),
                                this.dialogStage, "ERROR");
                        logger.catching(e);
                }

        }
        @FXML
        private void createRoeReport(){
                try {
                        String answer=(String)CommandProvider.getCommandProvider().getCommand(CommandName.CREATE_REPORT).execute(Report.ROE);
                        FilledAlert.getInstance().showAlert("Создание отчёта",
                                "Успех ", answer,
                                this.dialogStage, "INFORMATION");
                } catch (ControllerException e) {
                        FilledAlert.getInstance().showAlert("Создание отчёта",
                                "Ошибка ", e.getMessage(),
                                this.dialogStage, "ERROR");
                        logger.catching(e);
                }
        }
        @FXML
        private void createRoeYearReport(){
                try {
                        dialogStage = WorkWithFXMLLoader.getInstance().createStage(PathManager.getInstance().
                                getSearchByIDAndYear(), "Поиск данных");
                        SearchByIDAndYear controller = WorkWithFXMLLoader.getInstance().getLoader().getController();
                        controller.setData(dialogStage,company);
                        dialogStage.showAndWait();
                        ReportingPeriod reportingPeriod=controller.getReportingPeriod();
                        Report report=Report.ROE;
                        report.setCompanyId(reportingPeriod.getCompanyId());
                        report.setYear(reportingPeriod.getYear());
                        String answer=(String)CommandProvider.getCommandProvider().getCommand(CommandName.CREATE_REPORT).execute(report);
                        FilledAlert.getInstance().showAlert("Создание отчёта",
                                "Успех ", answer,
                                this.dialogStage, "INFORMATION");
                } catch (ViewException | ControllerException e) {
                        logger.catching(e);
                }

        }
}
