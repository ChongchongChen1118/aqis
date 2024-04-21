package com.neu.users.service.Impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neu.users.service.GptService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GptServiceImpl implements GptService {
    @Override
    public String getGpt(String url1,String body,String resultName) {
        String data="";
        // 设置请求的URL
        String url = url1;

        // 创建一个RestTemplate实例
        RestTemplate restTemplate = new RestTemplate();

        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 设置请求体参数
        String requestBody = body;

        // 创建HttpEntity对象，将请求体和请求头组合在一起
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        // 发送POST请求，如果需要发送GET请求，将HttpMethod.POST改为HttpMethod.GET
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        // 处理响应
        String responseBody = responseEntity.getBody();
        int statusCode = responseEntity.getStatusCodeValue();


        // 创建ObjectMapper实例，用于处理JSON
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // 将响应体字符串解析为JSON对象
            JsonNode jsonNode = objectMapper.readTree(responseBody);

            // 从JSON对象中获取某个属性（假设属性名为 "propertyName"）
            String propertyValue = jsonNode.get(resultName).asText();
            // 现在你可以使用propertyValue
            data=propertyValue;
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
        }


        System.out.println("响应状态码: " + statusCode);
        System.out.println("响应内容: " + responseBody);
        return data;
    }
}
