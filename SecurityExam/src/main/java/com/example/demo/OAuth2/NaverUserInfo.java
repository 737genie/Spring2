package com.example.demo.OAuth2;

import java.util.Map;

public class NaverUserInfo implements SocialUserInfo {

	private final Map<String, Object> attributes;

	public NaverUserInfo(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	
	@Override
	public String getId() {
		return (String) attributes.get("response");
	}

	@Override
	public String getName() {
		return (String) attributes.get("response");
	}

	@Override
	public String getEmail() {
		return (String) attributes.get("response");
	}

	@Override
	public String getProfileImageUrl() {
		return (String) attributes.get("response");
	}

}
