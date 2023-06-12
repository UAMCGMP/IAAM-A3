import java.util.List;
import java.util.Map;

public class Normalizador {
    public static List<Map<String, Object>> normalizar(List<Map<String, Object>> dados){
        for (int i = 0; i < dados.size(); i++) {
            double max = Double.MIN_VALUE;
            double min = Double.MAX_VALUE;
            for (Map<String, Object> dado : dados) {

                if(dado.get(i) > max){
                    max = dado.get(i);
                }
                if((double) dado.get(i) < min){
                    min =  dado.get(i);
                }
            }
            double diferenca = max-min;


            for (int j = 0; j < dados.size(); j++) {
                dados.get(j) = dados
            }

        }
        return dados;
    }
}
