package com.loneliness.server.controller.command_implements.business;

import com.loneliness.entity.ROE;
import com.loneliness.server.controller.Command;
import com.loneliness.server.controller.ControllerException;
import com.loneliness.server.servise.ServiceException;
import com.loneliness.server.servise.ServiceFactory;

public class GetState implements Command<ROE, String> {
    @Override
    public String execute(ROE request) throws ControllerException {
        try {
            return ServiceFactory.getInstance().getBusinessService().state(request);
        } catch (ServiceException e) {
            throw new ControllerException(e.getCause(),e.getMessage());
        }
    }
}
