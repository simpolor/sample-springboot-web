package io.simpolor.advice.advice;

public enum ServiceResult {

    /***
     * 0 : 정상
     * 10000 : 프로세스
     * 20000 : 요청
     * 30000 : 응답
     * 40000 : 원격
     * 50000 : 시스템
     */
    // @formatter:off
    SUCCESS (0, "ok"),
    NOT_FOUND ( 1000,"Not found"),
    UNKNOWN ( 5000,"Unknown system error");
    // @formatter:on

    ServiceResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;

    private String message;

    public int getResultCode() {
        return code;
    }

    public String getResultMessage() {
        return message;
    }
}
