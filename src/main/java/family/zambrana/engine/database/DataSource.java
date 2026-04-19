package family.zambrana.engine.database;

import family.zambrana.engine.GameEngine;
import family.zambrana.engine.common.MiniPlugin;
import java.sql.*;
import java.util.Properties;

/**
 * DataSource
 * <p>
 * Author: Atticus Zambrana
 * Created: 4/13/2026
 */
public class DataSource extends MiniPlugin {
    private Connection connection;
    private String databaseUrl;
    private String username;
    private String password;
    private boolean connected;

    public DataSource() {
        super("Data Source", "Atticus Zambrana", "1.0.0");
        this.connected = false;
    }

    /**
     * Initialize the database connection with Neon Postgres URL
     * @param databaseUrl The Neon Postgres connection URL
     * @param username Database username (optional for Neon)
     * @param password Database password (optional for Neon)
     */
    public void initialize(String databaseUrl, String username, String password) {
        this.databaseUrl = databaseUrl;
        this.username = username;
        this.password = password;
        
        log("Database configuration set. Connection will be established on enable.");
    }

    /**
     * Initialize the database connection with Neon Postgres URL (credentials embedded in URL)
     */
    public void initialize() {
        initialize(GameEngine.getInstance().getConfig().getString("database.url"), null, null);
        onEnable();
    }

    public void onEnable() {
        if (databaseUrl == null || databaseUrl.isEmpty()) {
            logError("Database URL not configured. Use initialize() method first.");
            return;
        }

        try {
            connect();
            super.onEnable();
        } catch (SQLException e) {
            logError("Failed to connect to database: " + e.getMessage());
        }
    }

    public void onDisable() {
        disconnect();
        super.onDisable();
    }

    /**
     * Establish database connection
     */
    private void connect() throws SQLException {
        if (connected && connection != null && !connection.isClosed()) {
            return;
        }

        Properties props = new Properties();
        props.setProperty("ssl", "true");
        props.setProperty("sslmode", "require");
        
        if (username != null && !username.isEmpty()) {
            props.setProperty("user", username);
        }
        if (password != null && !password.isEmpty()) {
            props.setProperty("password", password);
        }

        connection = DriverManager.getConnection(databaseUrl, props);
        connected = true;
        
        log("Successfully connected to database.");
        
        // Test the connection
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT version()")) {
            if (rs.next()) {
                log("Database version: " + rs.getString(1));
            }
        }
    }

    /**
     * Close database connection
     */
    public void disconnect() {
        if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    connection.close();
                    log("Database connection closed.");
                }
            } catch (SQLException e) {
                logError("Error closing database connection: " + e.getMessage());
            } finally {
                connection = null;
                connected = false;
            }
        }
    }

    /**
     * Execute a query and return the ResultSet
     * @param sql The SQL query to execute
     * @return ResultSet from the query
     * @throws SQLException if there's a database error
     */
    public ResultSet executeQuery(String sql) throws SQLException {
        ensureConnected();
        Statement stmt = connection.createStatement();
        return stmt.executeQuery(sql);
    }

    /**
     * Execute a query with parameters and return the ResultSet
     * @param sql The SQL query to execute
     * @param params The parameters for the prepared statement
     * @return ResultSet from the query
     * @throws SQLException if there's a database error
     */
    public ResultSet executeQuery(String sql, Object... params) throws SQLException {
        ensureConnected();
        PreparedStatement stmt = connection.prepareStatement(sql);
        
        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }
        
        return stmt.executeQuery();
    }

    /**
     * Execute an update/insert/delete statement
     * @param sql The SQL statement to execute
     * @return Number of rows affected
     * @throws SQLException if there's a database error
     */
    public int executeUpdate(String sql) throws SQLException {
        ensureConnected();
        Statement stmt = connection.createStatement();
        return stmt.executeUpdate(sql);
    }

    /**
     * Execute an update/insert/delete statement with parameters
     * @param sql The SQL statement to execute
     * @param params The parameters for the prepared statement
     * @return Number of rows affected
     * @throws SQLException if there's a database error
     */
    public int executeUpdate(String sql, Object... params) throws SQLException {
        ensureConnected();
        PreparedStatement stmt = connection.prepareStatement(sql);
        
        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }
        
        return stmt.executeUpdate();
    }

    /**
     * Execute a batch of statements
     * @param sql The SQL statement to execute
     * @param paramsList List of parameter arrays for each batch
     * @return Array of update counts for each batch
     * @throws SQLException if there's a database error
     */
    public int[] executeBatch(String sql, Object[][] paramsList) throws SQLException {
        ensureConnected();
        PreparedStatement stmt = connection.prepareStatement(sql);
        
        for (Object[] params : paramsList) {
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
            stmt.addBatch();
        }
        
        return stmt.executeBatch();
    }

    /**
     * Check if the database connection is active
     * @return true if connected, false otherwise
     */
    public boolean isConnected() {
        try {
            return connected && connection != null && !connection.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * Get the database connection
     * @return The Connection object
     * @throws SQLException if not connected
     */
    public Connection getConnection() throws SQLException {
        ensureConnected();
        return connection;
    }

    /**
     * Ensure the database is connected, reconnect if necessary
     * @throws SQLException if connection fails
     */
    private void ensureConnected() throws SQLException {
        if (!isConnected()) {
            connect();
        }
    }

    /**
     * Test the database connection
     * @return true if connection is working, false otherwise
     */
    public boolean testConnection() {
        try {
            ensureConnected();
            try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT 1")) {
                return rs.next() && rs.getInt(1) == 1;
            }
        } catch (SQLException e) {
            logError("Connection test failed: " + e.getMessage());
            return false;
        }
    }
}
