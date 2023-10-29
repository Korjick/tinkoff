package edu.hw3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task3 {

    private Task3() {

    }

    public static Map<?, Integer> freqDict(List<?> input) {
        Map<? super Object, Integer> returnMap = new HashMap<>();
        input.forEach(s -> {
            if (!returnMap.containsKey(s)) {
                returnMap.put(s, 0);
            }

            returnMap.replace(s, returnMap.get(s) + 1);
        });

        return returnMap;
    }

}
