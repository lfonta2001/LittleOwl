package online.theowlery.types;

import online.theowlery.types.enums.MiddlewareResult;

public interface IMiddleware {
    public MiddlewareResult run();
}
