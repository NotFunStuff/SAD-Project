package View.Panel;

import Controller.ControllerModel;
import Model.Order;
import Model.OrderList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ShipperMenu {
    public JPanel shipperForm;
    private JButton displayAllOrderButton;
    private JButton displayAvailableOrderButton;
    private JButton displayYourOrderButton;
    private JButton acceptOrderButton;
    private JButton deleteOrderButton;
    private JButton logOutButton;
    private JTable allOrderTable;
    private JButton refreshButton;
    private JTable availableOrder;
    private JTable yourOrder;
    private JFrame acceptOrder;
    private JFrame cancelOrder;
    private String shipperID;

    public void setShipperForm(JPanel shipperForm) {
        this.shipperForm = shipperForm;
    }

    public ShipperMenu(String shipperID) {
        this.shipperID = shipperID;
        displayAllOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                createAllOrderTable();
                allOrderTable.setVisible(true);
                availableOrder.setVisible(false);
                yourOrder.setVisible(false);
            }
        });
        displayAvailableOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                createAvailableTable();
                allOrderTable.setVisible(false);
                availableOrder.setVisible(true);
                yourOrder.setVisible(false);
            }
        });
        displayYourOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                createYourOrder();
                allOrderTable.setVisible(false);
                availableOrder.setVisible(false);
                yourOrder.setVisible(true);
            }
        });
        acceptOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acceptOrder = new JFrame("Accept order");
                acceptOrder.setContentPane(new AcceptOrder(shipperID).acceptOrder);
                acceptOrder.pack();
                acceptOrder.setLocationRelativeTo(null);
                acceptOrder.setVisible(true);
            }
        });
        deleteOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelOrder = new JFrame("Login");
                cancelOrder.setContentPane(new CancelOrder(shipperID).cancelOrder);
                cancelOrder.pack();
                cancelOrder.setLocationRelativeTo(null);
                cancelOrder.setVisible(true);
            }
        });
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginPanel.shipperClose();
            }
        });
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginPanel.refreshShipper(shipperID);
            }
        });
    }

    private void createAllOrderTable(){
        String[] columns = {"ID", "name", "quantity", "distance", "order time", "expected time", "Status", "Shipper"};
        ControllerModel CM = new ControllerModel();
        try {
            CM.loadFromFile();
            OrderList oL = CM.getOrderList();
            Object[][] table = new String[oL.size() + 1][8];
            table[0] = columns;
            for (int i = 0; i < oL.size(); i++) {
                String[] data = new String[8];
                Order order = oL.get(i);
                data[0] = order.getId();
                data[1] = order.getName();
                data[6] = order.getStatus().toString();
                data[2] = String.valueOf(order.getQuantity());
                data[3] = String.valueOf(order.getDistance());
                data[4] = order.getOrderTime().toString();
                data[5] = order.getExpectedTime().toString();
                data[7] = order.getShipper();
                table[i + 1] = data;
            }
            allOrderTable = new JTable();
            allOrderTable.setModel(new DefaultTableModel(table, columns));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
    }

    private void createAvailableTable(){
        String[] columns = {"ID", "name", "quantity", "distance", "order time", "expected time", "Status", "Shipper"};
        ControllerModel CM = new ControllerModel();
        try {
            CM.loadFromFile();
            OrderList oL = CM.getOrderList();
            for(int i =0; i<oL.size();i++){
                if(!oL.get(i).getShipper().equals("null")){
                    oL.remove(oL.get(i).getId());
                }
            }
            Object[][] table = new String[oL.size() + 1][8];
            table[0] = columns;
            for (int i = 0; i < oL.size(); i++) {
                String[] data = new String[8];
                Order order = oL.get(i);
                data[0] = order.getId();
                data[1] = order.getName();
                data[6] = order.getStatus().toString();
                data[2] = String.valueOf(order.getQuantity());
                data[3] = String.valueOf(order.getDistance());
                data[4] = order.getOrderTime().toString();
                data[5] = order.getExpectedTime().toString();
                data[7] = order.getShipper();
                table[i + 1] = data;
            }
            availableOrder = new JTable();
            availableOrder.setModel(new DefaultTableModel(table, columns));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
    }

    private void createYourOrder(){
        String[] columns = {"ID", "name", "quantity", "distance", "order time", "expected time", "Status", "Shipper"};
        ControllerModel CM = new ControllerModel();

        try {
            CM.loadFromFile();
            OrderList oL = CM.getOrderList();
            for(int i =0; i<oL.size();i++){
                if(!oL.get(i).getShipper().equals(shipperID)){
                    oL.remove(oL.get(i).getId());
                }
            }
            Object[][] table = new String[oL.size() + 1][8];
            table[0] = columns;
            for (int i = 0; i < oL.size(); i++) {
                String[] data = new String[8];
                Order order = oL.get(i);
                data[0] = order.getId();
                data[1] = order.getName();
                data[6] = order.getStatus().toString();
                data[2] = String.valueOf(order.getQuantity());
                data[3] = String.valueOf(order.getDistance());
                data[4] = order.getOrderTime().toString();
                data[5] = order.getExpectedTime().toString();
                data[7] = order.getShipper();
                table[i + 1] = data;
            }
            yourOrder = new JTable();
            yourOrder.setModel(new DefaultTableModel(table, columns));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
    }

    private void createUIComponents() {
        createAllOrderTable();
        createAvailableTable();
        createYourOrder();
        allOrderTable.setVisible(true);
        availableOrder.setVisible(false);
        yourOrder.setVisible(false);
    }
}
