package com.gezhiwe.lotterydlt.dao.entity;

import java.util.Date;

public class LotteryNumThink {
    private Long id;

    private Integer firstNum;

    private Integer secondNum;

    private Integer thirdNum;

    private Integer fourthNum;

    private Integer fifthNum;

    private Integer sixthNum;

    private Integer seventhNum;

    private Date openDay;

    private Long openNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFirstNum() {
        return firstNum;
    }

    public void setFirstNum(Integer firstNum) {
        this.firstNum = firstNum;
    }

    public Integer getSecondNum() {
        return secondNum;
    }

    public void setSecondNum(Integer secondNum) {
        this.secondNum = secondNum;
    }

    public Integer getThirdNum() {
        return thirdNum;
    }

    public void setThirdNum(Integer thirdNum) {
        this.thirdNum = thirdNum;
    }

    public Integer getFourthNum() {
        return fourthNum;
    }

    public void setFourthNum(Integer fourthNum) {
        this.fourthNum = fourthNum;
    }

    public Integer getFifthNum() {
        return fifthNum;
    }

    public void setFifthNum(Integer fifthNum) {
        this.fifthNum = fifthNum;
    }

    public Integer getSixthNum() {
        return sixthNum;
    }

    public void setSixthNum(Integer sixthNum) {
        this.sixthNum = sixthNum;
    }

    public Integer getSeventhNum() {
        return seventhNum;
    }

    public void setSeventhNum(Integer seventhNum) {
        this.seventhNum = seventhNum;
    }

    public Date getOpenDay() {
        return openDay;
    }

    public void setOpenDay(Date openDay) {
        this.openDay = openDay;
    }

    public Long getOpenNum() {
        return openNum;
    }

    public void setOpenNum(Long openNum) {
        this.openNum = openNum;
    }
}