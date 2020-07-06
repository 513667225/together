package com.together.entity;

import com.together.enun.UserRelationshipCase;

/**
 * @author Agu
 */
public class CaseEntity {

    private UserRelationshipCase userRelationshipCase;

    private boolean caseController;

    private boolean isAgain;


    public CaseEntity(UserRelationshipCase userRelationshipCase, boolean caseController,boolean isAgain) {
        this.userRelationshipCase = userRelationshipCase;
        this.caseController = caseController;
        this.isAgain = isAgain;
    }

    public UserRelationshipCase getUserRelationshipCase() {
        return userRelationshipCase;
    }

    public void setUserRelationshipCase(UserRelationshipCase userRelationshipCase) {
        this.userRelationshipCase = userRelationshipCase;
    }

    public boolean isCaseController() {
        return caseController;
    }

    public void setCaseController(boolean caseController) {
        this.caseController = caseController;
    }

    public boolean isAgain() {
        return isAgain;
    }

    public void setAgain(boolean again) {
        isAgain = again;
    }
}
