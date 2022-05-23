import java.sql.*;
import java.util.Scanner;

public class JDBCTest03 {
    public static void main(String[] args) {
        //user enters desc on the console is descending order, enters asc is ascending order
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter desc or asc");
        System.out.println("Enter: ");
        String keyword = s.nextLine();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bjpowernode","root","123");
            stmt = conn.createStatement();
            String sql = "select ename from emp order by ename " + keyword;
            //ps = conn.prepareStatement(sql);
            //ps.setString(1,keyword);
            rs = stmt.executeQuery(sql);
            while (rs.next()){
                System.out.println(rs.getString("ename"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(rs != null){
                try{
                    rs.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }

            if(stmt != null){
                try{
                    stmt.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }

            if(conn != null){
                try{
                    conn.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }


    }
}
