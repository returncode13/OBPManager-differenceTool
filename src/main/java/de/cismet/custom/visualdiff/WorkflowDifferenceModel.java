/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.cismet.custom.visualdiff;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sharath nair
 */
public class WorkflowDifferenceModel {
    private List<VersionHolder> lVersionHolder=new ArrayList<>();
    private List<VersionHolder> rVersionHolder=new ArrayList<>();

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
    
    
}
