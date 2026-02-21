package com.example.contact.model;

public class ApiResponseCodes {

    public enum ResponseCode {
        SUCCESS("00", "Operation successful"),
        Failed("01", "Operation Failed"),
        INVALID_REQUEST("02", "Invalid request")
        ;

        private final String code;
        private final String message;

        ResponseCode(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getCode() { return code; }
        public String getMessage() { return message; }
    }
}
