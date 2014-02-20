package voice.of.helix;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYTitleAnnotation;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleAnchor;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.LinkedList;

/**
 *
 * Panel containing charts and creation of charts
 *
 * Created by Scraphead on 18.02.14.
 */
public class LineGraphPanel extends JPanel {
    LinkedList<PokemonInput> inputList;
    int graphLength = 120;

    XYSeriesCollection upDownDataSet = new XYSeriesCollection();
    JFreeChart upDownChart = ChartFactory.createXYLineChart("", "Seconds", "", upDownDataSet, PlotOrientation.VERTICAL, true, false, false);

    XYSeriesCollection leftRightDataSet = new XYSeriesCollection();
    JFreeChart leftRightChart = ChartFactory.createXYLineChart("", "Seconds", "", leftRightDataSet, PlotOrientation.VERTICAL, true, false, false);

    XYSeriesCollection abStartDataSet = new XYSeriesCollection();
    JFreeChart abStartChart = ChartFactory.createXYLineChart("", "Seconds", "", abStartDataSet, PlotOrientation.VERTICAL, true, false, false);

    XYSeriesCollection anarchyDemocracyDataSet = new XYSeriesCollection();
    JFreeChart anarchyDemocracyChart = ChartFactory.createXYLineChart("", "Seconds", "", anarchyDemocracyDataSet, PlotOrientation.VERTICAL, true, false, false);

    XYSeries upSeries = new XYSeries("UP");
    XYSeries downSeries = new XYSeries("DOWN");
    XYSeries leftSeries = new XYSeries("LEFT");
    XYSeries rightSeries = new XYSeries("RIGHT");
    XYSeries aSeries = new XYSeries("B");
    XYSeries bSeries = new XYSeries("A");
    XYSeries startSeries = new XYSeries("START");
    XYSeries anarchySeries = new XYSeries("ANARCHY");
    XYSeries democracySeries = new XYSeries("DEMOCRACY");

    XYDataItem[] upItems = new XYDataItem[graphLength];
    XYDataItem[] downItems = new XYDataItem[graphLength];
    XYDataItem[] leftItems = new XYDataItem[graphLength];
    XYDataItem[] rightItems = new XYDataItem[graphLength];
    XYDataItem[] aItems = new XYDataItem[graphLength];
    XYDataItem[] bItems = new XYDataItem[graphLength];
    XYDataItem[] startItems = new XYDataItem[graphLength];
    XYDataItem[] anarchyItems = new XYDataItem[graphLength];
    XYDataItem[] democracyItems = new XYDataItem[graphLength];

    JSlider streamDelayEstimation = new JSlider(0, 60, 20);
    XYSeries delaySeries = new XYSeries("Estimated Delay");
    JCheckBox graphSmoothingCheckbox = new JCheckBox("Graph Smoothing");

    JLabel praiseHelix = new JLabel();

