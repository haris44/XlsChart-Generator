import java.util.HashMap;

public class Main {

	public static void main(String[] args) {

		String[] columnsData = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov"};

		HashMap<String, Double[]> dataMap = new HashMap<String, Double[]>();

		dataMap.put("Tokyo", new java.lang.Double[]{7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9});
		dataMap.put("NewYork",  new java.lang.Double[]{-0.2, 0.8, 5.7, 11.3, 17.0, 22.0, 24.8, 24.1, 20.1, 14.1, 8.6});
		dataMap.put("Berlin", new java.lang.Double[]{-0.9, 0.6, 3.5, 8.4, 13.5, 17.0, 18.6, 17.9, 14.3, 9.0, 3.9});

		new XlsGenerator("export/workbook.xls", columnsData, dataMap);

	}
	

}