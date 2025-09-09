
package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.User.Agent;
import com.example.demo.User.AgentRepository;


@Service
public class UserSecurityService implements UserDetailsService{
	
	private final AgentRepository agentRepository;
	
	public UserSecurityService(AgentRepository agentRepository) {
		this.agentRepository=agentRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<Agent> _user = this.agentRepository.findByusername(username);
		
		
		if(_user.isEmpty()) {
			throw new UsernameNotFoundException("사용자를 찾을 수 없음.");
		}
		
		Agent agent = _user.get();
		
		// 권한 부여 준비
		List<GrantedAuthority> auth = new ArrayList<>();
		// 시큐리티 기능으로 사용자의 역할에 따라 권한 부여 가능해짐
		if("MANAGER".equals(agent.getRole())) {
			auth.add(new SimpleGrantedAuthority("ROLE_MANAGER"));
		}else {
			auth.add(new SimpleGrantedAuthority("ROLE_AGENT"));
		}
		
		// 기본적으로 시큐리티는 로그인 로그아웃 처리 시
		// 사용자의 정보가 User라고 하는 시큐리티제공 클래스에 있다고 생각함
		// 우리의 회원정보는 SiteUser 엔티티에 담았기 때문에
		// 그 정보들을 시큐리티가 인식하는 User 클래스로 보내서 객체를 만들어 줘야 함
		return new User(agent.getUsername(), agent.getPassword(), auth);
	}
	
}
