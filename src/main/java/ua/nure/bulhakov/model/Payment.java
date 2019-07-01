package ua.nure.bulhakov.model;

import java.util.Date;

public class Payment extends Entity {

    private static final long serialVersionUID = 1L;

    private Contract contract;

    private Date paymentDate;

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
}
