package server;

import com.github.javafaker.Faker;
import com.loneliness.entity.*;
import com.loneliness.server.controller.CommandName;
import com.loneliness.server.controller.CommandProvider;
import com.loneliness.server.controller.ControllerException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;

public class AddData {
    private static CommandProvider commandProvider=CommandProvider.getCommandProvider();
    private Faker faker=new Faker(new Locale("ru"));
    private int quantity=300;
    private static Company[] companies;
    private static ReportingPeriod[] reportingPeriods;
    private static InitialData[] initialDatas ;
    private static Credit[] credits;
    private static Dividend[] dividends;
    private static ROE[] roes;
    private static UserData[] manager;

    @BeforeClass
    public static void setTestData() throws ControllerException {
        companies=((Map<Integer,Company>)commandProvider.getCommand(CommandName.RECEIVE_ALL_COMPANY).execute(new Transmission())).values().toArray(Company[]::new);
        reportingPeriods=((Map<Integer,ReportingPeriod>)commandProvider.getCommand(CommandName.RECEIVE_ALL_REPORTING_PERIOD).execute(new Transmission())).values().toArray(ReportingPeriod[]::new);
        initialDatas=((Map<Integer,InitialData>)commandProvider.getCommand(CommandName.RECEIVE_ALL_INITIAL_DATA).execute(new Transmission())).values().toArray(InitialData[]::new);
        credits=((Map<Integer,Credit>)commandProvider.getCommand(CommandName.RECEIVE_ALL_CREDIT).execute(new Transmission())).values().toArray(Credit[]::new);
        dividends=((Map<Integer,Dividend>)commandProvider.getCommand(CommandName.RECEIVE_ALL_DIVIDEND).execute(new Transmission())).values().toArray(Dividend[]::new);
        roes=((Map<Integer,ROE>)commandProvider.getCommand(CommandName.RECEIVE_ALL_ROE).execute(new Transmission())).values().toArray(ROE[]::new);
        manager =((Map<Integer,UserData>)commandProvider.getCommand(CommandName.RECEIVE_ALL_MANAGER).execute(new Transmission())).values().toArray(UserData[]::new);
    }

    @Test
    public void addUser() throws ControllerException {
        UserData userData;
        int c=0;
        for(int i=0;i<quantity;i++){
            userData=new UserData();
            userData.setLogin(faker.name().username());
            userData.setPassword(faker.internet().password());
            userData.setEmail(faker.internet().emailAddress());
            UserData.Type type;
            switch (faker.number().numberBetween(0,3)){
                case 0:
                    type= UserData.Type.ADMIN;
                    break;
                case 2:
                    type= UserData.Type.MANAGER;
                    break;
                default:type= UserData.Type.NO_TYPE;
            }
            userData.setType(type);
            if(((String) CommandProvider.getCommandProvider().getCommand(CommandName.CREATE_USER).
                    execute(userData)).equals("Пользователь успешно создан")){
                c++;
            }

        }
        Assert.assertEquals(c,quantity);
    }
    @Test
    public void addCompany() throws ControllerException {
        int c=0;
        Company company;
        for(int i=0;i<quantity;i++){
            company=new Company();
            company.setCompanyName(faker.company().name());
            if((((String)commandProvider.getCommand(CommandName.CREATE_COMPANY).execute(company))).equals("Данные успешно добавлены")){
                c++;
            }
        }
        Assert.assertEquals(quantity,c);
    }
    @Test
    public void addContactData() throws ControllerException {
        int c=0;

        ContactDetail contactDetail;
        for (int i=0;i<quantity;i++){
            contactDetail=new ContactDetail();
            contactDetail.setCompanyId(companies[faker.number().numberBetween(0,companies.length)].getCompanyId());
            contactDetail.setEmail(faker.internet().emailAddress());
            contactDetail.setPhoneNumber(faker.phoneNumber().phoneNumber());
            if((((String)commandProvider.getCommand(CommandName.CREATE_CONTACT_DETAIL).execute(contactDetail))).equals("Данные успешно добавлены")){
                c++;
            }
        }
        Assert.assertEquals(quantity,c);
    }
    @Test
    public void addReportingPeriod() throws ControllerException {
        int c=0;

        ReportingPeriod reportingPeriod;
        for (int i=0;i<quantity;i++){
            reportingPeriod=new ReportingPeriod();
            reportingPeriod.setCompanyId(companies[faker.number().numberBetween(0,companies.length)].getCompanyId());
            reportingPeriod.setYear(faker.number().numberBetween(2010,2020));
            Quarter quarter;
            switch (faker.number().numberBetween(0,4)) {
                case 0:
                    quarter = Quarter.Q1;
                    break;
                case 1:
                    quarter = Quarter.Q2;
                    break;
                case 2:
                    quarter = Quarter.Q3;
                    break;
                default:
                    quarter = Quarter.Q4;
            }

            reportingPeriod.setQuarter(quarter);
            if((((String)commandProvider.getCommand(CommandName.CREATE_REPORTING_PERIOD).execute(reportingPeriod))).equals("Данные успешно добавлены")){
                c++;
            }
        }
        Assert.assertEquals(quantity,c);
    }

