package View.Panel;

import Controller.ControllerModel;
import Model.Enum.Role;
import Model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoginPanel {
    private static ControllerModel CM;
    private static JFrame signUp;
    public JPanel LoginFrame;
    private JPasswordField passWord;
    private JTextField userName;
    private JButton submitButton;
    private JButton signUpButton;
    private JButton aboutButton;

    public LoginPanel() {
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userName.getText();
                String password = passWord.getText();
                CM = new ControllerModel();
                try {
                    CM.loadFromFile();
                    User u = CM.searchUserByUserName(username);
                    if (u.equals(null)) {
                        BasicPanel.failLoginAlert();
                    } else {
                        if (password.equals(u.getPassword())) {
                            if (u.getRole().equals(Role.Admin)) {
                                CM.closeLogin();
                                CM.makeAdminFrame(u.getId());
                            } else if (u.getRole().equals(Role.Customer)) {
                                CM.closeLogin();
                                CM.makeCustomerFrame(u.getId());
                            } else if (u.getRole().equals(Role.Shipper)) {
                                CM.closeLogin();
                                CM.makeShipperFrame(u.getId());
                            }
                        } else {
                            BasicPanel.failLoginAlert();
                        }
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }

            }
        });

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signUp = new JFrame("Test");
                signUp.setContentPane(new SignUpPanel().SignUpFrame);
                signUp.pack();
                signUp.setVisible(true);
            }
        });

        aboutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BasicPanel.about();
            }
        });
    }

    public static void refreshAdmin(String id) {
        CM.refreshAdmin(id);
    }

    public static void refreshCustomer(String id) {
        CM.refreshCustomer(id);
    }

    public static void customerClose() {
        CM.customerLogOut();
    }

    public static void shipperClose() {
        CM.shipperLogOut();
    }

    public static void adminClose() {
        CM.adminLogOut();
    }

    public static void close() {
        signUp.dispose();
    }

    public static void refreshShipper(String shipperID) {
        CM.refreshShipper(shipperID);
    }
}
