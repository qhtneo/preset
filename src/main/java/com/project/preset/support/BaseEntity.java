package com.project.preset.support;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Getter
@EqualsAndHashCode
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    // Auto Increment 쓸 때:

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
