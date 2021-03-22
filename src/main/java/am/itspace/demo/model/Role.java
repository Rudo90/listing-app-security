package am.itspace.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Role {

    @JsonProperty("user")
    USER,
    @JsonProperty("admin")
    ADMIN
}
