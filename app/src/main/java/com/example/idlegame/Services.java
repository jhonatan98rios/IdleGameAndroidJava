package com.example.idlegame;

public class Services {

    public int bucketPrice;
    public boolean boughtBucket;

    public int cdnPrice;
    public boolean boughtCdn;

    public int sqlDataBasePrice;
    public boolean boughtSqlDatabase;

    public Services() {
        bucketPrice = 1000;
        cdnPrice = 10000;
        sqlDataBasePrice=50000;

        boughtBucket = false;
        boughtCdn = false;
        boughtSqlDatabase = false;
    }
}
