package hiber.dao;

import hiber.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }
   @Override
   @SuppressWarnings("unchecked")
   public User getOneUserWithCar(String model, int series){
      try {
         return sessionFactory.getCurrentSession()
                 .createQuery("from User c left outer join fetch c.car where c.car.series=:seriesCar and c.car.model=:modelCar", User.class)
                 .setParameter("seriesCar",series).setParameter("modelCar", model)
                 .uniqueResult();
      } catch (HibernateException e) {
         System.out.println(e.getClass().getName() + e.getMessage());
         return null;
      }

   }

   @Override
   public List<User> listUserWithSameCar(String model, int series) {
      return sessionFactory.getCurrentSession().createQuery("from User c left outer join fetch c.car where c.car.series=:seriesCar and c.car.model=:modelCar", User.class)
              .setParameter("seriesCar",series).setParameter("modelCar", model)
              .getResultList();
   }
}
