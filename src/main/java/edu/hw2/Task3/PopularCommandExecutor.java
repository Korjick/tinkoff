package edu.hw2.Task3;

import edu.hw2.Task3.connection.Connection;
import edu.hw2.Task3.connection.exception.ConnectionException;
import edu.hw2.Task3.connection.manager.ConnectionManager;

public final class PopularCommandExecutor {
    private final ConnectionManager manager;
    private final int maxAttempts;

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public void updatePackages() {
        tryExecute("apt update && apt upgrade -y");
    }

    private void tryExecute(String command) throws ConnectionException {
        Throwable executedException = null;
        try (Connection connection = manager.getConnection()) {
            for (int i = 0; i < maxAttempts; i++) {
                try {
                    connection.execute(command);
                    return;
                } catch (ConnectionException e) {
                    executedException = e;
                }
            }
        } catch (Exception e) {
            throw new ConnectionException("Failed to close connection", e);
        }

        throw new ConnectionException("Failed to execute command", executedException);
    }
}
