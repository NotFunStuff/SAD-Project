package View.Panel;

import Controller.ControllerModel;
import Model.Enum.Role;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class SignUpPanel {
    public JPanel SignUpFrame;
    private JTextField Id;
    private JTextField username;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JTextField name;
    private JRadioButton adminRadioButton;
    private JRadioButton customerRadioButton;
    private JRadioButton shipperRadioButton;
    private JButton submitButton;
    private JPanel RadioRoleField;
    private JPanel SignUpField;
    private JPanel SubmitButton;
    private JButton backToLoginButton;

    public SignUpPanel() {

        backToLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                LoginPanel.close();

            }
        });
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerModel CM = new ControllerModel();
                try {
                    CM.loadFromFile();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
                Role role;
                String id = Id.getText();
                String userName = username.getText();
                String password = passwordField.getText();
                String confirmPassword = confirmPasswordField.getText();
                String nameText = name.getText();
                if (adminRadioButton.isSelected()) {
                    role = Role.Admin;
                } else if (customerRadioButton.isSelected()) {
                    role = Role.Customer;
                } else if (shipperRadioButton.isSelected()) {
                    role = Role.Shipper;
                } else {
                    BasicPanel.failCreateUserRoleAlert();
                    return;
                }
                if (!password.equals(confirmPassword)) {
                    BasicPanel.failCreateUserCPAlert();
                    return;
                }
                for (int i = 0; i < CM.getUserList().size(); i++) {
                    if (userName.equals(CM.getUserList().get(i).getUsername())) {
                        BasicPanel.failCreateUserNameAlert();
                        return;
                    }
                    if (id.equals(CM.getUserList().get(i).getId())) {
                        BasicPanel.failCreateUserIDAlert();
                        return;
                    }
                }
                CM.createUser(id, userName, password, nameText, role);
                try {
                    CM.saveToFile();
                    BasicPanel.successAlert();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }
}
