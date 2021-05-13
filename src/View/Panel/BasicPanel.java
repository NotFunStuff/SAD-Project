package View.Panel;

import javax.swing.*;

public class BasicPanel {
    public static void successAlert() {
        JOptionPane.showMessageDialog(null, "Success");
    }

    public static void failLoginAlert() {
        JOptionPane.showMessageDialog(null, "Wrong user credentials");
    }

    public static void failCreateItemAlert() {
        JOptionPane.showMessageDialog(null, "Item ID already existed");
    }

    public static void failCreateOrderAlert() {
        JOptionPane.showMessageDialog(null, "Item is out of stock");
    }

    public static void failCreateUserIDAlert() {
        JOptionPane.showMessageDialog(null, "User ID already existed");
    }

    public static void failCreateUserNameAlert() {
        JOptionPane.showMessageDialog(null, "Username already existed");
    }

    public static void failCreateUserCPAlert() {
        JOptionPane.showMessageDialog(null, "2 passwords do not match");
    }

    public static void failCreateUserRoleAlert() {
        JOptionPane.showMessageDialog(null, "User Role is not defined");
    }

    public static void failCreateIDOrder() {
        JOptionPane.showMessageDialog(null, "Item not found");
    }

    public static void failCreateIDCusOrder(){
        JOptionPane.showMessageDialog(null,"Order ID already existed");
    }

    public static void failEditOrder(){
        JOptionPane.showMessageDialog(null, "Item is delivering");
    }

    public static void about(){
        JOptionPane.showMessageDialog(null, "Supermarket Management System v1.0 \n made by FunStuff + tiendinh");
    }

    public static boolean confirmChange() {
        String[] confirm = {"yes", "no"};
        int i = JOptionPane.showOptionDialog(null, "Do you really want to make these change?", "Change alert", 1, 1, null, confirm, confirm[1]);
        return i == 0;
    }
}
