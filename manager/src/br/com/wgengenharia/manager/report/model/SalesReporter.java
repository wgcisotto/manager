package br.com.wgengenharia.manager.report.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import br.com.wgengenharia.manager.model.Product;
import br.com.wgengenharia.manager.model.Sale;
import br.com.wgengenharia.manager.report.AbstractManagerReport;
import br.com.wgengenharia.manager.report.ManagerReport;

public class SalesReporter extends AbstractManagerReport implements ManagerReport {

	
	private List<Sale> sales;
	
	public SalesReporter(List<Sale> sales) {
		this.sales =  sales;
	}
	
	@Override
	public InputStream generateReport() throws JRException {
		JasperReport report = JasperCompileManager.compileReport(getPath() + "listSales.jrxml");
		JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(parserSale()));
//		JasperExportManager.exportReportToPdfFile(print, "C:/Users/william.galindo/Desktop/firstDataTable.pdf");
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		JasperExportManager.exportReportToPdfStream(print, out);
		InputStream is = new ByteArrayInputStream(out.toByteArray());
		return is;
		
	}
	
	private List<SalesReport> parserSale(){
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		List<SalesReport> salesResport = new ArrayList<SalesReport>();
		for (Sale sale : sales) {
			if(sale.getProducts() == null || sale.getProducts().size() == 0){
				SalesReport saleReporter = new SalesReport();
				saleReporter.setDate(df.format(sale.getDate()));
				saleReporter.setDescription("Pagamento de Boleto");
				saleReporter.setPrice(sale.getTotal().toString());
				salesResport.add(saleReporter);
			}else{
				for (Product prd : sale.getProducts()) {
					SalesReport saleReporter = new SalesReport();
					saleReporter.setDate(df.format(sale.getDate()));
					saleReporter.setDescription(prd.getName());
					saleReporter.setPrice(prd.getPrice().toString());
					salesResport.add(saleReporter);
				}
			}
		}
		return salesResport;
	}

}
