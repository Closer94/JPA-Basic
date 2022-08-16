package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
//만약 DB 테이블 이름과 Class 명이 다르다면? @Table(name = "USER")
public class Member {

    @Id
    private Long id;
    //만약 DB 컬럼명과 Member 필드명이 다르다면? DB컬럼명이 username 이면, @Column(name = "username")
    //nullable = false  ==  not null 설정
    @Column(name = "name", nullable = false, columnDefinition = "varchar(100) default 'EMPTY'")
    private String username;

    private Integer age;

    //DB에 enum 타입을 사용하려면 EnumType.ORDINAL : enum의 순서를 DB에 저장 / EnumType.STRING : enum의 이름을 DB에 저장 -> DB 데이터 혼동을 막기 위해서 STRING 사용하기
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    //DB에 날짜 타입을 사용하려면
    //TIMESTAMP: 날짜+시간
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    private LocalDate testLocalDate; //YYYYMMDD
    private LocalDateTime testLocalDateTIME; //YYYYmmDD 시분초


    //VARCHAR 를 넘어서 큰 값의 문자열을 넣으려면 @LOB 을 사용
    @Lob
    private String description;

    //반드시 기본생성자를 만들고 추가적으로 생성자를 만들어야한다!
    public Member() {
    }

}
