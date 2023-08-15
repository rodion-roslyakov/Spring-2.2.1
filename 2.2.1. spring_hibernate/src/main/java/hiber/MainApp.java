package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        userService.deleteUsers();

        CarService carService = context.getBean(CarService.class);

        List<Car> cars = carService.listCars();
        carService.deleteCars();

        List<User> users = userService.listUsers();


        Car car1 = new Car("BMW", "M5");
        Car car2 = new Car("Mercedes", "e63s");
        Car car3 = new Car("AUDI", "Rs5");

        carService.add(car1);
        carService.add(car2);
        carService.add(car3);

        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        User user3 = new User("User3", "Lastname3", "user3@mail.ru");
        User user4 = new User("User4", "Lastname4", "user4@mail.ru");

        user1.setUserCar(car1);
        user2.setUserCar(car3);
        user3.setUserCar(car2);

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);


        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }

        System.out.println(userService.findUserByCar("Mercedes", "e63s"));

        context.close();
    }
}
