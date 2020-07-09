
package jdbc.util.CompositeQuery;

import java.util.*;

public class jdbcUtil_CompositeQuery_Booking {

	public static String get_aCondition_For_Oracle(String columnName, String value) {

		String aCondition = null;

		if ("bkno".equals(columnName) || "bkstatus".equals(columnName)) 
			aCondition = columnName + "=" + value;
		else if ("bkperiod".equals(columnName) || "memno".equals(columnName)) 
			aCondition = columnName + " like '%" + value + "%'";
		else if ("bkdate".equals(columnName))                          
			aCondition = "to_char(" + columnName + ",'yyyy-mm-dd')='" + value + "'";

		return aCondition + " ";
	}

	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0	&& !"action".equals(key)) {
				count++;
				String aCondition = get_aCondition_For_Oracle(key, value.trim());

				if (count == 1)
					whereCondition.append(" where " + aCondition);
				else
					whereCondition.append(" and " + aCondition);

				System.out.println("count = " + count);
			}
		}
		
		return whereCondition.toString();
	}

}
