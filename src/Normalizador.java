import java.util.List;

public class Normalizador {
    public static List<Dado>  normalizar(List<Dado> dados){
        int tamanho = dados.size();
        for (int i = 0; i < tamanho; i++) {
            double max = Double.MIN_VALUE;
            double min = Double.MAX_VALUE;
            for (Dado dado : dados) {
                double[] referencia = dado.getReferencias();
                if(referencia[i]>max){
                    max=referencia[i];
                }
                if(referencia[i]<min){
                    min=referencia[i];
                }
            }
            double diferenca = max-min;
            for (Dado dado : dados) {
                double[] referencia = dado.getReferencias();
                referencia[i]=(referencia[i]-min)/diferenca;
            }           
        }
        return dados;
    }
}
