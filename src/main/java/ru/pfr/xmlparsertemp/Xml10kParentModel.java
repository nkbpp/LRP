package ru.pfr.xmlparsertemp;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class Xml10kParentModel extends Xml10kModel {

    private List<Xml10kModel> children;
    private String status;
    private String documentType;
    private String documentSeria;
    private String documentNumber;
    private String documentDate;
    private String documentWhom;

    public Xml10kParentModel() {
    }

    public Xml10kParentModel(List<Xml10kModel> children, String status, String documentType, String documentSeria, String documentNumber, String documentDate, String documentWhom) {
        this.children = children;
        this.status = status;
        this.documentType = documentType;
        this.documentSeria = documentSeria;
        this.documentNumber = documentNumber;
        this.documentDate = documentDate;
        this.documentWhom = documentWhom;
    }

    public List<Xml10kModel> getChildren() {
        return children;
    }

    public void setChildren(List<Xml10kModel> children) {
        this.children = children;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentSeria() {
        return documentSeria;
    }

    public void setDocumentSeria(String documentSeria) {
        this.documentSeria = documentSeria;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getDocumentDate() {
        return documentDate;
    }

    public void setDocumentDate(String documentDate) {
        this.documentDate = documentDate;
    }

    public String getDocumentWhom() {
        return documentWhom;
    }

    public void setDocumentWhom(String documentWhom) {
        this.documentWhom = documentWhom;
    }


}