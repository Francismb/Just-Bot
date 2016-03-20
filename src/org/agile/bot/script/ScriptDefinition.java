package org.agile.bot.script;

import java.net.URL;

public final class ScriptDefinition {
	private String name;
	private double version;
	private String author;
	private String type;
	
	private URL source;
	private String className;
	
	public ScriptDefinition(ScriptManifest info, URL source, String className) {
		name = info.name();
		type = info.type();
		version = info.version();
		author = info.author();
		this.source = source;
		this.className = className;
	}
	
	public String getName() {
		return name;
	}
	
	public double getVersion() {
		return version;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public String getType() {
		return type;
	}
	
	public URL getSource() {
		return source;
	}
	
	public String getClassName() {
		return className;
	}
}
