package view;
import controller.ProductController;
import model.dto.CreateCustomerDto;
import model.dto.CreateProductDto;
import model.dto.ProductDto;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Scanner;

public class View {
    private static final ProductController productController = new ProductController();

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
        getValidChoice();
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
//                case 4:
//                    courseService.findCourseByTitle();
//                    break;
//                case 5:
//                    courseService.removeCourseById();
//                    break;
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
        System.out.print("Insert option (1-3 or 0 to exit) -> ");
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number between 0 and 3.");
            sc.next();
        }
        int choice = sc.nextInt();
        if (choice < 0 || choice > 3) {
            System.out.println("Invalid choice (out of range)");
            return getValidChoice(); // Recursively call to get valid input
        }
        return choice;
    }

    private static void printWelcomeBanner() {
        System.out.println("=".repeat(60));
    }

    private static void printMenuOptions() {
        System.out.println("-".repeat(62));
        System.out.println("1. Add New Product");
        System.out.println("2. Show List Product");
        System.out.println("3. Deleted Product By ID");
//        System.out.println("4. Find Course By Title");
//        System.out.println("5. Remove Course By Id");
        System.out.println("0. Exit");
        System.out.println("-".repeat(62));
    }

    private ProductDto deletedProductFromUserInput() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter product id: ");
        Integer productId = sc.nextInt();
        return  ProductDto.builder()
                .product_id(productId)
                .build();
    }
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
