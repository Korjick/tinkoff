package edu.hw6.Task1;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DiskMapTest {

    private static final Logger LOGGER = LoggerContext.getContext().getLogger(DiskMapTest.class);

    @Test
    @Order(1)
    void diskMapPutTest(){
        String path = "./test.txt";
        assertDoesNotThrow(() -> {
            DiskMap map = new DiskMap(Paths.get(path));
            map.put("A", "B");
            map.put("A", "B");
            map.put("B", "C");
        });
    }

    @Test
    @Order(2)
    void diskMapReadTest(){
        String path = "./test.txt";
        assertDoesNotThrow(() -> {
            DiskMap map = new DiskMap(Paths.get(path));
            map.forEach((k, v) -> LOGGER.info(k + " - " + v));
        });
    }
}
