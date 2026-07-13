package online.theowlery.core;

import online.theowlery.exceptions.*;
import online.theowlery.services.MessageService;
import online.theowlery.types.annotations.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Handler
public class ExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    private final MessageService messageService;

    public ExceptionHandler(MessageService messageService) {
        this.messageService = messageService;
    }

    public void handle(Throwable throwable) {
        if (throwable == null) return;

        Throwable root = unwrap(throwable);

        switch (root) {
            case BeanCreationException exception -> handleContainerException(exception);
            case BeanNotFoundException exception -> handleContainerException(exception);
            case InvalidBeanException exception -> handleContainerException(exception);
            case CircularDependencyException exception -> handleContainerException(exception);
            case BotException exception -> handleBotException(exception);
            default -> handleUnexpectedException(root);
        }
    }

    private Throwable unwrap(Throwable throwable) {
        Throwable current = throwable;

        while (current.getCause() != null && shouldUnwrap(current)) {
            current = current.getCause();
        }

        return current;
    }

    private boolean shouldUnwrap(Throwable throwable) {
        return throwable instanceof RuntimeException
                && !(throwable instanceof BotException);
    }

    private void handleContainerException(BotException exception) {
        logger.error("[ContainerException] {}", exception.getMessage(), exception);
    }

    private void handleBotException(BotException botException) {
        if (botException.isUserFacing()) {
            logger.warn("[UserFacingException {}", botException.getMessage(), botException);
            messageService.sendInteractionError(
                    botException.getInteraction(),
                    botException.getMessageKey(),
                    botException.getLocaleType(),
                    botException.getMessageParams()
            );
            return;
        }

        logger.error("[BotException] {}", botException.getMessage(), botException);
    }

    private void handleUnexpectedException(Throwable throwable) {
        logger.error("[UnexpectedException] ", throwable);
    }
}
