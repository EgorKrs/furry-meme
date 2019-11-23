package com.loneliness.client.controller.command_impl.differential_indicators;

import com.loneliness.client.controller.Command;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.service.FactoryService;
import com.loneliness.client.service.ServiceException;
import com.loneliness.entity.DifferentialIndicators;

public class DeleteDifferentialIndicators implements Command<DifferentialIndicators,String> {
    @Override
    public String execute(DifferentialIndicators request) throws ControllerException {
        try {
            return FactoryService.getInstance().getDifferentialIndicatorsService().delete(request);
        } catch (ServiceException e) {
            throw new ControllerException(e.getCause(),e.getMessage());
        }
    }
}
