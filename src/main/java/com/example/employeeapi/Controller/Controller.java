package com.example.employeeapi.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.employeeapi.EmployeeRepo.EmployeeRepo;
import com.example.employeeapi.module.Employee;


@RestController
@RequestMapping("/")
public class Controller {

    @Autowired
    EmployeeRepo employeeRepository;

    @GetMapping("/getAllEmployee")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        try {
            List<Employee> employeeList = new ArrayList<>();
            employeeRepository.findAll().forEach(employeeList::add);

            if (employeeList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(employeeList, HttpStatus.OK);
        } catch(Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getEmployeeById/{id}")
    public ResponseEntity<Employee> getBookById(@PathVariable Long id) {
        Optional<Employee> employeeObj = employeeRepository.findById(id);
        if (employeeObj.isPresent()) {
            return new ResponseEntity<>(employeeObj.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addEmployee")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        try {
            Employee employeeObj = employeeRepository.save(employee);
            return new ResponseEntity<>(employeeObj, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/updateEmployee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee newEmployeeData) {
            Optional<Employee> oldEmployeeData = employeeRepository.findById(id);
            if (oldEmployeeData.isPresent()) {
                Employee updatedEmployeeData = oldEmployeeData.get();
                updatedEmployeeData.setFirstname(newEmployeeData.getFirstname());
                updatedEmployeeData.setLastname(newEmployeeData.getLastname());
                updatedEmployeeData.setAddress(newEmployeeData.getAddress());
                updatedEmployeeData.setDateOfBirth(newEmployeeData.getDateOfBirth());
                updatedEmployeeData.setPhoneNumber(newEmployeeData.getPhoneNumber());

                Employee employeeObj = employeeRepository.save(updatedEmployeeData);
                return new ResponseEntity<>(employeeObj, HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        //catch (Exception e) {
            //return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        //}//s
  

    @DeleteMapping("/deleteEmplyeeById/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable Long id) {
       
        	employeeRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } 

    //@DeleteMapping("/deleteAllEmployee")
    //public ResponseEntity<HttpStatus> deleteAllEmployee() {
      //  try {
        //    employeeRepository.deleteAll();
          //  return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        //} catch (Exception e) {
          //  return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        //}
    //}

}
