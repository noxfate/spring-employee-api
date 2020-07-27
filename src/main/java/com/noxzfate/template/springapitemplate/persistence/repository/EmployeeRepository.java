package com.noxzfate.template.springapitemplate.persistence.repository;

import com.noxzfate.template.springapitemplate.persistence.model.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
}
