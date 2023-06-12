import java.util.List;
import java.util.Map;

public class Normalizador {

    static double minValue(List<Map<String, Object>> dados){
        Object[] labels = dados.get(0).keySet().toArray();
        double min = Double.parseDouble(dados.get(0).get(labels[0]).toString());
        double value;
        for(int i = 0; i< dados.size(); i++) {
            value = Double.parseDouble(dados.get(i).get(labels[i]).toString());
            if (value < min) min = value;
        }
        return min;
    }

    static double maxValue(List<Map<String, Object>> dados){
        Object[] labels = dados.get(0).keySet().toArray();
        double max = Double.parseDouble(dados.get(0).get(labels[0]).toString());
        double value;
        for(int i = 0; i< dados.size(); i++) {
            value = Double.parseDouble(dados.get(i).get(labels[i]).toString());
            if (value > max) max = value;
        }
        return max;
    }


    public static List<Map<String, Object>> normalizar(List<Map<String, Object>> dados){
        Object[] labels= dados.get(0).keySet().toArray();
        for (int i = 0; i < dados.size(); i++) {
            double max = maxValue(dados);
            double min = minValue(dados);

            double diferenca = max-min;

            for (int j = 0; j < dados.size(); j++) {
                double value = (Double.parseDouble(dados.get(j).get(labels[j]).toString()) - min)/diferenca;
                dados.get(i).put(labels[j].toString(), value);
            }

        }
        return dados;
    }
}
