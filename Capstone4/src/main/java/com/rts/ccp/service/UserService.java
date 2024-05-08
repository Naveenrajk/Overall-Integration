//package com.rts.ccp.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import org.springframework.stereotype.Service;
//
//import com.rts.ccp.bean.Department;
//import com.rts.ccp.bean.Invitation;
//import com.rts.ccp.bean.Login;
//import com.rts.ccp.bean.Project;
//import com.rts.ccp.bean.Region;
//import com.rts.ccp.bean.User;
//import com.rts.ccp.bean.UserProject;
//import com.rts.ccp.dto.DepartmentDTO;
//import com.rts.ccp.dto.UserDTO;
//import com.rts.ccp.repository.DepartmentRepo;
//import com.rts.ccp.repository.InvitationRepo;
//import com.rts.ccp.repository.LoginRepo;
//import com.rts.ccp.repository.ProjectRepo;
//import com.rts.ccp.repository.RegionRepo;
//import com.rts.ccp.repository.UserRepo;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@Service
//
//public class UserService {
//
//	@Autowired
//
//	private UserRepo userRepo;
//
//	@Autowired
//
//	private Department department;
//
//	@Autowired
//
//	private DepartmentRepo departmentDAO;
//
//	@Autowired
//
//	private Region region;
//
//	@Autowired
//
//	private RegionRepo regdao;
//
//	@Autowired
//
//	private ProjectRepo projectdao;
//
//	@Autowired
//
//	private LoginRepo loginRepo;
//	
//	@Autowired
//	private Invitation invitation;
//	
//	@Autowired
//	private InvitationRepo invitationRepo;
//	
//	@Autowired
//	private InvitationService invitationService;
//
//	public boolean saveOrUpdateUser(UserDTO userdto) {
//
//		User user = new User();
//
//		user.setUserId(userdto.getUserId());
//
//		user.setUserFirstName(userdto.getUserFirstName());
//
//		user.setUserLastName(userdto.getUserLastName());
//
//		user.setUserEmailId(userdto.getUserEmailId());
//
//		user.setUserType(userdto.getUserType());
//
//		user.setUserMobileNumber(userdto.getUserMobileNumber());
//		
//		List<Long> departmentId = userdto.getDepartment();
//		if (departmentId != null && !departmentId.isEmpty()) {
//
//			List<Department> departments = departmentDAO.findAllById(departmentId);
//
//			user.setDepartment(departments);
//
//		}
//
////		department = departmentDAO.findById(userdto.getDepartment().get(0)).get();
////
////		user.setDepartment.(department);
//
////		region = regdao.findById(userdto.getRegion()).get();
////
////		user.setRegion(region);
//		
//		List<Long> regionId = userdto.getRegion();
//		if (regionId != null && !regionId.isEmpty()) {
//
//			List<Region> regions = regdao.findAllById(regionId);
//
//			user.setRegion(regions);
//
//		}
//		
//		
//
//		List<Long> projectId = userdto.getProject();
//
//		if (projectId != null && !projectId.isEmpty()) {
//
//			List<Project> projects = projectdao.findAllById(projectId);
//
//			user.setProject(projects);
//
//		}
//
//		userRepo.save(user);
//
//		return true;
//
//	}
//	
//	
//	
//
//	public boolean deleteUserById(Long userId) {
//
//		userRepo.deleteById(userId);
//
//		return true;
//
//	}
//
//	public List<User> getAllUsers() {
//
//		return userRepo.findAll();
//
//	}
//
//	public List<UserDTO> getAllEmployees() {
//		Iterator<User> iterator = userRepo.viewAllEmployees().iterator();
//		List<UserDTO> userList = new ArrayList<>();
//
////		List<Long> userUserList=new ArrayList<>();
////		List<Long> loginUserList=new ArrayList<>();
//
//		while (iterator.hasNext()) {
//
//			User user = iterator.next();
//
//			Long userUserId = user.getUserId();
//
//			Login login = loginRepo.findByUserId(userUserId);
//
//
//			UserDTO userdto = new UserDTO();
//
//			if (login != null && (user.getDepartment() != null || user.getRegion() != null)) {
//				userdto.setUserId(user.getUserId());
//				userdto.setUserFirstName(user.getUserFirstName());
//
//				userdto.setUserLastName(user.getUserLastName());
//
//				userdto.setUserEmailId(user.getUserEmailId());
//
//				userdto.setUserType(user.getUserType());
//				userdto.setUserMobileNumber(user.getUserMobileNumber());
////				userdto.setDepartment(user.getDepartment().getDepartmentId());
////				userdto.setDepartmentName(user.getDepartment().getDepartmentName());
////				userdto.setRegion(user.getRegion().getRegionId());
////				userdto.setRegionName(user.getRegion().getRegionName());
//
//				userList.add(userdto);
//			}
//		}
//		return userList;
//
//	}
//
//	// Employee are going to map using this function
//	public List<UserDTO> getEmployeesToMap() {
//		Iterator<User> iterator = userRepo.viewAllEmployees().iterator();
//		List<UserDTO> userList = new ArrayList<>();
//		while (iterator.hasNext()) {
//
//			User user = iterator.next();
//			Long userUserId = user.getUserId();
//
//			Login login = loginRepo.findByUserId(userUserId);
////			Long loginuserId=login.getUser().getUserId();
//
//			UserDTO userdto = new UserDTO();
//			if (login != null && (user.getDepartment() == null || user.getRegion() == null)) {
//				userdto.setUserId(user.getUserId());
//				userdto.setUserFirstName(user.getUserFirstName());
//
//				userdto.setUserLastName(user.getUserLastName());
//
//				userdto.setUserEmailId(user.getUserEmailId());
//
//				userdto.setUserType(user.getUserType());
//				userdto.setUserMobileNumber(user.getUserMobileNumber());
//				userList.add(userdto);
//
//			}
//		}
//		return userList;
//
//	}
//
////
//
//	public List<UserDTO> generateEmployeePassword() {
//		Iterator<User> iterator = userRepo.viewAllEmployees().iterator();
//		List<UserDTO> userList = new ArrayList<>();
//		while (iterator.hasNext()) {
//
//			User user = iterator.next();
//			Long userUserId = user.getUserId();
//
//			Login login = loginRepo.findByUserId(userUserId);
////		Long loginuserId=login.getUser().getUserId();
//
//			UserDTO userdto = new UserDTO();
//
//			if (login == null) {
//				userdto.setUserId(user.getUserId());
//				userdto.setUserFirstName(user.getUserFirstName());
//
//				userdto.setUserLastName(user.getUserLastName());
//
//				userdto.setUserEmailId(user.getUserEmailId());
//
//				userdto.setUserType(user.getUserType());
//				userdto.setUserMobileNumber(user.getUserMobileNumber());
//				userList.add(userdto);
//
//			}
//		}
//		return userList;
//
//	}
//
//
//	List<String> projectList = new ArrayList<String>();
//	public String findUserProject(long userId) {
// 
//		projectList.clear();
//		List<List<Long>> list = projectdao.findUserProjectId(userId);
//		for (int i = 0; i < list.size(); i++) {
//			List<Long> j = list.get(i);
//			Long k = j.get(1);
//			Project project = projectdao.findById(k).get();
//			projectList.add(project.getProjectName());
//		}
//		
//		return projectList.stream().map(Object::toString)
//                .collect(Collectors.joining(", "));
//	}
//	
//	List<String> projectIdList = new ArrayList<String>();
//	public String findUserProjectId(long userId) {
// 
//		projectIdList.clear();
//		List<List<Long>> list = projectdao.findUserProjectId(userId);
//		for (int i = 0; i < list.size(); i++) {
//			List<Long> j = list.get(i);
//			Long k = j.get(1);
//			Project project = projectdao.findById(k).get();
//			projectIdList.add(project.getProjectId().toString());
//		}
//		
//		return projectIdList.stream().map(Object::toString)
//                .collect(Collectors.joining(", "));
//	}
//	
//	List<String> regionList = new ArrayList<String>();
//	public String findUserRegion(long userId) {
// 
//		regionList.clear();
//		List<List<Long>> list = regdao.findUserRegionId(userId);
//		for (int i = 0; i < list.size(); i++) {
//			List<Long> j = list.get(i);
//			Long k = j.get(1);
//			Region region = regdao.findById(k).get();
//			regionList.add(region.getRegionName());
//		}
//		
//		return regionList.stream().map(Object::toString)
//                .collect(Collectors.joining(", "));
//	}
//	
//	List<String> regionIdList = new ArrayList<String>();
//	public String findUserRegionId(long userId) {
// 
//		regionIdList.clear();
//		List<List<Long>> list = regdao.findUserRegionId(userId);
//		for (int i = 0; i < list.size(); i++) {
//			List<Long> j = list.get(i);
//			Long k = j.get(1);
//			Region region = regdao.findById(k).get();
//			regionIdList.add(region.getRegionId().toString());
//		}
//		
//		return regionIdList.stream().map(Object::toString)
//                .collect(Collectors.joining(", "));
//	}
//	
//	List<String> departmentList = new ArrayList<String>();
//	public String findUserDepartment(long userId) {
// 
//		departmentList.clear();
//		List<List<Long>> list = departmentDAO.findUserDepartmentId(userId);
//		for (int i = 0; i < list.size(); i++) {
//			List<Long> j = list.get(i);
//			Long k = j.get(1);
//			Department department = departmentDAO.findById(k).get();
//			departmentList.add(department.getDepartmentName());
//		}
//		
//		return departmentList.stream().map(Object::toString)
//                .collect(Collectors.joining(", "));
//	}
//	
//	List<String> departmentIdList = new ArrayList<String>();
//	public String findUserDepartmentId(long userId) {
// 
//		departmentIdList.clear();
//		List<List<Long>> list = departmentDAO.findUserDepartmentId(userId);
//		for (int i = 0; i < list.size(); i++) {
//			List<Long> j = list.get(i);
//			Long k = j.get(1);
//			Department department = departmentDAO.findById(k).get();
//			departmentList.add(department.getDepartmentId().toString());
//		}
//		
//		return departmentIdList.stream().map(Object::toString)
//                .collect(Collectors.joining(", "));
//	}
//	
//	public String saveUser(UserDTO userdto) {
//		User invite = new User();
//		System.out.println(userRepo.findByUserEmailId(userdto.getUserEmailId()));
//		if(userRepo.findByUserEmailId(userdto.getUserEmailId()) == null) {
//			if(invitationRepo.findUserByEmail(userdto.getUserEmailId()) != null) {
//				invite.setUserFirstName(userdto.getUserFirstName());
//				invite.setUserLastName(userdto.getUserLastName());
//				invite.setUserEmailId(userdto.getUserEmailId());
//				invite.setUserType("employee");
//				invite.setUserMobileNumber(userdto.getUserMobileNumber());
//				userRepo.save(invite);
//				updateInvite(userdto.getUserEmailId());
//				return "success";
//			}
//			return "invalid";
//		}else {
//			return "exist";
//		}
//		
//	}
//	
//	private void updateInvite(String email) {
//		invitation  = invitationRepo.findUserByEmail(email);
//		invitation.setInvitationId(invitation.getInvitationId());
//		invitation.setAccepted(true);
//		invitation.setAcceptedTime(LocalDateTime.now());
//		invitationService.saveOrUpdateInvitation(invitation);
//		
//	}
//	
//
//}

