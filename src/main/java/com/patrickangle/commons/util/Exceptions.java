/*
 * Patrick Angle Commons Library
 * Copyright 2018 Patrick Angle
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.patrickangle.commons.util;

import java.awt.Font;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author patrickangle
 */
public class Exceptions {
    public static final Thread.UncaughtExceptionHandler GUI_UNCAUGHT_EXCEPTION_HANDLER = (Thread thread, Throwable throwable) -> {
        StringWriter writer = new StringWriter();
        writer.write(Platform.systemDescriptor() + "\n");
        writer.write("Thread: " + thread.getName() + "\n");
        throwable.printStackTrace(new PrintWriter(writer));
        
        String exceptionInformation = writer.toString();
        
        System.err.println(exceptionInformation);
        
        JTextArea area = new JTextArea(20, 80);
        area.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 10));
        
        area.setTabSize(4);
        area.setText(exceptionInformation);
        area.setCaretPosition(0);
        
        String copyOption = "Copy to Clipboard";
        
        JOptionPane pane = new JOptionPane(new JScrollPane(area), JOptionPane.ERROR_MESSAGE, JOptionPane.YES_NO_OPTION, null, new String[]{copyOption, "Close"});
        pane.createDialog(null, "Uncaught Exception").setVisible(true);
        
        if (pane.getValue().equals(copyOption)) {
            area.setSelectionStart(0);
            area.setSelectionEnd(area.getText().length());
            area.copy();
        }
    };
    
    public static void installUncaughtExceptionHandler() {
        Thread.setDefaultUncaughtExceptionHandler(Exceptions.GUI_UNCAUGHT_EXCEPTION_HANDLER);
    }
    
    public static void raiseThrowableToUser(Throwable throwable) {
        Exceptions.GUI_UNCAUGHT_EXCEPTION_HANDLER.uncaughtException(Thread.currentThread(), throwable);
    }
}
