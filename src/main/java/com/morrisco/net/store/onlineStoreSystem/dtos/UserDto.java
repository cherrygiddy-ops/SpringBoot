package com.morrisco.net.store.onlineStoreSystem.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class UserDto {
    /**
     * @JsonIgnore for excluding a field
     * @JsonProperty for renaming a field
     * @JsonInclude for excluding null or empty fields
     * @JsonFormatt for formatting values especially date or numbers
     */
    @JsonIgnore
    private  Integer id;
    @JsonProperty("user_name")
    private String name;
    private String email;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String username;

    @JsonFormat(pattern = "YYYY-MM-DD HH:MM:SS")
    private LocalDateTime createdAt;

}
