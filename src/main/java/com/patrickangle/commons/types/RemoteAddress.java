/*
 * Patrick Angle Commons Library
 * Copyright 2018 Patrick Angle
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *    http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.patrickangle.commons.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.patrickangle.commons.beansbinding.BasicBinding;
import com.patrickangle.commons.beansbinding.BindingGroup;
import com.patrickangle.commons.beansbinding.interfaces.Binding;
import com.patrickangle.commons.beansbinding.swing.boundfields.JSpinnerBoundField;
import com.patrickangle.commons.beansbinding.swing.boundfields.JTextComponentBoundField;
import com.patrickangle.commons.json.JsonableObject;
import com.patrickangle.commons.objectediting.interfaces.CustomObjectEditingComponent;
import com.patrickangle.commons.objectediting.util.ObjectFieldEditorFactory;
import com.patrickangle.commons.observable.interfaces.PropertyChangeObservableBase;
import com.patrickangle.commons.util.Colors;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author patrickangle
 */
public class RemoteAddress extends PropertyChangeObservableBase implements CustomObjectEditingComponent, JsonableObject {
    @JsonProperty protected String address;
    @JsonProperty protected int port;
    
    public RemoteAddress() {
        this.address = "127.0.0.1";
        this.port = 10001;
    }
    
    public RemoteAddress(String address, int port) {
        this.address = address;
        this.port = port;
    }
    
    public RemoteAddress(String value) {
        String[] parts = value.split(":");
        if (parts.length == 2) {
            this.address = parts[0];
            this.port = Integer.parseInt(parts[1]);
        } else {
            throw new IllegalArgumentException("RemoteAddress can only be constructed with two values seperated by a colon.");
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        String oldAddress = this.address;
        this.address = address;
        this.propertyChangeSupport.firePropertyChange("address", oldAddress, this.address);
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        int oldPort = this.port;
        this.port = port;
        this.propertyChangeSupport.firePropertyChange("port", oldPort, this.port);
    }

    @Override
    public String toString() {
        return address + ":" + port;
    }

    @Override
    public ObjectFieldEditorFactory.ComponentReturn customObjectEditingComponent(BindingGroup bindingGroup) {
        JPanel remoteAddressEditor = new JPanel(new GridLayout(1, 2, 6, 0));
        
        JTextField addressField = new JTextField();
        
        Binding addressFieldBinding = new BasicBinding(this, "address", addressField, JTextComponentBoundField.SYNTHETIC_FIELD_TEXT_ON_ACTION_OR_FOCUS_LOST, Binding.UpdateStrategy.READ_WRITE);
        bindingGroup.add(addressFieldBinding);
        
        remoteAddressEditor.add(addressField);
        
        JSpinner portSpinner = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
        
        JSpinner.NumberEditor portSpinnerEditor = new JSpinner.NumberEditor(portSpinner);
        portSpinnerEditor.getFormat().setGroupingUsed(false);
        portSpinner.setEditor(portSpinnerEditor);
        
        Binding portSpinnerBinding = new BasicBinding(this, "port", portSpinner, JSpinnerBoundField.SYNTHETIC_FIELD_VALUE, Binding.UpdateStrategy.READ_WRITE);
        bindingGroup.add(portSpinnerBinding);
 
        remoteAddressEditor.add(portSpinner);
        
        return new ObjectFieldEditorFactory.ComponentReturn(remoteAddressEditor, false);
    }

}
