package ru.vsu.cs.Akimushkin;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class FormSort extends JFrame{
    private JPanel panelSort;
    private JTable tableInput;
    private JTable tableOutput;
    private JButton buttonLoadFromFile;
    private JButton buttonRandom;
    private JButton ButtonSaveResult;
    private JButton ButtonSort;
    private JTable tablePairs;
    private JTextField массивОбменовTextField;
    private JTextField textFieldSize;
    private JButton ButtonCreateBySize;
    private JSpinner spinnerSize;

    private JFileChooser fileChooserOpen;
    private JFileChooser fileChooserSave;

    public FormSort() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Сортировка с обменом");
        this.setContentPane(panelSort);

        util.JTableUtils.initJTableForArray(tableInput, 40, true, true, true, true);
        util.JTableUtils.initJTableForArray(tableOutput, 40, true, true, true, true);
        tableInput.setRowHeight(25);
        tableOutput.setRowHeight(25);



        fileChooserOpen = new JFileChooser();
        fileChooserSave = new JFileChooser();
        fileChooserOpen.setCurrentDirectory(new File("."));
        fileChooserSave.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooserOpen.addChoosableFileFilter(filter);
        fileChooserSave.addChoosableFileFilter(filter);

        fileChooserSave.setAcceptAllFileFilterUsed(false);
        fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooserSave.setApproveButtonText("Save");

        util.JTableUtils.writeArrayToJTable(tableInput, new int[] {1, 2, 3, 4, 5});
        util.JTableUtils.writeArrayToJTable(tableOutput, new int[] {1, 2, 3, 4, 5});

        int width = 800;
        int height = 900;

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((screenSize.width - width) / 2, (screenSize.height - height) / 2);
        this.setPreferredSize(new Dimension(width, height));

        spinnerSize.setValue(7);

        this.pack();

        buttonLoadFromFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (fileChooserOpen.showOpenDialog(panelSort) == JFileChooser.APPROVE_OPTION) {
                        int[] arr = util.ArrayUtils.readIntArrayFromFile(fileChooserOpen.getSelectedFile().getPath());
                        util.JTableUtils.writeArrayToJTable(tableInput, arr);
                    }
                } catch (Exception ex) {
                    util.SwingUtils.showErrorMessageBox(ex);
                }
            }
        });
        buttonRandom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int[] arr = util.ArrayUtils.createRandomIntArray(
                            tableInput.getColumnCount(), 100);
                    util.JTableUtils.writeArrayToJTable(tableInput, arr);
                } catch (Exception ex) {
                    util.SwingUtils.showErrorMessageBox(ex);
                }
            }
        });
        ButtonSort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int[] arr = util.JTableUtils.readIntArrayFromJTable(tableInput);

                    if (arr != null) {
                        int[] arrSorted = Sorts.sortArray(arr);
                        Return result = new Return(Sorts.findSwaps(arr, arrSorted));
                        util.JTableUtils.writeArrayToJTable(tableOutput, result.arrSorted);
                        util.JTableUtils.writeArrayToJTable(tablePairs, result.pairs);
                    }
                    //System.out.println(Arrays.toString(arr));
                } catch (Exception ex) {
                    util.SwingUtils.showErrorMessageBox(ex);
                }
            }
        });
        ButtonSaveResult.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (fileChooserSave.showSaveDialog(panelSort) == JFileChooser.APPROVE_OPTION) {
                        int[][] matrix = util.JTableUtils.readIntMatrixFromJTable(tableOutput);
                        String file = fileChooserSave.getSelectedFile().getPath();
                        if (!file.toLowerCase().endsWith(".txt")) {
                            file += ".txt";
                        }
                        util.ArrayUtils.writeArrayToFile(file, matrix);
                    }
                } catch (Exception ex) {
                    util.SwingUtils.showErrorMessageBox(ex);
                }
            }
        });
        ButtonCreateBySize.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int size = (int) spinnerSize.getValue();
                int[] arr = Sorts.createArrBySize(size);
                util.JTableUtils.writeArrayToJTable(tableInput, arr);
            }
        });
    }
}
