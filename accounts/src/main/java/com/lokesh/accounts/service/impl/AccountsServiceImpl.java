package com.lokesh.accounts.service.impl;

import com.lokesh.accounts.constant.AccountsConstants;
import com.lokesh.accounts.dto.CustomerDto;
import com.lokesh.accounts.entity.Accounts;
import com.lokesh.accounts.entity.Customer;
import com.lokesh.accounts.exception.CustomerAlreadyExistsException;
import com.lokesh.accounts.mapper.CustomerMapper;
import com.lokesh.accounts.repository.AccountsRepository;
import com.lokesh.accounts.repository.CustomerRepository;
import com.lokesh.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private CustomerRepository customerRepository;
    private AccountsRepository accountsRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Optional<Customer> customerOptional = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if(customerOptional.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already exists with Mobile# "+customerDto.getMobileNumber());
        }
        Customer customer = new Customer();
        CustomerMapper.mapToCustomer(customerDto, customer);
        customerRepository.save(customer);
        Accounts accounts = createNewAccountFromCustomer(customer);
        accountsRepository.save(accounts);
    }

    private Accounts createNewAccountFromCustomer(Customer customer) {
        Accounts accounts = new Accounts();
        accounts.setCustomerId(customer.getCustomerId());
        accounts.setAccountNumber(100000000 + (long) (Math.random() * (999999999 - 100000000)));
        accounts.setAccountType(AccountsConstants.SAVINGS);
        accounts.setBranchAddress(AccountsConstants.ADDRESS);
        return accounts;
    }
}
