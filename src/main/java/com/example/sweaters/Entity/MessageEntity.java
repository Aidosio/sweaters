package com.example.sweaters.Entity;

import javax.persistence.*;

import com.example.sweaters.Entity.UserEntity;

@Entity
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String text;
    private String tag;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity author;

    public MessageEntity(String text, String tag, UserEntity userEntity) {
        this.author = userEntity;
        this.text = text;
        this.tag = tag;
    }

    public MessageEntity() {
    }

    public String getAuthorName() {
        return author != null ? author.getUsername() : "<none>";
    }

    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
