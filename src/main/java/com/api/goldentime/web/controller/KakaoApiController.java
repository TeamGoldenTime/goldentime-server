package com.api.goldentime.web.controller;

import com.api.goldentime.domain.post.Address;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.xdevapi.JsonArray;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.*;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.*;
import java.util.Map;

@RestController
public class KakaoApiController {


    @GetMapping("/kakao")
    public static Address kakaoApi(Double latitude, Double longitude) {
        try {
            String url = "https://dapi.kakao.com/v2/local/geo/coord2regioncode.json?x=" + longitude + "&y=" + latitude;
            String key = "48071a3f7a9ef10d5090f2716be89379";

            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();

            HttpHeaders header = new HttpHeaders();
            header.add("Authorization", key); //헤더에 키 넣기
            HttpEntity<?> entity = new HttpEntity<>(header);

            ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);


            JSONParser jsonParser = new JSONParser();

            JSONObject jsonObject = (JSONObject) jsonParser.parse(result.getBody());
            JSONArray jsonArray = (JSONArray) jsonObject.get("documents");
            JSONObject local = (JSONObject) jsonArray.get(0);
            JSONObject jsonArray1 = (JSONObject) local.get("address");
            String region_1depth_name = (String) jsonArray1.get("region_1depth_name");
            String region_2depth_name = (String) jsonArray1.get("region_2depth_name");
            String region_3depth_name = (String) jsonArray1.get("region_3depth_name");
            String zipCode = (String) jsonArray1.get("zip_code");
            Address address = new Address(zipCode, region_1depth_name, region_2depth_name, region_3depth_name);

            return address;

        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

    }
}
