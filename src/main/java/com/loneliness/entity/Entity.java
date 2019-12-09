package com.loneliness.entity;

import com.loneliness.client.controller.ControllerException;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public interface Entity {
    String getPrimaryStringId();
    IntegerProperty getIntegerId();
    StringProperty getStringValue();
    String beautyToString() throws ControllerException;
}
