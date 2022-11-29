package com.example.study.objectmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BurgerTest {

    @Test
    public void 자바_객체를_JSON으로_변환() throws JsonProcessingException {
        //준비
        ObjectMapper objectMapper = new ObjectMapper(); //개체<ㅡ>JON 변환을 시켜주는 클래스
        List<String> ingredients = Arrays.asList("불고기 패티", "토파토", "양파");
        Burger buger = new Burger("불고기 버거", 5500, ingredients);

        //수행


        String json = objectMapper.writeValueAsString(buger);



        //예상
        String expected = "{\"name\":\"불고기 버거\",\"price\":5500,\"ingredients\":[\"불고기 패티\",\"토파토\",\"양파\"]}";

        //검증
        assertEquals(expected, json);
        JsonNode jsonNode = objectMapper.readTree(json);
        System.out.println(jsonNode.toPrettyString());
    }

    @Test
    public void JSON을_자바_객체로_변환() throws JsonProcessingException {
        // 준비
        ObjectMapper objectMapper = new ObjectMapper();
         /*
        {
          "name" : "불고기 버거",
          "price" : 5500,
          "ingredients" : [ "불고기 패티", "토파토", "양파" ]
        }
        */
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("name", "불고기 버거");
        objectNode.put("price", 5500);

        // 배열 넣기
        ArrayNode arrayNode = objectMapper.createArrayNode();
        arrayNode.add("불고기 패티");
        arrayNode.add("토파토");
        arrayNode.add("양파");
        objectNode.set("ingredients", arrayNode);
        String json = objectNode.toString();
        // 수행
        Burger burger = objectMapper.readValue(json, Burger.class);
        // 예상
        List<String> ingredients = Arrays.asList("불고기 패티", "토파토", "양파");
        Burger expected = new Burger("불고기 버거", 5500, ingredients);
        // 검증
        assertEquals(expected.toString(), burger.toString());
        JsonNode jsonNode = objectMapper.readTree(json);
        System.out.println(jsonNode.toPrettyString());
        System.out.println(burger.toString());
    }
}
