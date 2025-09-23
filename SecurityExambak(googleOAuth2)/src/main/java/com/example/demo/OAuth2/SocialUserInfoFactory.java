package com.example.demo.OAuth2;

import java.util.Map;

public class SocialUserInfoFactory {

	// 팩토리 디자인 패턴 : 객체 생성 로딕 자체를 캡슐화하여 결합도를 낮추고 유연성을 높이는 패턴
	// 사용 이유 : 유지보수성을 높이기 위해
	// -> 기존 코드 수정없이 새로운 구현테를 만들기 편하다
	// -> 코드의 재사용성 증대
	// -> 테스트시 괸장히 용이함
	
	// 소셜 로그인 제공자에 따라 다른 객체를 생성
	public static SocialUserInfo getSocialUserInfo(String registId, Map<String, Object> attributes) {
		switch (registId.toLowerCase()) {
			case "google": {
				return new GoogleUserInfo(attributes);
			}
			case "kakao": {
				return new KakaoUserInfo(attributes);
			}
			case "naver": {
				return new NaverUserInfo(attributes);
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + registId);
		}
	}
	
}
