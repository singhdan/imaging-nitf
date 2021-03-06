/**
 * Copyright (c) Codice Foundation
 *
 * This is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser
 * General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details. A copy of the GNU Lesser General Public License
 * is distributed along with this program and can be found at
 * <http://www.gnu.org/licenses/lgpl.html>.
 *
 **/
package org.codice.imaging.nitf.core.tre;

import java.text.ParseException;
import java.util.List;

/**
 A group of values within a TreEntry.
 */
public interface TreGroup {
    /**
     The entries in the TRE entry group.

     @return the list of entries within the group.
     */
    List<TreEntry> getEntries();

    /**
     Add an entry to the group.

     @param entry the entry to add
     */
    void add(TreEntry entry);

    /**
     Add multiple entries to the group.

     @param group the group containing the entry or entries to add
     */
    void addAll(TreGroup group);

    /**
     Set the list of entries.

     @param treEntries the new list of entries.
     */
    void setEntries(List<TreEntry> treEntries);

    /**
     Get the entry for a specific tag.

     @param tagName the name (tag) of the field to look up.
     @return the entry corresponding to the tag name.
     @throws ParseException when the tag is not found
     */
    TreEntry getEntry(String tagName) throws ParseException;

    /**
     Get the field value for a specific tag.

     @param tagName the name (tag) of the field to look up.
     @return the field value corresponding to the tag name.
     @throws ParseException when the tag is not found
     */
    String getFieldValue(String tagName) throws ParseException;


    /**
     Get the field value for a specific tag in integer format.

     @param tagName the name (tag) of the field to look up.
     @return the field value corresponding to the tag name, as an integer.
     @throws ParseException when the tag is not found or the value cannot be converted to integer format.
     */
    int getIntValue(String tagName) throws ParseException;

    /**
     Debug dump of the entries.
     */
    void dump();
}
