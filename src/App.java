import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App{
    public static void main(String[] args) {
        try {

            Leitor leitor = new Leitor();
            List<Map<String, Object>> trainingData = leitor.importar("descobrir se uma pessoa vai ter diabetes.csv");
            List<Map<String, Object>> trainingDataNormalizado = Normalizador.normalizar(trainingData);

            RandomForest randomForest = new RandomForest();
            randomForest.train(trainingDataNormalizado, 4, 4, 3);


            Map<String, Object> dadoTeste = new HashMap<>();
            dadoTeste.put("feature1", 7);
            dadoTeste.put("feature2", 8);
            dadoTeste.put("feature3", 9);
            dadoTeste.put("feature4", 10);
            dadoTeste.put("test", "negative");

            randomForest.predict(dadoTeste);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}