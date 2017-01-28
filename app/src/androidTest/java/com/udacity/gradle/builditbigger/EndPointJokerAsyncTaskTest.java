package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by guillermo on 1/28/17.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class EndPointJokerAsyncTaskTest {


    @Test
    public void testDoInBackground() throws Exception {
        String value;
        Context context = InstrumentationRegistry.getTargetContext();

        EndPointJokerAsyncTask task = new EndPointJokerAsyncTask();
        task.execute(context);
        //setting this because is the default time of the api to wait from the server
        //remember that this method is actually an asynchronous method
        Thread.sleep(3000);
        value = task.result;
        Assert.assertNotNull(value);
        Assert.assertTrue("error empty string",!value.isEmpty());
    }

}