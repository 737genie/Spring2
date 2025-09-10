package com.example.demo;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Parcel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String sender;

    private String receiver;
    private String destination;
    private String content;

    @Enumerated(EnumType.STRING) // Enum 타입을 문자열로 DB에 저장
    private Status status;

    public Parcel(String sender, String receiver, String destination, String content, Status status) {
        this.sender = sender;
        this.receiver = receiver;
        this.destination = destination;
        this.content = content;
        this.status = status;
    }

    // 상태 변경을 위한 메서드
    public void updateStatus(Status status) {
        this.status = status;
    }

}
