package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
//만약 DB 테이블 이름과 Class 명이 다르다면? @Table(name = "USER")
public class Member {

    @Id
    private Long id;
    //만약 DB 컬럼명과 Member 필드명이 다르다면? @Column(name = "username")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
