package com.yvolabs.department.service;

import com.yvolabs.department.entity.Department;
import com.yvolabs.department.repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Department saveDepartment(Department department) {
        log.info("Inside saveDepartment method of DepartmentService.");
        return departmentRepository.save(department);
    }

    public Department findDepartmentById(Long departmentId) {
        log.info("Inside findDepartmentById method of DepartmentService.");
        return departmentRepository.findByDepartmentId(departmentId);

    }

    public List<Department> findAllDepartments() {
        log.info("Inside findAllDepartments method of DepartmentService.");
        return departmentRepository.findAll();
    }
}
