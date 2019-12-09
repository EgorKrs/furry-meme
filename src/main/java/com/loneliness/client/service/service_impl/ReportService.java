package com.loneliness.client.service.service_impl;

import com.loneliness.client.service.FactoryService;
import com.loneliness.client.service.ServiceException;
import com.loneliness.entity.Report;
import com.loneliness.entity.ReportingPeriod;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ReportService {
    private static final ReportService instance = new ReportService();
    private final ROEService roeService= FactoryService.getInstance().getRoeService();
    private ReportService() {
    }

    public static ReportService getInstance() {
        return instance;
    }

    private String REPORT_pdf;
    private String REPORT_pattern;

    public String create(Report report) throws  ServiceException {
        JRBeanCollectionDataSource beanColDataSource=null;
        switch (report) {
            case ROE:
                REPORT_pattern = "Report\\QuarterReport.jrxml";
                if(report.getCompanyId()==0) {
                    REPORT_pdf = "Report\\ROEReport.pdf";
                    beanColDataSource = new JRBeanCollectionDataSource(roeService.receiveAllElem().values());
                }
                else{
                    REPORT_pdf="Report\\ROEYearReport.pdf";
                    ReportingPeriod period=new ReportingPeriod();
                    period.setYear(report.getYear());
                    period.setCompanyId(report.getCompanyId());
                    beanColDataSource = new JRBeanCollectionDataSource(roeService.findRoeByReportingPeriodYear(period).values());
                }
                break;
        }
        try {
        // Параметры
        Map<String, Object> parameters;
        parameters = new HashMap<String, Object>();
        parameters.put("DATE", new Date());
        Locale.setDefault(new Locale("ru", "RU"));
        parameters.put(JRParameter.REPORT_LOCALE, new Locale("ru", "RU"));

        File reportPattern = new File( REPORT_pattern);
        JasperDesign jasperDesign = null;

            jasperDesign = JRXmlLoader.load(reportPattern);

        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
                parameters, beanColDataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, REPORT_pdf);
        return "Отчёт успешно создан ";
    } catch (JRException e) {
        throw new ServiceException("Ошибка создание отчета по ROE ", e.getMessage());
    }
    }
}
