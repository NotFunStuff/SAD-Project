package View.Panel;

import Controller.ControllerModel;
import Model.Order;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class CancelOrder {
    public JPanel cancelOrder;
    private JButton submitButton;
    private JTextField orderID;
    private String shipperID;

    public CancelOrder(String shipperId) {
        this.shipperID = shipperId;
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerModel CM = new ControllerModel();
                try {
                    CM.loadFromFile();
                    String id = orderID.getText();
                    if(!CM.orderIsIn(id)){
                        BasicPanel.failCreateIDOrder();
                    }
                    ArrayList<Order> order = CM.searchOrderByShipper(shipperID);
                    for (int i = 0 ; i< order.size(); i++){
                        if(order.get(i).getId().equals(id)){
                            CM.searchOrderById(order.get(i).getId()).setShipper("null");
                            BasicPanel.successAlert();
                            CM.saveToFile();
                            return;
                        }
                    }
                    BasicPanel.failCreateIDOrder();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
            }
        });
    }
}
