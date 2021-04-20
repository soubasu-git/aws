package org.ecnanif.feeder.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

@Entity
public class MF {
    @Id
    private BigInteger mfId;
    private String schemeType;
    private String category;
    private String fundHouse;
    private String iSINDivPayoutGrowth;
    private String iSINDivReinvestment;
    private String mfName;
    private BigDecimal nav;
    private Date date;

    public MF() {
    }

    public MF(BigInteger mfId, String schemeType, String category, String fundHouse, String iSINDivPayoutGrowth, String iSINDivReinvestment, String mfName, BigDecimal nav, Date date) {
        this.mfId = mfId;
        this.schemeType = schemeType;
        this.category = category;
        this.fundHouse = fundHouse;
        this.iSINDivPayoutGrowth = iSINDivPayoutGrowth;
        this.iSINDivReinvestment = iSINDivReinvestment;
        this.mfName = mfName;
        this.nav = nav;
        this.date = date;
    }

    public String getSchemeType() {
        return schemeType;
    }

    public void setSchemeType(String schemeType) {
        this.schemeType = schemeType;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFundHouse() {
        return fundHouse;
    }

    public void setFundHouse(String fundHouse) {
        this.fundHouse = fundHouse;
    }

    public String getiSINDivPayoutGrowth() {
        return iSINDivPayoutGrowth;
    }

    public void setiSINDivPayoutGrowth(String iSINDivPayoutGrowth) {
        this.iSINDivPayoutGrowth = iSINDivPayoutGrowth;
    }

    public String getiSINDivReinvestment() {
        return iSINDivReinvestment;
    }

    public void setiSINDivReinvestment(String iSINDivReinvestment) {
        this.iSINDivReinvestment = iSINDivReinvestment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public BigInteger getMfId() {
        return mfId;
    }

    public void setMfId(BigInteger mfId) {
        this.mfId = mfId;
    }

    public BigDecimal getNav() {
        return nav;
    }

    public void setNav(BigDecimal nav) {
        this.nav = nav;
    }

    public String getMfName() {
        return mfName;
    }

    public void setMfName(String mfName) {
        this.mfName = mfName;
    }
}
