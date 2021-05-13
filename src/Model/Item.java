package Model;

import java.io.Serializable;
import java.time.LocalDateTime;
/**
 * @overview Item is are stuffs which ...
 *
 * @attributes
 * id             String
 * name               String
 * category          String
 * EXP               LocalDateTime
 * quantity           Integer
 * manufacturer        String
 * admin               String
 * price              Integer
 * @object
 *  A typical Item is <pre>i = <i,n,c,e,q,m,a,p></pre>, where
 *   id(i),name(i),category(c),EXP(e),quantity(q), manufacturer(m), admin(a),price(p)
 * @abstract_properties

 * @author FunStuff + tiendinh
 */
public class Item implements Serializable {
    private static final long serialVersionUID = -6500665823330706018L;
    private String id;
    private String name;
    private String category;
    private LocalDateTime EXP;
    private int quantity;
    private String manufacturer;
    private String admin;
    private int price;

    public Item() {

    }
    /**
     * @effects <pre>
     *              initialise this as Item:<i,n,c,e,q,m,a,p>

     *          </pre>
     */
    public Item(String id, String name, String category, LocalDateTime EXP, int quantity,
                String manufacturer, String admin, int price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.EXP = EXP;
        this.quantity = quantity;
        this.manufacturer = manufacturer;
        this.admin = admin;
        this.price = price;
    }
    /**
     * @effects return <tt>id</tt>
     */
    public String getId() {
        return id;
    }
    /**
     * @effects <pre>
     *     set this.id =id </pre>
     */
    public void setId(String id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDateTime getEXP() {
        return EXP;
    }

    public void setEXP(LocalDateTime EXP) {
        this.EXP = EXP;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }
}
