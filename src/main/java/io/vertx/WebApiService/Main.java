package io.vertx.WebApiService;

import io.vertx.mysqlclient.MySQLConnectOptions;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.PoolOptions;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;

public class Main {
    public static void main(String[] args) {
        MySQLConnectOptions connectOptions = new MySQLConnectOptions()
                .setPort(3306)
                .setHost("localhost")
                .setDatabase("prueba")
                .setUser("david")
                .setPassword("david");
        PoolOptions poolOptions=new PoolOptions()
                .setMaxSize(5);

        MySQLPool client = MySQLPool.pool(connectOptions,poolOptions);
        client.query("SELECT * FROM alumnos WHERE edad='20'", ar -> {
            if (ar.succeeded()) {
                RowSet<Row> result = ar.result();
                System.out.println("Got " + result.size() + " rows ");
            } else {
                System.out.println("Failure: " + ar.cause().getMessage());
            }
            client.close();
    });
}
}