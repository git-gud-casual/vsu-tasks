package ru.vsu.sps.gui;

import ru.vsu.sps.utils.ArrayUtils;
import ru.vsu.sps.utils.FileUtils;
import ru.vsu.sps.utils.SelectionSort;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class MainFrame extends JFrame {
    private JLabel inputFileLabel;
    private JTextField inputFileField;
    private JButton loadInputFileBtn;
    private JLabel outputFileLabel;
    private JTextField outputFileField;
    private JButton saveInOutputFileBtn;
    private JTable arrayTable;
    private JButton sortBtn;
    private JPanel mainPanel;

    public MainFrame() {
        setTitle("Task");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();

        ActionListener btnEvent = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setCurrentDirectory(new File("."));
                    int response = fileChooser.showOpenDialog(null);
                    if (response == JFileChooser.APPROVE_OPTION) {
                        File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                        if (e.getSource() == loadInputFileBtn) {
                            tableFromFilePath(file.toString());
                        } else {
                            saveToFilePath(file.toString());
                        }
                    }
                }
                catch (IOException exc) {
                    JOptionPane.showMessageDialog(null, exc.toString());
                }
            }
        };

        JButton[] filesButtons = new JButton[] {loadInputFileBtn, saveInOutputFileBtn};
        for (JButton button : filesButtons) {
            button.addActionListener(btnEvent);
        }

        sortBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[][] arr = getArray2FromTable();

                SelectionSort.sortColumns(arr);
                setArrayTable(arr);
            }
        });
    }

    private void setArrayTable(int[][] arr) {
        if (arr.length > 0) {
            TableModel tableModel = new DefaultTableModel(arr.length, arr[0].length);

            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[i].length; j++) {
                    tableModel.setValueAt(arr[i][j], i, j);
                }
            }

            arrayTable.setModel(tableModel);
        }
    }

    private void tableFromFilePath(String filePath) throws IOException {
        inputFileField.setText(filePath);
        String text = FileUtils.readFromFile(Paths.get(filePath));
        int[][] arr = ArrayUtils.getArray2FromString(text);
        if (ArrayUtils.array2IsRectangular(arr)) {
            setArrayTable(arr);
        }
        else {
            JOptionPane.showMessageDialog(this, "Array from src file is not rectangular");
        }
    }

    private void saveToFilePath(String filePath) throws IOException {
        outputFileField.setText(filePath);

        int[][] arr = getArray2FromTable();
        FileUtils.writeStringToFile(Paths.get(filePath), ArrayUtils.array2toString(arr));
    }

    private int[][] getArray2FromTable() {
        TableModel tableModel = arrayTable.getModel();

        int[][] arr = new int[tableModel.getRowCount()][tableModel.getColumnCount()];
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            for (int j = 0; j < tableModel.getColumnCount(); j++) {
                arr[i][j] = (int) tableModel.getValueAt(i, j);
            }
        }
        return arr;
    }
}
