package ru.nikitin.aop.springAOP.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "classes", uniqueConstraints = {
        @UniqueConstraint(columnNames = "class_name")
})
@Data
@NoArgsConstructor
public class ClassName {
    @Id
    @Column(name = "class_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long classId;

    @Column(name = "class_name")
    String className;


    public ClassName(String className) {
        this.className = className;
    }
}