package com.rts.ccp.service;
 
import org.springframework.beans.factory.annotation.Autowired;
 
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rts.ccp.bean.Department;
import com.rts.ccp.bean.Invitation;
import com.rts.ccp.bean.Login;
import com.rts.ccp.bean.Project;
import com.rts.ccp.bean.Region;
import com.rts.ccp.bean.User;
import com.rts.ccp.bean.UserProject;
import com.rts.ccp.dto.DepartmentDTO;
import com.rts.ccp.dto.UserCountDTO;
import com.rts.ccp.dto.UserDTO;
import com.rts.ccp.repository.DepartmentRepo;
import com.rts.ccp.repository.InvitationRepo;
import com.rts.ccp.repository.LoginRepo;
import com.rts.ccp.repository.ProjectRepo;
import com.rts.ccp.repository.RegionRepo;
import com.rts.ccp.repository.UserRepo;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
 
@Service
 
public class UserService {
 
	@Autowired
 
	private UserRepo userRepo;
 
	@Autowired
 
	private Department department;
 
	@Autowired
 
	private DepartmentRepo departmentDAO;
 
	@Autowired
 
	private Region region;
	@Autowired
 
	private User user;
 
	@Autowired
 
	private RegionRepo regdao;
 
	@Autowired
 
	private ProjectRepo projectdao;
 
