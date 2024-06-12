package model.dao;

import model.entity.Customer;
import model.entity.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao{

    @Override
    public int addNewOrder(Order order) {
        String sql = """
                INSERT INTO  "order" (order_name,order_description,cus_id,ordered_at)
                VALUES (?,?,?,?)
                """;
        try(

                Connection con = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        "Admin"
                );
                PreparedStatement pre = con.prepareStatement(sql)
                ){
            pre.setString(1,order.getOrderName());
            pre.setString(2,order.getOrderDescription());
            pre.setInt(3,order.getCustomer().getId());
            pre.setDate(4,order.getOrderedAt());
            int rowsAffectd = pre.executeUpdate();
            if(rowsAffectd>0){
                System.out.println("Added new Order Successfully");
            }else{
                System.out.println("Cannot  add new Order");
            }

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
                ResultSet rs = st.executeQuery(sql);
                ){
            List<Order> orders = new ArrayList<Order>();
            System.out.println(rs.getMetaData()
            .getColumnLabel(6));
            while(rs.next()){
                orders.add(Order.builder()
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
            return orders;

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }
}
