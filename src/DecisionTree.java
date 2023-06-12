import java.util.*;

class DecisionTree {
    private Node root;

    public void train(List<Map<String, Object>> data, int maxDepth, int numFeatures) {
        List<String> features = new ArrayList<>(data.get(0).keySet());
        features.remove("label");
        List<Integer> selectedFeatures = selectFeatures(features, numFeatures);
        this.root = buildTree(data, selectedFeatures, maxDepth);
    }

    public Object predict(Map<String, Object> instance) {
        return traverseTree(instance, root);
    }

    private Node buildTree(List<Map<String, Object>> data, List<Integer> selectedFeatures, int maxDepth) {
        if (data.isEmpty()) {
            return new Node(null, null, null, null);
        }

        // Check if all instances belong to the same class
        boolean sameClass = true;
        Object firstLabel = data.get(0).get("label");
        for (Map<String, Object> instance : data) {
            if (!instance.get("label").equals(firstLabel)) {
                sameClass = false;
                break;
            }
        }
        if (sameClass || selectedFeatures.isEmpty() || maxDepth == 0) {
            return new Node(null, null, null, getMajorityClass(data));
        }

        SplitCriteria bestSplit = findBestSplit(data, selectedFeatures);
        List<Integer> remainingFeatures = new ArrayList<>(selectedFeatures);
        remainingFeatures.remove(bestSplit.featureIndex);

        List<Map<String, Object>> leftSubset = new ArrayList<>();
        List<Map<String, Object>> rightSubset = new ArrayList<>();
        for (Map<String, Object> instance : data) {
            if (isInstanceLeft(instance, bestSplit)) {
                leftSubset.add(instance);
            } else {
                rightSubset.add(instance);
            }
        }

        Node leftChild = buildTree(leftSubset, remainingFeatures, maxDepth - 1);
        Node rightChild = buildTree(rightSubset, remainingFeatures, maxDepth - 1);

        return new Node(bestSplit.featureName, leftChild, rightChild, null);
    }

    private List<Integer> selectFeatures(List<String> features, int numFeatures) {
        List<Integer> selectedFeatures = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < numFeatures; i++) {
            int index = random.nextInt(features.size());
            selectedFeatures.add(index);
        }

