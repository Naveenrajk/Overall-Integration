package com.rts.ccp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rts.ccp.bean.Department;
import com.rts.ccp.bean.Login;
import com.rts.ccp.bean.Project;
import com.rts.ccp.bean.Region;
import com.rts.ccp.bean.User;
import com.rts.ccp.dto.ClaimsDTO;
import com.rts.ccp.dto.UserDTO;
import com.rts.ccp.repository.DepartmentRepo;
import com.rts.ccp.repository.LoginRepo;
import com.rts.ccp.repository.ProjectRepo;
import com.rts.ccp.repository.RegionRepo;
import com.rts.ccp.repository.UserRepo;

@Service
public class ClaimsService {
	
	@Autowired
	private LoginService loginService;
	
	public ClaimsDTO getEmployee(User user,String forcePass) {

//			User user = userRepo.findByUserEmailId(email);

			Long userUserId = user.getUserId();
			System.out.println("user Id :"+userUserId);

//			Login login = loginRepo.findByUserId(userUserId);


			ClaimsDTO userdto = new ClaimsDTO();

			
				userdto.setUserId(user.getUserId());
				userdto.setUserFirstName(user.getUserFirstName());

				userdto.setUserLastName(user.getUserLastName());

				userdto.setUserEmailId(user.getUserEmailId());

				userdto.setUserType(user.getUserType());
				userdto.setUserMobileNumber(user.getUserMobileNumber());
//				Department :
				List<Department> departments = user.getDepartment();
				List<Long> departmentId = new ArrayList<Long>();
				List<String> departmentName = new ArrayList<String>();
				for(int i=0;i<departments.size();i++) {
					departmentId.add(departments.get(i).getDepartmentId());
				}
				for(int i=0;i<departments.size();i++) {
					departmentName.add(departments.get(i).getDepartmentName());
				}
				
				userdto.setDepartment(departmentId.stream().map(Object::toString)
                .collect(Collectors.joining(", ")));
				
				userdto.setDepartmentName(departmentName.stream().map(Object::toString)
		                .collect(Collectors.joining(", ")));
				
//				Region
				
				List<Region> regions = user.getRegion();
				List<Long> regionId = new ArrayList<Long>();
				List<String> regiontName = new ArrayList<String>();
				for(int i=0;i<regions.size();i++) {
					regionId.add(regions.get(i).getRegionId());
				}
				for(int i=0;i<regions.size();i++) {
					regiontName.add(regions.get(i).getRegionName());
				}
				userdto.setRegion(regionId.stream().map(Object::toString)
		                .collect(Collectors.joining(", ")));
						
				userdto.setRegionName(regiontName.stream().map(Object::toString)
		                .collect(Collectors.joining(", ")));
				
//				Project
				
				List<Project> projects = user.getProject();
				List<Long> projectId = new ArrayList<Long>();
				List<String> projectName = new ArrayList<String>();
				for(int i=0;i<projects.size();i++) {
					projectId.add(projects.get(i).getProjectId());
				}
				for(int i=0;i<projects.size();i++) {
					projectName.add(projects.get(i).getProjectName());
				}
				userdto.setProject(projectId.stream().map(Object::toString)
		                .collect(Collectors.joining(", ")));
						
				userdto.setProjectName(projectName.stream().map(Object::toString)
		                .collect(Collectors.joining(", ")));
				userdto.setForcePass(forcePass);
				return userdto;


	}
}
