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

import org.netbeans.api.diff.Difference;
import org.netbeans.modules.editor.errorstripe.privatespi.Mark;
import org.netbeans.modules.editor.errorstripe.privatespi.Status;

import java.awt.Color;

/**
 * Error stripe mark for differences.
 *
 * @author   Maros Sandor
 * @version  $Revision$, $Date$
 */
final class DiffMark implements Mark {

    //~ Instance fields --------------------------------------------------------

    private final int[] span;
    private final Color color;
    private final String desc;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new DiffMark object.
     *
     * @param  difference  DOCUMENT ME!
     * @param  color       DOCUMENT ME!
     */
    public DiffMark(final Difference difference, final Color color) {
        if (difference.getType() == Difference.DELETE) {
            int start = difference.getSecondStart() - 1;
            if (start < 0) {
                start = 0;
            }
            span = new int[] { start, start };
        } else {
            span = new int[] { difference.getSecondStart() - 1, difference.getSecondEnd() - 1 };
        }
        this.color = color;
        desc = ""; // NOI18N
    }

    //~ Methods ----------------------------------------------------------------

    @Override
    public int getType() {
        return TYPE_ERROR_LIKE;
    }

    @Override
    public Status getStatus() {
        return Status.STATUS_OK;
    }

    @Override
    public int getPriority() {
        return Integer.MIN_VALUE;
    }

    @Override
    public Color getEnhancedColor() {
        return color;
    }

    @Override
    public int[] getAssignedLines() {
        return span;
    }

    @Override
    public String getShortDescription() {
        return desc;
    }
}
