package ru.nikitin.aop.springAOP.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "logs")
@Data
@NoArgsConstructor
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

    @Enumerated(EnumType.STRING)
    @Column(name = "exec_type", nullable = false)
    private TypeExecution type;

    @Column(name = "signature", nullable = false)
    private String signature;

    public Log(Date start, Date end, TypeExecution type, String signature) {
        this.start = start;
        this.end = end;
        this.type = type;
        this.executionTime = end.getTime() - start.getTime();
        this.signature = signature;

    }
}
