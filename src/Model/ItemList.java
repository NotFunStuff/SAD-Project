package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class ItemList implements Serializable {
    private static final long serialVersionUID = -6500665823330706018L;
    private final ArrayList<Item> itemList;

    public ItemList() {
        itemList = new ArrayList<>();
    }

    public void add(Item item) {
        for (int i = 0; i < size(); i++) {
            if (item.getId().equals(itemList.get(i).getId())) {
                return;
            }
        }
        itemList.add(item);
    }

    public void remove(String id) {
        for (int i = 0; i < size(); i++) {
            if (id.equals(itemList.get(i).getId())) {
                itemList.remove(i);
            }
        }
    }

    public Item searchById(String id) {
        for (int i = 0; i < size(); i++) {
            if (id.equals(itemList.get(i).getId())) {
                return itemList.get(i);
            }
        }
        return new Item();
    }

    public ArrayList<Item> searchByName(String name) {
        ArrayList<Item> temp = new ArrayList<>();
        for (int i = 0; i < size(); i++) {
            if (name.equals(itemList.get(i).getName())) {
                temp.add(itemList.get(i));
            }
        }
        return temp;
    }

    public boolean isIn(String id) {
        for (int i = 0; i < size(); i++) {
            if (id.equals(itemList.get(i).getId())) {
                return true;
            }
        }
        return false;
    }

    public void edit(Item item) {
        for (int i = 0; i < size(); i++) {
            if (item.getId().equals(itemList.get(i).getId())) {
                itemList.set(i, item);
            }
        }
    }

    public Item get(int index) {
        return itemList.get(index);
    }

    public int size() {
        return itemList.size();
    }
}
