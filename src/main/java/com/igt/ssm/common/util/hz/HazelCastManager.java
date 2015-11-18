package com.igt.ssm.common.util.hz;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.hazelcast.config.Config;
import com.hazelcast.config.XmlConfigBuilder;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

public class HazelCastManager {
	 private static final String SSM_HAZELCAST_CONFIG_FILE_NAME = "ssm-hazelcast-configuration.xml";
	  private static final String SSM_HAZELCAST_INSTANCE_NAME = "SSM_HAZELCAST_INSTANCE";
	  private static HazelcastInstance HZ_INSTANCE;
	  
	  static
	  {
	    String hazelcastConfigurationFilePath = null;
	    InputStream is = null;
	    try
	    {
	    	//System.out.println("ClassLoader ----------------> " + HazelCastManager.class.getClassLoader().getResource("ssm-hazelcast-configuration.xml"));
	      //hazelcastConfigurationFilePath = HazelCastManager.class.getClassLoader().getResource("ssm-hazelcast-configuration.xml").getPath();
	      //is = new FileInputStream(hazelcastConfigurationFilePath);
	      is = new FileInputStream("/ltm/rt/jboss/jboss-eap-6.3/modules/com/igt/solution/ssm/hazelcast/configuration/main/ssm-hazelcast-configuration.xml");
	      System.out.println("Hazelcast configuration file path -----> " +hazelcastConfigurationFilePath);
	      Config hazelcastSSMConfiguration = new XmlConfigBuilder(is).build();
	      hazelcastSSMConfiguration.setInstanceName("SSM_HAZELCAST_INSTANCE");
	      System.out.println("Creating Hazelcast instance");
	      HZ_INSTANCE = Hazelcast.getOrCreateHazelcastInstance(hazelcastSSMConfiguration);
	      System.out.println("instance created");
	    }
	    catch (FileNotFoundException e)
	    {
	    	System.out.println(e);
	    }
	    finally
	    {
	      try
	      {
	        if (is != null) {
	          is.close();
	        }
	      }
	      catch (IOException e)
	      {
	    	  System.out.println(e);
	        throw new ExceptionInInitializerError(e);
	      }
	    }
	  }
	  
	  public static IMap<String, String> getStringTest()
	  {
		  IMap<String, String> hzmap = HZ_INSTANCE.getMap("HZ_STRING_TEST");
		  hzmap.put("test1", "This is the first test");
		  hzmap.put("test2", "This is the second test");
	    return hzmap;
	    
	  }
}
