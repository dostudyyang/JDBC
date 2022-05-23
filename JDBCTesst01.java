import java.net.http.HttpResponse;
import java.sql.*;
import java.util.*;

public class JDBCTesst01 {
    public static void main(String[] args){

        Map<String,String> userLoginInfo = initUI();
        boolean loginSuccess = login(userLoginInfo);
        System.out.println(loginSuccess ? "success" : "fail");

    }

    /**
     * user login
     * @param userLoginInfo
     * @return false means fail, true means success
     */
    private static boolean login(Map<String, String> userLoginInfo) {
        boolean loginSuccess = false;
        ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
        String driver = bundle.getString("driver");
        String url = bundle.getString("url");
        String user = bundle.getString("user");
        String password = bundle.getString("password");
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try{
            // 1.register the driver class
            //DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Class.forName(driver);
            // 2. create the connection object
            conn = DriverManager.getConnection(url,user,password);
            // 3. create the statement object
            stmt = conn.createStatement();
            // 4. execute the query
            String sql = "select * from t_user where loginName = '"+userLoginInfo.get("loginName")+"' and loginPwd = '"+userLoginInfo.get("loginPwd")+"'";
            //int count = stmt.executeUpdate(sql);
            //System.out.println(count == 1 ? "success" : "fail");
            rs = stmt.executeQuery(sql);
            //while(rs.next()){
            //    String empno = rs.getString("empno");
            //    String ename = rs.getString("ename");
            //    String sal = rs.getString("sal");
            //    System.out.println(empno + "," + ename + "," + sal);
            //}
            if(rs.next()){
                loginSuccess = true;
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if(rs != null){
                try{
                    rs.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn != null){
                try{
                    conn.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
        return loginSuccess;
    }

    /**
     * user login
     * @return user name and password
     */
    private static Map<String, String> initUI() {
        Scanner s = new Scanner(System.in);
        System.out.println("UserName: ");
        String loginName = s.nextLine();
        System.out.println("Password: ");
        String loginPwd = s.nextLine();
        Map<String,String> userLoginInfo = new HashMap<>();
        userLoginInfo.put("loginName",loginName);
        userLoginInfo.put("loginPwd",loginPwd);
        return userLoginInfo;
    }
}
