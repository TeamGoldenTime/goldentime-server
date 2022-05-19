package com.api.goldentime.web.controller;

import com.api.goldentime.domain.post.Address;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Api("KaKao Controller")
public class KakaoApiController {


    @ApiOperation(value = "주소 받아오기")
    @GetMapping("/kakao")
    public static Address kakaoApi(@RequestParam Double latitude, @RequestParam Double longitude) {
        try {
            String url = "https://dapi.kakao.com/v2/local/geo/coord2regioncode.json?x=" + longitude + "&y=" + latitude;
            String key = "KakaoAK 48071a3f7a9ef10d5090f2716be89379";

            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders header = new HttpHeaders();
            header.add("Authorization", key); //헤더에 키 넣기
            HttpEntity<?> entity = new HttpEntity<>(header);

            ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);


            JSONParser jsonParser = new JSONParser();

            JSONObject jsonObject = (JSONObject) jsonParser.parse(result.getBody());
            JSONArray jsonArray = (JSONArray) jsonObject.get("documents");
            JSONObject local = (JSONObject) jsonArray.get(0);
            String addressName = (String) local.get("address_name");
            String region_1depth_name = (String) local.get("region_1depth_name");
            String region_2depth_name = (String) local.get("region_2depth_name");
            String region_3depth_name = (String) local.get("region_3depth_name");

            Address address = new Address(addressName, region_1depth_name, region_2depth_name, region_3depth_name);

            return address;

        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

    }
}
