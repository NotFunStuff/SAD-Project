package View.Panel;

import Controller.ControllerModel;
import Model.Item;
import Model.Order;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static View.Panel.AdminMenu.closeDelete;
import static View.Panel.BasicPanel.confirmChange;

public class DeleteOrder {
    private String customerID;
    public JPanel deleteForm;
    private JButton submitButton;
    private JTextField orderID;

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public DeleteOrder() {
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerModel CM = new ControllerModel();
                try {
                    CM.loadFromFile();
                    String id = orderID.getText();
                    if (!CM.orderIsIn(id)) {
                        BasicPanel.failCreateIDOrder();
                        return;
                    } else {
                        Order order = CM.searchOrderById(id);
                        if(!order.getShipper().equals("null")){
                            BasicPanel.failEditOrder();
                            return;
                        }
                        if (!customerID.equals(order.getCustomer())) {
                            BasicPanel.failCreateIDOrder();
                            return;
                        }
                        boolean confirm = confirmChange();
                        if (confirm) {
                            CM.deleteOrder(id);
                            CM.saveToFile();
                            BasicPanel.successAlert();
//                            closeDelete();
                        }
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
            }

        });
    }
}
