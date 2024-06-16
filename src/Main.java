import model.dao.CustomerDaoImpl;
import model.dao.OrderDaoImpl;
import model.dao.ProductDao;
import model.dao.ProductDaoImpl;
import model.entity.Customer;
import model.entity.Order;
import model.entity.Product;
import view.View;

import javax.swing.border.Border;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        View view = new View();
        view.UI();
    }
}