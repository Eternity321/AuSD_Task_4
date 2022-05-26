package ru.vsu.cs.Akimushkin;

public class Main {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                util.SwingUtils.setDefaultFont(18);
                new FormSort().setVisible(true);
            }
        });
        //System.out.println(Arrays.toString(MergeSort.sortArray(new int[]{3, 2, 4, 5, 1})));
    }
}
