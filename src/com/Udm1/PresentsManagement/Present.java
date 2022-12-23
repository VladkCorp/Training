package com.Udm1.PresentsManagement;


public class Present {
    Sweet[] packedSweets = new Sweet[100]; // Can't fit more than 100 sweets in a present!
    int elementToAdd = 0;
    Sweet[] packedSweetsFiltered;

    public void addSweet(Sweet sweet) {

        if (elementToAdd < 100 && sweet != null) {
            packedSweets[elementToAdd] = sweet;

            elementToAdd++;
        }
    }

    public double calculateTotalWeight() {
        double totalWeight = 0;

        for (Sweet sweet : packedSweets) {

            if (sweet == null) {
                break;
            }
           totalWeight += sweet.weight;
        }
        return totalWeight;
    }

    public Sweet[] clearNullSweets(Sweet[] packedSweetsWithNulls) {
        Sweet[] clearedPackedSweets;
        int firstNullElementIndex = packedSweetsWithNulls.length;

        for (int i = 0; i < packedSweetsWithNulls.length; i++) {

            if (packedSweetsWithNulls[i] == null) {
                firstNullElementIndex = i;
                break;
            }
        }
        clearedPackedSweets = new Sweet[firstNullElementIndex];

        for (int i = 0; i < clearedPackedSweets.length; i++) {

            if (packedSweetsWithNulls.length >= 1) {
                clearedPackedSweets[i] = packedSweetsWithNulls[i];
            }
        }
        packedSweets = clearedPackedSweets;
        return clearedPackedSweets;
    }

    public Sweet[] filterSweetsBySugarRange(double minSugarWeight,
                                            double maxSugarWeight) {
        clearNullSweets(packedSweets);
        packedSweetsFiltered = new Sweet[packedSweets.length];
        int filteredIndex = 0;

        for (Sweet sweet : packedSweets) {

            if (sweet == null) {
                break;
            }
            if (sweet.sugarWeight >= minSugarWeight
                    && sweet.sugarWeight <= maxSugarWeight) {
                packedSweetsFiltered[filteredIndex] = sweet;
                filteredIndex++;
            }
        }
        packedSweetsFiltered = clearNullSweets(packedSweetsFiltered);
        packedSweets = packedSweetsFiltered;
        return packedSweetsFiltered;
    }

    public Sweet[] getSweets() {
        Sweet[] copyOfPackedSweets;

        clearNullSweets(packedSweets);
        copyOfPackedSweets = packedSweets;
        return copyOfPackedSweets;
    }

}

