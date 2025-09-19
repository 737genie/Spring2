package com.example.demo.Service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Domain.NuTaMember;
import com.example.demo.Domain.NuTaMember.NuTaFaction;
import com.example.demo.Domain.NuTaMember.NuTaRole;
import com.example.demo.Domain.NuTaMemberSignupDto;
import com.example.demo.Repository.NuTaMemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NuTaUserService {
	
	private final PasswordEncoder passwordEncoder;
	private final NuTaMemberRepository userRepository;
	
	public void save(NuTaMemberSignupDto dto) {
		
		NuTaFaction faction;
	    if (dto.getFaction().equals("DOG")) {
	        faction = NuTaFaction.DOG;
	    } else if (dto.getFaction().equals("CAT")) {
	        faction = NuTaFaction.CAT;
	    } else {
	        throw new IllegalArgumentException("유효하지 않은 파벌 선택입니다.");
	    }
	    
	    NuTaMember user = new NuTaMember(
	            dto.getUsername(),
	            passwordEncoder.encode(dto.getPassword()),
	            NuTaRole.USER,
	            faction
	        );
	    
	    this.userRepository.save(user);
		
		
		
//		else if(dto.getFaction().equals("DOG")){
//			NuTaMember user = new NuTaMember(
//					dto.getUsername(),
//					passwordEncoder.encode(dto.getPassword()),
//					List.of(NuTaRole.ROLE_USER),
//					List.of(NuTaFaction.DOG)
//					);
//			this.userRepository.save(user);
//		} else if(dto.getFaction().equals("CAT")){
//			NuTaMember user = new NuTaMember(
//					dto.getUsername(),
//					passwordEncoder.encode(dto.getPassword()),
//					List.of(NuTaRole.ROLE_USER),
//					List.of(NuTaFaction.CAT)
//					);
//			this.userRepository.save(user);
//		}
		
		
	}

}
