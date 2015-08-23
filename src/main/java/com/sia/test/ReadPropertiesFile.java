package com.sia.test;
 
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.properties.EncryptableProperties;
 
/**
 * @author Crunchify.com
 * 
 */
 
public class ReadPropertiesFile {
	String result = "";
	InputStream inputStream;
 
	public String getPropValues() throws IOException {
 
		try {
			StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor(); 
			String encKey = System.getenv("ENC_KEY");
			String envronment = System.getenv("ENV");
			System.out.println("Encription Key" + encKey);
			encryptor.setPassword(encKey);
			
			String propFileName = "ConfigValues.properties";
			
			Properties props = new EncryptableProperties(encryptor);  
			
			props.load(getClass().getClassLoader().getResourceAsStream(propFileName));
			String datasourceUsername = props.getProperty("DbPassword_"+envronment);
			System.out.println("datasourceUsername" + datasourceUsername);
			Date time = new Date(System.currentTimeMillis());
 
			// get the property value and print it out
			String siteURL = props.getProperty("siteUrl");
			String shopUrl = props.getProperty("shopUrl");
			String dbUserName = props.getProperty("DbUserName_"+envronment);
			String dbPassword = props.getProperty("DbPassword_"+envronment);
 
			result = "Shop Url = " + shopUrl + ", " + dbUserName + ", " + dbPassword;
			System.out.println(result + "\nProgram Ran on " + time + " for site=" + siteURL);
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		return result;
	}
}