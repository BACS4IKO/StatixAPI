package ru.statix.api.base.utility.query;

public interface ResponseHandler<R, O> {
   R handleResponse(O var1);
}
