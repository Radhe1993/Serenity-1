package com.bestbuy.storeinfo;

import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class StoreCURDTestWithSteps extends TestBase {

    static String name = "Prime" + TestUtils.getRandomValue();
    static String type = "BigBox" + TestUtils.getRandomValue();
    static String address = "25,goldencity" + TestUtils.getRandomValue();
    static String address2 = "Motavaracha" + TestUtils.getRandomValue();
    static String city = "Surat" + TestUtils.getRandomValue();
    static String state = "Gujarat" + TestUtils.getRandomValue();
    static String zip = "55126" + TestUtils.getRandomValue();
    static double  lat = 44.969658 ;
    static double lng = 93.449539 ;
    static String hours = "Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8"+TestUtils.getRandomValue();

    @Steps
    StoreSteps storeSteps;

    @Title("This will create a new store")
    @Test
    public void test001() {

        storeSteps.creatStore1(name,type,address,address2,city,state,zip,lat,lng,hours).statusCode(201);

    }

    @Title("This will getting a store data")
    @Test
    public void test002()
    {

        storeSteps.getAllStoreInfo();
    }

    @Title("Update store")
    @Test
    public void test003()
    {

        storeSteps.updateID( name,type, address,  address2, city, state,  zip,  lat,  lng );

    }

    @Title("Delete Store")
    @Test
    public void test004()
    {

        storeSteps.deleteId();
    }




}
