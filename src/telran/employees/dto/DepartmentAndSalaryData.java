package telran.employees.dto;

import java.io.Serializable;

public class DepartmentAndSalaryData implements Serializable{
	private static final long serialVersionUID = 1L;
	public String department;
	public long id;
	public int from;
	public int to;
	public DepartmentAndSalaryData(String department, long id, int from, int to) {
		this.department = department;
		this.id = id;
		this.from = from;
		this.to = to;
	}
	

}
