package model.dao;
import model.entity.Customer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerDaoImpl implements CustomerDao{
    @Override
    public List<Customer> queryAllCustomers() {
        String sql = """
                SELECT * FROM "customer"
                """;
        try(
                Connection connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        "Admin"
                );
                 Statement statement = connection.createStatement();
                 ResultSet resultSet =  statement.executeQuery(sql)
                ){
               List<Customer> customersList =
                    new ArrayList<>();
                 while (resultSet.next()){
                     customersList.add(new Customer(
                                     resultSet.getInt("id"),
                                     resultSet.getString("name"),
                                     resultSet.getString("email"),
                                     resultSet.getString("password"),
                                     resultSet.getBoolean("is_deleted"),
                                     resultSet.getDate("created_date")
                     ));
                 }
                 return  customersList;

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public Customer searchCustomerById(Integer id) {

        String sql = """
                SELECT * FROM "customer"
                WHERE id =  ?
                """;
        try(
                Connection connetion = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        "Admin"
                );
                PreparedStatement pre = connetion.prepareStatement(sql)
                ){
                    pre.setInt(1,id);
                    ResultSet resultSet = pre.executeQuery();
                    Customer  customer = null;

                 while (resultSet.next()){
                     customer = Customer.builder().
                             id(resultSet.getInt("id"))
                             .name(resultSet.getString("name"))
                             .email(resultSet.getString("email"))
                             .password(resultSet.getString("password"))
                             .isDeleted(resultSet.getBoolean("is_deleted"))
                             .createdDate(resultSet.getDate("created_date"))
                             .build();
                 }
                 return customer;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public int updateCusttomerById(Integer id) {
        String sql  = """
                UPDATE  "customer" 
                SET  name = ? ,email = ? WHERE id = ?
                """;
        try(
                Connection connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        "Admin"
                );
                PreparedStatement pre = connection.prepareStatement(sql)
                ) {
            Customer customer = searchCustomerById(id);
            if (customer == null) {
                System.out.println("No customer in table ");
            } else {
                System.out.print("[+]  Insert name : ");
                pre.setString(1, new Scanner(System.in).next());
                System.out.print("[+]  Insert email : ");
                pre.setString(2, new Scanner(System.in).next());
                pre.setInt(3, id);
            int rowsAffected = pre.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Updated Successfully ");
            } else {
                System.out.println("Cannot update the record");
            }
        }

        }catch(SQLException e){
            System.out.println(e.getMessage());

        }
        return 0;
    }

    @Override
    public int addNewCustomer(Customer customer) {
        String sql  = """
                INSERT INTO "customer"(name,email,password,is_deleted,created_date)
                VALUES (?,?,?,?,?)
                """;
        try(
                Connection connection = DriverManager.getConnection(
                         "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        "Admin"

                );
                 PreparedStatement pre = connection.prepareStatement(sql)
                ){
                    pre.setString(1,customer.getName());
                    pre.setString(2,customer.getEmail());
                    pre.setString(3,customer.getPassword());
                    pre.setBoolean(4,customer.getIsDeleted());
                    pre.setDate(5,customer.getCreatedDate());
             int rowAffected  =  pre.executeUpdate();
             if(rowAffected>0){
                 System.out.println(" Insert Customer Successfully ");
             }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public int deleteCustomerById(Integer id) {
        String sql = """
                 DELETE FROM "customer" WHERE  id = ?
                 """;
        try(
                Connection connetion = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        "Admin"
                );
                PreparedStatement pre = connetion.prepareStatement(sql);
        ){
            pre.setInt(1,id);
            int rowAffected = pre.executeUpdate();
            String message = rowAffected >0 ? "Successfully deleted " : "Cannot Deleted ";
            System.out.println(message);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return 0;
    }


}
