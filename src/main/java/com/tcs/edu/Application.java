package com.tcs.edu;

import static com.tcs.edu.decorator.Severity.*;
import static com.tcs.edu.service.MessageService.process;

class Application {
    public static void main(String[] args) {
        process(MINOR,"Hello world!", "Hi!", "Aloha");
        process(REGULAR,"Hello world!");
        process(REGULAR,"Hello world!");
        process(MINOR,"Hello world!", "Ola", "Ciao", "Hola!");
        process(MAJOR,"Hello world!", "Hallo", "Salut", "Guten Tag");
        process(REGULAR,"Hello world!");
    }
}