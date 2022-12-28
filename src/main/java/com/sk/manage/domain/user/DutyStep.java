package com.sk.manage.domain.user;

import lombok.Getter;

import java.util.Arrays;

public enum DutyStep {
    STAFF("01", "사원"),
    ASSISTANT_MANAGE("02","대리"),
    MANAGER("03","과장"),
    DEPUTY_GENERAL_MANAGER("04","차장"),
    GENERAL_MANAGER("05","부장");

    private String code;
    @Getter
    private String name;

    DutyStep(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static DutyStep findDutyStepByName(String dutyStepName){
        return Arrays.stream(DutyStep.values()).filter(ds -> ds.name.equals(dutyStepName)).findAny().orElseThrow(()->new IllegalArgumentException("there is no matched duty step about '" + dutyStepName + "'"));
    }
}
