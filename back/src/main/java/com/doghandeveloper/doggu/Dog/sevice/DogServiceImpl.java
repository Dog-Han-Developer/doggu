package com.doghandeveloper.doggu.Dog.sevice;

import com.doghandeveloper.doggu.Dog.domain.Sex;
import com.doghandeveloper.doggu.Dog.dto.response.DogAuthenticationResponse;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@RequiredArgsConstructor
@Service
@Slf4j
public class DogServiceImpl implements DogService {

    public DogAuthenticationResponse sendDogInfo(String ownerName, String registerNumber) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1543061/animalInfoSrvc/animalInfo"); /*URL*/
        urlBuilder.append("?").append(URLEncoder.encode("serviceKey", "UTF-8")).append("=").append("%2FkuF7pxh9KQVzCR2oY03fB%2BO6W%2F%2FBehWJDjOMv0c1RebifOp9jWQ%2FUw8sPPif8xZ%2BR6KFETznjA9W47YS4pDZw%3D%3D");/*Service Key*/
        urlBuilder.append("&").append(URLEncoder.encode("dog_reg_no", "UTF-8")).append("=").append(URLEncoder.encode(registerNumber, "UTF-8")); /*동물등록번호*/
        urlBuilder.append("&").append(URLEncoder.encode("owner_nm", "UTF-8")).append("=").append(URLEncoder.encode(ownerName, "UTF-8")); /*견주 성명*/
        urlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));

        URL url = new URL(urlBuilder.toString());
        log.info(urlBuilder.toString());


        // HttpURLConnection 객체 생성해 URL 연결 (웹페이지 URL 연결)
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        // 요청 방식 선택(GET)
        conn.setRequestMethod("GET");
        // Request Header 값 세팅
        // 타입설정(application/json) 형식으로 전송 (Request Body 전달시 application/json로 서버에 전달.)
        conn.setRequestProperty("Content-type", "application/json");

        log.info("Response code: " + conn.getResponseCode());
        BufferedReader rd;

        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close(); // InputStreamReader 종료
        conn.disconnect(); // 접속해지
        log.info(sb.toString());

        JsonObject jsonObject = JsonParser.parseString(sb.toString()).getAsJsonObject();

        System.out.println(jsonObject);
        JsonObject parseResponse = (JsonObject) jsonObject.get("response");
        JsonObject parseBody = (JsonObject) parseResponse.get("body");

        if (parseBody.size() == 0) {
            return null;
        }

        JsonObject parseItem = (JsonObject) parseBody.get("item");

        String dogName = parseItem.get("dogNm").getAsString();
        String dogKind = parseItem.get("kindNm").getAsString();
        String dogSex = parseItem.get("sexNm").getAsString();

        Enum changeDogSex;
        log.info(dogSex);

        if(dogSex.equals("암컷")){
            changeDogSex = Sex.FEMALE;
        }else {
            changeDogSex = Sex.MALE;
        }

        DogAuthenticationResponse dogAuthenticationResponse = DogAuthenticationResponse.toDogAuthenticationResponse(dogName, dogKind, changeDogSex);

        return dogAuthenticationResponse;
    }

}