import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Leitor {
    public List<Map<String, Object>>  importar(String nomeArquivo) throws IOException {
        List<String> linhas = new ArrayList<>();
        List<Map<String, Object>> dados = new ArrayList<>();
        try {
            BufferedReader leitor = new BufferedReader(new FileReader(nomeArquivo));
            while (leitor.ready()) {
                String linha = leitor.readLine();
                linhas.add(linha);
            }

            String[] labels = linhas.get(0).split(";");
            for (int i = 1; i < linhas.size(); i++) {
                String[] refsDados = linhas.get(i).split(";");
                Map<String, Object> dado = new HashMap<>();
                for (int j = 0; j < labels.length; j++) {
                    dado.put(labels[j], refsDados[j]);
                }
                dados.add(dado);
            }

            leitor.close();

        } catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        }

        return dados;
    }
}
