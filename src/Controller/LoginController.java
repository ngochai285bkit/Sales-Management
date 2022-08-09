package Controller;

import Model.LoginModel;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class LoginController {
    public static boolean checkData(Connection conn, LoginModel login) {
        try {
            CallableStatement statement = conn.prepareCall("{ CALL sp_Accounts_Check(?,?) }");
            statement.setString(1, login.getUserName());
            statement.setString(2, login.getPassword());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
