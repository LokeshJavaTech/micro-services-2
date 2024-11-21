package com.lokesh.accounts.service;

import com.lokesh.accounts.dto.CustomerDto;

public interface IAccountsService {
    void createAccount(CustomerDto customerDto);
}