        return selectedFeatures;
    }

    private Object traverseTree(Map<String, Object> instance, Node node) {
        if (node.isLeaf()) {
            return node.label;
        }

        Object value = instance.get(node.featureName);
        if (value instanceof Comparable && node.leftChild != null && node.rightChild != null) {
            return traverseTree(instance, node.rightChild);

        }

        return null;
    }

    private SplitCriteria findBestSplit(List<Map<String, Object>> data, List<Integer> selectedFeatures) {
        SplitCriteria bestSplit = null;
        double bestScore = -1.0;

        for (int featureIndex : selectedFeatures) {
            String featureName = getFeatureName(featureIndex);
            List<String> featureValues = extractFeatureValues(data, featureName);
            double score = calculateGiniIndex(data, featureName, featureValues);

            if (bestSplit == null || score < bestScore) {
                bestSplit = new SplitCriteria(featureIndex, featureName, featureValues.get(0));
                bestScore = score;
            }
        }

        return bestSplit;
    }

    private String getFeatureName(int featureIndex) {
        // Assuming feature names are stored in a list
        List<String> featureNames = new ArrayList<>(Arrays.asList("feature1", "feature2", "feature3"));
        return featureNames.get(featureIndex);
    }

    private List<String> extractFeatureValues(List<Map<String, Object>> data, String featureName) {
        List<String> featureValues = new ArrayList<>();

        for (Map<String, Object> instance : data) {
            Object value = instance.get(featureName);
            featureValues.add(String.valueOf(value));
        }

        return featureValues;
    }

    /*private double calculateGiniIndex(List<Map<String, Object>> data, String featureName, List<String> featureValues) {
        double giniIndex = 0.0;

        for (String value : featureValues) {
            int countLeft = 0;
            int countRight = 0;

            for (Map<String, Object> instance : data) {
                Object instanceValue = instance.get(featureName);
                if (instanceValue instanceof Comparable) {
                        countLeft++;
                    } else {
                        countRight++;
                    }

            }

            double leftWeight = (double) countLeft / data.size();
            double rightWeight = (double) countRight / data.size();
            double giniLeft = calculateGiniImpurity(data, featureName, value, true);
            double giniRight = calculateGiniImpurity(data, featureName, value, false);

            double weightedGini = leftWeight * giniLeft + rightWeight * giniRight;
            giniIndex = Math.min(giniIndex, weightedGini);
        }

        return giniIndex;
    }*/




    private double calculateGiniIndex(List<Map<String, Object>> data, String featureName, Object splitValue) {
        int totalCount = data.size();
        double giniIndex = 0.0;

        // Contagem das classes/rótulos
        Map<Object, Integer> classCounts = new HashMap<>();
        for (Map<String, Object> instance : data) {
            Object label = instance.get("label");
            classCounts.put(label, classCounts.getOrDefault(label, 0) + 1);
        }

        // Cálculo do índice de Gini
        for (Object classLabel : classCounts.keySet()) {
            double classProbability = (double) classCounts.get(classLabel) / totalCount;
            giniIndex += Math.pow(classProbability, 2);
        }

        // Aplicação da divisão com base na característica e valor fornecidos
        List<Map<String, Object>> leftSubset = new ArrayList<>();
        List<Map<String, Object>> rightSubset = new ArrayList<>();
        for (Map<String, Object> instance : data) {
            Object value = instance.get(featureName);
            if (value instanceof Comparable && ((Comparable<?>) value).compareTo(splitValue) < 0) {
                leftSubset.add(instance);
            } else {
                rightSubset.add(instance);
            }
        }

        // Cálculo do ganho de Gini
        double leftGini = calculateGiniIndex(leftSubset);
        double rightGini = calculateGiniIndex(rightSubset);
        double giniGain = (leftSubset.size() * leftGini + rightSubset.size() * rightGini) / totalCount;

        return giniGain;
    }

    private int compareValues(Object value1, Object value2) {
        if (value1 == null && value2 == null) {
            return 0;
        } else if (value1 == null) {
            return -1;
        } else if (value2 == null) {
            return 1;
        } else {
            return value1.toString().compareTo(value2.toString());
        }
    }


    private double calculateGiniImpurity(List<Map<String, Object>> data, String featureName, Comparable<?> value, boolean isLeft) {
        int count = 0;
        int positiveCount = 0;

        for (Map<String, Object> instance : data) {
            Object instanceValue = instance.get(featureName);
            if (instanceValue instanceof Comparable) {
                Comparable<?> comparableValue = (Comparable<?>) instanceValue;
                boolean condition = isLeft ;
                if (condition) {
                    count++;
                    if (instance.get("label").equals("positive")) {
                        positiveCount++;
                    }
                }
            }
        }

        double proportion = (double) positiveCount / count;
        double giniImpurity = 1.0 - Math.pow(proportion, 2) - Math.pow(1 - proportion, 2);
        return giniImpurity;
    }

    private Object getMajorityClass(List<Map<String, Object>> data) {
        Map<Object, Integer> classCounts = new HashMap<>();

        for (Map<String, Object> instance : data) {
            Object label = instance.get("label");
            classCounts.put(label, classCounts.getOrDefault(label, 0) + 1);
        }

        int maxCount = 0;
        Object majorityClass = null;
        for (Map.Entry<Object, Integer> entry : classCounts.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                majorityClass = entry.getKey();
            }
        }

        return majorityClass;
    }

    private boolean isInstanceLeft(Map<String, Object> instance, SplitCriteria splitCriteria) {
        Object value = instance.get(splitCriteria.featureName);

        return true;

    }
}