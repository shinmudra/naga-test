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
			
			Properties prop = new Properties();
			String propFileName = "ConfigValues.properties";
			
			Properties props = new EncryptableProperties(encryptor);  
			
			props.load(getClass().getClassLoader().getResourceAsStream(propFileName));
			String datasourceUsername = props.getProperty("DbPassword_"+envronment);
			System.out.println("datasourceUsername" + datasourceUsername);
			
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
 
			Date time = new Date(System.currentTimeMillis());
 
			// get the property value and print it out
			String siteURL = prop.getProperty("siteUrl");
			String shopUrl = prop.getProperty("shopUrl");
			String dbUserName = prop.getProperty("DbUserName");
			String dbPassword = prop.getProperty("DbPassword");
 
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