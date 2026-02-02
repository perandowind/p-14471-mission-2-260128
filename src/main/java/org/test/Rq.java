package org.test;

import java.util.HashMap;
import java.util.Map;

public class Rq {
    private String action;
    private Map<String, String> params = new HashMap<>();; //여러 파라미터 담기 위해 Map 사용

    public Rq(String command) {
        String [] commandBits = command.split("\\?", 2); // ? 기준으로 두 파트로 자르기
        this.action = commandBits[0];

        if(commandBits.length == 1) return;

        String[] paramBits = commandBits[1].split("&"); // 여러 파라미터 대응 (id=1 & name=abc)의 경우

        for (String paramBit : paramBits) {
            String[] keyValue = paramBit.split("=", 2);
            if (keyValue.length == 2) {
                params.put(keyValue[0], keyValue[1]);
            }
        }

    }

    public String getAction() {
        return action;
    }

    public int getIntParam(String key, int defaultValue) {
        // 파라미터가 없거나 숫자가 아니면 기본 값 반환
        try {
            return Integer.parseInt(params.get(key));
        } catch (Exception e) {
            return defaultValue;
        }
    }

}
