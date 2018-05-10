package net.gunivers.commandparser.selector;

@FunctionalInterface
public interface SelectorMatcher
{
	public boolean matches(String value);
}
