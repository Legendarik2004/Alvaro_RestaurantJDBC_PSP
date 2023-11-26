package dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import common.Configuration;
import common.Constantes;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


@Singleton
public class DBConnectionPool {

    private final Configuration config;
    private final DataSource hikariDataSource;


    @Inject
    public DBConnectionPool(Configuration config) {
        this.config = config;
        hikariDataSource = getHikariPool();
    }

    private DataSource getHikariPool() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(config.getProperty(Constantes.URL_DB));
        hikariConfig.setUsername(config.getProperty(Constantes.USER_NAME));
        hikariConfig.setPassword(config.getProperty(Constantes.PASSWORD));
        hikariConfig.setDriverClassName(config.getProperty(Constantes.DRIVER));
        hikariConfig.setMaximumPoolSize(4);

        hikariConfig.addDataSourceProperty(Constantes.CACHE_PREP_STMTS, true);
        hikariConfig.addDataSourceProperty(Constantes.PREP_STMT_CACHE_SIZE, 250);
        hikariConfig.addDataSourceProperty(Constantes.PREP_STMT_CACHE_SQL_LIMIT, 2048);

        return new HikariDataSource(hikariConfig);
    }

    public Connection getConnection() {
        Connection con = null;
        try {
            con = hikariDataSource.getConnection();
        } catch (SQLException e) {
            Logger.getLogger(DBConnectionPool.class.getName()).log(Level.SEVERE, null, e);
        }

        return con;
    }

    @PreDestroy
    public void closePool() {
        ((HikariDataSource) hikariDataSource).close();
    }

}
