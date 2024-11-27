package com.jnu.mcd.ddobagi.common.consts;

import org.springframework.http.HttpMethod;

public class StaticConst {

    /** 회원가입 정책 REGEX */
    public static final String LOGINID_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    public static final String PASSWORD_REGEX =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$";
    public static final String PHONENUMBER_REGEX = "^(010|011|016|017|018|019)-\\d{3,4}-\\d{4}$";

    /** Encrypt */
    public static final String HMAC_ALGORITHM = "HmacSHA256";

    /** Token Names */
    public static final String AT_NAME = "smart_farm_at";

    public static final String RT_NAME = "smart_farm_rt";
    public static final String AT_EXPIRATION_TIME = "at_expiration_time";
    public static final String RT_EXPIRATION_TIME = "rt_expiration_time";

    /** Message */
    public static final String KOREA_COUNTRY_CODE = "82";

    public static final String SMS = "SMS";
    public static final String MMS = "MMS";
    public static final String LMS = "LMS";
    public static final String COMM = "COMM";
    public static final String FROM_NUMBER = "01030665016"; // 나중에 환경 변수로 관리
    public static final String ABAS_TITLE = "[ABAS]";
    public static final String NAVER_SMS_SUCCESS = "202";
    public static final long CERT_NUM_EXPIRED_TIME = 180000;

    public static final String SPACE = " ";
    public static final String NEW_LINE = "\n";
    public static final String HTTP_GET = HttpMethod.GET.toString();
    public static final String HTTP_POST = HttpMethod.POST.toString();
    public static final String HTTP_PUT = HttpMethod.PUT.toString();
    public static final String HTTP_DELETE = HttpMethod.DELETE.toString();
    public static final String HTTP_PATCH = HttpMethod.PATCH.toString();

    private static final String PHONE_AUTH_MESSAGE = "[ABAS] 본인확인을 위해 휴대폰 인증번호 [%s]를 입력해 주세요.";
    public static final String MIDDLE_FIX = "::";

    public static String getPhoneAuthMessage(String certNumber) {
        return String.format(PHONE_AUTH_MESSAGE, certNumber);
    }
}
