package ru.nikitin.aop.springAOP.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "logs")
@Data
@NoArgsConstructor
@NamedEntityGraphs(
        @NamedEntityGraph(name = "className",
                attributeNodes = {
                        @NamedAttributeNode(value = "className")})
)
public class Log {

    @Id
    @SequenceGenerator(name = "logs_seq", sequenceName = "logs_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "logs_seq")
    private Long id;
    @Column(name = "start_date", nullable = false)
    private Date start;
    @Column(name = "end_date", nullable = false)
    private Date end;
    @Column(name = "execution_time", nullable = false)
    private long executionTime;
    @ManyToOne
    @JoinColumn(name = "class_id", referencedColumnName = "class_id")
    ClassName className;

    @Enumerated(EnumType.STRING)
    @Column(name = "exec_type", nullable = false)
    private TypeExecution type;

    public Log(Date before, Date after, TypeExecution type, ClassName className) {
        this.start = before;
        this.end = after;
        this.type = type;
        this.executionTime = end.getTime() - start.getTime();
        this.className = className;
    }
}
