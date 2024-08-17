package io.nerdbyteslns.productcatalogservice.models.reponses;

import io.nerdbyteslns.productcatalogservice.models.ErrorDetails;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class ApiResponse<T> {
    private String message;
    private Boolean success;
    private int statusCode;
    private T data;
    private List<ErrorDetails> errors;
}
