
package dao;

import interfaces.IUser;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.client.Client;
import models.User;

import java.util.List;

public class UserDAO implements IUser {
    @Override
    public void save(User user) {
        EntityManager em = EntityManagerAdmin.getInstance();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    @Override
    public List<User> getUsers() {
        EntityManager em = EntityManagerAdmin.getInstance();
        List<User> user = em.createQuery("select c from User c", User.class).getResultList();
        return user;
    }

    @Override
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

    public Client findById(int id) {
        EntityManager em = EntityManagerAdmin.getInstance();
        return em.find(Client.class, id);
    }
}
