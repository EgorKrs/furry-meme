package com.loneliness.client.controller.command_impl.sg;

import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.service.FactoryService;
import com.loneliness.client.service.ServiceException;
import com.loneliness.entity.SG;
import com.loneliness.client.controller.Command;
import com.loneliness.server.servise.ServiceFactory;

import java.util.Map;

public class ReceiveAllSG implements Command<Void, Map<Integer, SG>> {
    @Override
    public Map<Integer, SG> execute(Void request) throws ControllerException {
        try {
            return FactoryService.getInstance().getSgService().receiveAllElem();
        } catch (ServiceException e) {
            throw new ControllerException(e.getCause(),e.getMessage());
        }
    }
}
