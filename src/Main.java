import model.dao.CustomerDaoImpl;
import model.dao.OrderDaoImpl;
import model.dao.ProductDaoImpl;
import model.entity.Customer;
import model.entity.Order;
import model.entity.Product;

import javax.swing.border.Border;
import java.sql.Date;
import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {



//         new CustomerDaoImpl()
//                 .addNewCustomer(new Customer(
//                         5,
//                         "Hayaborsa",
//                         "hay@gmail.com",
//                         "hy@@3973",
//                         false,
//                           Date.valueOf(LocalDate.now())
//
//                 ));



//        new CustomerDaoImpl()
//                .queryAllCustomers()
//                .forEach(System.out::println);

//        new CustomerDaoImpl()
//                .deleteCustomerById(2);


//        new CustomerDaoImpl()
//                .updateCusttomerById(6);







//        new ProductDaoImpl()
//                .addNewProduct(
//                        new Product(
//                                1,
//                               "Samsung Galaxy",
//                                "22333",
//                                false,
//                                Date.valueOf(LocalDate.of(2024,9,10)),
//                                Date.valueOf(LocalDate.of(2025,9,12)),
//                                "This is a samsung galaxy product"
//                        ));


//        new ProductDaoImpl()
//                .addNewProduct(
//                        new Product(
//                                3,
//                               "Carabaov ",
//                                "90833",
//                                false,
//                                Date.valueOf(LocalDate.of(2026,6,29)),
//                                Date.valueOf(LocalDate.of(2029,11,28)),
//                                "This is a sting  product"
//                        )
//                );



//
//        new ProductDaoImpl()
//                .queryAllProducts()
//                .forEach(System.out::println);

//        new ProductDaoImpl()
//                .updateProduct(1);

//        new OrderDaoImpl()
//                .addNewOrder(
//                        new Order(
//                                1,
//                                "kimlang",
//                                "kimlang Orders Coca cola",
//                                 Customer.builder()
//                                        .id(3)
//                                        .build(),
//                                Date.valueOf(LocalDate.of(2025,4,24))
//                        )
//                );
        new OrderDaoImpl()
                .gueryAllOrders()
                .forEach(System.out::println);



    }
}