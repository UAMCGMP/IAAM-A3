import java.util.*;

public class RandomForest {
    private List<DecisionTree> trees;

    public RandomForest() {
        trees = new ArrayList<>();
    }

    public void train(List<Map<String, Object>> data, int numTrees, int maxDepth, int numFeatures) {
        for (int i = 0; i < numTrees; i++) {
            List<Map<String, Object>> bootstrapSample = getBootstrapSample(data);
            DecisionTree tree = new DecisionTree();
            tree.train(bootstrapSample, maxDepth, numFeatures);
            trees.add(tree);
        }
    }

    public Object predict(Map<String, Object> instance) {
        Map<Object, Integer> votes = new HashMap<>();

        for (DecisionTree tree : trees) {
            Object prediction = tree.predict(instance);
            votes.put(prediction, votes.getOrDefault(prediction, 0) + 1);
        }

        int maxVotes = 0;
        Object predictedClass = null;
        for (Map.Entry<Object, Integer> entry : votes.entrySet()) {
            if (entry.getValue() > maxVotes) {
                maxVotes = entry.getValue();
                predictedClass = entry.getKey();
            }
        }

        return predictedClass;
    }

    private List<Map<String, Object>> getBootstrapSample(List<Map<String, Object>> data) {
        List<Map<String, Object>> bootstrapSample = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < data.size()-1; i++) {
            int index = random.nextInt(data.size());
            bootstrapSample.add(data.get(index));
        }

        return bootstrapSample;
    }
}
