package com.ge.impl;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;


public class GetBaiduDataImpl {

    public static String getdata(String urlstring){
        try {
            URL url = new URL(urlstring);
            String finaldata=null;
            try {
                InputStream is = url.openStream();
                InputStreamReader isr = new InputStreamReader(is,"utf-8");
                BufferedReader br = new BufferedReader(isr);
                String data = br.readLine();
                while (data!=null){
                	finaldata=finaldata+data;
                    data = br.readLine();
                }
                br.close();
                isr.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    		return finaldata;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
		return null;
    }
}