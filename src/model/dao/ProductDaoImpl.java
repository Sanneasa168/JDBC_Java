package model.dao;

import model.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductDaoImpl implements ProductDao{
    @Override
    public List<Product> queryAllProducts() {
        String sql = """
                SELECT * FROM "product"
                """;
        try(
                Connection connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        "Admin"
                );
                Statement pre = connection.createStatement();
                ResultSet rs = pre.executeQuery(sql)
                ){
            List<Product> productsList =
                    new ArrayList<>();
            while(rs.next()){
               productsList.add(
                       new Product().builder()
                               .id(rs.getInt("id"))
                               .productName(rs.getString("product_name"))
                               .productCode(rs.getString("product_code"))
                               .isDeleted(rs.getBoolean("is_deleted"))
                               .importedDate(rs.getDate("imported_at"))
                               .expiredDate(rs.getDate("expired_at"))
                               .productDescription(rs.getString("product_description"))
                               .build()
               );

            }
            return  productsList;
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public int addNewProduct(Product product) {
        String sql = """
                INSERT INTO "product"(id,product_name,product_code,is_deleted,imported_at,expired_at,product_description)
                VALUES(?,?,?,?,?,?,?)
                """;
        try(
                Connection connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        "Admin"
                );
                PreparedStatement pre = connection.prepareStatement(sql)
                ){
                pre.setInt(1, product.getId());
                pre.setString(2,product.getProductName());
                pre.setString(3,product.getProductCode());
                pre.setBoolean(4,product.getIsDeleted());
                pre.setDate(5,product.getImportedDate());
                pre.setDate(6,product.getExpiredDate());
                pre.setString(7,product.getProductDescription());
                int rowsAffected = pre.executeUpdate();
                if(rowsAffected>0){
                    System.out.println(" Successfully insert product");
                    return rowsAffected;
                }else{
                    System.out.println("Cannot insert product");
                }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public Product searchProduct(Integer id) {
        String sql = """
                SELECT * FROM "product"  WHERE id = ?
                """;
        try(
                Connection connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        "Admin"
                );
                PreparedStatement pre = connection.prepareStatement(sql)
                ){
               pre.setInt(1,id);
               ResultSet rs = pre.executeQuery();
               Product proudcts = null;
               while(rs.next()){
                   proudcts = Product.builder()
                           .id(rs.getInt("id"))
                           .productName(rs.getString("product_name"))
                           .productCode(rs.getString("product_code"))
                           .isDeleted(rs.getBoolean("is_deleted"))
                           .importedDate(rs.getDate("imported_at"))
                           .expiredDate(rs.getDate("expired_at"))
                           .productDescription(rs.getString("product_description"))
                           .build();
               }
               return proudcts;

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return  null;
    }

    @Override
    public int updateProduct(Integer id) {
        String sql = """
                UPDATE  "product" 
                SET product_name = ?, product_code = ? ,product_description =?
                WHERE id = ?
                """;
        try(
                Connection con = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        "Admin"
                );
                PreparedStatement pre = con.prepareStatement(sql)
                ){
                Product productsList = searchProduct(id);
                if(productsList==null){
                    System.out.println("Product  haven't in a table ");
                    return 0;
                }else{
                    System.out.print("[+] Insert product name :");
                    pre.setString(1,new Scanner(System.in).next());

                    System.out.print("[+] Insert product code :");
                    pre.setString(2,new Scanner(System.in).next());

                    System.out.print("[+] Insert product description :");
                    pre.setString(3 ,new Scanner(System.in).next());

                    pre.setInt(4,id);
                    int rowsAffected = pre.executeUpdate();
                    if(rowsAffected>0){
                        System.out.println(" Successfully updated");
                    }else{
                        System.out.println(" Cannot update product");
                    }
                }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public int deleteProduct(Integer id) {
        String sql =
                """
                 DELETE FROM  "product" WHERE id = ?
                 """;
        try(
                Connection con = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        "Admin"
                );
                PreparedStatement pre = con.prepareStatement(sql);
                ){
                pre.setInt(1,id);
                int rowsAffected = pre.executeUpdate();
                String message = rowsAffected > 0 ?
                        "Product deleted successfully"
                        : "Product cannot be deleted";
            System.out.println(message);

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return 0;

    }
}
