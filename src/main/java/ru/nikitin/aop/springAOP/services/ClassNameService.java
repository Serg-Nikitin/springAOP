package ru.nikitin.aop.springAOP.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nikitin.aop.springAOP.model.ClassName;
import ru.nikitin.aop.springAOP.repository.ClassNameRepo;

@Service
@Transactional(readOnly = true)
@CacheConfig(cacheNames = "classNames")
public class ClassNameService {

    @Autowired
    private final ClassNameRepo classNameRepo;

    public ClassNameService(ClassNameRepo classNameRepo) {
        this.classNameRepo = classNameRepo;
    }

    @Cacheable(cacheNames = "classNames", key = "#className")
    public ClassName getByName(String className) {
        return classNameRepo.findByClassName(className);
    }

    @CacheEvict(cacheNames = "classNames", allEntries = true)
    @Transactional
    public ClassName save(ClassName className) {
        return classNameRepo.save(className);
    }
}