    public LineGraphPanel(LinkedList<PokemonInput> inputList) {
        setLayout(new BorderLayout());
        JPanel graphPanel = new JPanel();
        graphPanel.setLayout(new BoxLayout(graphPanel, BoxLayout.Y_AXIS));
        this.inputList = inputList;
        graphSmoothingCheckbox.setSelected(true);

        for (int i = 0; i < graphLength; i++) {
            upItems[i] = new XYDataItem((float) i / 2f, 0.0f);
            downItems[i] = new XYDataItem((float) i / 2f, 0.0f);
            leftItems[i] = new XYDataItem((float) i / 2f, 0.0f);
            rightItems[i] = new XYDataItem((float) i / 2f, 0.0f);
            aItems[i] = new XYDataItem((float) i / 2f, 0.0f);
            bItems[i] = new XYDataItem((float) i / 2f, 0.0f);
            startItems[i] = new XYDataItem((float) i / 2f, 0.0f);
            anarchyItems[i] = new XYDataItem((float) i / 2f, 0.0f);
            democracyItems[i] = new XYDataItem((float) i / 2f, 0.0f);
        }

        for (int i = 0; i < graphLength; i++) {
            upSeries.add(upItems[i]);
            downSeries.add(downItems[i]);
            leftSeries.add(leftItems[i]);
            rightSeries.add(rightItems[i]);
            aSeries.add(aItems[i]);
            bSeries.add(bItems[i]);
            startSeries.add(startItems[i]);
            anarchySeries.add(anarchyItems[i]);
            democracySeries.add(democracyItems[i]);
        }

        streamDelayEstimation.setMinorTickSpacing(1);
        streamDelayEstimation.setMajorTickSpacing(5);
        streamDelayEstimation.setPaintTicks(true);
        streamDelayEstimation.setPaintLabels(true);
        streamDelayEstimation.setSnapToTicks(true);
        streamDelayEstimation.setPaintLabels(true);

        delaySeries.add(20, 0);
        delaySeries.add(20, 5);

        upDownDataSet.addSeries(upSeries);
        upDownDataSet.addSeries(downSeries);
        upDownDataSet.addSeries(delaySeries);
        leftRightDataSet.addSeries(leftSeries);
        leftRightDataSet.addSeries(rightSeries);
        leftRightDataSet.addSeries(delaySeries);
        abStartDataSet.addSeries(bSeries);
        abStartDataSet.addSeries(aSeries);
        abStartDataSet.addSeries(startSeries);
        abStartDataSet.addSeries(delaySeries);
        anarchyDemocracyDataSet.addSeries(anarchySeries);
        anarchyDemocracyDataSet.addSeries(democracySeries);
        anarchyDemocracyDataSet.addSeries(delaySeries);


        Stroke s = new BasicStroke(3f);
        Color c = new Color(0, 0, 0, 0);

        upDownChart.setAntiAlias(true);
        upDownChart.getLegend().setVisible(false);
        XYTitleAnnotation upDownLegend = new XYTitleAnnotation(0.98f, 0.98f, new LegendTitle(upDownChart.getXYPlot()), RectangleAnchor.TOP_RIGHT);
        upDownLegend.setMaxWidth(0.48);
        upDownChart.getXYPlot().addAnnotation(upDownLegend);
        upDownChart.setNotify(false);
        upDownChart.getXYPlot().getRangeAxis().setVisible(false);
        upDownChart.getXYPlot().getDomainAxis().setVisible(false);
        XYItemRenderer upDownRender = upDownChart.getXYPlot().getRenderer();

        upDownRender.setSeriesPaint(0, Color.RED);
        upDownRender.setSeriesPaint(1, Color.BLUE);
        upDownRender.setSeriesPaint(2, Color.BLACK);
        upDownRender.setSeriesStroke(0, s);
        upDownRender.setSeriesStroke(1, s);
        upDownRender.setSeriesStroke(2, s);
        upDownChart.setBackgroundPaint(c);
        ChartPanel upDownPanel = new ChartPanel(upDownChart);
        upDownPanel.setPreferredSize(new Dimension(600, 150));

        leftRightChart.setAntiAlias(true);
        leftRightChart.getLegend().setVisible(false);
        XYTitleAnnotation leftRightLegend = new XYTitleAnnotation(0.98f, 0.98f, new LegendTitle(leftRightChart.getXYPlot()), RectangleAnchor.TOP_RIGHT);
        leftRightLegend.setMaxWidth(0.48);
        leftRightChart.getXYPlot().addAnnotation(leftRightLegend);
        leftRightChart.setNotify(false);
        leftRightChart.getXYPlot().getRangeAxis().setVisible(false);
        leftRightChart.getXYPlot().getDomainAxis().setVisible(false);
        XYItemRenderer leftRightRender = leftRightChart.getXYPlot().getRenderer();
        leftRightRender.setSeriesPaint(0, Color.RED);
        leftRightRender.setSeriesPaint(1, Color.BLUE);
        leftRightRender.setSeriesPaint(2, Color.BLACK);
        leftRightRender.setSeriesStroke(0, s);
        leftRightRender.setSeriesStroke(1, s);
        leftRightRender.setSeriesStroke(2, s);
        leftRightChart.setBackgroundPaint(c);
        ChartPanel leftRightPanel = new ChartPanel(leftRightChart);
        leftRightPanel.setPreferredSize(new Dimension(600, 150));

        abStartChart.setAntiAlias(true);
        abStartChart.getLegend().setVisible(false);
        XYTitleAnnotation abStartLegend = new XYTitleAnnotation(0.98f, 0.98f, new LegendTitle(abStartChart.getXYPlot()), RectangleAnchor.TOP_RIGHT);
        abStartLegend.setMaxWidth(0.48);
        abStartChart.getXYPlot().addAnnotation(abStartLegend);
        abStartChart.setNotify(false);
        abStartChart.getXYPlot().getRangeAxis().setVisible(false);
        abStartChart.getXYPlot().getDomainAxis().setVisible(false);
        XYItemRenderer abStartRender = abStartChart.getXYPlot().getRenderer();
        abStartRender.setSeriesPaint(0, Color.RED);
        abStartRender.setSeriesPaint(1, Color.BLUE);
        abStartRender.setSeriesPaint(2, Color.GREEN);
        abStartRender.setSeriesPaint(3, Color.BLACK);
        abStartRender.setSeriesStroke(0, s);
        abStartRender.setSeriesStroke(1, s);
        abStartRender.setSeriesStroke(2, s);
        abStartRender.setSeriesStroke(3, s);
        abStartChart.setBackgroundPaint(c);
        ChartPanel abStartPanel = new ChartPanel(abStartChart);
        abStartPanel.setPreferredSize(new Dimension(600, 150));

        anarchyDemocracyChart.setAntiAlias(true);
        anarchyDemocracyChart.getLegend().setVisible(false);
        XYTitleAnnotation anarchyDemocracyLegend = new XYTitleAnnotation(0.98f, 0.98f, new LegendTitle(anarchyDemocracyChart.getXYPlot()), RectangleAnchor.TOP_RIGHT);
        anarchyDemocracyLegend.setMaxWidth(0.70);
        anarchyDemocracyChart.getXYPlot().addAnnotation(anarchyDemocracyLegend);
        anarchyDemocracyChart.setNotify(false);
        anarchyDemocracyChart.getXYPlot().getRangeAxis().setVisible(false);
        anarchyDemocracyChart.getXYPlot().getDomainAxis().setVisible(false);
        XYItemRenderer anarchyDemocracyRender = anarchyDemocracyChart.getXYPlot().getRenderer();
        anarchyDemocracyRender.setSeriesPaint(0, Color.RED);
        anarchyDemocracyRender.setSeriesPaint(1, Color.GREEN);
        anarchyDemocracyRender.setSeriesPaint(2, Color.BLACK);
        anarchyDemocracyRender.setSeriesStroke(0, s);
        anarchyDemocracyRender.setSeriesStroke(1, s);
        anarchyDemocracyRender.setSeriesStroke(2, s);
        anarchyDemocracyChart.setBackgroundPaint(c);
        ChartPanel anarchyDemocracyPanel = new ChartPanel(anarchyDemocracyChart);
        anarchyDemocracyPanel.setPreferredSize(new Dimension(600, 150));

        graphPanel.add(streamDelayEstimation);

        graphPanel.add(upDownPanel);
        graphPanel.add(leftRightPanel);
        graphPanel.add(abStartPanel);
        graphPanel.add(anarchyDemocracyPanel);
        add(graphPanel, BorderLayout.NORTH);
        add(graphSmoothingCheckbox, BorderLayout.WEST);
        add(praiseHelix, BorderLayout.EAST);

    }


