package edu.hw2.Task3.connection.manager;

import edu.hw2.Task3.connection.Connection;
import edu.hw2.Task3.connection.FaultyConnection;
import edu.hw2.Task3.connection.StableConnection;
import java.util.Random;

public class DefaultConnectionManager implements ConnectionManager {

    private static final float FAULT_CONNECTION_RETURN_CHANCE = 0.33f;

    private final Random random;

    public DefaultConnectionManager() {
        random = new Random();
    }

    @Override
    public Connection getConnection() {

        if (random.nextFloat() < FAULT_CONNECTION_RETURN_CHANCE) {
            return new FaultyConnection();
        }

        return new StableConnection();
    }
}
