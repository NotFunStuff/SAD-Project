package View.Panel;

import Controller.ControllerModel;
import Model.Item;
import Model.Order;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDateTime;

public class EditOrderFrame {
    public JPanel editOrder;
    private JButton submitButton;
    private JTextField itemID;
    private JSpinner quantitySpinner;
    private JSpinner distanceSpinner;
    private String customerID;

    public EditOrderFrame() {
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerModel CM = new ControllerModel();
                try {
                    CM.loadFromFile();
                    String id = itemID.getText();
                    int qS = (int) quantitySpinner.getValue();
                    int dS = (int) distanceSpinner.getValue();

                    if (!CM.orderIsIn(id)) {
                        BasicPanel.failCreateIDOrder();
                        return;
                    }
                    Order order = CM.searchOrderById(id);
                    if(!order.getShipper().equals("null")){
                        BasicPanel.failEditOrder();
                        return;
                    }
                    if (CM.searchItemById(id).getQuantity() + order.getQuantity() < qS) {
                        BasicPanel.failCreateOrderAlert();
                        return;
                    } else {
//                        Order order = new Order(item.getId(),item.getName(), item.getCategory(), item.getMFG(), item.getEXP(), qS, item.getAdmin(),
//                                item.getManufacturer(), dS, null,
//                                LocalDateTime.now(), customerId);
                        CM.editOrder(order.getId(), order.getName(), order.getCategory(), order.getEXP(), qS,
                        order.getManufacturer(), dS, customerID,order.getAdmin(), order.getPrice());
                        CM.editItem(order.getId(), order.getName(), order.getCategory(), order.getEXP(),
                                order.getQuantity() + CM.searchItemById(id).getQuantity() - qS, order.getManufacturer(), order.getAdmin(), order.getPrice());
                        CM.saveToFile();
                        BasicPanel.successAlert();
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
            }
        });
    }

    private void createUIComponents() {
        SpinnerNumberModel qS = new SpinnerNumberModel(1, 1, 100, 1);
        SpinnerNumberModel dS = new SpinnerNumberModel(1, 1, 1000, 1);
        quantitySpinner = new JSpinner(qS);
        distanceSpinner = new JSpinner(dS);
    }


}
