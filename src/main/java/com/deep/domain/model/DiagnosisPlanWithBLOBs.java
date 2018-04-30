package com.deep.domain.model;

import java.io.Serializable;

public class DiagnosisPlanWithBLOBs extends DiagnosisPlan implements Serializable {
    private String diagnosisC;

    private String diagnosisM;

    private String drugQ;

    private static final long serialVersionUID = 1L;

    public String getDiagnosisC() {
        return diagnosisC;
    }

    public void setDiagnosisC(String diagnosisC) {
        this.diagnosisC = diagnosisC == null ? null : diagnosisC.trim();
    }

    public String getDiagnosisM() {
        return diagnosisM;
    }

    public void setDiagnosisM(String diagnosisM) {
        this.diagnosisM = diagnosisM == null ? null : diagnosisM.trim();
    }

    public String getDrugQ() {
        return drugQ;
    }

    public void setDrugQ(String drugQ) {
        this.drugQ = drugQ == null ? null : drugQ.trim();
    }
}