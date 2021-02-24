package Servlets.admin;

import db.DBManager;
import db.entity.Delivery;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


/**
 * Save report servlet
 *
 * @author  Vlad Salimovskyi
 */
@WebServlet(name = "/saveReport")
public class ServletSaveReport extends HttpServlet {


    public static Logger logger = Logger.getLogger("ServletSaveReport");
    private final DBManager dbManager = DBManager.getInstance();
    private static final String SCR_PAGE = "ISO-8859-1";
    private static final String DST_PAGE = "UTF-8" ;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Connection connection = dbManager.getConnection();
        String date = encode(request.getParameter("dateSend"),SCR_PAGE,DST_PAGE);
        String city = encode(request.getParameter("cityFrom"),SCR_PAGE,DST_PAGE);
        List<Delivery> deliveryList = null;

        try {
            deliveryList = dbManager.findReports(connection,date,city);
            logger.info("Find reports by date and send city");
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        }

        HSSFWorkbook workbook = new HSSFWorkbook();

        /* creating page with name "Просто лист"*/
        HSSFSheet sheet = workbook.createSheet("Просто лист");

        // rows counter
        int rowNum = 0;

        // create name to columns (this will be the first row of page)
        Row row = sheet.createRow(rowNum);
        row.createCell(0).setCellValue("Имя получателя");
        row.createCell(1).setCellValue("Фамилия получателя");
        row.createCell(2).setCellValue("Адрес");
        row.createCell(3).setCellValue("Дата отправления");
        row.createCell(4).setCellValue("Дата получения");
        row.createCell(5).setCellValue("Цена");
        row.createCell(6).setCellValue("Город - отправитель");
        row.createCell(7).setCellValue("Город - получатель");
        row.createCell(8).setCellValue("Статус");

        // fill page with data
        for (Delivery dataModel : deliveryList) {
            createSheetHeader(sheet, ++rowNum, dataModel);
        }

        try (FileOutputStream out = new FileOutputStream(new File("D:\\IDEA Projects\\DeliveryTask\\Reports.xls"))) {
            workbook.write(out);
            logger.info("Writing into a new xls file report");
        } catch (IOException e) {
            logger.debug(e.getMessage());
        }

        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition","attachment; filename=Report.xls");

        File my_file = new File("D:\\IDEA Projects\\DeliveryTask\\Reports.xls");

        // send file to response
        OutputStream out = response.getOutputStream();
        FileInputStream in = new FileInputStream(my_file);

        byte[] buffer = new byte[4096];
        int length;

        while ((length = in.read(buffer)) > 0){
            out.write(buffer, 0, length);
        }

        // free resources
        in.close();
        out.flush();

    }

    /**
     * fill page with data
     */
    private static void createSheetHeader(HSSFSheet sheet, int rowNum, Delivery dataModel) {

        Row row = sheet.createRow(rowNum);
        row.createCell(0).setCellValue(dataModel.getReceiverName());
        row.createCell(1).setCellValue(dataModel.getReceiverSurname());
        row.createCell(2).setCellValue(dataModel.getAddress());
        row.createCell(3).setCellValue(dataModel.getSendDate());
        row.createCell(4).setCellValue(dataModel.getDeliveryDate());
        row.createCell(5).setCellValue(dataModel.getPrice());
        row.createCell(6).setCellValue(dataModel.getCityFrom());
        row.createCell(7).setCellValue(dataModel.getCityTo());
        row.createCell(8).setCellValue(dataModel.getStatus());
    }

    /**
     * Get parameter from jsp with UTF-8
     */
    public String encode(String src, String defpage ,String codepage) {
        String answer;
        try {
            answer= new String(src.getBytes(defpage), codepage);
        } catch (Throwable e){
            answer=src;
        }
        return answer;
    }

}
