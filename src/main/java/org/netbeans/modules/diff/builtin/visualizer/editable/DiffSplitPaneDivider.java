/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2010 Oracle and/or its affiliates. All rights reserved.
 *
 * Oracle and Java are registered trademarks of Oracle and/or its affiliates.
 * Other names may be trademarks of their respective owners.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common
 * Development and Distribution License("CDDL") (collectively, the
 * "License"). You may not use this file except in compliance with the
 * License. You can obtain a copy of the License at
 * http://www.netbeans.org/cddl-gplv2.html
 * or nbbuild/licenses/CDDL-GPL-2-CP. See the License for the
 * specific language governing permissions and limitations under the
 * License.  When distributing the software, include this License Header
 * Notice in each file and include the License file at
 * nbbuild/licenses/CDDL-GPL-2-CP.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the GPL Version 2 section of the License file that
 * accompanied this code. If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * Contributor(s):
 *
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2007 Sun
 * Microsystems, Inc. All Rights Reserved.
 *
 * If you wish your version of this file to be governed by only the CDDL
 * or only the GPL Version 2, indicate your decision by adding
 * "[Contributor] elects to include this software in this distribution
 * under the [CDDL or GPL Version 2] license." If you do not indicate a
 * single choice of license, a recipient has the option to distribute
 * your version of this file under either the CDDL, the GPL Version 2 or
 * to extend the choice of license to its licensees as provided above.
 * However, if you add GPL Version 2 code and therefore, elected the GPL
 * Version 2 license, then the option applies only if the new code is
 * made subject to such option by the copyright holder.
 */
package org.netbeans.modules.diff.builtin.visualizer.editable;

import org.openide.util.NbBundle;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.GeneralPath;

import java.util.*;
import java.util.List;

import javax.accessibility.Accessible;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;

/**
 * Split pane divider with Diff decorations.
 *
 * @author   Maros Sandor
 * @version  $Revision$, $Date$
 */
class DiffSplitPaneDivider extends BasicSplitPaneDivider implements MouseMotionListener, MouseListener, Accessible {

    //~ Instance fields --------------------------------------------------------

    private final Image insertAllImage = org.openide.util.Utilities.loadImage(
            "org/netbeans/modules/diff/builtin/visualizer/editable/move_all.png");        // NOI18N
    private final Image insertAllActiveImage = org.openide.util.Utilities.loadImage(
            "org/netbeans/modules/diff/builtin/visualizer/editable/move_all_active.png"); // NOI18N
    private final int actionIconsHeight;
    private final int actionIconsWidth;
    private final Point POINT_ZERO = new Point(0, 0);

    private final EditableDiffView master;

    private Point lastMousePosition = POINT_ZERO;
    private HotSpot lastHotSpot = null;
    private java.util.List<HotSpot> hotspots = new ArrayList<HotSpot>(0);

    private DiffSplitDivider mydivider;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new DiffSplitPaneDivider object.
     *
     * @param  splitPaneUI  DOCUMENT ME!
     * @param  master       DOCUMENT ME!
     */
    DiffSplitPaneDivider(final BasicSplitPaneUI splitPaneUI, final EditableDiffView master) {
        super(splitPaneUI);
        this.master = master;

        actionIconsHeight = insertAllImage.getHeight(this);
        actionIconsWidth = insertAllImage.getWidth(this);

        setBorder(null);
        setLayout(new BorderLayout());
        mydivider = new DiffSplitDivider();
        add(mydivider);

        addMouseListener(this);
        addMouseMotionListener(this);
    }

    //~ Methods ----------------------------------------------------------------

    @Override
    public void mouseClicked(final MouseEvent e) {
        if (!e.isPopupTrigger()) {
            final HotSpot spot = getHotspotAt(e.getPoint());
            if (spot != null) {
                performAction(); // there is only one hotspot
            }
        }
    }

    @Override
    public void mouseExited(final MouseEvent e) {
        lastMousePosition = POINT_ZERO;
        if (lastHotSpot != null) {
            mydivider.repaint(lastHotSpot.getRect());
        }
        lastHotSpot = null;
    }

