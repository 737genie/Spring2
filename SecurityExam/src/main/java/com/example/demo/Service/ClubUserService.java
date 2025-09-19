package com.example.demo.Service;

import java.util.Optional;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Domain.ClubUser;
import com.example.demo.Domain.ClubUser.ClubRole;
import com.example.demo.Domain.ClubUserRegistrationDto;
import com.example.demo.Repository.ClubUserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClubUserService {
	
	private final ClubUserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public ClubUser registerNewUser(ClubUserRegistrationDto registrationDto) {
		
		if(userRepository.existByUsername(registrationDto.getUsername())) {
			throw new RuntimeException("이미 존재하는 사용자 명입니다. " + registrationDto.getUsername());
		} else {
			ClubUser user = new ClubUser(
					registrationDto.getUsername(),
					registrationDto.getEmail(),
					passwordEncoder.encode(registrationDto.getPassword()),
					registrationDto.getNickname(),
					Set.of(ClubRole.ROLE_USER)
					);
			ClubUser saveuser = userRepository.save(user);
			
			return saveuser;
		}
	}
	
    public boolean isUsernameAvailable(String username) {
        return !userRepository.existByUsername(username);
    }
    
    public boolean isEmailAvailable(String email) {
        return !userRepository.existByEmail(email);
    }
    
    @Transactional
	public void promoteToVip(String username) {
    	
//		Optional<ClubUser> user = this.userRepository.findByUsername(username);
//		user.get().setRoles(Set.of(ClubRole.ROLE_VIP));
		
		ClubUser user = this.userRepository.findByUsername(username)
				.orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다." + username));
		user.getRoles().add(ClubRole.ROLE_VIP);
		
	}

}
