package com.kb.shop.domain;

import lombok.Data;

import jakarta.persistence.*;
import java.util.Set;
import java.util.List;

@Data
@Entity
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    @ElementCollection(fetch = FetchType.EAGER) // JPA에서 값 타입 컬렉션을 매핑할 때 사용, // Eager = 엔티티가 로드될 때 관련된 컬렉션이나 엔티티도 즉시로딩 - memory 관리 필요
    // 사용자 정보는 인증 및 권한 부여 로직에서 즉시 필요
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id")) // @ElementCollection과 함께 사용되며, 값 타입 컬렉션이 저장될 테이블을 지정
    private Set<String> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Membership> memberships;

    // getters and setters
}
