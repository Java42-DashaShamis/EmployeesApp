package telran.employees.net.dto;

import telran.employees.dto.Employee;
import telran.employees.services.ReturnCode;

public interface ApiConstants {
	
	String ADD_EMPLOYEE = "/employee/add";
	String GET_EMPLOYEES = "/get";
	String REMOVE_EMPLOYEE = "/employee/remove";
	String GET_EMPLOYEE = "/employee/get";
	String GET_EMPLOYEES_BY_AGE = "/get/byAge";
	String GET_EMPLOYEES_BY_SALARY = "/get/bySalary";
	String GET_EMPLOYEES_BY_DEPARTMENT = "/get/byDepartment";
	String GET_EMPLOYEES_BY_DEPARTMENT_AND_SALARY = "/get/byDepartmentAndSalary";
	String UPDATE_SALARY = "/update/salary";
	String UPDATE_DEPARTMENT = "/update/department";
	String RESTORE = "/restore";
	String SAVE = "/save";
	
}
