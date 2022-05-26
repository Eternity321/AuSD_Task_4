package ru.vsu.cs.Akimushkin;

public class Return {
    int[] arrSorted;
    int[][] pairs;

    Return (int[] arrSorted, int[][] pairs) {
        this.arrSorted = arrSorted;
        this.pairs = pairs;
    }

    Return (Return swaps) {
        this.arrSorted = swaps.arrSorted;
        this.pairs = swaps.pairs;
    }
}
