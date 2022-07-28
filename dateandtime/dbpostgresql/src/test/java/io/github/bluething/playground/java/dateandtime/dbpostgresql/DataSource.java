package io.github.bluething.playground.java.dateandtime.dbpostgresql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource dataSource;

    static {
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/localdb");
        config.setUsername("postgres");
        config.setPassword("4lpuk4tk0c0k");
        config.addDataSourceProperty( "cachePrepStmts" , "true" );
        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        dataSource = new HikariDataSource( config );
    }

    private DataSource(){

    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
