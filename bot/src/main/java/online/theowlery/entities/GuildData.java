package online.theowlery.entities;

import lombok.Builder;

@Builder
public record GuildData(
        String id,
        String name) { }
