package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class OrderList implements Serializable {
    private static final long serialVersionUID = -6500665823330706018L;
    private final ArrayList<Order> orderList;

    public OrderList() {
        orderList = new ArrayList<>();
    }

    public void add(Order order) {
        orderList.add(order);
    }

    public void remove(String id) {
        for (int i = 0; i < size(); i++) {
            if (id.equals(orderList.get(i).getId())) {
                orderList.remove(i);
            }
        }
    }

    public Order searchById(String id) {
        for (int i = 0; i < size(); i++) {
            if (id.equals(orderList.get(i).getId())) {
                return orderList.get(i);
            }
        }
        return null;
    }

    public ArrayList<Order> searchByName(String name) {
        ArrayList<Order> temp = new ArrayList<>();
        for (int i = 0; i < size(); i++) {
            if (name.equals(orderList.get(i).getName())) {
                temp.add(orderList.get(i));
            }
        }
        return temp;
    }

    public ArrayList<Order> searchByShipper(String shipper) {
        ArrayList<Order> temp = new ArrayList<>();
        for (int i = 0; i < size(); i++) {
            if (shipper.equals(orderList.get(i).getShipper())) {
                temp.add(orderList.get(i));
            }
        }
        return temp;
    }

    public ArrayList<Order> searchByCustomer(String customer) {
        ArrayList<Order> temp = new ArrayList<>();
        for (int i = 0; i < size(); i++) {
            if (customer.equals(orderList.get(i).getShipper())) {
                temp.add(orderList.get(i));
            }
        }
        return temp;
    }

    public boolean isIn(String id) {
        for (int i = 0; i < size(); i++) {
            if (id.equals(orderList.get(i).getId())) {
                return true;
            }
        }
        return false;
    }

    public void edit(Order order) {
        for (int i = 0; i < size(); i++) {
            if (order.getId().equals(orderList.get(i).getId())) {
                orderList.set(i, order);
            }
        }
    }

    public Order get(int i) {
        return orderList.get(i);
    }

    public int size() {
        return orderList.size();
    }
}
