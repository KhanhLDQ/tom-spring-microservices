package org.tommap.accounts.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tommap.accounts.dto.CustomerDTO;
import org.tommap.accounts.dto.ResponseDTO;
import org.tommap.accounts.service.IAccountsService;

import static org.tommap.accounts.constants.AccountsConstants.MESSAGE_200;
import static org.tommap.accounts.constants.AccountsConstants.MESSAGE_201;
import static org.tommap.accounts.constants.AccountsConstants.MESSAGE_500;
import static org.tommap.accounts.constants.AccountsConstants.STATUS_201;
import static org.tommap.accounts.constants.AccountsConstants.STATUS_200;
import static org.tommap.accounts.constants.AccountsConstants.STATUS_500;

@RestController
@RequestMapping(
        path = "/api/v1/accounts",
        produces = {MediaType.APPLICATION_JSON_VALUE}
)
@RequiredArgsConstructor
public class AccountsController {
    private final IAccountsService accountsService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createAccount(
            @RequestBody CustomerDTO customerDTO
    ) {
        accountsService.createAccount(customerDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(STATUS_201, MESSAGE_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity<CustomerDTO> fetchAccountDetails(
            @RequestParam("mobileNumber") String mobileNumber
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountsService.fetchAccount(mobileNumber));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateAccountDetails(
            @RequestBody CustomerDTO customerDTO
    ) {
        boolean isUpdated = accountsService.updateAccount(customerDTO);

        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(STATUS_200, MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(STATUS_500, MESSAGE_500));
        }
    }
}
