package edu.hw6.Task6;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import java.net.DatagramSocket;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("MagicNumber")
public class PortScanner {
    private static final Map<Integer, String> KNOWN_PORTS = new HashMap<>();
    private static final String SPACE = " ";
    private static final String TEXT_FORMAT = "%10s";
    private static final Logger LOGGER = LoggerContext.getContext().getLogger(PortScanner.class);

    static {
        KNOWN_PORTS.put(80, "HTTP (HyperText Transfer Protocol)");
        KNOWN_PORTS.put(21, "FTP (File Transfer Protocol)");
        KNOWN_PORTS.put(25, "SMTP (Simple Mail Transfer Protocol)");
        KNOWN_PORTS.put(22, "SSH (Secure Shell)");
        KNOWN_PORTS.put(443, "HTTPS (HyperText Transfer Protocol Secure)");
        KNOWN_PORTS.put(53, "DNS (Domain Name System)");
        KNOWN_PORTS.put(3306, "PostgreSQL Database");
        KNOWN_PORTS.put(5355, "Link-Local Multicast Name Resolution (LLMNR)");
        KNOWN_PORTS.put(5432, "MySQL Database");
        KNOWN_PORTS.put(17500, "Dropbox");
        KNOWN_PORTS.put(27017, "MongoDB");
    }

    private PortScanner() {

    }

    @SuppressWarnings("UncommentedMain")
    public static void main(String[] args) {
        PortScanner.checkPorts();
    }

    public static void checkPorts() {
        printFormat("Протокол", "Порт", "Сервис");
        for (int port = 0; port <= 49151; port++) {
            checkPort(Protocol.TCP, port);
            checkPort(Protocol.UDP, port);
        }
    }

    private static void checkPort(Protocol protocol, int port) {
        String service = KNOWN_PORTS.getOrDefault(port, "");

        try {
            switch (protocol) {
                case TCP -> {
                    try (ServerSocket serverSocket = new ServerSocket(port)) {
                        printFormat("TCP", Integer.toString(port), service);
                    }
                }
                case UDP -> {
                    try (DatagramSocket datagramSocket = new DatagramSocket(port)) {
                        printFormat("UDP", Integer.toString(port), service);
                    }
                }
                default -> {
                }
            }
        } catch (IOException ignored) {

        }
    }

    private static void printFormat(String... params) {
        LOGGER.info(String.format(TEXT_FORMAT, params[0])
            + SPACE
            + String.format(TEXT_FORMAT, params[1])
            + SPACE
            + String.format(TEXT_FORMAT, params[2]));
    }

    private enum Protocol {
        TCP,
        UDP
    }
}
