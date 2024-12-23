package org.tommap.accounts.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tommap.accounts.dto.CustomerDTO;
import org.tommap.accounts.entity.Account;
import org.tommap.accounts.entity.Customer;
import org.tommap.accounts.exception.CustomerAlreadyExistsException;
import org.tommap.accounts.repository.AccountsRepository;
import org.tommap.accounts.repository.CustomerRepository;
import org.tommap.accounts.service.IAccountsService;

import static org.tommap.accounts.constants.AccountsConstants.ADDRESS;
import static org.tommap.accounts.constants.AccountsConstants.SAVINGS;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import static org.tommap.accounts.mapper.CustomerMapper.toCustomer;

@Service
@RequiredArgsConstructor
public class AccountsServiceImpl implements IAccountsService {
    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDTO customerDTO) {
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDTO.getMobileNumber());
        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException(
                    "Customer already registered with given mobile number: " + customerDTO.getMobileNumber()
            );
        }

        Customer customer = toCustomer(customerDTO, new Customer());
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Anonymous");
        Customer savedCustomer = customerRepository.save(customer);

        accountsRepository.save(createAccountEntity(savedCustomer));
    }

    private Account createAccountEntity(Customer customer) {
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        Account newAccount = new Account();
        newAccount.setId(randomAccNumber);
        newAccount.setCustomerId(customer.getId());
        newAccount.setAccountType(SAVINGS);
        newAccount.setBranchAddress(ADDRESS);
        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setCreatedBy("Anonymous");

        return newAccount;
    }
}
