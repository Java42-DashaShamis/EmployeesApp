package telran.employees.net;

import telran.employees.dto.DepartmentAndSalaryData;
import telran.employees.dto.Employee;
import telran.employees.services.EmployeesMethods;
import telran.employees.services.ReturnCode;

import static telran.employees.net.dto.ApiConstants.*;

import java.io.Serializable;
import java.util.*;

import telran.net.AppProtocol;
import telran.net.dto.*;

public class EmployeeProtocol implements AppProtocol {
	
	private EmployeesMethods employees;

	public EmployeeProtocol(EmployeesMethods employees) {
		this.employees = employees;
	}
	 
	@Override
	public Response getResponse(Request request) {
		switch(request.requestType) {
		case ADD_EMPLOYEE: return _employee_add(request.requestData);
		case GET_EMPLOYEES: return _get(request.requestData);
		case REMOVE_EMPLOYEE: return _employee_remove(request.requestData);
		case GET_EMPLOYEE: return _employee_get(request.requestData);
		case GET_EMPLOYEES_BY_AGE: return _get_by_age(request.requestData);
		case GET_EMPLOYEES_BY_SALARY: return _get_by_salary(request.requestData);
		case GET_EMPLOYEES_BY_DEPARTMENT: return _get_by_department(request.requestData);
		case GET_EMPLOYEES_BY_DEPARTMENT_AND_SALARY: return _get_by_departmentANDsalary(request.requestData);
		case UPDATE_SALARY: return _update_salary(request.requestData);
		case UPDATE_DEPARTMENT: return _update_department(request.requestData);
		case RESTORE: return _restore(request.requestData);
		case SAVE: return _save(request.requestData);
		default: return new Response(ResponseCode.UNKNOWEN_REQUEST, request.requestType + "not implemented");
		}
	}

	private Response _save(Serializable requestData) {
		employees.save();
		return new Response(ResponseCode.OK, "");
	}

	private Response _restore(Serializable requestData) {
		employees.restore();
		return new Response(ResponseCode.OK, "");
	}

	private Response _update_department(Serializable requestData) {
		try {
			DepartmentAndSalaryData data = (DepartmentAndSalaryData) requestData;
			ReturnCode responseData = employees.updateDepartment(data.id, data.department);
			return new Response(ResponseCode.OK, responseData);
		} catch (Exception e) {
			return new Response(ResponseCode.WRONG_REQUEST_DATA, e.getMessage());
		}
	}

	private Response _update_salary(Serializable requestData) {
		try {
			DepartmentAndSalaryData data = (DepartmentAndSalaryData) requestData;
			ReturnCode responseData = employees.updateSalary(data.id, data.to);
			return new Response(ResponseCode.OK, responseData);
		} catch (Exception e) {
			return new Response(ResponseCode.WRONG_REQUEST_DATA, e.getMessage());
		}
	}

	private Response _get_by_departmentANDsalary(Serializable requestData) {
		try {
			DepartmentAndSalaryData data = (DepartmentAndSalaryData) requestData;
			List<Employee> responseData = new ArrayList<>();
			employees.getEmployeesByDepartmentAndSalary(data.department, data.from, data.to).forEach(responseData::add);
			return new Response(ResponseCode.OK, (Serializable) responseData);
		} catch (Exception e) {
			return new Response(ResponseCode.WRONG_REQUEST_DATA, e.getMessage());
		}
	}

	private Response _get_by_department(Serializable requestData) {
		try {
			List<Employee> responseData = new ArrayList<>();
			employees.getEmployeesByDepartment((String) requestData).forEach(responseData::add);
			return new Response(ResponseCode.OK, (Serializable) responseData);
		} catch (Exception e) {
			return new Response(ResponseCode.WRONG_REQUEST_DATA, e.getMessage());
		}
	}

	private Response _get_by_salary(Serializable requestData) {
		try {
			DepartmentAndSalaryData data = (DepartmentAndSalaryData) requestData;
			List<Employee> responseData = new ArrayList<>();
			employees.getEmployeesBySalary(data.from, data.to).forEach(responseData::add);
			return new Response(ResponseCode.OK, (Serializable) responseData);
		} catch (Exception e) {
			return new Response(ResponseCode.WRONG_REQUEST_DATA, e.getMessage());
		}
	}

	private Response _get_by_age(Serializable requestData) {
		try {
			DepartmentAndSalaryData data = (DepartmentAndSalaryData) requestData;
			List<Employee> responseData = new ArrayList<>();
			employees.getEmployeesByAge(data.from, data.to).forEach(responseData::add);
			return new Response(ResponseCode.OK, (Serializable) responseData);
		} catch (Exception e) {
			return new Response(ResponseCode.WRONG_REQUEST_DATA, e.getMessage());
		}
	}

	private Response _employee_get(Serializable requestData) {
		try {
			long id = (long) requestData;
			Employee responseData = employees.getEmployee(id);
			return new Response(ResponseCode.OK, responseData);
		} catch (Exception e) {
			return new Response(ResponseCode.WRONG_REQUEST_DATA, e.getMessage());
		}
	}

	private Response _employee_remove(Serializable requestData) {
		try {
			long id = (long) requestData;
			ReturnCode responseData = employees.removeEmployee(id);
			return new Response(ResponseCode.OK, responseData);
		} catch (Exception e) {
			return new Response(ResponseCode.WRONG_REQUEST_DATA, e.getMessage());
		}
	}

	private Response _get(Serializable requestData) { //массив интов, например
		try {
			List<Employee> responseData = new ArrayList<>(); //serializable
			employees.getAllEmployees().forEach(responseData::add);
			return new Response(ResponseCode.OK, (Serializable) responseData);
		} catch (Exception e) {
			return new Response(ResponseCode.WRONG_REQUEST_DATA, e.getMessage());
		}
	}

	private Response _employee_add(Serializable requestData) {
		try {
			Employee employee = (Employee) requestData;
			ReturnCode responseData = employees.addEmployee(employee);
			return new Response(ResponseCode.OK, responseData);
		} catch (Exception e) {
			return new Response(ResponseCode.WRONG_REQUEST_DATA, e.getMessage());
		}
	}

}
