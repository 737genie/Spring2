package com.example.demo.Domain;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class NuTaMember {
	public static enum NuTaRole {
	       USER, ADMIN
	   }
	
	public static enum NuTaFaction {
		DOG, CAT
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String username;
    
    @Column(nullable = false)
    private String password;
    
    @Enumerated(EnumType.STRING)
    private NuTaRole role;
    
    @Enumerated(EnumType.STRING)
    private NuTaFaction faction;

	public NuTaMember(String username, String password
			, NuTaRole role, NuTaFaction faction) {
		this.username = username;
		this.password = password;
		this.role=role;
		this.faction=faction;
	}
    
    
}
