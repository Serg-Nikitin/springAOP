package ru.nikitin.aop.springAOP.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nikitin.aop.springAOP.model.Log;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {
    Log findLogById(Long id);
}
