package com.loneliness.server.controller.command_implements.reporting_period;

import com.loneliness.entity.InitialData;
import com.loneliness.entity.ReportingPeriod;
import com.loneliness.server.controller.Command;
import com.loneliness.server.servise.ServiceFactory;

public class CreateReportingPeriod implements Command <ReportingPeriod,String>{
    @Override
    public String execute(ReportingPeriod request) {
        return ServiceFactory.getInstance().getReportingPeriodService().add(request);
    }
}
