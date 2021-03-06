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
package org.codice.imaging.nitf.render.imagerep;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.IOException;
import javax.imageio.stream.ImageInputStream;

/**
 * Image representation handler for 16 bit mono (greyscale) images.
 */
class Mono16IntegerImageRepresentationHandler extends SharedMonoImageRepresentationHandler implements ImageRepresentationHandler {

    public Mono16IntegerImageRepresentationHandler(int selectedBandZeroBase) {
        super(selectedBandZeroBase);
    }

    @Override
    public void renderPixelBand(DataBuffer dataBuffer, int pixelIndex, ImageInputStream imageInputStream, int bandIndex) throws IOException {
        if (bandIndex == selectedBandZeroBase) {
            dataBuffer.setElem(pixelIndex, ((short) imageInputStream.readShort()));
        } else {
            imageInputStream.readShort();
        }
    }

    @Override
    public BufferedImage createBufferedImage(int width, int height) {
        return new BufferedImage(width, height, BufferedImage.TYPE_USHORT_GRAY);
    }
}
