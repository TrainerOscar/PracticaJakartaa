package interfaces;

import models.User;

import java.util.List;

public interface IUser {
    public void save (User user);

    public List<User> getUsers ();

    public void update (User useer);

    public void delete (User user);

    void setRuc(String s);

    void setDepartment(String s);

    void setCity(String s);
}