package org.providence.formdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.log4j.Logger;

/**
 *
 * @author theider
 */
public class DBConnectionProvider {

    private final String hostAddress;

    private final String hostPort;

    private final String databaseName;

    private final String username;

    private final String password;

    public DBConnectionProvider(String hostAddress, String hostPort, String databaseName, String username, String password) {
        this.hostAddress = hostAddress;
        this.hostPort = hostPort;
        this.databaseName = databaseName;
        this.username = username;
        this.password = password;
    }

    private static final Logger LOGGER = Logger.getLogger(DBConnectionProvider.class);

    private boolean initialized = false;

    private String connectString;

    public synchronized Connection openConnection() throws SQLException {
        if(!initialized) {
            connectString = String.format("jdbc:mysql://%s:%s/%s?user=%s&password=%s&useSSL=true",
                    this.hostAddress,
                    this.hostPort,
                    this.databaseName,
                    this.username,
                    this.password);
            try {
                LOGGER.debug("initializing DB connection provider");
                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                throw new SQLException("failed to initialize driver", ex);
            }
            LOGGER.debug("completed initialization");
            initialized = true;
        }
        Connection conn = DriverManager.getConnection(connectString);
        return conn;
    }

    public void closeConnection(Connection cnctn) throws SQLException {
        cnctn.close();
    }

}
