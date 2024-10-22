package com.atuluttam.SpringBootWithJSPLoginForm.Repository;

import com.atuluttam.SpringBootWithJSPLoginForm.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    public Employee findByName(String name);
}
