
import com.servlet.crud.business.EmployeeBusiness;
import com.servlet.crud.dao.EmployeeDAO;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        EmployeeBusiness e = new EmployeeBusiness();
        System.out.println(e.getAllEmps());
    }
}
