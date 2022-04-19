package com.tcs.edu.decorator;

import static com.tcs.edu.decorator.Severity.*;

/**
 * Class for calculating the level of importance
 *
 * @author Gavrikova Irina
 */
public class SeverityLevelDecorator {
    /**
     * Method for calculating and decorating a message with a level of importance
     *
     * @param severity importance level
     * @return the importance level of the displayed message
     */
    public static String mapSeverity(Severity severity) {
        //String SeverityString = null;
        switch (severity) {
            case MAJOR:
                return MAJOR.getLevel();
            case MINOR:
                return MINOR.getLevel();
            case REGULAR:
                return REGULAR.getLevel();
            default:
                return "Уровень не найден";
        }
    }
}