    @Test
    public void addCredit() throws ControllerException {
        int c=0;
        Calendar calendarPast = new GregorianCalendar();
        calendarPast.add(Calendar.YEAR, -1);
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.YEAR, +1);
        Credit credit;
        for (int i=0;i<quantity;i++){
            credit=new Credit();
            credit.setCompanyId(companies[faker.number().numberBetween(0,companies.length)].getCompanyId());
            java.sql.Date datePast= new java.sql.Date(faker.date().between(calendarPast.getTime(),
                    new Date()).getTime());
            credit.setDateOfCollection(datePast.toLocalDate());
            java.sql.Date date= new java.sql.Date(faker.date().between(new Date(),
                    calendar.getTime()).getTime());
            credit.setPayDate(date.toLocalDate());
            credit.setR(new BigDecimal(faker.number().randomDouble(2,1,40)));
            credit.setLoanPercentage(new BigDecimal(faker.number().randomDouble(2,1,40)));
            credit.setLoanTotal(new BigDecimal(faker.number().randomDouble(2,1,40)));
            if((((String)commandProvider.getCommand(CommandName.CREATE_CREDIT).execute(credit))).equals("Данные успешно добавлены")){
                c++;
            }
        }
        Assert.assertEquals(quantity,c);
    }
    @Test
    public void addInitialData() throws ControllerException {
        int c=0;
        InitialData initialData;
        for (int i=0;i<quantity;i++){
            initialData=new InitialData();
            initialData.setCompanyId(companies[faker.number().numberBetween(0,companies.length)].getCompanyId());
            initialData.setReportingDateId(reportingPeriods[faker.number().numberBetween(0,reportingPeriods.length)].getReportingPeriodId());
            initialData.setAssets(new BigDecimal(faker.number().randomDouble(2,1,4000)));
            initialData.setCredit(new BigDecimal(faker.number().randomDouble(2,1,4000)));
            initialData.setEquity(new BigDecimal(faker.number().randomDouble(2,1,4000)));
            initialData.setPBIT(new BigDecimal(faker.number().randomDouble(2,1,4000)));
            initialData.setSales(new BigDecimal(faker.number().randomDouble(2,1,4000)));
            if((((String)commandProvider.getCommand(CommandName.CREATE_INITIAL_DATA).execute(initialData))).equals("Данные успешно добавлены")){
                c++;
            }
        }
        Assert.assertEquals(quantity,c);
    }
    @Test
    public void addDividend() throws ControllerException {
        int c=0;
        Dividend dividend;
        for (int i=0;i<quantity;i++){
            dividend=new Dividend();
            dividend.setCompanyId(companies[faker.number().numberBetween(0,companies.length)].getCompanyId());
            dividend.setReportingPeriodId(reportingPeriods[faker.number().numberBetween(0,reportingPeriods.length)].getReportingPeriodId());
            dividend.setDividendPercentage(new BigDecimal(faker.number().randomDouble(2,1,4000)));
            dividend.setRecipient(faker.company().name());

            if((((String)commandProvider.getCommand(CommandName.CREATE_DIVIDEND).execute(dividend))).equals("Данные успешно добавлены")){
                c++;
            }
        }
        Assert.assertEquals(quantity,c);
    }
    @Test
    public void addRoe() throws ControllerException {
        int c=0;
        ROE roe;
        for (int i=0;i<quantity;i++){
            roe=new ROE();
            roe.setCompanyId(companies[faker.number().numberBetween(0,companies.length)].getCompanyId());
            roe.setInitialDataId(initialDatas[faker.number().numberBetween(0,initialDatas.length)].getInitialDataId());
            roe.setDividendID(dividends[faker.number().numberBetween(0,dividends.length)].getDividendId());
            roe.setCreditId(credits[faker.number().numberBetween(0,credits.length)].getCreditId());
            roe.setROE(new BigDecimal(faker.number().randomDouble(2,1,4000)));
            roe.setEBIT(new BigDecimal(faker.number().randomDouble(2,1,4000)));


            if((((String)commandProvider.getCommand(CommandName.CREATE_ROE).execute(roe))).equals("Данные успешно добавлены")){
                c++;
            }
        }
        Assert.assertEquals(quantity,c);
    }
    @Test
    public void addSg() throws ControllerException {
        int c = 0;
        SG sg;
        for (int i = 0; i < quantity; i++) {
            sg = new SG();
            sg.setCompanyId(companies[faker.number().numberBetween(0, companies.length)].getCompanyId());
            sg.setInitialDataId(initialDatas[faker.number().numberBetween(0, initialDatas.length)].getInitialDataId());
            sg.setDividendID(dividends[faker.number().numberBetween(0, dividends.length)].getDividendId());
            sg.setCreditId(credits[faker.number().numberBetween(0, credits.length)].getCreditId());
            sg.setRoeId(roes[faker.number().numberBetween(0, roes.length)].getROEId());
            sg.setReinvestmentProfit(new BigDecimal(faker.number().randomDouble(2, 1, 4000)));
            sg.setReinvestmentRatio(new BigDecimal(faker.number().randomDouble(2, 1, 100)));

            if ((((String) commandProvider.getCommand(CommandName.CREATE_SG).execute(sg))).equals("Данные успешно добавлены")) {
                c++;
            }
        }
        Assert.assertEquals(quantity, c);
    }
    @Test
    public void addCompanyCompanyRepresentatives() throws ControllerException {
        int c=0;
        CompanyRepresentatives companyRepresentatives;
        for (int i = 0; i< manager.length; i++){
            companyRepresentatives=new CompanyRepresentatives();
           companyRepresentatives.setCompanyId(companies[faker.number().numberBetween(0,manager.length)].getCompanyId());
           companyRepresentatives.setManagerId(manager[faker.number().numberBetween(0,manager.length)].getId());
            if ((((String) commandProvider.getCommand(CommandName.CREATE_COMPANY_REPRESENTATIVES).
                    execute(companyRepresentatives))).equals("Данные успешно добавлены")) {
                c++;
            }
        }
        Assert.assertEquals(quantity, c);
    }

}
