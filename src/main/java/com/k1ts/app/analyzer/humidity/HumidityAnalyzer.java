package com.k1ts.app.analyzer.humidity;

import com.k1ts.app.analyzer.entity.HumidityData;
import com.k1ts.app.analyzer.entity.TemperatureData;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.DefaultXYDataset;

import javax.swing.*;
import java.awt.*;

public class HumidityAnalyzer {

    public void plot(HumidityData[] data) {

        double[][] preparedData = new double[2][data.length];

        for (int i = 0; i < data.length; i++) {
            preparedData[0][i] = data[i].timestamp() - data[0].timestamp();
            preparedData[1][i] = data[i].humidity();
        }

        DefaultXYDataset dataset = new DefaultXYDataset();
        dataset.addSeries("Humidity", preparedData);

        SwingUtilities.invokeLater(() -> {

            JFreeChart chart = ChartFactory.createXYLineChart(
                    "График относительной влажности",
                    "t, c",
                    "влажность, %",
                    dataset
            );

            // Получаем область графика
            XYPlot plot = chart.getXYPlot();

            // Renderer для обычной линии
            XYLineAndShapeRenderer renderer =
                    new XYLineAndShapeRenderer();

            // Линия чёрная
            renderer.setSeriesPaint(0, Color.BLACK);

            // Не показывать точки
            renderer.setSeriesShapesVisible(0, false);

            // Линия видимая
            renderer.setSeriesLinesVisible(0, true);

            // Применяем renderer
            plot.setRenderer(renderer);

            // Белый фон графика
            plot.setBackgroundPaint(Color.WHITE);

            // Белый фон области вокруг графика
            chart.setBackgroundPaint(Color.WHITE);

            // Сетка
            plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
            plot.setRangeGridlinePaint(Color.LIGHT_GRAY);

            // Окно
            JFrame frame = new JFrame("Humidity Analyzer");

            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            frame.setLayout(new BorderLayout());

            ChartPanel chartPanel = new ChartPanel(chart);

            frame.add(chartPanel, BorderLayout.CENTER);

            frame.setSize(1920, 1080);

            frame.setLocationRelativeTo(null);

            frame.setVisible(true);
        });
    }
}
