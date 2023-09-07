package com.example.idlegame;

public class Services {

    public int bucketPrice;
    public boolean boughtBucket;

    public int cdnPrice;
    public boolean boughtCdn;

    public int sqlDataBasePrice;
    public boolean boughtSqlDatabase;

    public Services() {
        bucketPrice = 10000;
        cdnPrice = 50000;
        sqlDataBasePrice=100000;

        boughtBucket = false;
        boughtCdn = false;
        boughtSqlDatabase = false;
    }
}
