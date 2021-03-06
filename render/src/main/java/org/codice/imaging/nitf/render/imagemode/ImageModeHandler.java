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
package org.codice.imaging.nitf.render.imagemode;

import java.awt.Graphics2D;
import java.io.IOException;

import org.codice.imaging.nitf.core.image.ImageSegment;

/**
 * An ImageModeHandler abstracts the processing of an ImageSegment based on the NITF Image Mode.
 *
 * Pixel-by-pixel rendering is delegated to the supplied ImageRepresentationHandler.
 */
public interface ImageModeHandler {

    /**
     *
     * @param imageSegment - the ImageSegment for the image being rendered.
     * @param targetImage - the Graphic2D that the image will be rendered to.
     * @throws IOException - propagated from the ImageInputStream.
     */
    void handleImage(ImageSegment imageSegment, Graphics2D targetImage)
            throws IOException;
}
