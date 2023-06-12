import java.util.List;
import java.util.Map;

public class Normalizador {
    public static List<Map<String, Object>> normalizar(List<Map<String, Object>> dados){
        Object[] labels= dados.get(0).keySet().toArray();
        for (int i = 0; i < dados.size(); i++) {
            double max = Double.MIN_VALUE;
            double min = Double.MAX_VALUE;
            for (Map<String, Object> dado : dados) {

                if(Double.parseDouble(dado.get(i).toString()) > max){
                    max = Double.parseDouble(dado.get(i).toString());
                }
                if(Double.parseDouble(dado.get(i).toString()) < min){
                    min =  Double.parseDouble(dado.get(i).toString());
                }
            }
            double diferenca = max-min;


            for (int j = 0; j < dados.size(); j++) {
                double value = (Double.parseDouble(dados.get(i).toString()) - min)/diferenca;
                dados.get(i).put(labels[j].toString(), value);
            }

        }
        return dados;
    }
}
