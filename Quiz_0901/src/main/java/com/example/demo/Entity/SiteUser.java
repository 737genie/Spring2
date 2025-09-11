package com.example.demo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class SiteUser {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long user_id;

	    @Column(unique = true)
	    private String username;

	    private String password;

	    @Builder
		public SiteUser(String username, String password) {
			this.username = username;
			this.password = password;
		}
	    
	    
}
