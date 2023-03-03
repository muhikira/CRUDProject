package com.example.springbootbackend.controller;

import com.example.springbootbackend.model.Employee;
import com.example.springbootbackend.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    // build create employee REST API
    @PostMapping()
    public ResponseEntity <Employee> saveEmployee(@RequestBody Employee employee){
        return new ResponseEntity<Employee> (employeeService.saveEmployee (employee), HttpStatus.CREATED);

    }
    // build get all employees  REST API
@GetMapping
    public List <Employee> getAllEmployees(){
        return employeeService.getAllEmployees ();

    }

    // build get employee by Id REST API
 //   http://localhost:8080/api/employees/1
    @GetMapping("{id}")
    public ResponseEntity <Employee> getEmployeeById (@PathVariable("id") long employeeId){

        return  new ResponseEntity<Employee> (employeeService.getEmployeeById (employeeId),HttpStatus.OK);

    }

    // build update employee by Id REST API
    //   http://localhost:8080/api/employees/1
    @PutMapping("/{id}")
    public  ResponseEntity<Employee>updateEmployee(@PathVariable("id") long id,@RequestBody Employee employee){
        return new ResponseEntity<Employee> (employeeService.updateEMployee (employee,id),HttpStatus.OK);

    }

    // Build delete employee Rest API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id){
        employeeService.deleteEmployee (id);
        return new ResponseEntity<String> ("Employee Deleted successfully!.",HttpStatus.OK);

    }


}