	@Autowired
 
	private LoginRepo loginRepo;
	@Autowired
	private Invitation invitation;
	@Autowired
	private InvitationRepo invitationRepo;
	@Autowired
	private InvitationService invitationService;
 
	public boolean saveOrUpdateUser(UserDTO userdto) {
 
		User user = new User();
 
		user.setUserId(userdto.getUserId());
 
		user.setUserFirstName(userdto.getUserFirstName());
 
		user.setUserLastName(userdto.getUserLastName());
 
		user.setUserEmailId(userdto.getUserEmailId());
 
		user.setUserType(userdto.getUserType());
 
		user.setUserMobileNumber(userdto.getUserMobileNumber());
		List<Long> departmentId = userdto.getDepartment();
		if (departmentId != null && !departmentId.isEmpty()) {
 
			List<Department> departments = departmentDAO.findAllById(departmentId);
 
			user.setDepartment(departments);
 
		}
 
//		department = departmentDAO.findById(userdto.getDepartment().get(0)).get();
//
//		user.setDepartment.(department);
 
//		region = regdao.findById(userdto.getRegion()).get();
//
//		user.setRegion(region);
		List<Long> regionId = userdto.getRegion();
		if (regionId != null && !regionId.isEmpty()) {
 
			List<Region> regions = regdao.findAllById(regionId);
 
			user.setRegion(regions);
 
		}

 
		List<Long> projectId = userdto.getProject();
 
		if (projectId != null && !projectId.isEmpty()) {
 
			List<Project> projects = projectdao.findAllById(projectId);
 
			user.setProject(projects);
 
		}
 
		userRepo.save(user);
 
		return true;
 
	}
 
