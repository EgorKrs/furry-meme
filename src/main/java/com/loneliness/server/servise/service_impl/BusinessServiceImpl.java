package com.loneliness.server.servise.service_impl;

import com.loneliness.entity.InitialData;
import com.loneliness.entity.ROE;
import com.loneliness.entity.SG;
import com.loneliness.server.dao.DAOFactory;
import com.loneliness.server.dao.sql_dao.SQLInitialDataDAO;
import com.loneliness.server.dao.sql_dao.SQLROEDAO;
import com.loneliness.server.dao.sql_dao.SQLReportingPeriodDAO;
import com.loneliness.server.servise.ServiceException;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BusinessServiceImpl {
    private final static RoundingMode roundingMode=RoundingMode.HALF_UP;
    private final static int scale=4;
    private final static BigDecimal T=new BigDecimal(0.35);


    public BigDecimal calculateProfitability(InitialData data) throws ServiceException {//рентабельность продаж
        try {
            return (data.getPBIT().divide(data.getSales(), scale, roundingMode)).
                    multiply(new BigDecimal(100)).setScale(scale, roundingMode);
        }catch (ArithmeticException | NullPointerException e){
            throw new ServiceException(e.getMessage(),e.getCause());
        }
    }


    public BigDecimal calculateNetAssetTurnover(InitialData data) throws ServiceException { //оборачиваемость чистых активов
        try {
        return data.getSales().divide(data.getAssets(),
                scale,RoundingMode.HALF_DOWN).setScale(scale, roundingMode);
        }catch (ArithmeticException | NullPointerException e){
            throw new ServiceException(e.getMessage(),e.getCause());
        }
    }


    public BigDecimal calculateRONA(ROE data) throws ServiceException {
        try {
            InitialData initialData= SQLInitialDataDAO.getInstance().receive(data.getInitialDataId());
            return calculateProfitability(initialData).multiply(calculateNetAssetTurnover(initialData)).setScale(scale, roundingMode);
        }
        catch (NullPointerException | ServiceException e){
            throw new ServiceException(e.getMessage(),e.getCause());
        }
    }


    public BigDecimal calculateFL(ROE data) throws ServiceException {
        try {
            InitialData initialData= SQLInitialDataDAO.getInstance().receive(data.getInitialDataId());
        return (initialData.getCredit().add(initialData.getEquity())).divide(initialData.getEquity(),scale,roundingMode);
        }catch (ArithmeticException | NullPointerException e){
            throw new ServiceException(e.getMessage(),e.getCause());
        }
    }

    public BigDecimal calculateROE(ROE data) throws ServiceException {
        try {
            return calculateRONA(data).multiply(calculateFL(data)).setScale(scale, roundingMode);
        } catch (ArithmeticException | NullPointerException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }


    public BigDecimal calculateSG(SG data) throws ServiceException {
        try {
            ROE roe= SQLROEDAO.getInstance().receive(data.getRoeId());
            InitialData initialData= SQLInitialDataDAO.getInstance().receive(data.getInitialDataId());
            return ((calculateRONA(roe).multiply(calculateFL(roe))).multiply(T).multiply(initialData.getPBIT().
                    divide(roe.getEBIT(),scale,roundingMode))).multiply(SQLReportingPeriodDAO.getInstance().findPreviousEquity(initialData).
                    divide(SQLReportingPeriodDAO.getInstance().findFutureEquity(initialData),scale,roundingMode));
        } catch (ArithmeticException | NullPointerException|ServiceException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }

    }

    public BigDecimal calculateWACC(InitialData initialData) throws ServiceException {
        try {
            return SQLReportingPeriodDAO.getInstance().findCreditInPeriod(initialData.getReportingDateId()).multiply(new BigDecimal(1).min(T)).multiply( initialData.getCredit().divide( initialData.getCredit(),
                    scale,roundingMode).add(initialData.getEquity())).add( initialData.getCredit().divide(( initialData.getCredit().add(initialData.getEquity())),
                    scale,roundingMode)).add((new BigDecimal(0.07).multiply(initialData.getPBIT())).multiply(initialData.getEquity().divide((initialData.getCredit().add(initialData.getEquity())),scale,roundingMode)));
        }catch (ArithmeticException e){
            throw new ServiceException(e.getMessage(), e.getCause());
        }

    }
    public ROE calculateAllROEData(ROE note) throws ServiceException {
        note.setRONA(calculateRONA(note));
        note.setROE(calculateROE(note));
        note.setFL(calculateFL(note));
        InitialData initialData= SQLInitialDataDAO.getInstance().receive(note.getInitialDataId());
        note.setProfR(calculateProfitability(initialData));
        return note;
    }

    public String state(ROE data) throws ServiceException {
        data= DAOFactory.getInstance().getRoeDAO().receive(data);
        InitialData initialData= SQLInitialDataDAO.getInstance().receive(data.getInitialDataId());
        try {
            int res=calculateWACC(initialData).multiply(new BigDecimal("1").min(T)).compareTo(calculateRONA(data));
            if(res<0){
                return "ХОРОШО";
            }
            else if(res>0){
                return "ПЛОХО";
            }
            else return "НОРМ";
        } catch (ServiceException|ArithmeticException e) {
            return "ПЛОХО";
        } catch (NullPointerException e){
            throw new ServiceException(e.getCause(),"не валидные данные");
        }
    }


}
