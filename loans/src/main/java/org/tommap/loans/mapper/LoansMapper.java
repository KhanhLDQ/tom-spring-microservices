package org.tommap.loans.mapper;

import org.tommap.loans.dto.LoansDTO;
import org.tommap.loans.entity.Loan;

public class LoansMapper {
    public static LoansDTO toLoansDTO(Loan loan, LoansDTO loansDTO) {
        loansDTO.setLoanNumber(loan.getLoanNumber());
        loansDTO.setLoanType(loan.getLoanType());
        loansDTO.setMobileNumber(loan.getMobileNumber());
        loansDTO.setTotalLoan(loan.getTotalLoan());
        loansDTO.setAmountPaid(loan.getAmountPaid());
        loansDTO.setOutstandingAmount(loan.getOutstandingAmount());

        return loansDTO;
    }

    public static Loan toLoan(LoansDTO loansDTO, Loan loan) {
        loan.setLoanNumber(loansDTO.getLoanNumber());
        loan.setLoanType(loansDTO.getLoanType());
        loan.setMobileNumber(loansDTO.getMobileNumber());
        loan.setTotalLoan(loansDTO.getTotalLoan());
        loan.setAmountPaid(loansDTO.getAmountPaid());
        loan.setOutstandingAmount(loansDTO.getOutstandingAmount());

        return loan;
    }
}
