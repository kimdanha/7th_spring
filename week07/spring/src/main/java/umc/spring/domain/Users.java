package umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.enums.Gender;
import umc.spring.domain.mapping.FoodUsers;
import umc.spring.domain.mapping.MissionUsers;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Users extends BaseEntity {

    @Id //pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private Long id; //bigint를 Long 타입으로 지정

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String userId;

    @Enumerated(EnumType.ORDINAL) //DB에서 정수로 저장하도록 설정
    private Gender gender; //성별을 0과 1 둘 중 하나로 저장, 성별 상태가 늘어날 수 있기 때문에(미입력) 열거형으로 저장

    private LocalDate birth;

    @Column(nullable = false, length = 100)
    private String address;

    @Column(nullable = false, length = 20)
    private String password;

    private Long point;

    private Boolean locationAgree;

    private Boolean marketingAgree;

    private Long phoneNumber;

    @OneToMany(mappedBy = "user")  // MissionUsers에서 user 필드에 의해 매핑됨
    private List<MissionUsers> missionUsersList;

    @OneToMany(mappedBy = "user")  // MissionUsers에서 user 필드에 의해 매핑됨
    private List<FoodUsers> foodUsersList;

    @OneToMany(mappedBy = "user")  // MissionUsers에서 user 필드에 의해 매핑됨
    private List<Reviews> reviewsList;

}

