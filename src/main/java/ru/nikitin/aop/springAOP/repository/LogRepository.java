package ru.nikitin.aop.springAOP.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nikitin.aop.springAOP.model.LogRow;

@Repository
public interface LogRepository extends JpaRepository<LogRow, Long> {
    LogRow findLogById(Long id);
}
