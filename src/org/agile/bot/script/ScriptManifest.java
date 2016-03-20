package org.agile.bot.script;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ScriptManifest {

	public String name() default "Unknown";
    public double version() default 1.0;
    public String author() default "Unknown" ;
    public String type() default "Unknown";

}
