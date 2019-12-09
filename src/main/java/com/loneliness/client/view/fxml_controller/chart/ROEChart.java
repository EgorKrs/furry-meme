package com.loneliness.client.view.fxml_controller.chart;

import com.loneliness.entity.Quarter;
import com.loneliness.entity.ROE;
import com.loneliness.entity.SG;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.Iterator;
import java.util.Map;

public class ROEChart {
    @FXML
    private CategoryAxis quarterAxis = new CategoryAxis();

    @FXML
    private NumberAxis valueAxis = new NumberAxis();



    @FXML
    private BarChart<String, Number> barChart = new BarChart<>(quarterAxis, valueAxis);
    private XYChart.Series<String, Number> dataSeries = new XYChart.Series<>();


    public void setROeData(Map<Quarter, ROE> roeData, int year, String action) {
        dataSeries.setName("Текущий отчётный период за год " + year);
        Iterator<Quarter> key = roeData.keySet().iterator();
        switch (action) {
            case "FL":
            for (ROE roe : roeData.values()) {
                dataSeries.getData().add(new XYChart.Data<>(key.next().toString(), roe.getFL()));
            }
            break;
            case "RONA":
                for (ROE roe : roeData.values()) {
                    dataSeries.getData().add(new XYChart.Data<>(key.next().toString(), roe.getRONA()));
                }
                break;
            case "PROFITABILITY":
                for (ROE roe : roeData.values()) {
                    dataSeries.getData().add(new XYChart.Data<>(key.next().toString(), roe.getProfR()));
                }
                break;
            case "ROE":
                for (ROE roe : roeData.values()) {
                    dataSeries.getData().add(new XYChart.Data<>(key.next().toString(), roe.getROE()));
                }
                break;
        }
        barChart.getData().add(dataSeries);
        barChart.setTitle(action);

    }
    public void setSgData(Map<Quarter, SG> sgData, int year, String action) {
        dataSeries.setName("Текущий отчётный период за год " + year);
        Iterator<Quarter> key = sgData.keySet().iterator();
                for (SG sg : sgData.values()) {
                    dataSeries.getData().add(new XYChart.Data<>(key.next().toString(), sg.getSG()));
                }
        barChart.getData().add(dataSeries);
        barChart.setTitle(action);
    }

}
