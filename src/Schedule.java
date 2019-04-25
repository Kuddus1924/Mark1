import java.util.ArrayList;
import java.util.List;
import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;

public class Schedule {
    public static void print (ArrayList<Double> array, ArrayList<Double> array1, ArrayList<Double> array2,ArrayList<Double> array3, String name) {

        List<Double> tmp = new ArrayList<>();
        for(int i = 0;i <= 10; i++)
        {
            tmp.add(0.1*i);
        }

        XYChart chart = new XYChartBuilder().width(800).height(600).title(name).xAxisTitle("x").yAxisTitle("y").build();

        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        chart.getStyler().setHasAnnotations(false);
        chart.getStyler().setPlotGridLinesVisible(false);

        chart.addSeries("Выведенная", tmp, array);
        chart.addSeries("Полный перебор", tmp,array1);
        chart.addSeries("Моделирование", tmp,array2);
        chart.addSeries("Моделирование улучшенное", tmp,array2);
        List<XYChart> charts = new ArrayList<XYChart>();
        charts.add(chart);
        new SwingWrapper<XYChart>(charts).displayChartMatrix();

    }
    public static void printS(ArrayList<Double>list,String name)
    {
        System.out.println(name);
        double d = 0.0;
        for(int i = 0; i <= 10; i++)
        {
            d =  i/10.0;
            System.out.println((d) + ":" + list.get(i).toString());
        }
        System.out.println();
    }
}
