//package com.example.demo.User;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.Setter;
//
//@Setter
//@Getter
//@Entity
//public class DiaryKeeper {
//    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    @Column(unique = true, nullable = false)
//    private String username;
//    @Column(nullable = false)
//    private String password;
//
//    @Builder
//    public DiaryKeeper(String username, String password) {
//        this.username = username;
//        this.password = password;
//    }
//}
