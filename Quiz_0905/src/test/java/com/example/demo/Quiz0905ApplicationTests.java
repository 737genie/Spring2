package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Quiz0905ApplicationTests {

	@Autowired
	private StoreRepository storeRepository;
	
	
	@Test
	void contextLoads() {
		
		
		Store store = new Store();
		
		store.setName("test1");
		store.setLocation("test2");
		
		this.storeRepository.save(store);
		
	}

}
