package com.dunno.bankingsystem;

public interface UseCase<I, O> {
    O execute(I input);
}
