package com.tcs.edu;

import static com.tcs.edu.enumeration.MessageOrder.ASC;
import static com.tcs.edu.enumeration.MessageOrder.DESC;
import static com.tcs.edu.enumeration.Severity.*;
import static com.tcs.edu.service.MessageService.process;

class Application {
    public static void main(String[] args) {
        process(REGULAR, DESC, "Hello world! 1", "Hello world! 2", "Hello world! 3", "Hello world! 4");
    }
}