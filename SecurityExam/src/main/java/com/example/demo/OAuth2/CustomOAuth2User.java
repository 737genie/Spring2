package com.example.demo.OAuth2;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.example.demo.Domain.ClubUser;

// ClubUser 엔티티를 포함하여 사용하기 쉽도록 만들기 위한 커스텀 OAuth2 객체
public class CustomOAuth2User implements OAuth2User{

	private final Collection<? extends GrantedAuthority> authorities;
    private final Map<String, Object> attributes;
    private final String nameAttributeKey;
    private final ClubUser clubUser;
    
	public CustomOAuth2User(Collection<? extends GrantedAuthority> authorities, Map<String, Object> attributes, String nameAttributeKey, ClubUser clubUser) {
		this.authorities = authorities;
		this.attributes = attributes;
		this.nameAttributeKey = nameAttributeKey;
		this.clubUser = clubUser;
	}

	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getName() {
		return (String) attributes.get(nameAttributeKey);
	}
	
	// 추가 메서드
    public ClubUser getClubUser() {
        return clubUser;
    }

    public String getEmail() {
        return clubUser.getEmail();
    }

    public String getNickname() {
        return clubUser.getNickname();
    }

    public String getProfileImageUrl() {
        return clubUser.getProfileImageUrl();
    }
    
}

