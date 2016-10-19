import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.charts.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class XlsGenerator {

	public XlsGenerator(String path, String[] columnsData, HashMap<String, Double[]> dataMap) {
		try {
			Workbook wb = new XSSFWorkbook();
			Sheet sheet =  wb.createSheet("new sheet");

			Drawing drawing = sheet.createDrawingPatriarch();
			ClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 0, 5, 10, 15);

			Chart chart = drawing.createChart(anchor);
			ChartLegend legend = chart.getOrCreateLegend();
			legend.setPosition(LegendPosition.TOP_RIGHT);

			LineChartData data = chart.getChartDataFactory().createLineChartData();

			ChartAxis bottomAxis = chart.getChartAxisFactory().createCategoryAxis(AxisPosition.BOTTOM);
			ValueAxis leftAxis = chart.getChartAxisFactory().createValueAxis(AxisPosition.LEFT);
			leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);

			int line = 1;
			int column = 1;

			// Generate title
			insertCellLine(sheet, line, column, "", columnsData);
			ChartDataSource<String> xs = DataSources.fromStringCellRange(sheet, new CellRangeAddress(line, line, column + 1, columnsData.length + 1));
			line++;

			for(Map.Entry<String, Double[]> entry : dataMap.entrySet()) {
				String key = entry.getKey();
				Double[] value = entry.getValue();
				insertCellLine(sheet, line, column, key, value);

				// Generate Series
				ChartDataSource<Number> y = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(line, line, column + 1, value.length + 1));
				LineChartSeries chartSerie = data.addSeries(xs, y);
				chartSerie.setTitle(key);
				line++;
			}


			chart.plot(data, bottomAxis, leftAxis);

			FileOutputStream fileOut = new FileOutputStream(path);
			wb.write(fileOut);
			fileOut.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void insertCellLine(Sheet sheet, int line, int startColumn, String title, String[] data) {
		Row row = sheet.createRow(line);
		int column = startColumn + 1;
		Cell cell = row.createCell(startColumn);
		cell.setCellValue(title);

		for (String colData: data) {
			cell = row.createCell(column);
			cell.setCellValue(title);
			cell.setCellValue(colData);
			column++;
		}
	}

	private void insertCellLine(Sheet sheet, int line, int startColumn, String title, Double[] data) {
		Row row = sheet.createRow(line);
		int column = startColumn + 1;
		Cell cell = row.createCell(startColumn);
		cell.setCellValue(title);

		for (Double colData: data) {
			cell = row.createCell(column);
			cell.setCellValue(title);
			cell.setCellValue(colData);
			column++;
		}
	}
}