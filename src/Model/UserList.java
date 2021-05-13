package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class UserList implements Serializable {
    private static final long serialVersionUID = -6500665823330706018L;
    private final ArrayList<User> userList;

    public UserList() {
        userList = new ArrayList<>();
    }

    public void add(User user) {
        for (int i = 0; i < size(); i++) {
            if (user.getId().equals(userList.get(i).getId())) {
                return;
            } else if (user.getUsername().equals(userList.get(i).getUsername())) {
                return;
            }
        }
        userList.add(user);
    }

    public void remove(String id) {
        for (int i = 0; i < size(); i++) {
            if (id.equals(userList.get(i).getId())) {
                userList.remove(i);
            }
        }
    }

    public User searchById(String id) {
        for (int i = 0; i < size(); i++) {
            if (id.equals(userList.get(i).getId())) {
                return userList.get(i);
            }
        }
        return new User();
    }

    public User searchByUserName(String username) {
        for (int i = 0; i < size(); i++) {
            if (username.equals(userList.get(i).getUsername())) {
                return userList.get(i);
            }
        }
        return new User();
    }

    public boolean isIn(String id) {
        for (int i = 0; i < size(); i++) {
            if (id.equals(userList.get(i).getId())) {
                return true;
            }
        }
        return false;
    }

    public void edit(User user) {
        for (int i = 0; i < size(); i++) {
            if (user.getId().equals(userList.get(i).getId())) {
                userList.set(i, user);
            }
        }
    }

    public User get(int i) {
        return userList.get(i);
    }

    public int size() {
        return userList.size();
    }
}
