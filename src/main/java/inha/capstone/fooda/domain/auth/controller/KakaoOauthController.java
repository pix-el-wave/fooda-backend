package inha.capstone.fooda.domain.auth.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import inha.capstone.fooda.domain.auth.dto.GetGetKakaoUserInfoAuthResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
public class KakaoOauthController {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final HttpRequestController httpRequestController;

    public GetGetKakaoUserInfoAuthResDto getKakaoUserInfo(String accessToken) throws JsonProcessingException {
        String requestUrl = "https://kapi.kakao.com/v2/user/me";

        // HTTP header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HTTP request 보내기
        ResponseEntity<String> response = httpRequestController.sendHttpRequest(requestUrl, headers);

        // Response의 body에서 user info 추출
        Map<String, Object> attributes = objectMapper.readValue(response.getBody(), new TypeReference<>() {
        });

        return GetGetKakaoUserInfoAuthResDto.from(attributes);
    }
}
