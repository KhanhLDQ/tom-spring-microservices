package org.tommap.accounts.service;

import org.tommap.accounts.dto.CustomerDTO;

public interface IAccountsService {
    /**
     *
     * @param  customerDTO - CustomerDTO object
     */
    void createAccount(CustomerDTO customerDTO);

    /**
     *
     * @param mobileNumber - String
     * @return Accounts Details based on a given mobileNumber
     */
    CustomerDTO fetchAccount(String mobileNumber);

    /**
     *
     * @param customerDTO - CustomerDTO object
     * @return boolean indicating if the update of Account details is successful or not
     */
    boolean updateAccount(CustomerDTO customerDTO);

    /**
     *
     * @param mobileNumber -  String
     * @return boolean indicating if the delete of Account is successful or not
     */
    boolean deleteAccount(String mobileNumber);
}
