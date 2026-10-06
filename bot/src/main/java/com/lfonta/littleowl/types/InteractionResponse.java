package com.lfonta.littleowl.types;

import lombok.Getter;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import com.lfonta.littleowl.types.enums.ResponseState;

import java.time.Instant;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;

public class InteractionResponse {

    @Getter
    private final SlashCommandInteraction interaction;

    @Getter
    private final Instant createdAt = Instant.now();

    @Getter
    private final AtomicReference<ResponseState> state = new AtomicReference<>(ResponseState.PENDING);

    private final CompletableFuture<InteractionHook> hookFuture = new CompletableFuture<>();
    public InteractionResponse(SlashCommandInteraction interaction) {
        this.interaction = interaction;
    }

    public boolean markDeferred() {
        return state.compareAndSet(ResponseState.PENDING, ResponseState.DEFERRED);
    }

    public boolean markReplied() {
        return state.compareAndSet(ResponseState.PENDING, ResponseState.REPLIED);
    }

    public void completeHook(InteractionHook hook) {
        hookFuture.complete(hook);
    }

    public void fail(Throwable throwable) {
        state.set(ResponseState.FAILED);
        hookFuture.completeExceptionally(throwable);
    }

    public CompletableFuture<InteractionHook> hookFuture() {
        return hookFuture;
    }
}
