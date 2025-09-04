//package com.example.demo.User;
//
//import java.util.Collections;
//
//import org.springframework.stereotype.Service;
//
//import lombok.RequiredArgsConstructor;
//
//@Service
//@RequiredArgsConstructor
//public class DiaryKeeperDetailsService implements UserDetailsService {
//
//    private final DiaryKeeperRepository diaryKeeperRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        DiaryKeeper keeper = diaryKeeperRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + username));
//        return new org.springframework.security.core.userdetails.User(keeper.getUsername(), keeper.getPassword(), Collections.emptyList());
//    }
//}
