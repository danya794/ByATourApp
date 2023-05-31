package data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
    private static String url = System.getProperty("db.url");
    private static String user = System.getProperty("db.user");
    private static String password = System.getProperty("db.password");


    @SneakyThrows
    public static void clearTables() {
        var deletePaymentEntity = "DELETE FROM payment_entity";
        var deleteCreditEntity = "DELETE FROM credit_request_entity";
        var deleteOrderEntity = "DELETE FROM order_entity";
        var runner = new QueryRunner();
        var conn = DriverManager.getConnection(url, user, password);

        {
            runner.update(conn, deletePaymentEntity);
            runner.update(conn, deleteCreditEntity);
            runner.update(conn, deleteOrderEntity);
        }
    }

    @SneakyThrows
    public static String getPaymentStatus() {
        String status = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1";
        return getStatus(status);
    }

    @SneakyThrows
    public static String getCreditStatus() {
        String status = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1";
        return getStatus(status);
    }

    @SneakyThrows
    public static String getStatus(String status) {
        String result = "";
        var runner = new QueryRunner();
        var conn = DriverManager.getConnection(url, user, password);
        {
            result = runner.query(conn, status, new ScalarHandler<String>());
            System.out.println(result);
            return result;
        }
    }
}