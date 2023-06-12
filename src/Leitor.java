import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Leitor {
    public Dado[] importar(String nomeArquivo) throws IOException{
        try {
            BufferedReader contador = new BufferedReader(new FileReader(nomeArquivo));
            int linhas = 0;
            while (contador.ready()){
                contador.readLine();
                linhas++;
            }
            contador.close();
            Dado[] dados = new Dado[linhas];
            int indice = 0;
            BufferedReader leitor = new BufferedReader(new FileReader(nomeArquivo));
            while(leitor.ready()){
                String linha = leitor.readLine();
                String ref[] = linha.split(";");
                int tamanho = ref.length;
                double[] referencias = new double[tamanho];
                for (int i = 0; i < tamanho; i++) {
                    referencias[i] = Double.parseDouble(ref[i]);
                }
                dados[indice++] = new Dado(referencias);
            }
            leitor.close();
            return dados;           
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        }
    }
}
