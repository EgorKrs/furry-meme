package com.loneliness.server.controller.command_implements.roe;

import com.loneliness.entity.ROE;
import com.loneliness.entity.Transmission;
import com.loneliness.server.controller.Command;
import com.loneliness.server.servise.ServiceFactory;

import java.util.Map;

public class ReceiveAllROE implements Command<Transmission, Map<Integer, ROE>> {
    @Override
    public Map<Integer, ROE> execute(Transmission request) {
        return ServiceFactory.getInstance().getRoeService().receiveAll();
    }
}
