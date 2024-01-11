package com.examples.customer.controller;

import com.examples.customer.dto.CustomerDTO;
import com.examples.customer.dto.ResponseDTO;
import com.examples.customer.entity.Customer;
//import com.examples.customer.service.AuthenticationService;
import com.examples.customer.service.CustomerService;
import com.examples.customer.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("api/v1/customers")

public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ResponseDTO responseDTO;
//
//    @Autowired
//    private AuthenticationService authService;
//
//    @PostMapping("/authenticate")
//    public ResponseEntity<String> authenticateUser(@RequestBody Map<String, String> credentials) {
//        String loginId = credentials.get("login_id");
//        String password = credentials.get("password");
//        String bearerToken = authService.authenticateAndGetBearerToken(loginId, password);
//        return ResponseEntity.ok(bearerToken);
//    }

//    @PostMapping("/saveCustomer")
//    public ResponseEntity<String> addCustomer(@RequestBody Customer customer, @RequestHeader("Authorization") String bearerToken) {
//        return service.createCustomerWithToken(customer, bearerToken);
//    }

    @PostMapping(value="/saveCustomer")
    public ResponseEntity saveCustomer (@RequestBody CustomerDTO customerDTO){
        try{
            String res = customerService.saveCustomer(customerDTO);
            if (res.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(customerDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);

            }else if (res.equals("06")){
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("Customer Registered");
                responseDTO.setContent(customerDTO);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }else{
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Error");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);

            }

        }
        catch (Exception ex){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PutMapping(value="/updateCustomer")
    public ResponseEntity updateCustomer(@RequestBody CustomerDTO customerDTO){
        try{
            String res = customerService.updateCustomer(customerDTO);
            if (res.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(customerDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);

            }else if (res.equals("01")){
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("Customer Not Found");
                responseDTO.setContent(customerDTO);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }else{
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Error");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);

            }

        }
        catch (Exception ex){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/getAllCustomers")
    public ResponseEntity getAllCustomers(){
        try{
            List<CustomerDTO> customerDTOList = customerService.getAllCustomers();
            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("Success");
            responseDTO.setContent(customerDTOList);
            return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);

        }catch (Exception ex){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/searchCustomer/{custID}")
    public ResponseEntity searchCustomer(@PathVariable int custID){
        try{
            CustomerDTO customerDTO = customerService.searchCustomer(custID);
            if (customerDTO !=null){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(customerDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);

            }else{
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No Customer Available For This ID");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);

            }

        }
        catch (Exception ex){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @DeleteMapping("/deleteCustomer/{custID}")
    public ResponseEntity deleteCustomer(@PathVariable int custID){
        try{
            String resp = customerService.deleteCustomer(custID);
            if (resp.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);

            }else{
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No Customer Available For This ID");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);

            }

        }
        catch (Exception ex){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
