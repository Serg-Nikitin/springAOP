package ru.nikitin.aop.springAOP.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nikitin.aop.springAOP.model.ClassName;

@Repository
public interface ClassNameRepo extends JpaRepository<ClassName, Long> {

    ClassName findByClassName(String name);

}
