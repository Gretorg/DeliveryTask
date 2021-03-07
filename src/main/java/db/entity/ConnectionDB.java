package db.entity;

import java.sql.Connection;

public interface ConnectionDB {
    Connection getConnection();
}
