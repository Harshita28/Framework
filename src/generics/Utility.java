package generics;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utility {
	
	public static String getFormatedDateTime()
	{
		SimpleDateFormat sd = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		return sd.format(new Date());
		
	}
}
