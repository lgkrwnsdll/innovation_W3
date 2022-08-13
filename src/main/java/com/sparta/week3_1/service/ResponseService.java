package com.sparta.week3_1.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseService {

    // 성공 시
    public<T> SingleResponse<T> getSingleResponse(T data) {
        SingleResponse singleResponse = new SingleResponse();
        singleResponse.data = data;
        setSuccessResponse(singleResponse);

        return singleResponse;
    }

    public<T> MultiResponse<T> getMultiResponse(List<T> data) {
        MultiResponse multiResponse = new MultiResponse();
        multiResponse.data = data;
        setSuccessResponse(multiResponse);

        return multiResponse;
    }

    void setSuccessResponse(CommonResponse response) {
        response.success = true;
        response.error = null;
    }

    // 실패 시
    public<T> SingleResponse<T> getNullIdResponse() {
        SingleResponse singleResponse = new SingleResponse();
        singleResponse.success = false;
        singleResponse.data = null;
        singleResponse.error = "NULL_ID";
        return singleResponse;
    }

    public<T> SingleResponse<T> getWrongPwResponse() {
        SingleResponse singleResponse = new SingleResponse();
        singleResponse.success = false;
        singleResponse.data = null;
        singleResponse.error = "WRONG_PASSWORD";
        return singleResponse;
    }

}
