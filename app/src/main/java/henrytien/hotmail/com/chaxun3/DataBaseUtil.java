package henrytien.hotmail.com.chaxun3;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * Created by henry on 6/22/2016.
 */
public class DataBaseUtil {
    private static Connection getSQLConnection(String ip, String user, String pwd, String db)
    {
        Connection con = null;
        try
        {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
              con = DriverManager.getConnection("jdbc:jtds:sqlserver://" + ip + ":1433/" + db + ";charset=UTF8", user, pwd);
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return con;
    }

    public static String testSQL()
    {
        String result = "条码区域  -  剩余条码数\n";
        try
        {
            Connection conn = getSQLConnection("www.eshaoyi.com", "cx", "123456", "邵轶小包");
            String sql = "select 条码区域, count(*) as 剩余条码数 from 条码库 where 使用标志='0' group by 条码区域 order by 条码区域";
            Statement stmt = conn.createStatement();//
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next())
            {//
                String s1 = rs.getString("条码区域");
                String s2 = rs.getString("剩余条码数");
                result += s1 + "  -  " + s2 + "\n";
                System.out.println(s1 + s2);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
            result += "查询数据异常!" + e.getMessage();
        }
        return result;
    }

    public static void main(String[] args)
    {
        testSQL();
    }

}

