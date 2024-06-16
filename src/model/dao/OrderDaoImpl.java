package model.dao;

import model.entity.Customer;
import model.entity.Order;
import model.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderDaoImpl implements OrderDao{

    @Override
    public int addNewOrder(Order order) {
        String sql = """
                INSERT INTO  "order" (id,order_name,order_description,cus_id,ordered_at)
                VALUES (?,?,?,?,?)
                """;
        String sql1 = """
                INSERT INTO "product_order"
                VALUES(?,?);
                """;
        try(

                Connection con = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        "Admin"
                );
                PreparedStatement pre = con.prepareStatement(sql);
                PreparedStatement pre1 = con.prepareStatement(sql1);
                Statement st = con.createStatement();
                ){
            pre.setInt(1,order.getId());
            pre.setString(2,order.getOrderName());
            pre.setString(3,order.getOrderDescription());
            pre.setInt(4,order.getCustomer().getId());
            pre.setDate(5,order.getOrderedAt());
            // Product  order
            for(Product product :order.getProductList()){
                pre1.setInt(1,product.getId());
                pre1.setInt(2,order.getId());
            }
            int rowsAffectd = pre.executeUpdate();
            int rowsAffected1 = pre1.executeUpdate();
            String message = rowsAffectd >0 ? "Insert Successfully order" : "Insert field";
            System.out.println(message);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public List<Order> gueryAllOrders() {
        String sql =
                """
                        SELECT * FROM "order" 
                        INNER JOIN "customer" c on c.id = "order".cus_id
                        """;
        try(
                Connection con = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        "Admin"
                );
                Statement st = con.createStatement();
                ){
            ResultSet rs = st.executeQuery(sql);
            List<Order> ordersList = new ArrayList<>();
//            System.out.println(rs.getMetaData()
//            .getColumnLabel(6));
            while(rs.next()){
                ordersList.add(Order.builder()
                                .id(rs.getInt("id"))
                                .orderName(rs.getString("order_name"))
                                .orderDescription(rs.getString("order_description"))
                                .orderedAt(rs.getDate("ordered_at"))
                                .customer(
                                        Customer.builder()
                                                .id(rs.getInt("id"))
                                                .name(rs.getString("name"))
                                                .email(rs.getString("email"))
                                                .password(rs.getString("password"))
                                                .createdDate(rs.getDate("created_date"))
                                                .isDeleted(rs.getBoolean("is_deleted"))
                                        .build())
                                .build());
            }
            return ordersList;

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public Order searchByIdOrder(Integer id) {
        String sql =
                """
                        SELECT * FROM "order"
                        WHERE id = ?
                        """;
        try(
                Connection con = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        "Admin"
                );
                PreparedStatement pre = con.prepareStatement(sql);
                ){
                Order order = null;
                pre.setInt(1, id);
                ResultSet rs = pre.executeQuery();
                while(rs.next()){
                     order = Order.builder()
                             .id(rs.getInt("id"))
                             .orderName(rs.getString("order_name"))
                             .orderDescription(rs.getString("order_description"))
                             .customer(
                                     Customer.builder()
                                             .id(rs.getInt("id"))
                                     .build())
                             .orderedAt(rs.getDate("ordered_at"))
                             .productList(new ArrayList<>())
                             .build();
                }
                return order;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public int updateOrderById(Integer id) {
        String sql = """
                UPDATE "order"
                SET order_name = ?, order_description = ?
                WHERE id = ?
                """;
        try(

               Connection connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        "Admin"
                );
                PreparedStatement pre = connection.prepareStatement(sql);
                ){
               Order order = searchByIdOrder(id);
               if(order!=null){
                   System.out.print("[+] Insert name : ");
                   pre.setString(1,new Scanner(System.in).nextLine());
                   System.out.print("[+] Insert description : ");
                   pre.setString(2,new Scanner(System.in).nextLine());
                   pre.setInt(3,order.getId());
                   int rowsAffected = pre.executeUpdate();
                   if(rowsAffected>0){
                       System.out.println("Update Successfully order");
                       return  rowsAffected;
                   }else{
                       System.out.println("Order Not Found");
                   }
               }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public int deleteOrerById(Integer id) {
        String sql = """
                DELETE FROM "order"
                WHERE id = ?
                """;
        try(
                Connection con = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        "Admin"
                );
                PreparedStatement pre = con.prepareStatement(sql);
                ){
             Order order = searchByIdOrder(id);
             String message = order==null ? "Cannot find order " : "Found Order";
             System.out.println(message);
             if(order!=null) {
                 pre.setInt(1, id);
                 int rowsAffected = pre.executeUpdate();
                 String message1 = rowsAffected > 0 ? "Delete Successfully " : " Cannot Delete ";
                 System.out.println(message1);
                 return  rowsAffected;
             }

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return 0;
    }
}
