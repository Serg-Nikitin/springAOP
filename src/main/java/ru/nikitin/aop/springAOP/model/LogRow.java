package ru.nikitin.aop.springAOP.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "log_rows")
@Data
@NoArgsConstructor
public class LogRow {

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

    @Column(name = "class")
    String clazz;

    @Column(name = "method")
    String method;

    @Enumerated(EnumType.STRING)
    @Column(name = "exec_type", nullable = false)
    private TypeExecution type;

    public LogRow(Date start, Date end, Long execute, TypeExecution type, String clazz, String method) {
        this.start = start;
        this.end = end;
        this.type = type;
        this.executionTime = this.end.getTime() - this.start.getTime();
        this.clazz = clazz;
        this.method = method;
    }
}
