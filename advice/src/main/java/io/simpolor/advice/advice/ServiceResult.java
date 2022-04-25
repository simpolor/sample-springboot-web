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
    SUCCESS (0, "Success"),
    NOT_FOUND ( 10000,"Not found"),
    UNKNOWN ( 50000,"Unknown system error");
    // @formatter:on

    ServiceResult(int resultCode, String resultMessage) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }

    private int resultCode;

    private String resultMessage;

    public int getResultCode() {
        return resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }
}
