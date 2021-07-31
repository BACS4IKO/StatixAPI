package ru.statix.api.base.interfaces;

public interface ResponseHandler<R, O, T extends Throwable> {

    R handleResponse(O o) throws T;
}
