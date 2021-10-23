package hiber.service;

import hiber.dao.UserDao;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

   @Autowired
   private UserDao userDao;

   @Transactional
   @Override
   public void add(User user) {
      userDao.add(user);
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }

   @Transactional(readOnly = true)
   @Override
   public User getOneUserWithCar(String model, int series){
      return userDao.getOneUserWithCar(model, series);
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> listUserWithSameCar(String model, int series) {
      return userDao.listUserWithSameCar(model, series);
   }
}
