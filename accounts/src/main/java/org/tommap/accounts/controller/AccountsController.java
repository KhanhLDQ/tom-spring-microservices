package org.tommap.accounts.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tommap.accounts.dto.CustomerDTO;
import org.tommap.accounts.dto.ResponseDTO;
import org.tommap.accounts.service.IAccountsService;

import static org.tommap.accounts.constants.AccountsConstants.MESSAGE_201;
import static org.tommap.accounts.constants.AccountsConstants.STATUS_201;

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
}
