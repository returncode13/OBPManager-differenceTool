/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.cismet.custom.visualdiff;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import org.netbeans.api.diff.DiffView;

/**
 *
 * @author sharath nair
 */
public class WorkflowDifferenceNode extends javax.swing.JPanel {
    private static final String MIMETYPE_TEXT = "text/plain";

    WorkflowDifferenceModel wfdmodel;
    DefaultComboBoxModel lmodel=new DefaultComboBoxModel();
    DefaultComboBoxModel rmodel=new DefaultComboBoxModel();
    
    private JButton nextDifferenceButton=new JButton("next diff");
    private JButton prevDifferenceButton=new JButton("prev diff");
    private JPanel controls=new JPanel();
    private JComboBox subVersions=new JComboBox();
    private JComboBox volVersions=new JComboBox();
    private DiffPanel diffPanel;
    /**
     * Creates new form WorkflowDifferencePanel
     */
    public WorkflowDifferenceNode(WorkflowDifferenceModel model) {
        initComponents();
        
        this.wfdmodel=model;
        this.setLayout(new BorderLayout());
        controls.setMinimumSize(new java.awt.Dimension(462, 37));
        controls.setName(""); // NOI18N
        controls.setPreferredSize(new java.awt.Dimension(460, 37));
        prevDifferenceButton.addActionListener(e->{btnPrevDifferenceActionPerformed(e);});
        nextDifferenceButton.addActionListener(e->{btnNextDifferenceActionPerformed(e);});
        
        controls.add(prevDifferenceButton);
        controls.add(nextDifferenceButton);
        for (Iterator<VersionHolder> iterator = wfdmodel.getlVersionHolder().iterator(); iterator.hasNext();) {
            VersionHolder next = iterator.next();
            lmodel.addElement(next.version);
        }
        
        
        for (Iterator<VersionHolder> iterator = wfdmodel.getrVersionHolder().iterator(); iterator.hasNext();) {
            VersionHolder next = iterator.next();
            rmodel.addElement(next.version);
        }
        
        diffPanel = new DiffPanel();
        
        subVersions=new JComboBox(lmodel);
        volVersions=new JComboBox(rmodel);
        subVersions.addActionListener(e->{
        
          diffPanel.setLeftAndRight(wfdmodel.getlVersionHolder().get(subVersions.getSelectedIndex()).getContent(),  
                MIMETYPE_TEXT,
                wfdmodel.getlVersionHolder().get(subVersions.getSelectedIndex()).getVersion(),
                wfdmodel.getrVersionHolder().get(volVersions.getSelectedIndex()).getContent(), 
                MIMETYPE_TEXT, 
                wfdmodel.getrVersionHolder().get(volVersions.getSelectedIndex()).getVersion());
        });
        
        volVersions.addActionListener(e->{
        
       diffPanel.setLeftAndRight(wfdmodel.getlVersionHolder().get(subVersions.getSelectedIndex()).getContent(),  
                MIMETYPE_TEXT,
                wfdmodel.getlVersionHolder().get(subVersions.getSelectedIndex()).getVersion(),
                wfdmodel.getrVersionHolder().get(volVersions.getSelectedIndex()).getContent(), 
                MIMETYPE_TEXT, 
                wfdmodel.getrVersionHolder().get(volVersions.getSelectedIndex()).getVersion());
        
        });
     
        JPanel jpane=new JPanel();
        jpane.setLayout(new GridLayout(1, 2));
        jpane.add(subVersions);//,BorderLayout.CENTER);
        jpane.add(volVersions);//,BorderLayout.WEST);
        this.add(controls,BorderLayout.SOUTH);
        this.add(jpane,BorderLayout.NORTH);
        this.add(diffPanel, BorderLayout.CENTER);
        
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
   
    
    
    
     private void btnPrevDifferenceActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        if (diffPanel.getDiffView() != null) {
            final DiffView view = diffPanel.getDiffView();
            if (view.canSetCurrentDifference()) {
                view.setCurrentDifference(((view.getCurrentDifference() == 0) ? (view.getDifferenceCount() - 1)
                        : (view.getCurrentDifference() - 1))
                        % view.getDifferenceCount());
            }
        }
    }     
    
      private void btnNextDifferenceActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        if (diffPanel.getDiffView() != null) {
            final DiffView view = diffPanel.getDiffView();
            if (view.canSetCurrentDifference()) {
                view.setCurrentDifference((view.getCurrentDifference() + 1) % view.getDifferenceCount());
            }
        }
    }    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}