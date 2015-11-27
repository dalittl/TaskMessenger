package com.acnc.mm.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PathTest {
	
	
	public static void main(String[] args) {
        System.setProperty("user.dir", "/Network/Servers/opdirsrv.acnc-md.com/Volumes/RProfileRAID/reggjackson/Documents");
        
        System.out.println(System.getProperty("user.dir"));
        System.out.println(new File("sample.xls").exists());
        System.out.println(new File("sample.xls").isFile());
        System.out.println(new File("sample.xls").canRead());

        System.out.println(new File("sample.xls").getAbsolutePath());
        System.out.println(new File(new File("sample.xls").getAbsolutePath()).exists());
        System.out.println(new File(new File("sample.xls").getAbsolutePath()).isFile());
        System.out.println(new File(new File("sample.xls").getAbsolutePath()).canRead());

        try {
            new FileInputStream("sample.xls").toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

	}
}
