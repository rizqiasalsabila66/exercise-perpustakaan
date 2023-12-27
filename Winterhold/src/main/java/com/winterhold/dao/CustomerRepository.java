package com.winterhold.dao;

import com.winterhold.dto.author.AuthorRowDTO;
import com.winterhold.dto.customer.CustomerRowDTO;
import com.winterhold.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,String> {

    @Query("""
            SELECT new com.winterhold.dto.customer.CustomerRowDTO(cus.membershipNumber, CONCAT(cus.firstName,' ', cus.lastName),
            cus.membershipExpireDate)
            FROM Customer AS cus
            WHERE cus.membershipNumber LIKE %:membershipNumber%
            AND CONCAT(cus.firstName,' ', cus.lastName) LIKE %:name%
            """)
    public Page<CustomerRowDTO> getRow(@Param("membershipNumber") String membershipNumber,@Param("name") String name, Pageable pageable);

}
