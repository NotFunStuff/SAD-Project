package Controller;

import Model.Enum.Role;
import Model.*;
import View.View;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
/**
 * @overview  ControllerModel(CM) which allows data to flow between the view and the model.
 *                  The controller mediates between the view and model
 * @attribute
 *   view           View
 *   itemList       ItemList<ArrayList>
 *   orderList     OrderList<ArrayList>
 *   userList        UserList<ArrayList>
 *
 * @object A typical CM is cm =<v,i,o,u> where view(v), itemList(i),orderList(o),userList(u)
 *
 * @abstract_properties
 *
 * @author FunStuff + tiendinh
 */
public class ControllerModel {
    private View view;
    private ItemList itemList;
    private OrderList orderList;
    private UserList userList;
    /**
     * @effects
     *  initialise this to be empty.
     */
    public ControllerModel() {
        itemList = new ItemList();
        orderList = new OrderList();
        userList = new UserList();
        view = new View();
    }
    /**
     * @effects <pre>
     *              initialise this as ControllerModel:view(v), itemList(i),orderList(o),userListi(u).
     *           </pre>
     */
    public ControllerModel(ItemList itemList, OrderList orderList, UserList userList, View view) {
        this.itemList = itemList;
        this.orderList = orderList;
        this.userList = userList;
        this.view = view;
    }
    /**
     * @effects return <tt>itemlist</tt>
     */
    public ItemList getItemList() {
        return itemList;
    }
    /**
     * @modifies <tt>this</tt>
     * @effects <pre>
     *   if components is not in this
     *     do nothing
     *   else
     *     remove components from this, i.e.
     *     this_post = this - {components}</pre>
     */
    public void deleteItem(String id) {
        if (!itemList.isIn(id)) {
            return;
        } else {
            itemList.remove(id);
        }
    }
    /**
     * @effects <pre>
     *  if x is in this
     *    return true
     *  else
     *    return false</pre>
     */
    public boolean itemIsIn(String id) {
        return itemList.isIn(id);
    }
    /**
     * @effects return the cardinality of <tt>this</tt>
     */
    public int itemSize() {
        return itemList.size();
    }
    /**
     * Create a new Item.
     *  @effects
     *   create new Item(id,name,category,EXP,quantity,manufacturer, admin, price)
     *      add it to itemList
     *
     *
     */
    public void createItem(String id, String name, String category,
                           LocalDateTime EXP, int quantity,
                           String manufacturer, String admin, int price) {
        if (!itemList.isIn(id)) {
            Item temp = new Item(id, name, category, EXP, quantity, manufacturer, admin, price);
            itemList.add(temp);
        } else {
            return;
        }
    }
    /**
     * @modifies <tt>this</tt>
     * @effects <pre>
     *   if components is not in this
     *     do nothing
     *   else
     *     change components from this</pre>
     */
    public void editItem(String id, String name, String category, LocalDateTime EXP, int quantity,
                         String manufacturer, String admin, int price) {
        if (itemList.isIn(id)) {
            Item temp = new Item(id, name, category, EXP, quantity, manufacturer, admin, price);
            itemList.edit(temp);
        } else {
            return;
        }
    }

    /**
     * @effects <pre>
     *  if id is in this
     *    return component where id appears
     *  else
     *    do nothing</pre>
     */
    public Item searchItemById(String id) {
        return itemList.searchById(id);
    }

    /**
     * @effects return <tt>oderList</tt>
     */
    public OrderList getOrderList() {
        return orderList;
    }
    /**
     * @effects return the cardinality of <tt>this</tt>
     */
    public int orderSize() {
        return orderList.size();
    }
    /**
     * Create a new Order.
     *  @effects
     *   create newOrder( id, name,category, admin,  EXP, quantity,manufacturer,distance,customer,price)
     *      add it to OrderList
     *
     *
     */
    public void createOrder(String id, String name, String category, String admin, LocalDateTime EXP, int quantity,
                            String manufacturer, double distance, String customer, int price) {
        if (quantity > itemList.searchById(id).getQuantity()) {
            return;
        } else {
            Order temps = new Order(id, name, category, EXP, quantity, admin, manufacturer, price, distance, "null", LocalDateTime.now(), customer);
            orderList.add(temps);
        }
    }
    /**
     * @modifies <tt>this</tt>
     * @effects <pre>
     *   if components is not in this
     *     do nothing
     *   else
     *     remove components from this, i.e.
     *     this_post = this - {components}</pre>
     */
    public void deleteOrder(String id) {
        if (orderList.isIn(id)) {
            orderList.remove(id);
        } else {
            return;
        }
    }
    /**
     * @modifies <tt>this</tt>
     * @effects <pre>
     *   if components is not in this
     *     do nothing
     *   else
     *     change components from this</pre>
     */
    public void editOrder(String id, String name, String category, LocalDateTime EXP, int quantity,
                             String manufacturer, double distance, String customer, String admin, int price) {
        if (orderList.isIn(id)) {
            int avaQuantity = itemList.searchById(id).getQuantity() + orderList.searchById(id).getQuantity();
            if (quantity > avaQuantity) {
                return ;
            } else {
                Order temps = new Order(id, name, category, EXP, quantity, admin, manufacturer, price, distance, "null", LocalDateTime.now(), customer);
                orderList.edit(temps);
            }
        } else {
            return;
        }
    }
    /**
     * @effects <pre>
     *  if x is in this
     *    return true
     *  else
     *    return false</pre>
     */
    public boolean orderIsIn(String id){
        return orderList.isIn(id);
    }
    /**
     * @effects <pre>
     *  if id is in this
     *    return component where id appears
     *  else
     *    do nothing</pre>
     */
    public Order searchOrderById(String id) {
        return orderList.searchById(id);
    }

