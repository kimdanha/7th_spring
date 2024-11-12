package umc.spring.domain.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.Mission;
import umc.spring.domain.Shop;
import umc.spring.domain.enums.Status;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ShopMissions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;  // Users 엔티티와의 관계 설정

    @ManyToOne
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;  // Mission 엔티티와의 관계 설정

    @Enumerated(EnumType.STRING)
    private Status status;
}

