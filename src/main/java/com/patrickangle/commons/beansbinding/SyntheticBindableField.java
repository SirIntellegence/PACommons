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
package com.patrickangle.commons.beansbinding;

import com.patrickangle.commons.beansbinding.interfaces.BindableField;
import com.patrickangle.commons.util.Classes;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Patrick Angle
 */
public class SyntheticBindableField<C> implements BindableField<C> {
    private Class<C> containingClass;
    private String fieldName;
    private String beanFieldName;
    private Class fieldClass;
    
    private Method getter;
    private Method setter;
    
    private List<Object> additionalGetterParams;
    private List<Object> additionalSetterParams;
    
    public SyntheticBindableField(Class<C> containingClass, String fieldName, List<Object> additionalGetterParams, List<Object> additionalSetterParams) {
        if (!fieldName.contains("$") || fieldName.startsWith("$")) {
            throw new RuntimeException("Synthetic field propertie names must contain, but not start with, '$'. '" + fieldName + "' is not a valid synthetic field name.");
        }
        
        this.containingClass = containingClass;
        this.fieldName = fieldName;
        this.beanFieldName = fieldName.split("\\$", 2)[0];
        this.additionalGetterParams = additionalGetterParams;
        this.additionalSetterParams = additionalSetterParams;
        
        String getterSignature = methodSignatureForFieldName("get", this.beanFieldName);
        String isSignature = methodSignatureForFieldName("is", this.beanFieldName);
        String setterSignature = methodSignatureForFieldName("set", this.beanFieldName);

        try {
            this.getter = this.containingClass.getMethod(getterSignature, additionalParamClasses(additionalGetterParams).toArray(new Class[0]));
            if (!Modifier.isPublic(this.getter.getModifiers())) {
                this.getter = null;
            }
        } catch (NoSuchMethodException | SecurityException ex) {
        }

        if (this.getter == null) {
            try {
                this.getter = this.containingClass.getMethod(isSignature, additionalParamClasses(additionalGetterParams).toArray(new Class[0]));
                if (!Modifier.isPublic(this.getter.getModifiers())) {
                    this.getter = null;
                }
            } catch (NoSuchMethodException | SecurityException ex) {
            }
        }

        if (this.getter != null) {
            this.fieldClass = this.getter.getReturnType();
            try {
                List<Class> setterParams = new ArrayList<>(Arrays.asList(this.fieldClass));
                setterParams.addAll(additionalParamClasses(additionalSetterParams));

                this.setter = this.containingClass.getMethod(setterSignature, setterParams.toArray(new Class[0]));
                if (!Modifier.isPublic(this.setter.getModifiers())) {
                    this.setter = null;
                }
            } catch (NoSuchMethodException | SecurityException ex) {
            }
        } else {
            throw new RuntimeException("BindableField requires a get method for field '" + fieldName + "' with the signature '" + getterSignature + "' or, for booleans, '" + isSignature +"'");
        }
    }
    
    public SyntheticBindableField(Class<C> containingClass, String fieldName) {
        this(containingClass, fieldName, Collections.EMPTY_LIST, Collections.EMPTY_LIST);
    }

    @Override
    public Class<C> getContainingClass() {
        return this.containingClass;
    }

    @Override
    public String getFieldName() {
        return this.fieldName;
    }
    
    public String getBeanFieldName() {
        return this.beanFieldName;
    }

    @Override
    public Class getFieldClass() {
        return this.fieldClass;
    }

    @Override
    public boolean isReadable() {
        return this.getter != null;
    }
    
    @Override
    public boolean isWriteable() {
        return this.setter != null;
    }

    @Override
    public Object getValue(C object) {
        if (this.getter != null) {
            try {
                return this.getter.invoke(object, additionalGetterParams.toArray());
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                throw new RuntimeException("Getter could not be invoked", ex);
            }
        } else {
            throw new RuntimeException("BindableField.getValue requires that a getter exist on the underlying class to access the field");
        }
    }
    
    @Override
    public void setValue(C object, Object value) {
        if (this.setter != null) {
            try {
                List<Object> setterParams = new ArrayList<>(Arrays.asList(value));
                setterParams.addAll(additionalSetterParams);
                
                this.setter.invoke(object, setterParams.toArray());
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                throw new RuntimeException("Setter could not be invoked", ex);
            }
        } else {
            throw new RuntimeException("BindableField.setValue requires that a setter exist on the underlying class to mutate the field");
        }
    }
    
    private static String methodSignatureForFieldName(String verb, String fieldName) {
        return verb + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }
    
    private static List<Class> additionalParamClasses(List<Object> additionalGetterParams) {
        return additionalGetterParams.stream().map(t -> Classes.primitaveClassFor(t)).collect(Collectors.toList());
    }
    
    @Override
    public String toString() {
        return containingClass.getName() + "." + fieldName + " [ " + ((getter != null) ? getter.getName() + "(" + Arrays.asList(getter.getParameterTypes()).stream().map((t) -> {
            return t.getName();
        }).collect(Collectors.toList()).toString() + ") -> " + getter.getReturnType().getName() + "" : "-") + " | " + ((setter != null) ? setter.getName() + "(" + Arrays.asList(setter.getParameterTypes()).stream().map((t) -> {
            return t.getName();
        }).collect(Collectors.toList()).toString() + ")" : "-") + " ]";
    }
}
