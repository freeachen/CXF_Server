package cn.it.ws.cxf.b;

import java.util.ArrayList;
import java.util.List;

import cn.it.ws.cxf.bean.Employee;

/**员工管理的业务实现类
 */
public class EmployeeManagerImpl implements EmployeeManager {
	private List<Employee> employees=new ArrayList<>();
	@Override
	public void add(Employee employee){
		//添加到集合中
		employees.add(employee);
	}
	@Override
	public List<Employee> query(){
		return employees;
	}

}
