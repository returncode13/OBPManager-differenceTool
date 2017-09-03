/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.cismet.custom.visualdiff;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.scene.control.ComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import org.netbeans.api.diff.DiffView;
import org.openide.util.Exceptions;

/**
 *
 * @author adira0150
 */
public class WorkflowDiffApp1 extends javax.swing.JFrame {

    private static final String FILENAME1_TEXT = "/d/home/adira0150/programming/java/testingGrounds/duglogs/seq61_cab7_g1.log";
    private static final String FILENAME2_TEXT = "/d/home/adira0150/programming/java/testingGrounds/duglogs/notes.txt";
    private static final String MIMETYPE_TEXT = "text/plain";
     private DiffPanel pnlDiff;
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlControls = new javax.swing.JPanel();
        btnPrevDifference = new javax.swing.JButton();
        btnNextDifference = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(org.openide.util.NbBundle.getMessage(WorkflowDiffApp1.class, "WorkflowDiffApp1.title")); // NOI18N
        setMinimumSize(new java.awt.Dimension(462, 37));
        setPreferredSize(new java.awt.Dimension(460, 37));

        pnlControls.setMinimumSize(new java.awt.Dimension(462, 37));
        pnlControls.setName(""); // NOI18N
        pnlControls.setPreferredSize(new java.awt.Dimension(460, 37));

