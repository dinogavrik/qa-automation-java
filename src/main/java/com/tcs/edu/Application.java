package com.tcs.edu;

import static com.tcs.edu.decorator.Severity.*;
import static com.tcs.edu.service.MessageService.process;

class Application {
    public static void main(String[] args) {
        process(MINOR,"Hello world!");
        process(REGULAR,"Hello world!");
        process(REGULAR,"Hello world!");
        process(MINOR,"Hello world!");
        process(MINOR,"Hello world!");
        process(MAJOR,"Hello world!");
    }
}