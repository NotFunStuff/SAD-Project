package View.Panel;

import Controller.ControllerModel;
import Model.Item;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static View.Panel.AdminMenu.closeDelete;
import static View.Panel.BasicPanel.confirmChange;

public class DeleteItem {
    public JPanel deleteFrame;
    private JButton submitButton;
    private JTextField idField;
    private String adminID;

    public DeleteItem() {
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerModel CM = new ControllerModel();
                try {
                    CM.loadFromFile();
                    String id = idField.getText();
                    if (!CM.itemIsIn(id)) {
                        BasicPanel.failCreateIDOrder();
                        return;
                    } else {
                        Item item = CM.searchItemById(id);
                        if (!adminID.equals(item.getAdmin())) {
                            BasicPanel.failCreateIDOrder();
                            return;
                        }
                        boolean confirm = confirmChange();
                        if (confirm) {
                            CM.deleteItem(id);
//                            CM.deleteOrder(id);
                            CM.saveToFile();
                            BasicPanel.successAlert();
                            closeDelete();
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

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }
}
