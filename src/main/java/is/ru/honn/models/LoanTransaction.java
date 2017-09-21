package is.ru.honn.models;

import java.util.Date;

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
