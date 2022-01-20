package com.example.demoapi.model;

import lombok.*;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "board")
@Getter
@Setter
@NoArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name= "frstRegistDate")
    private String frstRegistDate;

    @Column(name = "updateDate")
    private String updateDate;

    @Builder(builderClassName = "ByUidBuilder", builderMethodName = "BoardBuilder")
    public Board (int uid, String title) {
        Assert.notNull(uid, "uid must not be null!!");
        Assert.notNull(title, "title must not be null!!");

        this.uid = uid;
        this.title = title;
    }
}
