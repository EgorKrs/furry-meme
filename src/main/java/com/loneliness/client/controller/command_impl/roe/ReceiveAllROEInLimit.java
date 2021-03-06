package com.loneliness.client.controller.command_impl.roe;

import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.service.FactoryService;
import com.loneliness.client.service.ServiceException;
import com.loneliness.entity.ROE;
import com.loneliness.client.controller.Command;
import com.loneliness.server.servise.ServiceFactory;

import java.util.Map;

public class ReceiveAllROEInLimit implements Command<int[], Map<Integer, ROE>> {
    @Override
    public Map<Integer, ROE> execute(int[] request) throws ControllerException {
        try {
            return FactoryService.getInstance().getRoeService().receiveAllElemInLimit(request[0],request[1]);
        } catch (ServiceException e) {
            throw new ControllerException(e.getCause(),e.getMessage());
        }
    }
}
