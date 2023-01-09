package ru.vsu.sps.gui;

import ru.vsu.sps.triangle.Triangle;
import ru.vsu.sps.triangle.TriangleComparatorBySquare;
import ru.vsu.sps.utils.ArrayUtils;
import ru.vsu.sps.utils.FileUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
    private JButton addRowBtn;
    private JButton deleteRowBtn;

    public MainFrame() {
        setTitle("Task");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();

        ActionListener btnEvent = e -> {
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
        };

        JButton[] filesButtons = new JButton[] {loadInputFileBtn, saveInOutputFileBtn};
        for (JButton button : filesButtons) {
            button.addActionListener(btnEvent);
        }

        sortBtn.addActionListener(e -> {
            try {
                List<List<Integer>> list = getList2FromTable();

                List<Triangle> trianglesList = new ArrayList<>();
                for (List<Integer> val : list) {
                    Triangle triangle = Triangle.fromIntegerList(val);
                    if (triangle.isExists()) {
                        trianglesList.add(triangle);
                    }
                }
                trianglesList.sort(new TriangleComparatorBySquare());

                list.clear();
                for (Triangle triangle : trianglesList) {
                    list.add(triangle.asList());
                }

                fillTableByList(list);
            }
            catch (IllegalArgumentException exc) {
                JOptionPane.showMessageDialog(null, exc.toString());
            }
        });

        JButton[] changeTableButtons = {deleteRowBtn, addRowBtn};
        for (JButton btn : changeTableButtons) {
            btn.addActionListener(e -> {
                List<List<Integer>> list = getList2FromTable();
                TableModel tableModel;

                if (e.getSource() == deleteRowBtn) {
                    tableModel = new DefaultTableModel(list.size() - 1, 6);
                } else {
                    tableModel = new DefaultTableModel(list.size() + 1, 6);
                }

                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    for (int j = 0; j < tableModel.getColumnCount(); j++) {
                        if (i < list.size()) {
                            tableModel.setValueAt(list.get(i).get(j), i, j);
                        } else {
                            tableModel.setValueAt(0, i, j);
                        }
                    }
                }
                arrayTable.setModel(tableModel);
            });
        }
    }

    private void fillTableByList(List<List<Integer>> list) {
        if (list.size() > 0) {
            TableModel tableModel = new DefaultTableModel(list.size(), list.get(0).size());

            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < list.get(i).size(); j++) {
                    tableModel.setValueAt(list.get(i).get(j), i, j);
                }
            }

            arrayTable.setModel(tableModel);
        }
        else {
            arrayTable.setModel(new DefaultTableModel());
        }
    }

    private void tableFromFilePath(String filePath) throws IOException {
        inputFileField.setText(filePath);
        String text = FileUtils.readFromFile(Paths.get(filePath));
        List<List<Integer>> list = ArrayUtils.getList2FromString(text);
        fillTableByList(list);
    }

    private void saveToFilePath(String filePath) throws IOException {
        outputFileField.setText(filePath);
        FileUtils.writeStringToFile(Paths.get(filePath), ArrayUtils.getStringFromList2(getList2FromTable()));
    }

    private List<List<Integer>> getList2FromTable() {
        TableModel tableModel = arrayTable.getModel();

        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            list.add(new ArrayList<>());
            for (int j = 0; j < tableModel.getColumnCount(); j++) {
                list.get(i).add((Integer) tableModel.getValueAt(i, j));
            }
        }
        return list;
    }
}
