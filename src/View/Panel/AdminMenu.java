package View.Panel;

import Controller.ControllerModel;
import Model.Item;
import Model.ItemList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AdminMenu {
    private static JFrame createItemPanel;
    private static JFrame deleteItem;
    private static JFrame editItem;
    public JPanel adminMenu1;
    private JButton addItemButton;
    private JButton deleteItemButton;
    private JButton updateItemButton;
    private JButton logOutButton;
    private JTable itemTable;
    private JButton refreshButton;
    private JPanel tablePanel;
    private String adminId;

    public AdminMenu() {
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginPanel.adminClose();
            }
        });
        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createItemPanel = new JFrame("Create Item");
                CreateItemPanel temps = new CreateItemPanel();
                temps.setAdminID(adminId);
                createItemPanel.setContentPane(temps.CreateFrame);
                createItemPanel.pack();
                createItemPanel.setLocationRelativeTo(null);
                createItemPanel.setVisible(true);
            }
        });
        deleteItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteItem = new JFrame("Delete Item");
                DeleteItem temps = new DeleteItem();
                temps.setAdminID(adminId);
                deleteItem.setContentPane(temps.deleteFrame);
                deleteItem.pack();
                deleteItem.setLocationRelativeTo(null);
                deleteItem.setVisible(true);
            }
        });
        updateItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editItem = new JFrame("Edit Item");
                EditItemFrame temps = new EditItemFrame();
                temps.setAdminID(adminId);
                editItem.setContentPane(temps.editItemFrame);
                editItem.pack();
                editItem.setLocationRelativeTo(null);
                editItem.setVisible(true);
            }
        });
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginPanel.refreshAdmin(adminId);
            }
        });
    }

    public static void closeCreate() {
        createItemPanel.dispose();
    }

    public static void closeEdit() {
        editItem.dispose();
    }

    public static void closeDelete() {
        deleteItem.dispose();
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    private void createUIComponents() {
        ControllerModel CM = new ControllerModel();
        try {
            CM.loadFromFile();
            ItemList iL = CM.getItemList();
            String[] columns = {"ID", "name", "category", "EXP", "quantity", "manufacturer", "price", "admin_ID"};
//            String[] data = new String[7];
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
            itemTable = new JTable(table, columns);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
    }
}
