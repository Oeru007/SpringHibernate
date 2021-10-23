package hiber.service;

import hiber.model.User;

import java.util.List;

public interface UserService {
    void add(User user);
    List<User> listUsers();
    User getOneUserWithCar(String model, int series);
    List<User> listUserWithSameCar(String model, int series);
}