	public boolean deleteUserById(Long userId) {
 
		userRepo.deleteById(userId);
 
		return true;
 
	}
 
	public List<User> getAllUsers() {
 
		return userRepo.findAll();
 
	}
 
	public List<UserDTO> getAllEmployees() {
		Iterator<User> iterator = userRepo.viewAllEmployees().iterator();
		List<UserDTO> userList = new ArrayList<>();
 
//		List<Long> userUserList=new ArrayList<>();
//		List<Long> loginUserList=new ArrayList<>();
 
		while (iterator.hasNext()) {
 
			User user = iterator.next();
 
			Long userUserId = user.getUserId();
 
			Login login = loginRepo.findByUserId(userUserId);
 
 
			UserDTO userdto = new UserDTO();
 
			if (login != null && (!user.getDepartment().isEmpty()||!user.getRegion().isEmpty())) {
				userdto.setUserId(user.getUserId());
				userdto.setUserFirstName(user.getUserFirstName());
 
				userdto.setUserLastName(user.getUserLastName());
 
				userdto.setUserEmailId(user.getUserEmailId());
 
				userdto.setUserType(user.getUserType());
				userdto.setUserMobileNumber(user.getUserMobileNumber());
//				userdto.setDepartment(user.getDepartment().getDepartmentId());
//				userdto.setDepartmentName(user.getDepartment().getDepartmentName());
//				userdto.setRegion(user.getRegion().getRegionId());
//				userdto.setRegionName(user.getRegion().getRegionName());
 
				userList.add(userdto);
			}
		}
		return userList;
 
	}
 
