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
        cdnPrice = 5000;
        sqlDataBasePrice=10000;

        boughtBucket = false;
        boughtCdn = false;
        boughtSqlDatabase = false;
    }
}
