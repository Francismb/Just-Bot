package org.agile.loader.applet;

import org.agile.loader.SessionLoader;
import org.agile.reflection.storage.ClassStorage;

import java.applet.Applet;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * User: Francis
 * Date: 26/03/13
 * Time: 8:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class CustomAppletLoader extends Applet {

    private Class clientClass;
    public Object clientInstance;
    private final SessionLoader session;

    private final CustomAppletContext ctx;

    public CustomAppletLoader(final SessionLoader session) {
        try {
            System.out.println("^ - Loading Applet");
            clientClass = ClassStorage.getClass("client");
        } catch (final Exception e) {
            e.printStackTrace();
        }
        this.session = session;
        this.ctx = new CustomAppletContext(session.getWorldUrl(), session.getWorldUrl() + "/" + session.gamePack, session.parameters);
        this.ctx.setApplet(this);
    }

    private void invokeMethod(Object[] arg0, Class[] arg2, String arg3) {
        try {
            final Method method = clientClass.getMethod(arg3, arg2);
            method.invoke(clientInstance, arg0);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public final synchronized void init() {
        if (clientClass != null) {
            try {
                System.out.println("^ - Initialized Applet");
                clientInstance = clientClass.getConstructor((Class[]) null).newInstance((Object[]) null);
                invokeMethod(new Object[]{ctx}, new Class[]{java.applet.AppletStub.class}, "setStub");
                invokeMethod(null, null, "init");
            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public final void update(Graphics arg0) {
        if (clientInstance != null) {
            invokeMethod(new Object[]{arg0}, new Class[]{Graphics.class}, "update");
        }
    }

    public final void stop() {
        if (clientInstance != null) {
            invokeMethod(null, null, "stop");
        }
    }

    public final void start() {
        if (clientInstance != null) {
            System.out.println("^ - Started Applet");
            invokeMethod(null, null, "start");
        }
    }

    public final void paint(Graphics arg0) {
        if (clientInstance != null) {
            invokeMethod(new Object[]{arg0}, new Class[]{Graphics.class}, "paint");
        }
    }

    public final void destroy() {
        if (clientInstance != null) {
            invokeMethod(null, null, "destroy");
        }
    }

}
