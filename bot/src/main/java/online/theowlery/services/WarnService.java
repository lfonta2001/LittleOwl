package online.theowlery.services;

import online.theowlery.types.annotations.Service;
import online.theowlery.types.interfaces.SharedContext;

import java.util.HashMap;
import java.util.Map;

@Service
public class WarnService {

    private final MessageService messageService;

    public WarnService(MessageService messageService) {
        this.messageService = messageService;
    }

    public <C extends SharedContext> void middlewareWarn(Class<?> middleware, C context, String message) {
        Map<String, Object> values = new HashMap<>();
        values.put("MIDDLEWARE", middleware.getSimpleName());
        values.put("REASON", message);

        messageService.sendWarning(context, "warnings.middleware", values);
    }

    public <C extends SharedContext> void middlewareWarn(Class<?> middleware, C context) {
        Map<String, Object> values = new HashMap<>();
        values.put("MIDDLEWARE", middleware.getSimpleName());

        messageService.sendWarning(context, "warnings.middleware", values);
    }
}
