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
package com.patrickangle.commons;

/**
 *
 * @author patrickangle
 */
public class Commons {
    public static final String APP_NAME = "PatrickAngleCommons";
    public static final int APP_VERSION_MAJOR = 0;
    public static final int APP_VERSION_MINOR = 0;
    public static final int APP_VERSION_MICRO = 1;
    public static final String APP_VERSION_BUILD = "---";
    public static final String APP_VERSION_SPECIAL = "SNAPSHOT";
    public static final String APP_VERSION = APP_VERSION_MAJOR + "." + APP_VERSION_MINOR + "." + APP_VERSION_MICRO + (APP_VERSION_SPECIAL.equals("") ? "" : " [" + APP_VERSION_SPECIAL + "]") + " Build " + APP_VERSION_BUILD;
}
