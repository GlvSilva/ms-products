package com.uol.compasso.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorObjectReturn {

    private String status_code;
    private String message;
}
