package com.zipcodewilmington.arrayutility;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by leon on 3/6/18.
 */
public class ArrayUtility<E> {

    private E[] array;

    public ArrayUtility(E[] inputArray) {
        this.array = inputArray;
    }

    public Integer countDuplicatesInMerge(E[] arrayToMerge, E valueToEvaluate) {
        return (int) Stream.concat(Arrays.stream(array), Arrays.stream(arrayToMerge))
                .filter(element -> element.equals(valueToEvaluate))
                .count();
    }

    public Integer getNumberOfOccurrences(List<E> list, E objectToCount) {
        int numOfOccurrences = 0;
        for (E element : list) {
            if (element.equals(objectToCount))
                numOfOccurrences = numOfOccurrences + 1;
        }
        return numOfOccurrences;
    }

    public E getMostCommonFromMerge(E[] arrayToMerge) {
        List<E> mergedList = Stream.concat(Arrays.stream(array), Arrays.stream(arrayToMerge)).collect(Collectors.toList());
        HashSet<E> set = new HashSet(mergedList);
        Map<E, Integer> map = new HashMap<>();
        for (E e : set) {
            map.put(e, getNumberOfOccurrences(mergedList, e));
        }
        return getMaxOccurrences(map);
    }

    private E getMaxOccurrences(Map<E, Integer> map) {
        int max = 0;
        E maxKey = null;
        for (Map.Entry<E, Integer> entry : map.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                maxKey = entry.getKey();
            }
        }
        return maxKey;
    }

    public Integer getNumberOfOccurrences(E valueToEvaluate) {
        Integer numOfOccurrences = 0;
        for (E value : array) {
            if (value.equals(valueToEvaluate))
                numOfOccurrences++;
        }
        return numOfOccurrences;
    }

    public E[] removeValue(E valueToRemove) {
        ArrayList<E> newList = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            if (!array[i].equals(valueToRemove))
                newList.add(array[i]);
        }
        E[] result = (E[]) Array.newInstance(valueToRemove.getClass(), this.array.length - getNumberOfOccurrences(valueToRemove));
        for (int i = 0; i < result.length; i++) {
            result[i] = newList.get(i);
        }
        return result;
    }
}
