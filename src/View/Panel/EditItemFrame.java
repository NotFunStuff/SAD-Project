package View.Panel;

import Controller.ControllerModel;
import Model.Item;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDateTime;

public class EditItemFrame {
    public JPanel editItemFrame;
    private JTextField id;
    private JTextField name;
    private JTextField category;
    private JSpinner daySpinner;
    private JSpinner monthSpinner;
    private JSpinner yearSpinner;
    private JTextField manufacturer;
    private JButton backButton;
    private JButton submitButton;
    private JSpinner priceSpinner;
    private JSpinner quantitySpinner;
    private String adminID;

    public EditItemFrame() {
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminMenu.closeEdit();
            }
        });
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerModel CM = new ControllerModel();
                try {
                    CM.loadFromFile();
                    String idText = id.getText();
                    String nameText = name.getText();
                    String categoryText = category.getText();
                    int quantity = (int) quantitySpinner.getValue();
                    int price = (int) priceSpinner.getValue();
                    int day = (int) daySpinner.getValue();
                    int month = (int) monthSpinner.getValue();
                    int year = (int) yearSpinner.getValue();
                    String manufacturerText = manufacturer.getText();
                    if (!CM.itemIsIn(idText)) {
                        BasicPanel.failCreateIDOrder();
                        return;
                    }
                    Item item = CM.searchItemById(idText);
                    if (!item.getAdmin().equals(adminID)) {
                        BasicPanel.failCreateIDOrder();
                        return;
                    }
                    CM.editItem(idText, nameText, categoryText, LocalDateTime.of(year, month, day, 0, 0), quantity, manufacturerText, adminID, price);
                    CM.saveToFile();
                    BasicPanel.successAlert();
                    AdminMenu.closeEdit();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
            }
        });
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

    private void createUIComponents() {
        SpinnerNumberModel qS = new SpinnerNumberModel(1, 1, 100, 1);
        SpinnerNumberModel pS = new SpinnerNumberModel(1, 1, 10000, 1);
        SpinnerNumberModel dS = new SpinnerNumberModel(1, 1, 31, 1);
        SpinnerNumberModel mS = new SpinnerNumberModel(1, 1, 12, 1);
        SpinnerNumberModel yS = new SpinnerNumberModel(2020, 2020, 2030, 1);
        quantitySpinner = new JSpinner(qS);
        priceSpinner = new JSpinner(pS);
        daySpinner = new JSpinner(dS);
        monthSpinner = new JSpinner(mS);
        yearSpinner = new JSpinner(yS);
    }
}
