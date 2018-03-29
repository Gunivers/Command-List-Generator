package net.mgdk.plugin.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public abstract class Plugin
{
    private boolean enabled = true;

    /**
     * @return The name of plugin
     */
    public abstract String name();

    /**
     * @return The creator's of plugin
     */
    public abstract String[] createdBy();

    /**
     * @return The version of plugin
     */
    public abstract double version();

    /**
     * @return To the description of plugin
     */
    public abstract String[] description();

    /**
     * @return The update URL
     */
    public abstract String updateURL();

    /**
     * @return Path to the image of plugin
     */
    public abstract String image();

    /**
     * @return If the plugin is enabled
     */
    public boolean isEnabled()
    {
        return enabled;
    }

    /**
     * @param enabled to set the set status of plugin
     */
    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }

    /**
     * Java Annotation class
     */

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface Start
    {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface Quit
    {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface Load
    {
    }
}
