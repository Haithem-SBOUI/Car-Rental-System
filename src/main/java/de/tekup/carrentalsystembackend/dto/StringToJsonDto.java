package de.tekup.carrentalsystembackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class StringToJsonDto {
//    @JsonProperty("blabla")
    private String message;


}
