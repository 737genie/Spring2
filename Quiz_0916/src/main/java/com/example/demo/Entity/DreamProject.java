package com.example.demo.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Setter;

@Setter
@Entity
public class DreamProject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String projectName;

    @Column(unique = true, nullable = false)
    private String activationCode;

    @OneToMany(mappedBy = "dreamProject", cascade = CascadeType.ALL)
    private List<DreamFragment> fragments = new ArrayList<>();
    
    // Getters and Setters ...
}
