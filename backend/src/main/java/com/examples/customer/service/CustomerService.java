package com.examples.customer.service;
import org.modelmapper.TypeToken;
import com.examples.customer.dto.CustomerDTO;
import com.examples.customer.entity.Customer;
import com.examples.customer.repo.CustomerRepo;
import com.examples.customer.util.VarList;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional
public class CustomerService {
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private ModelMapper modelMapper;

//    @Value("${authentication.api.url}")
//    private String authenticationApiUrl;
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//    @Value("${customer.api.url}")
//    private String customerApiUrl;
//
//    public ResponseEntity<String> createCustomerWithToken(Customer customer, String bearerToken) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.set("Authorization", "Bearer " + bearerToken);
//        HttpEntity<Customer> requestEntity = new HttpEntity<>(customer, headers);
//
//        ResponseEntity<String> responseEntity = restTemplate.postForEntity(customerApiUrl, requestEntity, String.class);
//
//        return responseEntity;
//    }

//    public String authenticateAndGetBearerToken(String loginId, String password) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        Map<String, String> requestBody = new HashMap<>();
//        requestBody.put("login_id", loginId);
//        requestBody.put("password", password);
//
//        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);
//
//        ResponseEntity<String> responseEntity = restTemplate.postForEntity(authenticationApiUrl, requestEntity, String.class);
//
//        if (responseEntity.getStatusCode() == HttpStatus.OK) {
//            return responseEntity.getBody();
//        } else {
//            return null;
//        }
//    }

    public String saveCustomer(CustomerDTO customerDTO){
        if(customerRepo.existsById(customerDTO.getCustID())){
            return VarList.RSP_DUPLICATED;
        }
        else{
            customerRepo.save(modelMapper.map(customerDTO, Customer.class));
            return VarList.RSP_SUCCESS;

        }
    }
    public String updateCustomer(CustomerDTO customerDTO){
        if(customerRepo.existsById(customerDTO.getCustID())){
            customerRepo.save(modelMapper.map(customerDTO, Customer.class));
            return VarList.RSP_SUCCESS;
        }
        else{
            return VarList.RSP_NO_DATA_FOUND;
        }
    }
    public List<CustomerDTO> getAllCustomers(){
        List<Customer> customerList = customerRepo.findAll();
        return modelMapper.map(customerList, new TypeToken<ArrayList<CustomerDTO>>(){}.getType());
    }
    public CustomerDTO searchCustomer(int custID){
        if(customerRepo.existsById(custID)) {
            Customer customer = customerRepo.findById(custID).orElse(null);
            return modelMapper.map(customer, CustomerDTO.class);
        }
        else{
            return null;
        }
    }
    public String deleteCustomer(int custID){
        if(customerRepo.existsById(custID)){
            customerRepo.deleteById(custID);
            return VarList.RSP_SUCCESS;
        }else{
            return VarList.RSP_NO_DATA_FOUND;
        }
    }
}