    void updateGraph() {

        //Reference time
        long currentUpdateTime = System.currentTimeMillis();

        //Reset graph before update
        for (int i = 0; i < graphLength; i++) {
            upItems[i].setY(0.0f);
            downItems[i].setY(0.0f);
            leftItems[i].setY(0.0f);
            rightItems[i].setY(0.0f);
            aItems[i].setY(0.0f);
            bItems[i].setY(0.0f);
            startItems[i].setY(0.0f);
            anarchyItems[i].setY(0.0f);
            democracyItems[i].setY(0.0f);
        }

        synchronized (inputList) {
            for (PokemonInput input : inputList) {
                int index = (int) ((currentUpdateTime - input.timeInput) / 500);
                if (index < 120) {
                    switch (input.keyCommand) {
                        case UP:
                            upItems[index].setY(upItems[index].getYValue() + 1);
                            break;
                        case DOWN:
                            downItems[index].setY(downItems[index].getYValue() + 1);
                            break;
                        case LEFT:
                            leftItems[index].setY(leftItems[index].getYValue() + 1);
                            break;
                        case RIGHT:
                            rightItems[index].setY(rightItems[index].getYValue() + 1);
                            break;
                        case A:
                            aItems[index].setY(aItems[index].getYValue() + 1);
                            break;
                        case B:
                            bItems[index].setY(bItems[index].getYValue() + 1);
                            break;
                        case START:
                            startItems[index].setY(startItems[index].getYValue() + 1);
                            break;
                        case SELECT:

                            break;
                        case ANARCHY:
                            anarchyItems[index].setY(anarchyItems[index].getYValue() + 1);
                            break;
                        case DEMOCRACY:
                            democracyItems[index].setY(democracyItems[index].getYValue() + 1);
                            break;
                    }
                }
            }
        }

        int smoothValue = 3;
        if (graphSmoothingCheckbox.isSelected()) {
            smoothValues(upItems, smoothValue);
            smoothValues(downItems, smoothValue);
            smoothValues(leftItems, smoothValue);
            smoothValues(rightItems, smoothValue);
            smoothValues(aItems, smoothValue);
            smoothValues(bItems, smoothValue);
            smoothValues(startItems, smoothValue);
            smoothValues(anarchyItems, smoothValue);
            smoothValues(democracyItems, smoothValue);
        }
        upSeries.clear();
        downSeries.clear();
        leftSeries.clear();
        rightSeries.clear();
        aSeries.clear();
        bSeries.clear();
        startSeries.clear();
        anarchySeries.clear();
        democracySeries.clear();

        for (int i = 0; i < graphLength - smoothValue; i++) {
            upSeries.add(upItems[i]);
            downSeries.add(downItems[i]);
            leftSeries.add(leftItems[i]);
            rightSeries.add(rightItems[i]);
            aSeries.add(aItems[i]);
            bSeries.add(bItems[i]);
            startSeries.add(startItems[i]);
            anarchySeries.add(anarchyItems[i]);
            democracySeries.add(democracyItems[i]);
        }


        delaySeries.clear();
        delaySeries.add(new XYDataItem(streamDelayEstimation.getValue(), 0));
        delaySeries.add(new XYDataItem(streamDelayEstimation.getValue(), 3));

        //Refresh charts, avoids flickering
        upDownChart.setNotify(true);
        upDownChart.fireChartChanged();
        upDownChart.setNotify(false);
        leftRightChart.setNotify(true);
        leftRightChart.fireChartChanged();
        leftRightChart.setNotify(false);
        abStartChart.setNotify(true);
        abStartChart.fireChartChanged();
        abStartChart.setNotify(false);
        anarchyDemocracyChart.setNotify(true);
        anarchyDemocracyChart.fireChartChanged();
        anarchyDemocracyChart.setNotify(false);

    }

    public void prayersToHelix(String prayer){
        praiseHelix.setText(prayer);
    }

    private void smoothValues(XYDataItem[] plotArray, int smoothLevel) {

        double[] tempArray = new double[plotArray.length];
        for (int i = 0; i < tempArray.length; i++) {
            tempArray[i] = plotArray[i].getYValue();

        }

        for (int i = 0; i < tempArray.length; i++) {
            double futureSmooth = tempArray[i];
            double pastSmooth = tempArray[i];
            for (int j = 0; j < smoothLevel; j++) {

                if(i+j+1 < tempArray.length){
                    futureSmooth = futureSmooth + ((tempArray[i + j + 1] -futureSmooth) * Math.pow(0.8f, j + 1));
                }
                if(i-j-1 >= 0){
                    pastSmooth = pastSmooth + ((tempArray[i - j - 1] - pastSmooth ) * Math.pow(0.8f, j + 1));
                }

            }
            plotArray[i].setY((futureSmooth+pastSmooth)/2);

        }

    }

}
