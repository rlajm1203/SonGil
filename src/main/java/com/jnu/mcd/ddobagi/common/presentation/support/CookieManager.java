package com.jnu.mcd.ddobagi.common.presentation.support;

import org.springframework.http.ResponseCookie;

public interface CookieManager {
	ResponseCookie setCookie(String key, String value);

	ResponseCookie deleteCookie(String key);
}
