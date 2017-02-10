package com.vzwcorp.pricinglab.datasource;

import oracle.jdbc.pool.OracleConnectionPoolDataSource;
import oracle.jdbc.proxy.annotation.Pre;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//Test for JNDI
public class JNDIDataSourceTest {

    //set up database connection before usage.
    @BeforeClass
    public static void setupClass () throws Exception{
        //setup jndi context and datasource
        try {

            System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");

            System.setProperty(Context.URL_PKG_PREFIXES, "org.apache.naming");

            InitialContext ic = new InitialContext();

            ic.createSubcontext("java:");
            ic.createSubcontext("java:/comp");
            ic.createSubcontext("java:/comp/env");
            ic.createSubcontext("java:/comp/env/jdbc");

            OracleConnectionPoolDataSource dsQuartz = new OracleConnectionPoolDataSource();
            dsQuartz.setURL("jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=tbajub2bdd-scan.tdc.vzwcorp.com)(PORT=1521))(CONNECT_DATA=(SERVICE_NAME=plabst1)))");
            dsQuartz.setUser("procctrl");
            dsQuartz.setPassword("spring167");

            ic.bind("java:/comp/env/jdbc/quartz", dsQuartz);

            OracleConnectionPoolDataSource dsPlab = new OracleConnectionPoolDataSource();
            dsPlab.setURL("jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=tbajub2bdd-scan.tdc.vzwcorp.com)(PORT=1521))(CONNECT_DATA=(SERVICE_NAME=plabst1)))");
            dsPlab.setUser("plab");
            dsPlab.setPassword("plab000");

            ic.bind("java:/comp/env/jdbc/plab", dsPlab);

        } catch (NamingException nex) {
            Logger.getLogger(JNDIDataSourceTest.class.getName()).log(Level.ERROR,nex.getMessage(),nex);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void testQuartzConnections () {
        Connection connection = null;
        PreparedStatement pstm = null;
        try {
            Context context = new InitialContext();
            Context webContext = (Context) context.lookup("java:/comp/env");

            DataSource ds = (DataSource) webContext.lookup("jdbc/quartz");
            //TODO : Create a connection pool to test.

            connection = ds.getConnection();
            String showTriggers = "SELECT * FROM QRTZ_TRIGGERS";

            pstm = connection.prepareStatement(showTriggers);
            ResultSet resultSet = pstm.executeQuery();

            if (resultSet!=null) {
                System.out.println("Total number of Triggers : "+ resultSet.getFetchSize());
            }

        } catch (Exception ex) {
            Logger.getLogger(JNDIDataSourceTest.class.getName()).log(Level.ERROR,ex.getMessage(),ex);
        } finally {
            try {

                if (pstm != null && !pstm.isClosed()) {
                    pstm.close();
                }

                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
