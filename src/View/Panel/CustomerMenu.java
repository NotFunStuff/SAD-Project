package View.Panel;

import Controller.ControllerModel;
import Model.Item;
import Model.ItemList;
import Model.Order;
import Model.OrderList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class CustomerMenu extends JFrame {
    public String customerID;
    public JPanel customerMenu;
    private JButton displayItemButton;
    private JButton displayOrderButton;
    private JButton createOrderButton;
    private JButton deleteOrderButton;
    private JButton editOrderButton;
    private JButton logOutButton;
    private JTable itemTable;
    private JButton refreshButton;
    private JLabel tableName;
    private JTable orderTable;
    private JFrame createOrder;
    private JFrame deleteOrder;
    private JFrame editOrder;


    public CustomerMenu(String customerID) {
        this.customerID = customerID;
        displayItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                createItemTable();
                orderTable.setVisible(false);
                tableName.setText("Item List");
                itemTable.setVisible(true);
            }
        });
        displayOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                createOrderTable();
                itemTable.setVisible(false);
                tableName.setText("Order List");
                orderTable.setVisible(true);
            }
        });
        createOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createOrder = new JFrame("Create Order");
                CreateOrder temps = new CreateOrder();
                temps.setCustomerId(customerID);
                createOrder.setContentPane(temps.createOrder);
                createOrder.pack();
                createOrder.setLocationRelativeTo(null);
                createOrder.setVisible(true);
            }
        });
        deleteOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteOrder = new JFrame("Delete Order");
                DeleteOrder temps = new DeleteOrder();
                temps.setCustomerID(customerID);
                deleteOrder.setContentPane(temps.deleteForm);
                deleteOrder.pack();
                deleteOrder.setLocationRelativeTo(null);
                deleteOrder.setVisible(true);
            }
        });
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginPanel.customerClose();
            }
        });
        editOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editOrder = new JFrame("Edit Order");
                editOrder.setContentPane(new EditOrderFrame().editOrder);
                editOrder.pack();
                editOrder.setLocationRelativeTo(null);
                editOrder.setVisible(true);
            }
        });
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginPanel.refreshCustomer(customerID);
            }
        });
    }

    private void createItemTable() {
        ControllerModel CM = new ControllerModel();
        try {
            CM.loadFromFile();
            ItemList iL = CM.getItemList();
            String[] columns = {"ID", "name", "category", "EXP", "quantity", "manufacturer", "price", "admin_ID"};
            Object[][] table = new String[iL.size() + 1][8];
            table[0] = columns;
            for (int i = 0; i < iL.size(); i++) {
                String[] data = new String[8];
                Item item = iL.get(i);
                data[0] = item.getId();
                data[1] = item.getName();
                data[2] = item.getCategory();
                data[3] = item.getEXP().toString();
                data[4] = String.valueOf(item.getQuantity());
                data[5] = item.getManufacturer();
                data[6] = String.valueOf(item.getPrice());
                data[7] = item.getAdmin();
                table[i + 1] = data;
            }
            itemTable = new JTable();
            itemTable.setModel(new DefaultTableModel(table, columns));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }

    }

    private void createOrderTable() {
        String[] columns = {"ID", "name", "price", "quantity", "distance", "order time", "expected time"};
        ControllerModel CM = new ControllerModel();

        try {
            CM.loadFromFile();
            OrderList oL = CM.getOrderList();
            Object[][] table = new String[oL.size() + 1][7];
            table[0] = columns;
            for (int i = 0; i < oL.size(); i++) {
                String[] data = new String[7];
                Order order = oL.get(i);
                data[0] = order.getId();
                data[1] = order.getName();
                data[2] = String.valueOf(order.getPrice());
                data[3] = String.valueOf(order.getQuantity());
                data[4] = String.valueOf(order.getDistance());
                data[5] = order.getOrderTime().toString();
                data[6] = order.getExpectedTime().toString();
                table[i + 1] = data;
            }
            orderTable = new JTable();
            orderTable.setModel(new DefaultTableModel(table, columns));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
    }

    public void setId(String w) {
        customerID = w;
    }

    private void createUIComponents() {
        createItemTable();
        orderTable = new JTable();
        createOrderTable();
        itemTable.setVisible(true);
        orderTable.setVisible(false);
    }
}
