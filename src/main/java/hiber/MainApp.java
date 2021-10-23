package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru",new Car("Model 1",100501)));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru",new Car("Model 2",100502)));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru",new Car("Model 3",100503)));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru",new Car("Model 4",100504)));
      userService.add(new User("User5", "Lastname5", "user5@mail.ru",new Car("Model 1",100501)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         if (user.getCar() != null){
            System.out.println("Car model = " + user.getCar().getModel());
            System.out.println("Car series = " + user.getCar().getSeries());
         }
         System.out.println();
      }

      User user = userService.getOneUserWithCar("Model 2",100502);
      if (user != null) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         if (user.getCar() != null){
            System.out.println("Car model = " + user.getCar().getModel());
            System.out.println("Car series = " + user.getCar().getSeries());
         }
      }
      System.out.println();

      List<User> usersWithSameCar = userService.listUserWithSameCar("Model 1", 100501);
      for (User userWithCar : usersWithSameCar) {
         System.out.println("Id = " + userWithCar.getId());
         System.out.println("First Name = " + userWithCar.getFirstName());
         System.out.println("Last Name = " + userWithCar.getLastName());
         System.out.println("Email = " + userWithCar.getEmail());
         System.out.println("Car model = " + userWithCar.getCar().getModel());
         System.out.println("Car series = " + userWithCar.getCar().getSeries());
         System.out.println();
      }

      context.close();
   }
}
