package edu.hw6.Task5;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HackerNews {
    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();
    private static final String HACKER_NEWS_URL = "https://hacker-news.firebaseio.com/v0/topstories.json";
    private static final String HACKER_SINGLE_URL = "https://hacker-news.firebaseio.com/v0/item/%d.json";
    private static final String HACKER_TITTLE_REGEX = "\"title\":\"(.*?)\"";
    private static final String EMPTY = "";

    private HackerNews() {

    }

    public static long[] hackerNewsTopStories() {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(HACKER_NEWS_URL)).build();

        try {
            HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();
            return parseJsonArrayToLong(responseBody);
        } catch (IOException | InterruptedException e) {
            return new long[0];
        }
    }

    public static String news(long id) {
        String url = String.format(HACKER_SINGLE_URL, id);
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

        try {
            HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            return extractTitle(response.body());
        } catch (IOException | InterruptedException e) {
            return EMPTY;
        }
    }

    private static String extractTitle(String jsonResponse) {
        Pattern pattern = Pattern.compile(HACKER_TITTLE_REGEX);
        Matcher matcher = pattern.matcher(jsonResponse);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }

    private static long[] parseJsonArrayToLong(String jsonArray) {
        return Arrays.stream(
                jsonArray
                    .replace("[", "")
                    .replace("]", "")
                    .replaceAll("\\s", "")
                    .split(","))
            .mapToLong(Long::parseLong)
            .toArray();
    }
}
