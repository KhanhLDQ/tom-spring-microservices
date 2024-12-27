package org.tommap.loans.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tommap.loans.dto.LoansDTO;
import org.tommap.loans.entity.Loan;
import org.tommap.loans.exception.LoanAlreadyExistsException;
import org.tommap.loans.mapper.LoansMapper;
import org.tommap.loans.repository.LoansRepository;
import org.tommap.loans.service.ILoansService;
import org.tommap.loans.exception.ResourceNotFoundException;

import java.util.Optional;
import java.util.Random;

import static org.tommap.loans.constants.LoansConstants.HOME_LOAN;
import static org.tommap.loans.constants.LoansConstants.NEW_LOAN_LIMIT;

@Service
@RequiredArgsConstructor
public class LoansServiceImpl implements ILoansService {
    private final LoansRepository loansRepository;

    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loan> optionalLoan = loansRepository.findByMobileNumber(mobileNumber);
        if (optionalLoan.isPresent()) {
            throw new LoanAlreadyExistsException(
                    "Loan already registered with given mobileNumber" + mobileNumber
            );
        }

        loansRepository.save(createNewLoan(mobileNumber));
    }

    @Override
    public LoansDTO fetchLoan(String mobileNumber) {
        Loan loan = loansRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber));

        return LoansMapper.toLoansDTO(loan, new LoansDTO());
    }

    @Override
    public boolean updateLoan(LoansDTO loansDTO) {
        Loan loan = loansRepository.findByLoanNumber(loansDTO.getLoanNumber())
                .orElseThrow(() -> new ResourceNotFoundException("Loan", "loanNumber", loansDTO.getLoanNumber()));

        LoansMapper.toLoan(loansDTO, loan);
        loansRepository.save(loan);

        return true;
    }

    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loan loan = loansRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber));

        loansRepository.deleteById(loan.getId());

        return true;
    }

    private Loan createNewLoan(String mobileNumber) {
        Loan newLoan = new Loan();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(HOME_LOAN);
        newLoan.setTotalLoan(NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(NEW_LOAN_LIMIT);

        return newLoan;
    }
}
