package com.example.springbootbackend.serviceImpl;

import com.example.springbootbackend.exception.ResourceNotFoundException;
import com.example.springbootbackend.model.Employee;
import com.example.springbootbackend.repository.EmployeeRepository;
import com.example.springbootbackend.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save (employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll ();

    }
//    @Override
//    public Employee  getEmployeeById(long id){
//        Optional <Employee> employee = employeeRepository.findById (id);
//        if(employee .isPresent ())
//            return employee.get();
//        else {
//            throw new ResourceNotFoundException ("Employee" ,"Id",id);
//        }
//    }

    @Override
    public Employee getEmployeeById(long id) {
        return employeeRepository.findById (id)
                .orElseThrow (() -> new ResourceNotFoundException ("Employee", "Id", id));
    }

    @Override
    public Employee updateEMployee(Employee employee, long id) {

        // we need to check whether employee with given id is exist in DB or not
        Employee existingEmployee = employeeRepository.findById (id).orElseThrow (
                () -> new ResourceNotFoundException ("Employee", "Id", id));

        existingEmployee.setFirstName (employee.getFirstName ());
        existingEmployee.setLastName (employee.getLastName ());
        existingEmployee.setEmail (employee.getEmail ());
        // save existing employee to DB
        employeeRepository.save (existingEmployee);
        return existingEmployee;
    }

    @Override
    public void deleteEmployee(long id) {

        // check whether the employee is exists in our DB or not

        employeeRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException ("Employee","Id",id));
        employeeRepository.deleteById (id);

    }


}
