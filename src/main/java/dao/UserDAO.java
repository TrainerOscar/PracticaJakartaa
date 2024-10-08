
package dao;

import interfaces.IUser;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.client.Client;
import models.User;

import java.util.List;

public class UserDAO implements IUser {
    public static String getId() {
        return "";
    }

    @Override
    public void save(User user) {
        EntityManager em = EntityManagerAdmin.getInstance();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    public List<User> getUsers() {
        EntityManager em = EntityManagerAdmin.getInstance();
        List<User> user = em.createQuery("select c from User c", User.class).getResultList();
        return user;
    }

    public void update(User user) {
        EntityManager em = EntityManagerAdmin.getInstance();
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
    }

    @Override
    public void delete(User user) {
        EntityManager em = EntityManagerAdmin.getInstance();
        em.getTransaction().begin();
        em.remove(user);
        em.getTransaction().commit();
    }

    @Override
    public void setRuc(String s) {

    }

    @Override
    public void setDepartment(String s) {

    }

    @Override
    public void setCity(String s) {

    }

    public static Client findById(int id) {
        EntityManager em = EntityManagerAdmin.getInstance();
        return em.find(Client.class, id);
    }

    public boolean isEmpty() {
        return false;
    }

}
