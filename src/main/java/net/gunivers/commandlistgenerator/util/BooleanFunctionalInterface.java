package net.gunivers.commandlistgenerator.util;

@FunctionalInterface
public interface BooleanFunctionalInterface<T> {

	public boolean invoke(T e);
}
