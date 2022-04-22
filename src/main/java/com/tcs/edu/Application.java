package com.tcs.edu;

import static com.tcs.edu.decorator.Severity.*;
import static com.tcs.edu.service.MessageService.process;

class Application {
    public static void main(String[] args) {
        process(MINOR,"Hello world!", "Hi!", "Aloha");
        process(REGULAR,"Hello world!", null);
        process(REGULAR,null);
        process(MINOR,"Hello world!", null, "Ola", "Hola!");
        process(MAJOR,"Hello world!", "Hallo", null, "Guten Tag");
        process(REGULAR, null, null);
    }
}