package com.project.preset.support;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.UUID;

@Getter
@EqualsAndHashCode
@MappedSuperclass
public abstract class UuidBaseEntity implements Serializable {
    // UUID(java.util.UUID) 아이디 사용한다면:

     @GeneratedValue(generator = "uuid2") // uuid -> 지문처럼. 만들고 보니 안 겹침. -> 겹칠 가능성이 극단적으로 낮음.
     @GenericGenerator(name = "uuid2", strategy = "uuid2")
     private UUID id;
}
