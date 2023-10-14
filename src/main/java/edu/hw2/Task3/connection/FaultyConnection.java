package edu.hw2.Task3.connection;

import edu.hw2.Task3.connection.exception.ConnectionException;
import java.util.Random;

public class FaultyConnection implements Connection {

    private static final float EXCEPTION_CHANCE = 0.25f;

    private final Random random;

    public FaultyConnection() {
        random = new Random();
    }

    @Override
    public void execute(String command) {
        if (random.nextFloat() < EXCEPTION_CHANCE) {
            throw new ConnectionException(
                "Connection of type %s throw exception when executed \"%s\" command".formatted(
                    FaultyConnection.class.getName(),
                    command
                ));
        }
    }

    @Override
    public void close() throws Exception {

    }
}
