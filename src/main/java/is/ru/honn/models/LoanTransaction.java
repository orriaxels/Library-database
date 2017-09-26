package is.ru.honn.models;

/**
 * @author Haraldur Ingi Shoshan og Orri Axelsson
 * @version LoanTransaction.java 1.0 26 September 2017
 * Copyright (c) Haraldur Ingi Shoshan & Orri Axelsson
 *
 * The LoanTransaction class keeps track on all the loans, it is used
 * when we create a transaction, it tracks the person, the book and the
 * date of the transaction
 */

public class LoanTransaction
{
    private int pId;
    private int bId;
    private String date;

    public LoanTransaction(int pId, int bId, String date)
    {
        this.pId = pId;
        this.bId = bId;
        this.date = date;
    }

    public int getpId() {
        return pId;
    }

    public int getbId() {
        return bId;
    }

    public String getDate() {
        return date;
    }
}
