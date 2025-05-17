package org.tommap.loans.controller;

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
import org.tommap.loans.dto.ErrorResponseDTO;
import org.tommap.loans.dto.LoansDTO;
import org.tommap.loans.dto.ResponseDTO;
import org.tommap.loans.service.ILoansService;

import static org.tommap.loans.constants.LoansConstants.MESSAGE_200;
import static org.tommap.loans.constants.LoansConstants.MESSAGE_201;
import static org.tommap.loans.constants.LoansConstants.MESSAGE_417_DELETE;
import static org.tommap.loans.constants.LoansConstants.MESSAGE_417_UPDATE;
import static org.tommap.loans.constants.LoansConstants.STATUS_200;
import static org.tommap.loans.constants.LoansConstants.STATUS_201;
import static org.tommap.loans.constants.LoansConstants.STATUS_417;

@RestController
@RequestMapping(
        path = "/api/v1/loans",
        produces = {MediaType.APPLICATION_JSON_VALUE}
)
@RequiredArgsConstructor
@Validated
@Tag(
        name = "CRUD REST APIs for Loans",
        description = "CREATE, UPDATE, FETCH, AND DELETE loans details"
)
public class LoansController {
    private final ILoansService loansService;

    @Operation(
            summary = "Create Loan REST API",
            description = "REST API to create a new Loan inside TomBank"
    )
    @PostMapping("/create")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Loans created successfully"),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    })
    public ResponseEntity<ResponseDTO> createLoan(
            @RequestParam("mobileNumber") @Pattern(regexp = "^\\d{10}$", message = "mobile number should be 10 digits") String mobileNumber
    ) {
        loansService.createLoan(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(STATUS_201, MESSAGE_201));
    }

    @Operation(
            summary = "Fetch Loan Details REST API",
            description = "REST API to fetch Loan details based on Mobile Number"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Loan details fetched successfully"),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    })
    @GetMapping("/fetch")
    public ResponseEntity<LoansDTO> fetchLoansDetails(
            @RequestParam("mobileNumber") @Pattern(regexp = "^\\d{10}$", message = "mobile number should be 10 digits") String mobileNumber
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(loansService.fetchLoan(mobileNumber));
    }

    @Operation(
            summary = "Update Loan Details REST API",
            description = "REST API to update Loan details inside TomBank"
    )
    @PutMapping("/update")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Loan details updated successfully"),
            @ApiResponse(responseCode = "417", description = "Expectation Failed"),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    })
    public ResponseEntity<ResponseDTO> updateLoansDetails(
            @RequestBody @Valid LoansDTO loansDTO
    ) {
        boolean isUpdated = loansService.updateLoan(loansDTO);

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
            summary = "Delete Loan Details REST API",
            description = "REST API to delete Loan details based on Mobile Number"
    )
    @DeleteMapping("/delete")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Loan details deleted successfully"),
            @ApiResponse(responseCode = "417", description = "Expectation Failed"),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    })
    public ResponseEntity<ResponseDTO> deleteLoansDetails(
            @RequestParam("mobileNumber") @Pattern(regexp = "^\\d{10}$", message = "mobile number should be 10 digits") String mobileNumber
    ) {
        boolean isDeleted = loansService.deleteLoan(mobileNumber);

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
