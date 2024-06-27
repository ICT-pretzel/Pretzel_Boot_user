package com.ict.pretzel.ko.service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ict.pretzel.ko.mapper.UserMapper;
import com.ict.pretzel.ko.vo.TossVO;

@Service
public class TossService {

    @Autowired
    private UserMapper userMapper;

    @Transactional
    public TossVO confirmPayment(String user_id, String paymentKey, String orderId, int amount) {
        try {

            JSONObject obj = new JSONObject();
            obj.put("orderId", orderId);
            obj.put("amount", amount);
            obj.put("paymentKey", paymentKey);

            // 결제 위젯 시크릿 키
            String widgetSecretKey = "test_gsk_docs_OaPz8L5KdmQXkzRz3y47BMw6";

            // 비밀번호가 없다는 것을 알리기 위해 시크릿 키 뒤에 콜론을 추가합니다.
            Base64.Encoder encoder = Base64.getEncoder();
            byte[] encodedBytes = encoder.encode((widgetSecretKey + ":").getBytes(StandardCharsets.UTF_8));
            // 헤더에 보낼 인증 토큰
            String authorizations = "Basic " + new String(encodedBytes);

            // 결제 승인 API를 호출하세요.
            URL url = new URL("https://api.tosspayments.com/v1/payments/confirm");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Authorization", authorizations);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(obj.toString().getBytes("UTF-8"));

            // 결제승인 성공여부 확인
            int code = connection.getResponseCode();
            boolean isSuccess = code == 200;
            
            InputStream responseStream = isSuccess ? connection.getInputStream() : connection.getErrorStream();
            Reader reader = new InputStreamReader(responseStream, StandardCharsets.UTF_8);
            Gson gson = new Gson();
            // JSON 파싱
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            responseStream.close();

            System.out.println("승인 결과 : " + jsonObject);

            TossVO toss = new TossVO();
            toss.setAmount(jsonObject.get("totalAmount").getAsInt());
            toss.setOrderId(jsonObject.get("orderId").getAsString());
            toss.setApprovedAt(jsonObject.get("approvedAt").getAsString());
            toss.setOrderName(jsonObject.get("orderName").getAsString());
            toss.setUser_id(user_id);
            System.out.println("금액 : " + toss.getAmount());
            System.out.println("결제 승인 시간 : " + toss.getApprovedAt());
            System.out.println("주문 번호 : " + toss.getOrderId());
            System.out.println("주문 이름 : " + toss.getOrderName());
            System.out.println("주문 유저 : " + toss.getUser_id());

            int result = userMapper.toss_insert(toss);
            result += userMapper.subs_update(toss);

            if (result >= 2) {
                return toss;
            }
        } catch (Exception e) {
            System.out.println("TossService : " + e);
        }
        return null;
    }
}
