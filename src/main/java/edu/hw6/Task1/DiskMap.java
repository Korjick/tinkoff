package edu.hw6.Task1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.IllegalFormatWidthException;
import java.util.Map;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DiskMap implements Map<String, String> {

    private static final String SPLITTER = ":";
    private static final int SPLIT_SIZE = 2;

    private final Map<String, String> map;
    private final File file;

    public DiskMap(Path path) throws Exception {
        this.map = new HashMap<>();
        this.file = path.toFile();
        load();
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        String returnValue = map.put(key, value);
        try {
            write();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return returnValue;
    }

    @Override
    public String remove(Object key) {
        String returnValue = map.remove(key);
        try {
            write();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return returnValue;
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        map.putAll(m);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public String get(Object key) {
        return map.get(key);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        return map.keySet();
    }

    @NotNull
    @Override
    public Collection<String> values() {
        return map.values();
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        return map.entrySet();
    }

    private void load() throws Exception {
        clear();

        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = Files.newBufferedReader(file.toPath())) {
            while (reader.ready()) {
                String[] splitted = reader.readLine().split(SPLITTER);
                if (splitted.length != SPLIT_SIZE) {
                    throw new IllegalFormatWidthException(SPLIT_SIZE);
                }
                put(splitted[0], splitted[1]);
            }
        }
    }

    private void write() throws Exception {

        if (!file.exists()) {
            if (!file.createNewFile()) {
                throw new IOException();
            }
        }

        try (BufferedWriter writer = Files.newBufferedWriter(file.toPath())) {
            Set<Entry<String, String>> entrySet = entrySet();
            for (Entry<String, String> entry : entrySet) {
                writer.write(entry.getKey() + SPLITTER + entry.getValue());
                writer.newLine();
            }
        }
    }
}
