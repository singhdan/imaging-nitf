/*
 * Copyright (c) 2014, Codice
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package org.codice.imaging.cgm;

import java.awt.Graphics2D;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
class EdgeVisibilityElement extends ElementHelpers implements AbstractElement {

    private static final Logger LOG = LoggerFactory.getLogger(InteriorStyleElement.class);
    private Mode mode = Mode.OFF;

    enum Mode {
        OFF,
        ON    }

    EdgeVisibilityElement() {
        super(CgmIdentifier.EDGE_VISIBILITY);
    }

    @Override
    public void readParameters(final CgmInputReader inputReader, final int parameterListLength) throws IOException {
        int data = inputReader.readEnumValue();
        switch (data) {
            case 0:
                mode = Mode.OFF;
                break;
            case 1:
                mode = Mode.ON;
                break;
            default:
                LOG.info("Unknown Edge Visibility value: " + data);
                break;
        }
    }

    @Override
    public void addStringDescription(final StringBuilder builder) {
        builder.append("\tEdge visibility: ");
        builder.append(mode);
        builder.append(System.lineSeparator());
    }

    @Override
    public void render(final Graphics2D g2, final CgmGraphicState graphicState) {
        graphicState.setEdgeVisibility(mode);
    }

}
