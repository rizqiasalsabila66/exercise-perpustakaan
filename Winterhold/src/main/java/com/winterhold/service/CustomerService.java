package com.winterhold.service;

import com.winterhold.dao.CustomerRepository;
import com.winterhold.dao.LoanRepository;
import com.winterhold.dto.customer.*;
import com.winterhold.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private LoanRepository loanRepository;

    public Page<CustomerRowDTO> getCustomerRows(String membershipNumber, String name, int page) {
        var pageable = PageRequest.of(page-1,10, Sort.by("membershipNumber"));
        var rows = customerRepository.getRow(membershipNumber,name,pageable);
        return rows;
    }

    public CustomerRespondUpsertDTO save(UpsertCustomerDTO dto) {
        var entity = new Customer();
        entity.setMembershipNumber(dto.getMembershipNumber());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setBirthDate(dto.getBirthDate());
        entity.setGender(dto.getGender());
        entity.setPhone(dto.getPhone());
        entity.setAddress(dto.getAddress());
        entity.setMembershipExpireDate(dto.getMembershipExpireDate());
        var response = customerRepository.save(entity);
        var responseDTO = new CustomerRespondUpsertDTO(
                response.getMembershipNumber(),
                response.getFirstName(),
                response.getLastName(),
                response.getBirthDate(),
                response.getGender(),
                response.getPhone(),
                response.getAddress(),
                response.getMembershipExpireDate()
        );
        return responseDTO;
    }

    public UpsertCustomerDTO findOne(String membershipNumber){
        var entity = customerRepository.findById(membershipNumber).get();
        var dto = new UpsertCustomerDTO(
                entity.getMembershipNumber(), entity.getFirstName(), entity.getLastName(), entity.getBirthDate(),
                entity.getGender(),entity.getPhone(),entity.getAddress(),entity.getMembershipExpireDate()
        );
        return dto;
    }
    public BiodataCustomerDTO findOneContact(String membershipNumber) {
        var entity = customerRepository.findById(membershipNumber).get();
        var dot = new BiodataCustomerDTO(
                entity.getMembershipNumber(), entity.getFirstName(), entity.getLastName(),
                entity.getBirthDate(), entity.getGender(), entity.getPhone(),entity.getAddress()
        );
        return dot;
    }

    public void delete(String membershipNumber){
        customerRepository.deleteById(membershipNumber);
    }

    public Long countLoanByCustomer (String membershipNumber){
        return loanRepository.countByCustomer(membershipNumber);

    }

    public String extend (String membershipNumber){
        var entity = customerRepository.findById(membershipNumber).get();
        entity.setMembershipExpireDate(entity.getMembershipExpireDate().plusYears(2));
        customerRepository.save(entity);
        String response = entity.getMembershipNumber() + " extended membership";
        return response;
    }

}
