package com.winterhold.dao;

import com.winterhold.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<Loan,String> {

    @Query("""
            SELECT COUNT(loa.id)
            FROM Loan AS loa
            WHERE loa.bookCode = :bookCode
            """)
    public Long countByBook(@Param("bookCode") String bookCode);

    @Query("""
            SELECT COUNT(loa.id)
            FROM Loan AS loa
            WHERE loa.customerNumber = :customerNumber
            """)
    public Long countByCustomer(@Param("customerNumber") String customerNumber);

}
