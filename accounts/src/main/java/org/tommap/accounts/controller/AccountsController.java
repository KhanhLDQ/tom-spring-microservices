package org.tommap.accounts.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tommap.accounts.dto.CustomerDTO;
import org.tommap.accounts.dto.ErrorResponseDTO;
import org.tommap.accounts.dto.ResponseDTO;
import org.tommap.accounts.service.IAccountsService;

import static org.tommap.accounts.constants.AccountsConstants.MESSAGE_200;
import static org.tommap.accounts.constants.AccountsConstants.MESSAGE_201;
import static org.tommap.accounts.constants.AccountsConstants.MESSAGE_417_DELETE;
import static org.tommap.accounts.constants.AccountsConstants.MESSAGE_417_UPDATE;
import static org.tommap.accounts.constants.AccountsConstants.STATUS_200;
import static org.tommap.accounts.constants.AccountsConstants.STATUS_201;
import static org.tommap.accounts.constants.AccountsConstants.STATUS_417;

@RestController
@RequestMapping(
        path = "/api/v1/accounts",
        produces = {MediaType.APPLICATION_JSON_VALUE}
)
@RequiredArgsConstructor
@Validated
@Tag(
        name = "CRUD REST APIs for Accounts",
        description = "CREATE, UPDATE, FETCH, AND DELETE account details"
)
public class AccountsController {
    private final IAccountsService accountsService;

    @Operation(
            summary = "Create Account REST API",
            description = "REST API to create a new Customer & Account inside TomBank"
    )
    @PostMapping("/create")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Account created successfully"),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    })
    public ResponseEntity<ResponseDTO> createAccount(
            @RequestBody @Valid CustomerDTO customerDTO
    ) {
        accountsService.createAccount(customerDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(STATUS_201, MESSAGE_201));
    }

    @Operation(
            summary = "Fetch Account Details REST API",
            description = "REST API to fetch Customer & Account details based on Mobile Number"
    )
    @GetMapping("/fetch")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Account details fetched successfully"),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    })
    public ResponseEntity<CustomerDTO> fetchAccountDetails(
            @RequestParam("mobileNumber") @Pattern(regexp = "^\\d{10}$", message = "mobile number should be 10 digits") String mobileNumber
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountsService.fetchAccount(mobileNumber));
    }

    @Operation(
            summary = "Update Account Details REST API",
            description = "REST API to update Customer & Account details inside TomBank"
    )
    @PutMapping("/update")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Account details updated successfully"),
            @ApiResponse(responseCode = "417", description = "Expectation Failed"),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    })
    public ResponseEntity<ResponseDTO> updateAccountDetails(
            @RequestBody @Valid CustomerDTO customerDTO
    ) {
        boolean isUpdated = accountsService.updateAccount(customerDTO);

        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(STATUS_200, MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDTO(STATUS_417, MESSAGE_417_UPDATE));
        }
    }

    @Operation(
            summary = "Delete Account Details REST API",
            description = "REST API to delete Customer & Account details based on Mobile Number"
    )
    @DeleteMapping("/delete")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Account details deleted successfully"),
            @ApiResponse(responseCode = "417", description = "Expectation Failed"),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    })
    public ResponseEntity<ResponseDTO> deleteAccount(
            @RequestParam("mobileNumber") @Pattern(regexp = "^\\d{10}$", message = "mobile number should be 10 digits") String mobileNumber
    ) {
        boolean isDeleted = accountsService.deleteAccount(mobileNumber);

        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(STATUS_200, MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDTO(STATUS_417, MESSAGE_417_DELETE));
        }
    }
}
