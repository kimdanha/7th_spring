package umc.spring.domain.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.Food;
import umc.spring.domain.Users;
import umc.spring.domain.enums.Status;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FoodUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "users_id", nullable = false)
    private Users user;  // Users 엔티티와의 관계 설정

    @ManyToOne
    @JoinColumn(name = "food_id", nullable = false)
    private Food food;  // Mission 엔티티와의 관계 설정

    @Enumerated(EnumType.STRING)
    private Status status;
}


