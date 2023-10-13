package edu.project1.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public final class ListWordsDao implements IWordsDao {

    private final List<String> words;
    private final Random random;

    public ListWordsDao(){
        random = new Random();
        words = new ArrayList<>();

        Collections.addAll(words, "Волос", "Дом", "Рука", "Порт");
    }

    @Override
    public String getRandomWord() throws ArrayIndexOutOfBoundsException {

        if (words == null || words.isEmpty())
            throw new ArrayIndexOutOfBoundsException("There are no elements in words list.");

        return words.get(random.nextInt(words.size()));
    }
}
