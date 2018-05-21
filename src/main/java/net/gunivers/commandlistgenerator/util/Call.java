package net.gunivers.commandlistgenerator.util;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(METHOD)
/**
 * @author Oromis
 * The method with this annotation can be run by the main function
 */
public @interface Call
{
}
