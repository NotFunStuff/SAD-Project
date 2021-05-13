package Model;

import Model.Enum.Status;

import java.io.Serializable;
import java.time.LocalDateTime;


public class Order extends Item implements Serializable {
    private static final long serialVersionUID = -6500665823330706018L;
    private double distance;
    private String customer;
    private String shipper;
    private LocalDateTime orderTime;
    private LocalDateTime expectedTime;
    private Status status;

    public Order(String id, String name, String category, LocalDateTime EXP, int quantity, String admin,
                 String manufacturer, int price, double distance, String shipper,
                 LocalDateTime orderTime, String customer) {
        super(id, name, category, EXP, quantity, manufacturer, admin, price);
        this.distance = distance;
        this.shipper = shipper;
        this.orderTime = orderTime;
        this.expectedTime = computeExTime();
        this.customer = customer;
        this.status = Status.Pending;
    }

    private LocalDateTime computeExTime() {
        long seconds = (long) distance * 3;
        LocalDateTime temp = this.orderTime;
        temp.plusMinutes(15 + seconds);
        return temp;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getShipper() {
        return shipper;
    }

    public void setShipper(String shipper) {
        this.shipper = shipper;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public LocalDateTime getExpectedTime() {
        return expectedTime;
    }

    public void setExpectedTime(LocalDateTime expectedTime) {
        this.expectedTime = expectedTime;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
