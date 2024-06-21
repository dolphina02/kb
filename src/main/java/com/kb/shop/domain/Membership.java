package com.kb.shop.domain;

import com.kb.shop.domain.enums.MembershipGrade;
import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "memberships")
public class Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type; // 예: BASIC, PREMIUM, VIP 등
    private LocalDate startDate;
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private MembershipGrade grade; // 멤버십 등급 정보

    private int durationInMonths; // 멤버십 유지 기간 (개월 단위)
    private String benefits; // 멤버십 혜택

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // getters and setters
}
