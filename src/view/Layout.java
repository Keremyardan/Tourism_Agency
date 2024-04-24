package view;

import core.Helper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Layout extends JFrame {
    // initialization of visual interface
    public void visualInitialize(int width, int height) {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // label of the windows
        this.setTitle("Hotel");
        // width and height values
        this.setSize(width, height);
        // window location setter
        this.setLocation(Helper.getLocationPoint("x", this.getSize()), Helper.getLocationPoint("y", this.getSize()));
        // setting visibility to true
        this.setVisible(true);

    }

    public void createTable(DefaultTableModel model, JTable table, Object[] columns, ArrayList<Object[]> rows) {
        model.setColumnIdentifiers(columns);
        // set table as model
        table.setModel(model);
        table.getTableHeader().setReorderingAllowed(false);
        // max width
        table.getColumnModel().getColumn(0).setMaxWidth(75);
        table.setEnabled(false);


        // table clear by setting the count to 0
        DefaultTableModel clearModel = (DefaultTableModel) table.getModel();
        clearModel.setRowCount(0);


        // Eğer satırlar null ise boş bir liste oluştur // if rows are empty, create a new arraylist(create a list)
        // and add rows to default table
        if (rows == null) {
            rows = new ArrayList<>();
        }

        for (Object[] row : rows) {
            model.addRow(row);
        }
    }


    // method for row selection
    public int getTableSelectedRow(JTable table, int index) {
        int selectedRow = table.getSelectedRow();
        // -1 indicates no row is selected
        if (selectedRow != -1) {
            try {
                return Integer.parseInt(table.getValueAt(selectedRow, index).toString());
            } catch (NumberFormatException e) {
                // handling the NumberFormatException
                // returning a default value, such as -1, or implementing another error handling strategy
                System.err.println("NumberFormatException: " + e.getMessage());
                return -1;
            }
        } else {
            // if no row is selected, returning a default value, such as -1, or implementing another error handling strategy
            return -1;
        }
    }


    // listener for table row selection
    public void tableRowSelect(JTable table) {
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int selected_row = table.rowAtPoint(e.getPoint());
                table.setRowSelectionInterval(selected_row, selected_row);
            }
        });
    }
}
