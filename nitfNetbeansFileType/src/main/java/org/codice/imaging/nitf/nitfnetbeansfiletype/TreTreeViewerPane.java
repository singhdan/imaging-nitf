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
package org.codice.imaging.nitf.nitfnetbeansfiletype;

import javax.swing.tree.TreeModel;
import org.openide.awt.ActionID;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;

/**
 * TRE Tree-style View for the editor compartment.
 */
@TopComponent.Description(
        preferredID = "TreTreeView",
        persistenceType = TopComponent.PERSISTENCE_NEVER)
@TopComponent.Registration(
        mode = "editor",
        openAtStartup = false)
@ActionID(
        category = "Window",
        id = "org.codice.imaging.nitf.nitfnetbeansfiletype.TreTreeViewerPane")
@TopComponent.OpenActionRegistration(
        displayName = "CTL_TreTreeViewerPane")
@NbBundle.Messages({
    "CTL_TreTreeViewerPane=TRE Viewer"
})
public class TreTreeViewerPane extends TopComponent {

    /**
     * Creates new form TreTreeViewerPane.
     */
    public TreTreeViewerPane() {
        initComponents();
    }

    // CSOFF: MagicNumber
    // CSOFF: WhitespaceAround
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        treTree = new javax.swing.JTree();

        jScrollPane1.setViewportView(treTree);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // CSON: MagicNumber
    // CSON: WhitespaceAround

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTree treTree;
    // End of variables declaration//GEN-END:variables

    final void setTreeModel(final TreeModel model) {
        treTree.setModel(model);
    }
}