	// Employee are going to map using this function
	public List<UserDTO> getEmployeesToMap() {
		Iterator<User> iterator = userRepo.viewAllEmployees().iterator();
		List<UserDTO> userList = new ArrayList<>();
		while (iterator.hasNext()) {
 
			User user = iterator.next();
			Long userUserId = user.getUserId();
 
			Login login = loginRepo.findByUserId(userUserId);
//			Long loginuserId=login.getUser().getUserId();
			user.getDepartment().isEmpty();

 
			UserDTO userdto = new UserDTO();
			if (login != null && (user.getDepartment().isEmpty()|| user.getRegion().isEmpty())) {
				userdto.setUserId(user.getUserId());
				userdto.setUserFirstName(user.getUserFirstName());
 
				userdto.setUserLastName(user.getUserLastName());
 
				userdto.setUserEmailId(user.getUserEmailId());
 
				userdto.setUserType(user.getUserType());
				userdto.setUserMobileNumber(user.getUserMobileNumber());
				userList.add(userdto);
 
			}
		}
		return userList;
 
	}
 
//
 
	public List<UserDTO> generateEmployeePassword() {
		Iterator<User> iterator = userRepo.viewAllEmployees().iterator();
		List<UserDTO> userList = new ArrayList<>();
		while (iterator.hasNext()) {
 
			User user = iterator.next();
			Long userUserId = user.getUserId();
 
			Login login = loginRepo.findByUserId(userUserId);
//		Long loginuserId=login.getUser().getUserId();
 
			UserDTO userdto = new UserDTO();
 
			if (login == null) {
				userdto.setUserId(user.getUserId());
				userdto.setUserFirstName(user.getUserFirstName());
 
				userdto.setUserLastName(user.getUserLastName());
 
				userdto.setUserEmailId(user.getUserEmailId());
 
				userdto.setUserType(user.getUserType());
				userdto.setUserMobileNumber(user.getUserMobileNumber());
				userList.add(userdto);
 
			}
		}
		return userList;
 
	}
 
 
	List<String> projectList = new ArrayList<String>();
	public String findUserProject(long userId) {
		projectList.clear();
		List<List<Long>> list = projectdao.findUserProjectId(userId);
		for (int i = 0; i < list.size(); i++) {
			List<Long> j = list.get(i);
			Long k = j.get(1);
			Project project = projectdao.findById(k).get();
			projectList.add(project.getProjectName());
		}
		return projectList.stream().map(Object::toString)
                .collect(Collectors.joining(", "));
	}
	List<String> regionList = new ArrayList<String>();
	public String findUserRegion(long userId) {
		regionList.clear();
		List<List<Long>> list = regdao.findUserRegionId(userId);
		for (int i = 0; i < list.size(); i++) {
			List<Long> j = list.get(i);
			Long k = j.get(1);
			Region region = regdao.findById(k).get();
			regionList.add(region.getRegionName());
		}
		return regionList.stream().map(Object::toString)
                .collect(Collectors.joining(", "));
	}
	List<String> departmentList = new ArrayList<String>();
	public String findUserDepartment(long userId) {
		departmentList.clear();
		List<List<Long>> list = departmentDAO.findUserDepartmentId(userId);
		for (int i = 0; i < list.size(); i++) {
			List<Long> j = list.get(i);
			Long k = j.get(1);
			Department department = departmentDAO.findById(k).get();
			departmentList.add(department.getDepartmentName());
		}
		return departmentList.stream().map(Object::toString)
                .collect(Collectors.joining(", "));
	}
	public String saveUser(UserDTO userdto) {
		User invite = new User();
		System.out.println(userRepo.findByUserEmailId(userdto.getUserEmailId()));
		if(userRepo.findByUserEmailId(userdto.getUserEmailId()) == null) {
			if(invitationRepo.findUserByEmail(userdto.getUserEmailId()) != null) {
				invite.setUserFirstName(userdto.getUserFirstName());
				invite.setUserLastName(userdto.getUserLastName());
				invite.setUserEmailId(userdto.getUserEmailId());
				invite.setUserType("employee");
				invite.setUserMobileNumber(userdto.getUserMobileNumber());
				userRepo.save(invite);
				updateInvite(userdto.getUserEmailId());
				return "success";
			}
			return "invalid";
		}else {
			return "exist";
		}
	}
	private void updateInvite(String email) {
		invitation  = invitationRepo.findUserByEmail(email);
		invitation.setInvitationId(invitation.getInvitationId());
		invitation.setAccepted(true);
		invitation.setAcceptedTime(LocalDateTime.now());
		invitationService.saveOrUpdateInvitation(invitation);
	}
	public UserDTO findParticularEmployee(Long userId) {
		 user =userRepo.findByUserId(userId);
	UserDTO userdto=new UserDTO();
	userdto.setUserId(user.getUserId());
	userdto.setUserFirstName(user.getUserFirstName());
	userdto.setUserLastName(user.getUserLastName());
	userdto.setUserEmailId(user.getUserEmailId());
	userdto.setUserMobileNumber(user.getUserMobileNumber());
	List<String> regionName=new ArrayList<>();
       for(int i=0;i<user.getRegion().size();i++) {
    	   regionName.add (user.getRegion().get(i).getRegionName());
       }
       userdto.setRegionName(regionName);

       List<String> departmentName=new ArrayList<>();
       for(int i=0;i<user.getDepartment().size();i++) {
    	   departmentName.add (user.getDepartment().get(i).getDepartmentName());
       }
       userdto.setDepartmentName(departmentName);

List<String> projects=new ArrayList<>();
       for(int i=0;i<user.getProject().size();i++) {
    	   projects.add (user.getProject().get(i).getProjectName());
       }
       userdto.setProjectName(projects);

       return userdto;
	}

