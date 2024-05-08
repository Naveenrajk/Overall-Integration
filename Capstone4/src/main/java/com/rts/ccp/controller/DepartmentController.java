package com.rts.ccp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.rts.ccp.dto.DepartmentDTO;
import com.rts.ccp.service.DepartmentService;

import java.util.ArrayList;
import java.util.List;
@RestController
@CrossOrigin("http://localhost:4200/")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    @PostMapping("/insertDepartments")
    public void performInsert(@RequestBody DepartmentDTO departmentdto) {
        departmentService.saveOrUpdateDepartment(departmentdto);
    }
    @PutMapping("/updateDepartments")
    public void performUpdate(@RequestBody DepartmentDTO departmentdto) {
        departmentService.saveOrUpdateDepartment(departmentdto);
    }
    @DeleteMapping("/deleteDepartments/{departmentId}")
    public boolean performDelete(@PathVariable ("departmentId")Long departmentId) {
         return departmentService.deleteDepartmentById(departmentId);
    }
    @GetMapping("/findAllDepartments")
    public List<DepartmentDTO> viewAllDepartments() {
        return departmentService.getAllDepartments();
    }
    @GetMapping("/ViewDepartments/{departmentId}")
    public List<DepartmentDTO> viewDepartments(@PathVariable("departmentId")Long departmentId) {
        return departmentService.getDepartment( departmentId);
    }
    @GetMapping("/getDepartment/{regionId}")
    public List<DepartmentDTO> getDepartments(@PathVariable("regionId")String regionId) {
    	
    	String[] regionId1 = regionId.split(",");
    	List<Long> regionIds = new ArrayList<>();
    	for (String numberString : regionId1) {
    	    Long number = Long.parseLong(numberString);
    	    regionIds.add(number);
    	}

        return departmentService.getParticular(regionIds);
    }
}