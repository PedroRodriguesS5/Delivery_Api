package com.deliveryproject.exceptions;

public class UniqueConstraintException extends RuntimeException {

    public UniqueConstraintException(Throwable mostSpecificCause){super(mostSpecificCause);}

}
