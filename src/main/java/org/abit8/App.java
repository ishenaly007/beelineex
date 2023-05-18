package org.abit8;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.abit8.others.CountryCity;
import org.abit8.others.CountryResult;
import org.abit8.others.Student;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();

        // Выполнение GET-запроса и получение данных
        String getUrl = "https://procodeday-01.herokuapp.com/meet-up/get-country-list";
        Request getRequest = new Request.Builder()
                .url(getUrl)
                .build();
        List<CountryCity> countryCityList = new ArrayList<>();

        try (Response getResponse = client.newCall(getRequest).execute()) {
            if (getResponse.isSuccessful()) {
                String responseBody = getResponse.body().string();
                countryCityList = Arrays.asList(objectMapper.readValue(responseBody, CountryCity[].class));
                System.out.println("Полученные данные:");
                for (CountryCity countryCity : countryCityList) {
                    System.out.println("Страна: " + countryCity.getCountry() + ", Город: " + countryCity.getCity());
                }
            } else {
                System.out.println("Не удалось выполнить GET-запрос: " + getResponse.code());
            }
        } catch (IOException e) {
            System.out.println("Произошла ошибка при выполнении GET-запроса: " + e.getMessage());
        }

        // Группировка городов по странам
        Map<String, List<String>> countryCitiesMap = countryCityList.stream()
                .collect(Collectors.groupingBy(CountryCity::getCountry,
                        Collectors.mapping(CountryCity::getCity, Collectors.toList())));

        // Сортировка городов в списке по алфавиту
        countryCitiesMap.forEach((country, cities) -> Collections.sort(cities));

        // Подсчет количества городов в каждой стране
        Map<String, Integer> citiesCountMap = countryCitiesMap.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().size()));

        // Заполнение данные в объекте Student
        Student student = new Student();
        student.setName("Ishenaly");
        student.setPhone("0553252685");
        student.setGithubUrl("https://github.com/ishenaly007");
        List<CountryResult> result = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : countryCitiesMap.entrySet()) {
            String country = entry.getKey();
            List<String> cities = entry.getValue();
            int citiesCount = cities.size();
            result.add(new CountryResult(country, citiesCount, cities));
        }
        student.setResult(result);

        // Выполнение POST-запроса с результатом
        String postUrl = "https://procodeday-01.herokuapp.com/meet-up/post-request";
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        try {
            String requestBody = objectMapper.writeValueAsString(student);
            Request postRequest = new Request.Builder()
                    .url(postUrl)
                    .post(RequestBody.create(requestBody, JSON))
                    .build();

            Response postResponse = client.newCall(postRequest).execute();
            if (postResponse.isSuccessful()) {
                System.out.println("Результат успешно отправлен в POST-запросе.");
            } else {
                System.out.println("Не удалось выполнить POST-запрос: " + postResponse.code());
            }
        } catch (IOException e) {
            System.out.println("Произошла ошибка при выполнении POST-запроса: " + e.getMessage());
        }
    }
}



