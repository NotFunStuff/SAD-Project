package View.Panel;

import Controller.ControllerModel;
import Model.Item;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class CreateOrder {
    public JPanel createOrder;
    private String customerId;
    private JTextField itemIDField;
    private JButton submitButton;
    private JSpinner quantitySpinner;
    private JSpinner distanceSpinner;

    public CreateOrder() {
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerModel CM = new ControllerModel();
                try {
                    CM.loadFromFile();
                    String id = itemIDField.getText();
                    int qS = (int) quantitySpinner.getValue();
                    int dS = (int) distanceSpinner.getValue();
                    Item item = CM.searchItemById(id);
                    if (!CM.itemIsIn(id)) {
                        BasicPanel.failCreateIDOrder();
                        return;
                    }
                    if (CM.orderIsIn(id)){
                        BasicPanel.failCreateIDCusOrder();
                        return;
                    }
                    if (item.getQuantity() < qS) {
                        BasicPanel.failCreateOrderAlert();
                        return;
                    } else {
//                        Order order = new Order(item.getId(),item.getName(), item.getCategory(), item.getMFG(), item.getEXP(), qS, item.getAdmin(),
//                                item.getManufacturer(), dS, null,
//                                LocalDateTime.now(), customerId);
                        CM.createOrder(item.getId(), item.getName(), item.getCategory(), item.getAdmin(), item.getEXP(), qS,
                                item.getManufacturer(), dS, customerId, item.getPrice());
                        CM.editItem(item.getId(), item.getName(), item.getCategory(), item.getEXP(), item.getQuantity() - qS, item.getManufacturer(), item.getAdmin(), item.getPrice());
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

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
