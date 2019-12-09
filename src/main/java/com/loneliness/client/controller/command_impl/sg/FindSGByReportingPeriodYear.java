package com.loneliness.client.controller.command_impl.sg;

import com.loneliness.client.controller.Command;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.service.FactoryService;
import com.loneliness.client.service.ServiceException;
import com.loneliness.entity.Quarter;
import com.loneliness.entity.ReportingPeriod;
import com.loneliness.entity.SG;

import java.util.Map;

public class FindSGByReportingPeriodYear implements Command<ReportingPeriod, Map<Quarter, SG>> {
    @Override
    public  Map<Quarter, SG> execute(ReportingPeriod request) throws ControllerException {
        try {
            return FactoryService.getInstance().getSgService().findSGByReportingPeriodYear(request);
        } catch (ServiceException e) {
            throw new ControllerException(e.getCause(),e.getMessage());
        }
    }
}
