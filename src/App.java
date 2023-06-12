import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App{
    public static void main(String[] args) {
        try {

            List<Map<String, Object>> trainingData = new ArrayList<>();

            Map<String, Object> instance1 = new HashMap<>();
            instance1.put("feature1", 5);
            instance1.put("feature2", "A");
            instance1.put("feature3", true);
            instance1.put("label", "positive");
            trainingData.add(instance1);

            Map<String, Object> instance2 = new HashMap<>();
            instance2.put("feature1", 7);
            instance2.put("feature2", "B");
            instance2.put("feature3", false);
            instance2.put("label", "negative");
            trainingData.add(instance2);

            Map<String, Object> instance3 = new HashMap<>();
            instance3.put("feature1", 9);
            instance3.put("feature2", "C");
            instance3.put("feature3", false);
            instance3.put("label", "positive");
            trainingData.add(instance3);


            RandomForest randomForest = new RandomForest();
            randomForest.train(trainingData, 4, 4, 3);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}