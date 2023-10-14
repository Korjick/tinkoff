package edu.hw2.Task3.connection;

import edu.hw2.Task3.connection.exception.ConnectionException;

public interface Connection extends AutoCloseable {
    void execute(String command) throws ConnectionException;
}
