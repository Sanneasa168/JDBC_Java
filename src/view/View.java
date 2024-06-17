package view;
import controller.CustomerController;
import controller.OrderController;
import controller.ProductController;
import model.dto.*;
import model.entity.Customer;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Scanner;

public class View {
    private static final ProductController productController = new ProductController();
   private static final CustomerController customerController = new CustomerController();
   private static final OrderController orderController = new OrderController();
    public final void UI() {
        Scanner sc = new Scanner(System.in);
        System.out.println("""
                \s
                  ██╗    ██╗███████╗██╗     ██╗      ██████╗ ██████╗ ███╗   ███╗    ████████╗ ██████╗      ██████╗███████╗████████╗ █████╗ ██████╗\s
                  ██║    ██║██╔════╝██║     ██║     ██╔════╝██╔═══██╗████╗ ████║    ╚══██╔══╝██╔═══██╗    ██╔════╝██╔════╝╚══██╔══╝██╔══██╗██╔══██╗
                  ██║ █╗ ██║█████╗  ██║     ██║     ██║     ██║   ██║██╔████╔██║       ██║   ██║   ██║    ██║     ███████╗   ██║   ███████║██║  ██║
                  ██║███╗██║██╔══╝  ██║     ██║     ██║     ██║   ██║██║╚██╔╝██║       ██║   ██║   ██║    ██║     ╚════██║   ██║   ██╔══██║██║  ██║
                  ╚███╔███╔╝███████╗███████╗███████╗╚██████╗╚██████╔╝██║ ╚═╝ ██║       ██║   ╚██████╔╝    ╚██████╗███████║   ██║   ██║  ██║██████╔╝
                   ╚══╝╚══╝ ╚══════╝╚══════╝╚══════╝ ╚═════╝ ╚═════╝ ╚═╝     ╚═╝       ╚═╝    ╚═════╝      ╚═════╝╚══════╝   ╚═╝   ╚═╝  ╚═╝╚═════╝                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \s

                """);
        printWelcomeBanner();
//        getValidChoice();
        printMenuOptions();
        while (true) {
            int choice = getValidChoice();
            switch (choice) {
                case 1:
                    CreateProductDto addNew= addProductFromUserInput();
                    productController.addNewProduct(addNew);
                    break;
                case 2:
                    System.out.println(productController.getAllProducts());
                    break;
                case 3:
                    ProductDto newDeleted =deletedProductFromUserInput();
                    productController.deleteProduct(newDeleted.product_id());
                    break;
                case 4:
                    System.out.println(customerController.getAllProducts());
                    break;
                case 5:
                    CreateCustomerDto addNewCustomer= addCustomerFromUserInput();
                    customerController.addNewCustomer(addNewCustomer);
                    break;
                case 6:
                        CustomerDto newDeletedCustomer =deletedCustomerFromUserInput();
                        customerController.deletedCustomer(newDeletedCustomer.id());
                    break;
                case 7:
                    System.out.println(orderController.getAllOrders());
                    break;
                case 8:
                    OrderDto addNewOrder = addOrderFromUserInput();
                    orderController.addNewOrder(addNewOrder);
                    break;
                case 9 :
                    CreateOrderDto newDeleltedOrdered = deletedOrderedFromUserInput();
                    orderController.deleteOrder(newDeleltedOrdered.id());
                case 0: // Add an exit option
                    System.out.println("Exiting program...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private int getValidChoice() {
        Scanner sc = new Scanner(System.in);
        System.out.print(" Please click  (1-9 or 0 to exit) -> ");
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number between 0 and 9.");
            sc.next();
        }
        int choice = sc.nextInt();
        if (choice < 0 || choice > 9) {
            System.out.println("Invalid choice (out of range)");
            return getValidChoice(); // Recursively call to get valid input
        }
        return choice;
    }

    private static void printWelcomeBanner() {
        System.out.println("========================== SHOW MENU =========================");
    }

    private static void printMenuOptions() {
        System.out.println("-".repeat(62));
        System.out.println("1. Add New Product");
        System.out.println("2. Show List Of Product");
        System.out.println("3. Deleted Product By ID");

        System.out.println("4. Show List Of Customer ");
        System.out.println("5. Add new Customer ");
        System.out.println("6. Deleted Customer By ID ");

        System.out.println("7. Show List of Orders ");
        System.out.println("8. Add new Orders ");
        System.out.println("9. Deleted Orders By Id ");

        System.out.println("0. Exit");
        System.out.println("-".repeat(62));
    }


    // Scanner  Deleted product
    private ProductDto deletedProductFromUserInput() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Customer ID : ");
        Integer productId = sc.nextInt();
        return  ProductDto.builder()
                .product_id(productId)
                .build();
    }
    // Scanner Deleted Customer
    private CustomerDto deletedCustomerFromUserInput() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Customer ID : ");
        Integer customerId = sc.nextInt();
        return  CustomerDto.
                builder().
                id(customerId).
                build();
    }
    // Deleted Ordered
    private CreateOrderDto deletedOrderedFromUserInput() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Customer ID : ");
        Integer orderedId = sc.nextInt();
        return  CreateOrderDto.builder().
                id(orderedId)
                .build();

    }

    // Add new  Ordered
    private OrderDto addOrderFromUserInput(){
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Order ID : ");
        Integer orderId = sc.nextInt();

        System.out.print("Enter Order Name :");
        String orderName = sc.nextLine();

        System.out.print("Enter Order Description : ");
        String orderDescription = sc.nextLine();
        return  OrderDto.builder().
                id(orderId).
                order_name(orderName).
                order_description(orderDescription).
                cus_id(
                        Customer.builder()
                                .id(orderId)
                                .build()
                )
                .ordered_at(Date.valueOf(LocalDate.now())).
                build();

    }
    // Scanner CreateProduct
    private CreateProductDto addProductFromUserInput() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter product id: ");
        Integer productId = sc.nextInt();

        System.out.print("Enter product name: ");
        String productName = sc.nextLine();

        System.out.print("Enter product code: ");
        String productCode = sc.nextLine();

        System.out.print("Enter product description: ");
        String productDescription = sc.nextLine();

        return CreateProductDto.builder()
                .product_isDeleted(false) // Set isDeleted to false by default
                .product_id(productId)  // Set ID to null
                .product_name(productName)
                .product_code(productCode)
                .product_description(productDescription)
                .product_importedDate(Date.valueOf(LocalDate.now()))
                .product_expiredDate(Date.valueOf(LocalDate.now()))
                .build();
    }
    // Scanner Create Customer
   private  CreateCustomerDto addCustomerFromUserInput(){
       Scanner sc = new Scanner(System.in);
       System.out.print("Enter Customer id : ");
       Integer customertId = sc.nextInt();

       System.out.print("Enter Customer name : ");
       String customerName = sc.nextLine();

       System.out.print("Enter Customer Email : ");
       String customerEmail = sc.nextLine();

       System.out.print("Enter Customer Password  : ");
       String  customerPassword = sc.nextLine();
       return CreateCustomerDto.builder()
               .id(customertId)
               .name(customerName)
               .email(customerEmail)
               .password(customerPassword)
               .isDeleted(false)
               .createDate(Date.valueOf(LocalDate.now()))
               .build();
   }
    // Scanner Add new Product
    private ProductDto createProductFromUserInput() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter product name: ");
        String productName = sc.nextLine();

        System.out.print("Enter product Code : ");
        String productCode = sc.nextLine();

        System.out.print( "Enter product description : ");
        String productPassword = sc.nextLine();

        return  ProductDto.builder()
                .product_name(productName)
                .product_code(productCode)
                .product_description(productPassword)
                .build();

    }
}