    public ArrayList<Order> searchOrderByShipper(String shipper) {
        return orderList.searchByShipper(shipper);
    }
    /**
     * @effects <pre>
     *  if customer is in this
     *    return component where customer appears
     *  else
     *    do nothing</pre>
     */
    public ArrayList<Order> searchOrderByCustomer(String customer) {
        return orderList.searchByCustomer(customer);
    }
    /**
     * @effects return <tt>userList</tt>
     */
    public UserList getUserList() {
        return userList;
    }
    /**
     * Create a new User.
     *  @effects
     *   create User( id, username,password, name, role)
     *      add it to userList
     *
     *
     */
    public void createUser(String id, String username, String password, String name, Role role) {
        if (userList.isIn(id)) {
            return;
        } else {
            User temp = new User(id, username, password, name, role);
            userList.add(temp);
        }
    }
    /**
     * @effects return userList size <tt></tt>
     */
    public int userSize() {
        return userList.size();
    }
    /**
     * @effects <pre>
     *  if id is in this
     *    return component where id appears
     *  else
     *    do nothing</pre>
     */
    public User searchUserById(String id) {
        return userList.searchById(id);
    }
    /**
     * @effects <pre>
     *  if username is in this
     *    return component where username appears
     *  else
     *    do nothing</pre>
     */
    public User searchUserByUserName(String username) {
        return userList.searchByUserName(username);
    }
    /**
     * @effects
     *
     * if file system.bin is already exists
     *     save Lists on file
     * else
     *   create file system.bin
     *   save Lists to a file system.bin in the same directory
     *   as the program's
     */
    public void saveToFile() throws IOException {
        File file = new File("System.bin");
        if (!file.exists()) { // check if file exists or not
            file.createNewFile(); // if not so print it
        } else {
            FileOutputStream FOS = new FileOutputStream(file);
            ObjectOutputStream OOS = new ObjectOutputStream(FOS);
            OOS.writeObject(itemList); // write on file
            OOS.writeObject(orderList);
            OOS.writeObject(userList);
            OOS.close();
        }
    }
    /**
     * @effects
     *    load Lists from file System.bin
     *
     */

    public void loadFromFile() throws IOException, ClassNotFoundException {
        File file = new File("System.bin");
        if (!file.exists()) {
            file.createNewFile();
        } else {
            FileInputStream FIS = new FileInputStream(file);
            ObjectInputStream OIS = new ObjectInputStream(FIS);
            itemList = (ItemList) OIS.readObject();
            orderList = (OrderList) OIS.readObject();
            userList = (UserList) OIS.readObject();
            OIS.close();
        }
    }

    public void makeLoginFrame() {
        View.makeLoginFrame();
    }

    public void makeCustomerFrame(String username) {
        View.makeCustomerFrame(username);
    }

    public void makeShipperFrame(String username) {
        View.makeShipperFrame(username);
    }

    public void makeAdminFrame(String username) {
        View.makeAdminFrame(username);
    }

    public void closeLogin() {
        View.loginClose();
    }

    public void adminLogOut() {
        View.adminLogOut();
    }

    public void customerLogOut() {
        View.customerLogOut();
    }

    public void shipperLogOut() {
        View.shipperLogOut();
    }

    public void refreshAdmin(String id) {
        View.refreshAdmin(id);
    }

    public void refreshCustomer(String id) {
        View.refreshCustomer(id);
    }

    public void refreshShipper(String id){
        View.refreshShipper(id);
    }

    public void acceptOrder(String id, String customerId,String shipperId){
        ArrayList<Order> order = searchOrderByCustomer(customerId);
        for(int i =0; i< order.size();i++){
            Order o = order.get(i);
            Order k = new Order(o.getId(),o.getName(),o.getCategory(),o.getEXP(),o.getQuantity(),
                    o.getAdmin(),o.getManufacturer(),o.getPrice(),o.getDistance(),shipperId,o.getOrderTime(),o.getCustomer());
            if(o.getId().equals(id)){
                orderList.edit(k);
            }
        }
    }

    public void refuseOrder(String id, String customerId){
        ArrayList<Order> order = searchOrderByCustomer(customerId);
        for(int i =0; i< order.size();i++){
            Order o = order.get(i);
            Order k = new Order(o.getId(),o.getName(),o.getCategory(),o.getEXP(),o.getQuantity(),
                    o.getAdmin(),o.getManufacturer(),o.getPrice(),o.getDistance(),"null",o.getOrderTime(),o.getCustomer());
            if(o.getId().equals(id)){
                orderList.edit(k);
            }
        }
    }
}
