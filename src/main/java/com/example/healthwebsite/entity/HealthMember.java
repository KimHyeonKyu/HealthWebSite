package com.example.healthwebsite.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="HealthMember")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class HealthMember {
    @Id
    String id;

    @Column(length = 50, nullable = false)
    String password;

    @Column(length = 50, nullable = false)
    String phoneNumber;

    @Column(length = 50, nullable = false)
    String email;

    @OneToMany(mappedBy = "healthMember", cascade = CascadeType.REMOVE)
    // 질문 하나에 여러개 답변이 작성될 수 있는데 질문을 삭제하면 그에 달린 답변들도 모두 함께 삭제하기 위해 Remove 사용
    private List<BuyerInform> buyerInforms;

}
