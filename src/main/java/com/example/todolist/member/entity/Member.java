package com.example.todolist.member.entity;

import com.example.todolist.common.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = "password")
@EqualsAndHashCode(callSuper=true)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_gen")
    @SequenceGenerator(name = "member_seq_gen", sequenceName = "member_seq", allocationSize = 1)
    @Column
    private Long mno;

    @Column
    private String email;

    @Column
    private String password;

}
