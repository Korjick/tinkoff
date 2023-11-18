package edu.hw6.Task5;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

class HackerNewsTest {

    private static final Logger LOGGER = LoggerContext.getContext().getLogger(HackerNewsTest.class);

    @Test
    void hackerNewsTopStories() {
        LOGGER.info(Arrays.toString(HackerNews.hackerNewsTopStories()));
        String tittleExpected = "JDK 21 Release Notes";
        String tittleResult = HackerNews.news(37570037);
        assertEquals(tittleExpected, tittleResult);
    }
}
