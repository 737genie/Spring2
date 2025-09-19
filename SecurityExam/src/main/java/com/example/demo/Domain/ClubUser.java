package com.example.demo.Domain;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="club_users")
public class ClubUser {
   
	public static enum ClubRole {
       ROLE_USER, ROLE_VIP, ROLE_ADMIN
   }
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String username;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false)
    private String nickname;
    
    @Column(nullable = false)
    private boolean enabled = true;
    
    @Column(nullable = false)
    private boolean accountNonExpired = true;
    
    @Column(nullable = false)
    private boolean accountNonLocked = true;
    
    @Column(nullable = false)
    private boolean credentialsNonExpired = true;
    
    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    
    @Column
    private LocalDateTime lastLoginAt;
	
    
    // 한 명의 사용자가 여러 값을 가질 수 있을때
    // 그 값을 컬렉션으로 만들어서 관리하고, 그걸 지연(Lazy)없이 바로(Eager) 불러옴
    @ElementCollection(fetch = FetchType.EAGER)  
    // Enum 타입 데이터베이스 저장 방식 지정 -> String (문자열)
    @Enumerated(EnumType.STRING)
    // 컬렉션 데이터를 저장할 별도의 테이블 저장
    @CollectionTable(name = "club_user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Set<ClubRole> roles;
    
    // 생성자
    public ClubUser(String username, String email, String password, String nickname, Set<ClubRole> roles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.roles = roles;
    }
	
}