        org.openide.awt.Mnemonics.setLocalizedText(btnPrevDifference, org.openide.util.NbBundle.getMessage(WorkflowDiffApp1.class, "WorkflowDiffApp1.btnPrevDifference.text")); // NOI18N
        btnPrevDifference.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevDifferenceActionPerformed(evt);
            }
        });
        pnlControls.add(btnPrevDifference);

        org.openide.awt.Mnemonics.setLocalizedText(btnNextDifference, org.openide.util.NbBundle.getMessage(WorkflowDiffApp1.class, "WorkflowDiffApp1.btnNextDifference.text")); // NOI18N
        btnNextDifference.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextDifferenceActionPerformed(evt);
            }
        });
        pnlControls.add(btnNextDifference);

        getContentPane().add(pnlControls, java.awt.BorderLayout.SOUTH);

        getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(WorkflowDiffApp1.class, "WorkflowDiffApp1.AccessibleContext.accessibleName")); // NOI18N

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPrevDifferenceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevDifferenceActionPerformed
        if (pnlDiff.getDiffView() != null) {
            final DiffView view = pnlDiff.getDiffView();
            if (view.canSetCurrentDifference()) {
                view.setCurrentDifference(((view.getCurrentDifference() == 0) ? (view.getDifferenceCount() - 1)
                        : (view.getCurrentDifference() - 1))
                        % view.getDifferenceCount());
            }
        }
    }//GEN-LAST:event_btnPrevDifferenceActionPerformed

    private void btnNextDifferenceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextDifferenceActionPerformed
        if (pnlDiff.getDiffView() != null) {
            final DiffView view = pnlDiff.getDiffView();
            if (view.canSetCurrentDifference()) {
                view.setCurrentDifference((view.getCurrentDifference() + 1) % view.getDifferenceCount());
            }
        }
    }//GEN-LAST:event_btnNextDifferenceActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(WorkflowDiffApp1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WorkflowDiffApp1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WorkflowDiffApp1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WorkflowDiffApp1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new WorkflowDiffApp1().setVisible(true);
                } catch (Exception ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNextDifference;
    private javax.swing.JButton btnPrevDifference;
    private javax.swing.JPanel pnlControls;
    // End of variables declaration//GEN-END:variables
    private JComboBox subVersions=new JComboBox();
    private JComboBox volVersions=new JComboBox();
    private List<String> leftVersions=new ArrayList<>();
    private List<String> rightVersions=new ArrayList<>();
    private List<VersionHolder> lVersionHolder=new ArrayList<>();
    private List<VersionHolder> rVersionHolder=new ArrayList<>();


 /**
     * Creates new form WorkflowDiffApp
     */
    public WorkflowDiffApp1()  throws Exception{
        initComponents();
        
        leftVersions.add("This is 1");
        leftVersions.add("This is 2\nsomething\nNew line");
        rightVersions.add("This is 1");
        rightVersions.add("This is 2\nNew line");
        setSize(new java.awt.Dimension(729, 706));
        setLocationRelativeTo(null);
         final File file1 = new File(FILENAME1_TEXT);
        final File file2 = new File(FILENAME2_TEXT);
        
        
        
        DefaultComboBoxModel lmodel=new DefaultComboBoxModel();
        for (Iterator<VersionHolder> iterator = lVersionHolder.iterator(); iterator.hasNext();) {
            VersionHolder next = iterator.next();
            lmodel.addElement(next.version);
        }
        
        DefaultComboBoxModel rmodel=new DefaultComboBoxModel();
        for (Iterator<VersionHolder> iterator = rVersionHolder.iterator(); iterator.hasNext();) {
            VersionHolder next = iterator.next();
            rmodel.addElement(next.version);
        }
        pnlDiff = new DiffPanel();
        
        subVersions=new JComboBox(lmodel);
        volVersions=new JComboBox(rmodel);
        subVersions.addActionListener(e->{
        
          pnlDiff.setLeftAndRight(lVersionHolder.get(subVersions.getSelectedIndex()).getContent(),  
                MIMETYPE_TEXT,
                lVersionHolder.get(subVersions.getSelectedIndex()).getVersion(),
                rVersionHolder.get(volVersions.getSelectedIndex()).getContent(), 
                MIMETYPE_TEXT, 
                rVersionHolder.get(volVersions.getSelectedIndex()).getVersion());
        });
        
        volVersions.addActionListener(e->{
        
        pnlDiff.setLeftAndRight(lVersionHolder.get(subVersions.getSelectedIndex()).getContent(), 
                MIMETYPE_TEXT,
                lVersionHolder.get(subVersions.getSelectedIndex()).getVersion(),
                rVersionHolder.get(volVersions.getSelectedIndex()).getContent(), 
                MIMETYPE_TEXT, 
                rVersionHolder.get(volVersions.getSelectedIndex()).getVersion());
        
        });
        
        /*pnlDiff.setLeftAndRight(getLines(new FileReader(file1)),
        MIMETYPE_TEXT,
        file1.getName(),
        getLines(new FileReader(file2)),
        MIMETYPE_TEXT,
        file2.getName());*/
        
        
        
        
        
       
        
    }

    private String getLines(final Reader reader) throws IOException {
        final StringBuilder result = new StringBuilder();
        final BufferedReader bufferedReader = new BufferedReader(reader);

        try {
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                result.append(line);
                result.append("\n");
            }
        } finally {
            bufferedReader.close();
        }

        return result.toString();
    }

    public List<String> getLeftVersions() {
        return leftVersions;
    }

    public void setLeftVersions(List<String> leftVersions) {
        this.leftVersions = leftVersions;
    }

    public List<String> getRightVersions() {
        return rightVersions;
    }

    public void setRightVersions(List<String> rightVersions) {
        this.rightVersions = rightVersions;
    }

    public List<VersionHolder> getlVersionHolder() {
        return lVersionHolder;
    }

    public void setlVersionHolder(List<VersionHolder> lVersionHolder) {
        this.lVersionHolder = lVersionHolder;
    }

    public List<VersionHolder> getrVersionHolder() {
        return rVersionHolder;
    }

    public void setrVersionHolder(List<VersionHolder> rVersionHolder) {
        this.rVersionHolder = rVersionHolder;
    }

    
    
     public void showPanel(){
          JPanel jpane=new JPanel();
        jpane.setLayout(new GridLayout(1, 2));
        jpane.add(subVersions);//,BorderLayout.CENTER);
        jpane.add(volVersions);//,BorderLayout.WEST);
        getContentPane().add(jpane,BorderLayout.NORTH);
        getContentPane().add(pnlDiff, BorderLayout.CENTER);
     }
    

}

