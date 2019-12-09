package com.loneliness.server.controller.command_implements.sg;

import com.loneliness.entity.Quarter;
import com.loneliness.entity.ReportingPeriod;
import com.loneliness.entity.SG;
import com.loneliness.server.controller.Command;
import com.loneliness.server.servise.ServiceFactory;

import java.util.Map;

public class FindSGByReportingPeriodYear implements Command<ReportingPeriod, Map<Quarter, SG>> {
    @Override
    public Map<Quarter, SG> execute(ReportingPeriod request) {
        return ServiceFactory.getInstance().getSgService().findSGByReportingPeriodYear(request);
    }
}
