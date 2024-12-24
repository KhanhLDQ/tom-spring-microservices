package org.tommap.accounts.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tommap.accounts.dto.AccountsDTO;
import org.tommap.accounts.dto.CustomerDTO;
import org.tommap.accounts.entity.Account;
import org.tommap.accounts.entity.Customer;
import org.tommap.accounts.exception.CustomerAlreadyExistsException;
import org.tommap.accounts.exception.ResourceNotFoundException;
import org.tommap.accounts.mapper.AccountsMapper;
import org.tommap.accounts.mapper.CustomerMapper;
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
        Customer savedCustomer = customerRepository.save(customer);

        accountsRepository.save(createAccountEntity(savedCustomer));
    }

    @Override
    public CustomerDTO fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

        Account account = accountsRepository.findByCustomerId(customer.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Account", "customerId", customer.getId().toString()));

        CustomerDTO customerDTO = CustomerMapper.toCustomerDTO(customer, new CustomerDTO());
        customerDTO.setAccountsDTO(AccountsMapper.toAccountsDTO(account, new AccountsDTO()));

        return customerDTO;
    }

    @Override
    public boolean updateAccount(CustomerDTO customerDTO) {
        boolean isUpdated = false;

        AccountsDTO accountsDTO = customerDTO.getAccountsDTO();
        if (null != accountsDTO) {
            Account account = accountsRepository.findById(accountsDTO.getAccountNumber())
                    .orElseThrow(() -> new ResourceNotFoundException("Account", "accountNumber", accountsDTO.getAccountNumber().toString()));

            AccountsMapper.toAccount(accountsDTO, account);
            account = accountsRepository.save(account);

            Long customerId = account.getCustomerId();
            Customer customer = customerRepository.findById(customerId)
                    .orElseThrow(() -> new ResourceNotFoundException("Customer", "customerId", customerId.toString()));
            CustomerMapper.toCustomer(customerDTO, customer);
            customerRepository.save(customer);

            isUpdated = true;
        }

        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

        accountsRepository.deleteByCustomerId(customer.getId());
        customerRepository.deleteById(customer.getId());

        return true;
    }

    private Account createAccountEntity(Customer customer) {
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        Account newAccount = new Account();
        newAccount.setId(randomAccNumber);
        newAccount.setCustomerId(customer.getId());
        newAccount.setAccountType(SAVINGS);
        newAccount.setBranchAddress(ADDRESS);

        return newAccount;
    }
}
