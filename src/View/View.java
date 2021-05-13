package View;

import Controller.ControllerModel;
import Model.User;
import View.Panel.AdminMenu;
import View.Panel.CustomerMenu;
import View.Panel.LoginPanel;
import View.Panel.ShipperMenu;

import javax.swing.*;
import java.io.IOException;

public class View {
    private static JFrame loginFrame;
    private static JFrame adminFrame;
    private static JFrame customerFrame;
    private static JFrame shipperFrame;

    public View() {
    }

    public static void loginClose() {
        loginFrame.dispose();
    }

    public static void makeLoginFrame() {
        loginFrame = new JFrame("Login");
        loginFrame.setContentPane(new LoginPanel().LoginFrame);
        loginFrame.pack();
        loginFrame.setVisible(true);
        loginFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        loginFrame.setLocationRelativeTo(null);
    }

    public static void adminLogOut() {
        adminFrame.dispose();
        makeLoginFrame();
    }

    public static void customerLogOut() {
        customerFrame.dispose();
        makeLoginFrame();
    }

    public static void shipperLogOut() {
        shipperFrame.dispose();
        makeLoginFrame();
    }

    private static String getNameOfUser(String id) {
        ControllerModel CM = new ControllerModel();
        try {
            CM.loadFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        User u = CM.searchUserById(id);
        return u.getName();
    }

    public static void makeCustomerFrame(String id) {
        String username = getNameOfUser(id);
        customerFrame = new JFrame("Customer: " + username);
        CustomerMenu a = new CustomerMenu(id);
        customerFrame.setContentPane(a.customerMenu);
        customerFrame.pack();
        customerFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        customerFrame.setSize(1200,200);
        customerFrame.setLocationRelativeTo(null);
        customerFrame.setVisible(true);
    }

    public static void makeShipperFrame(String id) {
        String username = getNameOfUser(id);
        shipperFrame = new JFrame("Shipper " + username + " !");
        shipperFrame.setContentPane(new ShipperMenu(id).shipperForm);
        shipperFrame.pack();
        shipperFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        shipperFrame.setSize(1200,200);
        shipperFrame.setLocationRelativeTo(null);
        shipperFrame.setVisible(true);
    }

    public static void makeAdminFrame(String id) {
        String username = getNameOfUser(id);
        adminFrame = new JFrame("Admin " + username + " !");
        AdminMenu a = new AdminMenu();
        a.setAdminId(id);
        adminFrame.setContentPane(a.adminMenu1);
        adminFrame.pack();
        adminFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        adminFrame.setSize(1200,200);
        adminFrame.setLocationRelativeTo(null);
        adminFrame.setVisible(true);
    }

    public static void refreshAdmin(String id) {
        adminFrame.dispose();
        makeAdminFrame(id);
    }

    public static void refreshCustomer(String id) {
        customerFrame.dispose();
        makeCustomerFrame(id);
    }

    public static void refreshShipper(String id) {
        shipperFrame.dispose();
        makeShipperFrame(id);
    }
}
