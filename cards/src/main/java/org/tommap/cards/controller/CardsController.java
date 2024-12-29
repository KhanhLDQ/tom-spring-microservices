package org.tommap.cards.controller;

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
import org.tommap.cards.dto.CardsDTO;
import org.tommap.cards.dto.ErrorResponseDTO;
import org.tommap.cards.dto.ResponseDTO;
import org.tommap.cards.service.ICardsService;

import static org.tommap.cards.constants.CardsConstants.MESSAGE_200;
import static org.tommap.cards.constants.CardsConstants.MESSAGE_201;
import static org.tommap.cards.constants.CardsConstants.MESSAGE_417_DELETE;
import static org.tommap.cards.constants.CardsConstants.MESSAGE_417_UPDATE;
import static org.tommap.cards.constants.CardsConstants.STATUS_200;
import static org.tommap.cards.constants.CardsConstants.STATUS_201;
import static org.tommap.cards.constants.CardsConstants.STATUS_417;

@RestController
@RequestMapping(
        path = "/api/v1/cards",
        produces = {MediaType.APPLICATION_JSON_VALUE}
)
@RequiredArgsConstructor
@Validated
@Tag(
        name = "CRUD REST APIs for Cards",
        description = "CREATE, UPDATE, FETCH, AND DELETE cards details"
)
public class CardsController {
    private final ICardsService cardsService;

    @Operation(
            summary = "Create Card REST API",
            description = "REST API to create a new Card inside TomBank"
    )
    @PostMapping("/create")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cards created successfully"),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    })
    public ResponseEntity<ResponseDTO> createCard(
            @RequestParam("mobileNumber") @Pattern(regexp = "^\\d{10}$", message = "mobile number should be 10 digits") String mobileNumber
    ) {
        cardsService.createCard(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(STATUS_201, MESSAGE_201));
    }

    @Operation(
            summary = "Fetch Card Details REST API",
            description = "REST API to fetch Card details based on Mobile Number"
    )
    @GetMapping("/fetch")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cards details fetched successfully"),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    })
    public ResponseEntity<CardsDTO> fetchCardsDetails(
            @RequestParam("mobileNumber") @Pattern(regexp = "^\\d{10}$", message = "mobile number should be 10 digits") String mobileNumber
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cardsService.fetchCard(mobileNumber));
    }

    @Operation(
            summary = "Update Card Details REST API",
            description = "REST API to update Card details inside TomBank"
    )
    @PutMapping("/update")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Card details updated successfully"),
            @ApiResponse(responseCode = "417", description = "Expectation Failed"),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    })
    public ResponseEntity<ResponseDTO> updateCardDetails(
            @RequestBody @Valid CardsDTO cardsDTO
    ) {
        boolean isUpdated = cardsService.updateCard(cardsDTO);

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
            summary = "Delete Card Details REST API",
            description = "REST API to delete card details based on Mobile Number"
    )
    @DeleteMapping("/delete")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Card details deleted successfully"),
            @ApiResponse(responseCode = "417", description = "Expectation Failed"),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    })
    public ResponseEntity<ResponseDTO> deleteCardDetails(
            @RequestParam("mobileNumber") @Pattern(regexp = "^\\d{10}$", message = "mobile number should be 10 digits") String mobileNumber
    ) {
        boolean isDeleted = cardsService.deleteCard(mobileNumber);

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
