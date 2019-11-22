package com.loneliness.client.launcher;

import com.loneliness.client.view.FilledAlert;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.concurrent.atomic.AtomicBoolean;

public class Reconnect {
    private final static Reconnect instance =new Reconnect();
    private AtomicBoolean cancel = new AtomicBoolean(false);
    private final ReconnectService service = new ReconnectService();
    private Reconnect(){
    }

    public AtomicBoolean getCancel() {
        return cancel;
    }

    public static Reconnect getInstance() {
        return instance;
    }
    public void reconnect(){
        service.setPeriod(Duration.seconds(5));
        service.createTask();
        service.setOnSucceeded(t -> {
            if((Boolean) t.getSource().getValue()){
                cancel.set(true);
                service.cancel();
            }
        });
        if(!service.isRunning()){
            service.start();
        }

    }
    public boolean startReconnectCycle(Stage dialogStage){
        while (!Reconnect.getInstance().getCancel().get()) {
            if (!FilledAlert.getInstance().showAlert("Ошибка подключения",
                    "Подключение к серверу отсутствует",
                    "Нажмите ок для переподключения", dialogStage, "ERROR")) {
                return false;
            }
        }
        return true;
    }
    private static class ReconnectService extends ScheduledService<Boolean> {
        private BooleanProperty connection = new SimpleBooleanProperty();

        public final void setConnection(Boolean value) {
            connection.set(value);
        }

        public final Boolean getConnection() {
            return connection.get();
        }

        public boolean isConnection() {
            return connection.get();
        }

        public BooleanProperty connectionProperty() {
            return connection;
        }

        protected Task<Boolean> createTask() {
            return new Task<>() {
                protected Boolean call() {
                    connection.set(Client.connect());
                    return getConnection();
                }
            };
        }
    }
}
