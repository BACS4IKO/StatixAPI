package ru.statix.api.base.utility.query;

public interface ThrowableResponseHandler<R, O, T extends Throwable> {
   R handleResponse(O var1) throws T;
}
