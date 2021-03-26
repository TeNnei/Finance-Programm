package sample;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


public class InsertFromExcel {

       public void InsertFromExcel (String a){
            String fileName="D:\\Finance-Programm\\Finance-Programm\\Main_Information.xls";
            Vector dataHolder = read(fileName);
            saveToDatabase(dataHolder);
        }
        public static Vector read(String fileName){
            Vector cellVectorHolder = new Vector();
            try{
                FileInputStream myInput = new FileInputStream(fileName);
                HSSFWorkbook myWorkBook = new HSSFWorkbook(myInput);
                HSSFSheet mySheet = myWorkBook.getSheetAt(0);
                Iterator rowIter = mySheet.rowIterator();
                while(rowIter.hasNext()){
                    HSSFRow myRow = (HSSFRow) rowIter.next();
                    Iterator cellIter = myRow.cellIterator();
                    List list = new ArrayList();
                    while(cellIter.hasNext()){
                        HSSFCell myCell = (HSSFCell) cellIter.next();
                        list.add(myCell);
                    }
                    cellVectorHolder.addElement(list);
                }
            }catch (Exception e){e.printStackTrace(); }
            return cellVectorHolder;
        }
        private static void saveToDatabase(Vector dataHolder) {
            String contract_number = "";
            String contract = "";
            String debit= "";
            String credit = "";
            String date_of = "";
            String coments = "";
            String som = "";
            String usd = "";
            System.out.println(dataHolder);

            Iterator iterator = dataHolder.iterator();
            iterator.next(); // пропустит шапку
            while(iterator.hasNext())
            {
                iterator.next();
                List list = (List) iterator.next();
                contract_number = list.get(0).toString();
                contract = list.get(1).toString();
                debit = list.get(2).toString();
                credit = list.get(3).toString();
                date_of = list.get(4).toString();
                coments = list.get(5).toString();
                som = list.get(6).toString();
                usd = list.get(7).toString();

               int a  = (int) Double.parseDouble(date_of);


                try {
                    Class.forName("org.postgresql.Driver");
                    Connection con = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "987346521");
                    PreparedStatement stmt=con.prepareStatement("INSERT INTO maininf(contract_number,contract,debit,kredit, date_of, coments, som, usd) VALUES(?,?,?,?,(date '1899-12-30' + ?::int * interval '1' day),?,?,?)");
                    stmt.setString(1, contract_number);
                    stmt.setString(2, contract);
                    stmt.setString(3, debit);
                    stmt.setString(4, credit);
                    stmt.setInt(5, a);
                    stmt.setString(6, coments);
                    stmt.setBigDecimal(7, BigDecimal.valueOf(Double.parseDouble(som)));
                    stmt.setBigDecimal(8, BigDecimal.valueOf(Double.parseDouble(usd)));
                    stmt.executeUpdate();
                    System.out.println("Data is inserted");
                    stmt.close();
                    con.close();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
