package umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.mapping.MissionUsers;
import umc.spring.domain.mapping.ShopMissions;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String content;

    private Long points;

    @OneToMany(mappedBy = "mission")
    private List<MissionUsers> missionUsersList;

    @OneToMany(mappedBy = "mission")
    private List<ShopMissions> shopMissionsList;
}

