package web;

import entity.SysParameter;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonLib {
     public static void main(String [] args)
     {
    	 String[] str = { "Jack", "Tom", "90", "true" };
         JSONArray json = JSONArray.fromObject(str);	
         System.err.println(json);
         
         SysParameter system = new SysParameter("1000", "10", "4", "10", "2", "1", "5");
         JSONObject jsonObject = JSONObject.fromObject(system);
         System.out.println(jsonObject);
         
     }
}
