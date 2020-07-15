
package jdbcUtil_CompositeQuery;

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

	public static String get_WhereCondition66(Map<String, String[]> map) {
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
	
	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value[] = map.get(key);
			for (int i = 0; i < value.length; i++) {
				if (value[i] != null && value[i].trim().length() != 0 && !"action".equals(key)) {
					count++;
					String aCondition = get_aCondition_For_Oracle(key, value[i].trim());

					if (count == 1)
						whereCondition.append(" where (" + aCondition);
					else if ("bkstatus".equals(key) && count != 1)
						whereCondition.append(" or " + aCondition);
					else
						whereCondition.append(" and (" + aCondition);

					System.out.println("count = " + count);
				}
			}
			if(value.length==1 && value[0].trim().length() == 0)
				continue;
			if(!"action".equals(key))
				whereCondition.append(")");
		}
		
		return whereCondition.toString();
	}

}
