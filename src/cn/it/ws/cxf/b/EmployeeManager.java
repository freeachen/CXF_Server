package cn.it.ws.cxf.b;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import cn.it.ws.cxf.bean.Employee;
@WebService(serviceName="EmployeeService")
public interface EmployeeManager {

	public abstract void add(@WebParam(name="employee")Employee employee);

	public abstract @WebResult(name="employees")List<Employee> query();

}