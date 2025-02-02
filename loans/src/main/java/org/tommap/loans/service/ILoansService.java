package org.tommap.loans.service;

import org.tommap.loans.dto.LoansDTO;

public interface ILoansService {
    void createLoan(String mobileNumber);
    LoansDTO fetchLoan(String mobileNumber);
    boolean updateLoan(LoansDTO loansDTO);
    boolean deleteLoan(String mobileNumber);
}