	// this method is used to find different stages of employee count
	 
		public UserCountDTO findEmployeeCount() {
			
			int existingEmployeeCount = 0;
			int newEmployeeCount = 0;
			int registeredEmployeeCount = 0;
	 
			Iterator<User> iterator = userRepo.viewAllEmployees().iterator();
			
	 
			while (iterator.hasNext()) {
				
				User user = iterator.next();
				Long userUserId = user.getUserId();
	 
				Login login = loginRepo.findByUserId(userUserId);
				
				
				if (login == null) {
					registeredEmployeeCount += 1;
					}
				
				
				if (login != null && (!user.getDepartment().isEmpty() || !user.getRegion().isEmpty())) {
	 
					existingEmployeeCount += 1;
					}
				
				
				if (login != null && (user.getDepartment().isEmpty() || user.getRegion().isEmpty())) {
					newEmployeeCount += 1;
				}
	 
			}
			
			UserCountDTO userCountdto =new UserCountDTO();
			userCountdto.setExistingEmployeeCount(existingEmployeeCount);
			userCountdto.setNewEmployeeCount(newEmployeeCount);
			userCountdto.setRegisteredEmployeeCount(registeredEmployeeCount);
			return userCountdto;
	 
		}
		
		public boolean updateProfile(MultipartFile file,Long userId) throws IOException {
			User user=new User();
			User oldUser = userRepo.findByUserId(userId);
			user.setUserId(oldUser.getUserId());
			user.setAboutUs(oldUser.getAboutUs());
			user.setDob(oldUser.getDob());
			user.setGender(oldUser.getGender());
			user.setUserEmailId(oldUser.getUserEmailId());
			user.setUserFirstName(oldUser.getUserFirstName());
			user.setUserLastName(oldUser.getUserLastName());
			user.setUserMobileNumber(oldUser.getUserMobileNumber());
			user.setUserType(oldUser.getUserType());
			user.setProject(oldUser.getProject());
			user.setDepartment(oldUser.getDepartment());
			user.setRegion(oldUser.getRegion());
			user.setProfileImage(file.getBytes());
			return userRepo.save(user) != null;
		}
		
		public boolean updateProfileDetails(User userDto) throws IOException {
			
			User user = new User();
			User oldUser = userRepo.findByUserId(user.getUserId());
			user.setUserId(oldUser.getUserId());
			user.setAboutUs(userDto.getAboutUs());//update
			user.setDob(userDto.getDob());//update
			user.setGender(userDto.getGender());//update
			user.setUserEmailId(oldUser.getUserEmailId());
			user.setUserFirstName(oldUser.getUserFirstName());
			user.setUserLastName(oldUser.getUserLastName());
			user.setUserMobileNumber(userDto.getUserMobileNumber());//update
			user.setUserType(oldUser.getUserType());
			user.setProject(oldUser.getProject());
			user.setDepartment(oldUser.getDepartment());
			user.setRegion(oldUser.getRegion());
			user.setProfileImage(oldUser.getProfileImage());
			return userRepo.save(user) != null;
		}
}