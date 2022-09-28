package com.example.healthwebsite.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BuyerInform {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int bid;

    @Column(length = 50, nullable = false)
    String goodsName;

    @Column(length = 50, nullable = false)
    int price;

    @Column(length = 50, nullable = false)
    String buyDate;

    @ManyToOne
    @JoinColumn(name = "id")
    private HealthMember healthMember;

}
