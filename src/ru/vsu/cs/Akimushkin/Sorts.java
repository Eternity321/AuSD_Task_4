package ru.vsu.cs.Akimushkin;

import java.util.ArrayList;
import java.util.HashMap;

public class Sorts {
    /*public static int[][] createObjectForNums (int[] numbers) {
        int len = numbers.length;
        int[][] objectNum = new int[len][3];

        for (int i = 0; i < len; i++) {
            objectNum[i][0] = numbers[i];
            objectNum[i][1] = i;
        }
        return objectNum;
    }*/

    /*public static void swapNums(int a, int b) {
        int temp = a;
        a = b;
        b = temp;
    }*/

    public static Return findSwaps(int[] arr, int[] arrSorted) {
        ArrayList<SwapPair> pairs = new ArrayList<>();

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], i);
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != arrSorted[i]) {
                int sourceElement = arr[i];

                int idx = map.get(arrSorted[i]);

                int tmp = arr[i];
                arr[i] = arr[idx];
                arr[idx] = tmp;
                //swapNums(arr[i], arr[idx]);

                pairs.add(new SwapPair(i, idx));

                map.put(sourceElement, map.get(arrSorted[i]));
                map.put(arrSorted[i], i);
            }
        }

        int[][] pairsResult = new int[pairs.size()][2];
        for (int i = 0; i < pairsResult.length; i++) {
            pairsResult[i] = new int[] {pairs.get(i).objectA, pairs.get(i).objectB};
        }

        return new Return(arr, pairsResult);
    }

    /*public static int[][] prepareForSortWithSwaps (int[] arr) {
        int[][] arrOfSwaps = createObjectForNums(arr);
        int[] arrTemp = sortArray(arr);
        int len = arr.length;

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (arrTemp[i] == arrOfSwaps[j][0]) {
                    arrOfSwaps[j][2] = i;
                }
            }
        }
        return arrOfSwaps;
    }*/

    /*public static int[][] sortWithSwaps (int[] arr, int[][] arrOfSwaps) {
        int len = arr.length;

        int[][] orderOfSwaps = new int[arr.length][2];

        int[] temp;
        int index;

        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                if (arrOfSwaps[j][2] == i && arrOfSwaps[j][1] != arrOfSwaps[j][2]) {
                    orderOfSwaps[i] = new int[]{arr[i], arr[j]};
                    swapNums(arr[i], arr[j]);
                    swapNums(arrOfSwaps[i][0], arrOfSwaps[j][0]);
                    swapNums(arrOfSwaps[i][2], arrOfSwaps[j][2]);
                    break;
                }
            }
        }
        //System.out.println(Arrays.deepToString(arrOfSwaps));
        for (int i = 0; i < len; i++) {
            arr[i] = arrOfSwaps[i][0];
        }
        return orderOfSwaps;
    }*/

    public static int[] sortArray(int[] arrayA) {
        if (arrayA == null) {
            return null;
        }

        if (arrayA.length < 2) {
            return arrayA;
        }

        int [] arrayB = new int[arrayA.length / 2];
        System.arraycopy(arrayA, 0, arrayB, 0, arrayA.length / 2);

        int [] arrayC = new int[arrayA.length - arrayA.length / 2];
        System.arraycopy(arrayA, arrayA.length / 2, arrayC, 0, arrayA.length - arrayA.length / 2);

        arrayB = sortArray(arrayB);
        arrayC = sortArray(arrayC);

        return mergeArray(arrayB, arrayC);
    }



    public static int [] mergeArray(int[] arrayA, int[] arrayB) {

        int[] arrayC = new int[arrayA.length + arrayB.length];
        int positionA = 0, positionB = 0;

        while (positionA < arrayA.length && positionB < arrayB.length) {
            if (arrayA[positionA] < arrayB[positionB]) {
                arrayC[positionA + positionB] = arrayA[positionA];
                positionA++;
            }
            else {
                arrayC[positionA + positionB] = arrayB[positionB];
                positionB++;
            }
        }
        if (positionA == arrayA.length) {
            while (positionB < arrayB.length) {
                arrayC[positionA + positionB] = arrayB[positionB];
                positionB++;
            }
        }
        if (positionB == arrayB.length) {
            while (positionA < arrayA.length) {
                arrayC[positionA + positionB] = arrayA[positionA];
                positionA++;
            }
        }
        return arrayC;
    }

    public static int[] createArrBySize(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i ++) {
            arr[i] = i + 1;
        }
        return arr;
    }
}
