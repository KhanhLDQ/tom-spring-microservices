package org.tommap.accounts.service;

import org.tommap.accounts.dto.CustomerDTO;

public interface IAccountsService {
    /**
     *
     * @param  customerDTO - CustomerDTO object
     */
    void createAccount(CustomerDTO customerDTO);
}