    @Override
    public void mouseEntered(final MouseEvent e) {
        // not interested
    }

    @Override
    public void mousePressed(final MouseEvent e) {
        // not interested
    }

    @Override
    public void mouseReleased(final MouseEvent e) {
        // not interested
    }

    @Override
    public void mouseDragged(final MouseEvent e) {
        // not interested
    }

    @Override
    public void mouseMoved(final MouseEvent e) {
        final Point p = e.getPoint();
        lastMousePosition = p;
        final HotSpot spot = getHotspotAt(p);
        if (lastHotSpot != spot) {
            mydivider.repaint((lastHotSpot == null) ? spot.getRect() : lastHotSpot.getRect());
        }
        lastHotSpot = spot;
        setCursor((spot != null) ? Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
                                 : Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
    }

    /**
     * DOCUMENT ME!
     */
    private void performAction() {
        master.rollback(null);
    }

    @Override
    public void setBorder(final Border border) {
        super.setBorder(BorderFactory.createEmptyBorder());
    }

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    DiffSplitDivider getDivider() {
        return mydivider;
    }

    /**
     * DOCUMENT ME!
     *
     * @param   p  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    private HotSpot getHotspotAt(final Point p) {
        for (final HotSpot hotspot : hotspots) {
            if (hotspot.getRect().contains(p)) {
                return hotspot;
            }
        }
        return null;
    }

    //~ Inner Classes ----------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @version  $Revision$, $Date$
     */
    private class DiffSplitDivider extends JPanel {

        //~ Instance fields ----------------------------------------------------

        private Map renderingHints;

        //~ Constructors -------------------------------------------------------

        /**
         * Creates a new DiffSplitDivider object.
         */
        public DiffSplitDivider() {
            setBackground(UIManager.getColor("SplitPane.background")); // NOI18N
            setOpaque(true);
            renderingHints = (Map)(Toolkit.getDefaultToolkit().getDesktopProperty(
                        "awt.font.desktophints"));                     // NOI18N
        }

        //~ Methods ------------------------------------------------------------

        @Override
        public String getToolTipText(final MouseEvent event) {
            final Point p = event.getPoint();
            final HotSpot spot = getHotspotAt(p);
            if (spot == null) {
                return null;
            }
            return NbBundle.getMessage(DiffSplitDivider.class, "TT_DiffPanel_MoveAll"); // NOI18N
        }

        @Override
        protected void paintComponent(final Graphics gr) {
            final Graphics2D g = (Graphics2D)gr.create();
            final Rectangle clip = g.getClipBounds();
            final Stroke cs = g.getStroke();

            g.setColor(getBackground());
            g.fillRect(clip.x, clip.y, clip.width, clip.height);

            if (master.getEditorPane1() == null) {
                g.dispose();
                return;
            }

            final Rectangle rightView = master.getEditorPane2().getScrollPane().getViewport().getViewRect();
            final Rectangle leftView = master.getEditorPane1().getScrollPane().getViewport().getViewRect();

            final int editorsOffset = master.getEditorPane2().getLocation().y + master.getEditorPane2()
                        .getInsets().top;

            final int rightOffset = -rightView.y + editorsOffset;
            final int leftOffset = -leftView.y + editorsOffset;

            if (renderingHints != null) {
                g.addRenderingHints(renderingHints);
            }
            final String diffInfo = (master.getCurrentDifference() + 1) + "/" + master.getDifferenceCount(); // NOI18N
            final int width = g.getFontMetrics().stringWidth(diffInfo);
            g.setColor(Color.BLACK);
            g.drawString(diffInfo, (getWidth() - width) / 2, g.getFontMetrics().getHeight());

            if (clip.y < editorsOffset) {
                g.setClip(clip.x, editorsOffset, clip.width, clip.height);
            }

            final int rightY = getWidth() - 1;

            g.setColor(Color.LIGHT_GRAY);
            g.drawLine(0, clip.y, 0, clip.height);
            g.drawLine(rightY, clip.y, rightY, clip.height);

            final int curDif = master.getCurrentDifference();

            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            final DiffViewManager.DecoratedDifference[] decoratedDiffs = master.getManager().getDecorations();
            int idx = 0;
            boolean everythingEditable = true;
            for (final DiffViewManager.DecoratedDifference dd : decoratedDiffs) {
                everythingEditable &= dd.canRollback();
                g.setColor(master.getColor(dd.getDiff()));
                g.setStroke((curDif == idx++) ? master.getBoldStroke() : cs);
                if (dd.getBottomLeft() == -1) {
                    paintMatcher(
                        g,
                        master.getColor(dd.getDiff()),
                        0,
                        rightY,
                        dd.getTopLeft()
                                + leftOffset,
                        dd.getTopRight()
                                + rightOffset,
                        dd.getBottomRight()
                                + rightOffset,
                        dd.getTopLeft()
                                + leftOffset);
                } else if (dd.getBottomRight() == -1) {
                    paintMatcher(
                        g,
                        master.getColor(dd.getDiff()),
                        0,
                        rightY,
                        dd.getTopLeft()
                                + leftOffset,
                        dd.getTopRight()
                                + rightOffset,
                        dd.getTopRight()
                                + rightOffset,
                        dd.getBottomLeft()
                                + leftOffset);
                } else {
                    paintMatcher(
                        g,
                        master.getColor(dd.getDiff()),
                        0,
                        rightY,
                        dd.getTopLeft()
                                + leftOffset,
                        dd.getTopRight()
                                + rightOffset,
                        dd.getBottomRight()
                                + rightOffset,
                        dd.getBottomLeft()
                                + leftOffset);
                }
            }

            if (master.isActionsEnabled() && everythingEditable) {
                final List<HotSpot> newActionIcons = new ArrayList<HotSpot>();
                final Rectangle hotSpot = new Rectangle((getWidth() - actionIconsWidth) / 2,
                        editorsOffset,
                        actionIconsWidth,
                        actionIconsHeight);
                if (hotSpot.contains(lastMousePosition)) {
                    g.drawImage(insertAllActiveImage, hotSpot.x, hotSpot.y, this);
                } else {
                    g.drawImage(insertAllImage, hotSpot.x, hotSpot.y, this);
                }
                newActionIcons.add(new HotSpot(hotSpot, null));
                hotspots = newActionIcons;
            }
            g.dispose();
        }

        /**
         * DOCUMENT ME!
         *
         * @param  g        DOCUMENT ME!
         * @param  fillClr  DOCUMENT ME!
         * @param  leftX    DOCUMENT ME!
         * @param  rightX   DOCUMENT ME!
         * @param  upL      DOCUMENT ME!
         * @param  upR      DOCUMENT ME!
         * @param  doR      DOCUMENT ME!
         * @param  doL      DOCUMENT ME!
         */
        private void paintMatcher(final Graphics2D g,
                final Color fillClr,
                final int leftX,
                final int rightX,
                final int upL,
                final int upR,
                final int doR,
                final int doL) {
            final int topY = Math.min(upL, upR);
            final int bottomY = Math.max(doL, doR);
            // try rendering only curves in viewable area
            if (!g.hitClip(leftX, topY, rightX - leftX, bottomY - topY)) {
                return;
            }
            final CubicCurve2D upper = new CubicCurve2D.Float(
                    leftX,
                    upL,
                    (rightX - leftX)
                            * .3f,
                    upL,
                    (rightX - leftX)
                            * .7f,
                    upR,
                    rightX,
                    upR);
            final CubicCurve2D bottom = new CubicCurve2D.Float(
                    rightX,
                    doR,
                    (rightX - leftX)
                            * .7f,
                    doR,
                    (rightX - leftX)
                            * .3f,
                    doL,
                    leftX,
                    doL);
            final GeneralPath path = new GeneralPath();
            path.append(upper, false);
            path.append(bottom, true);
            path.closePath();
            g.setColor(fillClr);
            g.fill(path);
            g.setColor(master.getColorLines());
            g.draw(upper);
            g.draw(bottom);
        }
    }
}
