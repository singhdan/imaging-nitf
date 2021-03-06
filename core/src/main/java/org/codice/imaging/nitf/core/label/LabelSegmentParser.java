/*
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
 */
package org.codice.imaging.nitf.core.label;

import java.text.ParseException;
import org.codice.imaging.nitf.core.RGBColour;
import org.codice.imaging.nitf.core.common.AbstractSegmentParser;
import org.codice.imaging.nitf.core.common.NitfParseStrategy;
import org.codice.imaging.nitf.core.common.NitfReader;
import static org.codice.imaging.nitf.core.label.LabelConstants.LA;
import static org.codice.imaging.nitf.core.label.LabelConstants.LALVL_LENGTH;
import static org.codice.imaging.nitf.core.label.LabelConstants.LCH_LENGTH;
import static org.codice.imaging.nitf.core.label.LabelConstants.LCW_LENGTH;
import static org.codice.imaging.nitf.core.label.LabelConstants.LDLVL_LENGTH;
import static org.codice.imaging.nitf.core.label.LabelConstants.LFS_LENGTH;
import static org.codice.imaging.nitf.core.label.LabelConstants.LID_LENGTH;
import static org.codice.imaging.nitf.core.label.LabelConstants.LLOC_HALF_LENGTH;
import static org.codice.imaging.nitf.core.label.LabelConstants.LXSHDL_LENGTH;
import static org.codice.imaging.nitf.core.label.LabelConstants.LXSOFL_LENGTH;
import org.codice.imaging.nitf.core.security.SecurityMetadataParser;
import org.codice.imaging.nitf.core.tre.TreCollection;
import org.codice.imaging.nitf.core.tre.TreSource;

/**
    Parser for a label segment in a NITF 2.0 file.
*/
public class LabelSegmentParser extends AbstractSegmentParser {

    private int labelExtendedSubheaderLength = 0;

    private LabelSegmentImpl segment = null;

    /**
     * default constructor.
     */
    public LabelSegmentParser() {
    }

    /**
     * Parse LabelSegment from the specified reader, using the specified parseStrategy.
     *
     * The reader provides the data. The parse strategy selects which data to store.
     *
     * @param nitfReader the reader to use to get the data.
     * @param parseStrategy the parsing strategy to use to process the data.
     * @return the parsed header.
     * @throws ParseException on parse failure.
     *
     */
    public final LabelSegment parse(final NitfReader nitfReader, final NitfParseStrategy parseStrategy) throws ParseException {

        reader = nitfReader;
        parsingStrategy = parseStrategy;
        segment = new LabelSegmentImpl();

        readLA();
        readLID();
        segment.setSecurityMetadata(new SecurityMetadataParser().parseSecurityMetadata(reader));
        readENCRYP();
        readLFS();
        readLCW();
        readLCH();
        readLDLVL();
        readLALVL();
        readLLOC();
        readLTC();
        readLBC();
        readLXSHDL();
        if (labelExtendedSubheaderLength > 0) {
            readLXSOFL();
            readLXSHD();
        }
        return segment;
    }

    private void readLA() throws ParseException {
        reader.verifyHeaderMagic(LA);
    }

    private void readLID() throws ParseException {
        segment.setIdentifier(reader.readTrimmedBytes(LID_LENGTH));
    }

    private void readLFS() throws ParseException {
        reader.skip(LFS_LENGTH);
    }

    private void readLCW() throws ParseException {
        segment.setLabelCellWidth(reader.readBytesAsInteger(LCW_LENGTH));
    }

    private void readLCH() throws ParseException {
        segment.setLabelCellHeight(reader.readBytesAsInteger(LCH_LENGTH));
    }

    private void readLDLVL() throws ParseException {
        segment.setLabelDisplayLevel(reader.readBytesAsInteger(LDLVL_LENGTH));
    }

    private void readLALVL() throws ParseException {
        segment.setAttachmentLevel(reader.readBytesAsInteger(LALVL_LENGTH));
    }

    private void readLLOC() throws ParseException {
        segment.setLabelLocationRow(reader.readBytesAsInteger(LLOC_HALF_LENGTH));
        segment.setLabelLocationColumn(reader.readBytesAsInteger(LLOC_HALF_LENGTH));
    }

    private void readLTC() throws ParseException {
        segment.setLabelTextColour(new RGBColour(reader.readBytesRaw(RGBColour.RGB_COLOUR_LENGTH)));
    }

    private void readLBC() throws ParseException {
        segment.setLabelBackgroundColour(new RGBColour(reader.readBytesRaw(RGBColour.RGB_COLOUR_LENGTH)));
    }

    private void readLXSHDL() throws ParseException {
        labelExtendedSubheaderLength = reader.readBytesAsInteger(LXSHDL_LENGTH);
    }

    private void readLXSOFL() throws ParseException {
        segment.setExtendedHeaderDataOverflow(reader.readBytesAsInteger(LXSOFL_LENGTH));
    }

    private void readLXSHD() throws ParseException {
        TreCollection extendedSubheaderTREs = parsingStrategy.parseTREs(reader,
                labelExtendedSubheaderLength - LXSOFL_LENGTH,
                TreSource.LabelExtendedSubheaderData);
        segment.mergeTREs(extendedSubheaderTREs);
    }
}
