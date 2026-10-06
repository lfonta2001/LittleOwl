package com.lfonta.littleowl.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class DiscordAuthUserData extends MappableAuthUserData {

    private String username;
    private String avatar;
    private int discriminator;
    @JsonProperty("global_name")
    private String globalName;
    @JsonProperty("public_flag")
    private int publicFlags;

    public DiscordAuthUserData(String id) {
        super(id);
    }
}
