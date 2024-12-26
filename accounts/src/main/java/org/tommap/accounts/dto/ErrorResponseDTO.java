package org.tommap.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Schema(
        name = "ErrorResponse",
        description = "Schema to hold error response information"
)
@Data
@AllArgsConstructor
public class ErrorResponseDTO {
    @Schema(description = "API path invoked by client")
    private String apiPath;
    @Schema(description = "Code representing when the error happened")
    private HttpStatus errorCode;
    @Schema(description = "Message representing when the error happened")
    private String errorMsg;
    @Schema(description = "Time representing when the error happened")
    private LocalDateTime errorTime;
}
