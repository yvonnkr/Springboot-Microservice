package com.yvolabs.department.controller;

import com.yvolabs.department.entity.Department;
import com.yvolabs.department.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@Slf4j //for lombok logger
public class DepartmentController {
    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("/")
    public Department saveDepartment(@RequestBody Department department){
        log.info("Inside saveDepartment method of DepartmentController.");
        return departmentService.saveDepartment(department);
    }

    @GetMapping("/{id}")
    public Department findDepartmentById(@PathVariable("id") Long departmentId ){
        log.info("Inside findDepartmentById method of DepartmentController.");
        return departmentService.findDepartmentById(departmentId);
    }

    @GetMapping("/")
    public List<Department> findAllDepartments( ){
        log.info("Inside findAllDepartments method of DepartmentController.");
        return departmentService.findAllDepartments();
    }

}
