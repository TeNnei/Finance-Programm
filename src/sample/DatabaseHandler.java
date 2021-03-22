package sample;

import java.sql.*;

public class DatabaseHandler {
    static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/postgres";
    static final String USER = "postgres";
    static final String PASS = "987346521";

    public static Connection getDbConnection() {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Ошибка нет драйвера");
            e.printStackTrace();
        }
        System.out.println("Драйвер найден");
        Connection connection = null;
        try {
            connection = DriverManager
                    .getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            System.out.println("Ошибка");
            e.printStackTrace();
        }
        if (connection != null) {
            System.out.println("Бзаданных подключена");
        } else {
            System.out.println("Ошибка");
        }
        return   connection;
    }

        public Boolean checkCode(ConsolidInfin auth) {
        ResultSet resultSet = null;
        String select = "SELECT * FROM " + ConsolidInf.TABLE_OF_SUMMARY + " WHERE "
                + ConsolidInf.CODE + "=?";
        try {
            PreparedStatement prst0 = getDbConnection().prepareStatement(select);
            prst0.setInt( 1, auth.getCode());
            resultSet = prst0.executeQuery();
            Boolean checkResult = resultSet.next();
            resultSet.close();
            return checkResult;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public void writeInProgram(ProgramData org){
        String insert = "INSERT INTO " + MainInf.TABLE_OF_INF + "(" + MainInf.CONTRACT_NUMBER
                + ", " + MainInf.CONTRACT + ", " + MainInf.DEBIT + ", " + MainInf.KREDIT + ", " + MainInf.DATE + ", " + MainInf.COMMENTS + ", " + MainInf.SOM + ", " + MainInf.USD +")" + "VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement prst = getDbConnection().prepareStatement(insert);
            prst.setString(1, org.getContract_number());
            prst.setString(2, org.getContract());
            prst.setInt(3, org.getDebit());
            prst.setInt(4, org.getKredit());
            prst.setDate(5, org.getDate());
            prst.setString(6, org.getComments());
            prst.setInt(7, org.getSom());
            prst.setInt(8, org.getUsd());
            prst.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void writeInProgram2(ConsolidInfin org){
        String insert = "INSERT INTO " + ConsolidInf.TABLE_OF_SUMMARY + "(" + ConsolidInf.CODE
                + ", " + ConsolidInf.ADDITIONAL_SCORE + ", " + ConsolidInf.CATEGORY + "," + ConsolidInf.NAME_SCORE + ", "  + ConsolidInf.SALDO_IN_SOM
                + ", " + ConsolidInf.DEBET + ", " + ConsolidInf.KREDIT + ", " + ConsolidInf.SALDO_OUT_SOM + ", " + ConsolidInf.DIFFERENCE + "," + ConsolidInf.SALDO_IN_USD
                + ", " + ConsolidInf.DEBET_USD + ", " + ConsolidInf.KREDIT_USD + ", " + ConsolidInf.SALDO_OUT_USD + "," + ConsolidInf.DIFFERENCE_USD +")" + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement prst = getDbConnection().prepareStatement(insert);
            prst.setInt(1, org.getCode());
            prst.setString(2, org.getAdittional_score());
            prst.setString(3, org.getCategory());
            prst.setString(4, org.getName_of_score());
            prst.setInt(5, org.getSaldo_in_som());
            prst.setInt(6, org.getDebit());
            prst.setInt(7, org.getKredit());
            prst.setInt(8, org.getSaldo_out_som());
            prst.setInt(9, org.getDifference());
            prst.setInt(10, org.getSaldo_in_usd());
            prst.setInt(11, org.getDebit_usd());
            prst.setInt(12, org.getKredit_usd());
            prst.setInt(13, org.getSaldo_out_usd());
            prst.setInt(14, org.getDifference_usd());
            prst.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void  consolidUpdate (ConsolidInfin auth1) {
        String update = "UPDATE " + ConsolidInf.TABLE_OF_SUMMARY +  " SET " + ConsolidInf.SALDO_IN_SOM + " = (SELECT SUM (som) FROM maininf WHERE "
                + MainInf.DEBIT + " =?" + ")" + " - (SELECT SUM(som) FROM maininf WHERE " + MainInf.KREDIT +" = ?" + ")" + " WHERE " + ConsolidInf.CODE + " = ?";
        try {
            PreparedStatement prepere = getDbConnection().prepareStatement(update);
            prepere.setInt(1, auth1.getDebit());
            prepere.setInt(2, auth1.getDebit());
            prepere.setInt(3, auth1.getDebit());
            prepere.executeUpdate();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void  consolidUpdatedebit (ConsolidInfin auth1) {
        String update = "UPDATE " + ConsolidInf.TABLE_OF_SUMMARY +  " SET " + ConsolidInf.DEBET + " = (SELECT SUM (som) FROM maininf WHERE "
                + MainInf.DEBIT + " =?)" + " WHERE " + ConsolidInf.CODE +" =?";
        try {
            PreparedStatement prepere = getDbConnection().prepareStatement(update);
            prepere.setInt(1, auth1.getDebit());
            prepere.setInt(2, auth1.getDebit());
            prepere.executeUpdate();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void  consolidUpdatekredit (ConsolidInfin auth1) {
        String update = "UPDATE " + ConsolidInf.TABLE_OF_SUMMARY +  " SET " + ConsolidInf.KREDIT + " = (SELECT SUM (som) FROM maininf WHERE "
                + MainInf.KREDIT + " =?)" + " WHERE " + ConsolidInf.CODE +" =?";
        try {
            PreparedStatement prepere = getDbConnection().prepareStatement(update);
            prepere.setInt(1, auth1.getDebit());
            prepere.setInt(2, auth1.getDebit());
            prepere.executeUpdate();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void consolidUpdateOut (ConsolidInfin auth2){
        String updateOut = "UPDATE consolid SET saldo_out_som = (SELECT saldo_in_som FROM consolid WHERE " + ConsolidInf.CODE + " =?) + ((SELECT debet FROM consolid WHERE " + ConsolidInf.CODE + " =?) - (SELECT kredit FROM consolid WHERE " + ConsolidInf.CODE + " =?))" + " WHERE " + ConsolidInf.CODE + " =?";
        try {
            PreparedStatement prepare = getDbConnection().prepareStatement(updateOut);
            prepare.setInt(1, auth2.getDebit());
            prepare.setInt(2, auth2.getDebit());
            prepare.setInt (3, auth2.getDebit());
            prepare.setInt(4, auth2.getDebit());
            prepare.executeUpdate();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void consolidUpdateDifference (ConsolidInfin auth2){
        String updateOut = "UPDATE consolid SET defference = (SELECT saldo_out_som FROM consolid WHERE " + ConsolidInf.CODE + " =?) - (SELECT saldo_in_som FROM consolid WHERE " + ConsolidInf.CODE + " =?)" + " WHERE " + ConsolidInf.CODE + " =?";
        try {
            PreparedStatement prepare = getDbConnection().prepareStatement(updateOut);
            prepare.setInt(1, auth2.getDebit());
            prepare.setInt(2, auth2.getDebit());
            prepare.setInt (3, auth2.getDebit());
            prepare.executeUpdate();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void  consolidUpdateUsd (ConsolidInfin auth1) {
        String update = "UPDATE " + ConsolidInf.TABLE_OF_SUMMARY +  " SET " + ConsolidInf.SALDO_IN_USD + " = (SELECT SUM (usd) FROM maininf WHERE "
                + MainInf.DEBIT + " =?" + ")" + " - (SELECT SUM(usd) FROM maininf WHERE " + MainInf.KREDIT +" = ?" + ")" + " WHERE " + ConsolidInf.CODE + " = ?";
        try {
            PreparedStatement prepere = getDbConnection().prepareStatement(update);
            prepere.setInt(1, auth1.getDebit());
            prepere.setInt(2, auth1.getDebit());
            prepere.setInt(3, auth1.getDebit());
            prepere.executeUpdate();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void  consolidUpdatedebitUsd (ConsolidInfin auth1) {
        String update = "UPDATE " + ConsolidInf.TABLE_OF_SUMMARY +  " SET " + ConsolidInf.DEBET_USD + " = (SELECT SUM (usd) FROM maininf WHERE "
                + MainInf.DEBIT + " =?)" + " WHERE " + ConsolidInf.CODE +" =?";
        try {
            PreparedStatement prepere = getDbConnection().prepareStatement(update);
            prepere.setInt(1, auth1.getDebit());
            prepere.setInt(2, auth1.getDebit());
            prepere.executeUpdate();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void  consolidUpdatekreditUsd (ConsolidInfin auth1) {
        String update = "UPDATE " + ConsolidInf.TABLE_OF_SUMMARY +  " SET " + ConsolidInf.KREDIT_USD + " = (SELECT SUM (usd) FROM maininf WHERE "
                + MainInf.KREDIT + " =?)" + " WHERE " + ConsolidInf.CODE +" =?";
        try {
            PreparedStatement prepere = getDbConnection().prepareStatement(update);
            prepere.setInt(1, auth1.getDebit());
            prepere.setInt(2, auth1.getDebit());
            prepere.executeUpdate();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void consolidUpdateOutUsd (ConsolidInfin auth2){
        String updateOut = "UPDATE consolid SET saldo_out_usd = (SELECT saldo_in_usd FROM consolid WHERE " + ConsolidInf.CODE
                + " =?) + ((SELECT debit_usd FROM consolid WHERE " + ConsolidInf.CODE + " =?) - (SELECT credit_usd FROM consolid WHERE "
                + ConsolidInf.CODE + " =?))" + " WHERE " + ConsolidInf.CODE + " =?";
        try {
            PreparedStatement prepare = getDbConnection().prepareStatement(updateOut);
            prepare.setInt(1, auth2.getDebit());
            prepare.setInt(2, auth2.getDebit());
            prepare.setInt (3, auth2.getDebit());
            prepare.setInt(4, auth2.getDebit());
            prepare.executeUpdate();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void consolidUpdateDifferenceUsd (ConsolidInfin auth2){
        String updateOut = "UPDATE consolid SET difference_usd = (SELECT saldo_out_usd FROM consolid WHERE "
                + ConsolidInf.CODE + " =?) - (SELECT saldo_in_usd FROM consolid WHERE " + ConsolidInf.CODE
                + " =?)" + " WHERE " + ConsolidInf.CODE + " =?";
        try {
            PreparedStatement prepare = getDbConnection().prepareStatement(updateOut);
            prepare.setInt(1, auth2.getDebit());
            prepare.setInt(2, auth2.getDebit());
            prepare.setInt (3, auth2.getDebit());
            prepare.executeUpdate();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void consolidUpdateScore(ConsolidInfin auth3){
        String updateOut = "UPDATE consolid SET name_score =" + " ?" + " WHERE " + ConsolidInf.CODE + " =?";
        try {
            PreparedStatement prepare = getDbConnection().prepareStatement(updateOut);
            prepare.setString(1, auth3.getName_of_score());
            prepare.setInt(2, auth3.getCode());
            prepare.executeUpdate();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void consolidUpdateAdditionalScore(ConsolidInfin auth3){
        String updateOut = "UPDATE consolid SET additional_score =" + " ?" + " WHERE " + ConsolidInf.CODE + " =?";
        try {
            PreparedStatement prepare = getDbConnection().prepareStatement(updateOut);
            prepare.setString(1, auth3.getAdittional_score());
            prepare.setInt(2, auth3.getCode());
            prepare.executeUpdate();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void consolidUpdateCategory(ConsolidInfin auth3){
        String updateOut = "UPDATE consolid SET category =" + " ?" + " WHERE " + ConsolidInf.CODE + " =?";
        try {
            PreparedStatement prepare = getDbConnection().prepareStatement(updateOut);
            prepare.setString(1, auth3.getCategory());
            prepare.setInt(2, auth3.getCode());
            prepare.executeUpdate();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void mainTableUpdateContractNumber(MainTableInf auth3){
        String updateOut = "UPDATE maininf SET contract_number =" + " ?" + " WHERE " + MainInf.DEBIT + " =? AND " + MainInf.SOM + " =? AND " + MainInf.USD + " =?";
        try {
            PreparedStatement prepare = getDbConnection().prepareStatement(updateOut);
            prepare.setString(1, auth3.getContract_number());
            prepare.setInt(2, auth3.getDebit());
            prepare.setInt(3, auth3.getSom());
            prepare.setInt(4, auth3.getUsd());
            prepare.executeUpdate();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void mainTableUpdateContract(MainTableInf auth3){
        String updateOut = "UPDATE maininf SET contract =" + " ?" + " WHERE " + MainInf.DEBIT + " =? AND " + MainInf.SOM +" =? AND " + MainInf.USD + " =?";
        try {
            PreparedStatement prepare = getDbConnection().prepareStatement(updateOut);
            prepare.setString(1, auth3.getContract_number());
            prepare.setInt(2, auth3.getDebit());
            prepare.setInt(3, auth3.getSom());
            prepare.setInt(4, auth3.getUsd());
            prepare.executeUpdate();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void mainTableUpdateComments(MainTableInf auth3){
        String updateOut = "UPDATE maininf SET coments =" + " ?" + " WHERE " + MainInf.DEBIT + " =? AND " + MainInf.SOM + " =? AND " + MainInf.USD + " =?";
        try {
            PreparedStatement prepare = getDbConnection().prepareStatement(updateOut);
            prepare.setString(1, auth3.getContract_number());
            prepare.setInt(2, auth3.getDebit());
            prepare.setInt(3, auth3.getSom());
            prepare.setInt(4, auth3.getUsd());
            prepare.executeUpdate();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void  consolidTableUpdateDebitByDate(ProgramData auth31){
        String updateByDate = "SELECT debit, SUM(som) as debit FROM " + MainInf.TABLE_OF_INF
                + " WHERE date_of >=? AND date_of <=?"
                + " GROUP BY code";
        try {
            PreparedStatement prepare = getDbConnection().prepareStatement(updateByDate);
            prepare.setDate(1, auth31.getDate());
            prepare.setDate(2, auth31.getDate2());
        }catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }

//    public void  consolidTableUpdateKreditByDate(ConsolidInfin auth3, ProgramData auth31){
//        String updateByDate = "UPDATE "+ ConsolidInf.TABLE_OF_SUMMARY + " SET " + ConsolidInf.DEBET + " = (SELECT SUM (som) FROM maininf WHERE "
//                + MainInf.KREDIT +" =? AND " + MainInf.DATE +" BETWEEN =? AND =?)"  ;
//        try {
//            PreparedStatement prepare = getDbConnection().prepareStatement(updateByDate);
//            prepare.setInt(1, auth3.getKredit());
//            prepare.setDate(2, auth31.getDate());
//            prepare.setDate(3, auth31.getDate());
//        }catch (SQLException throwables)
//        {
//            throwables.printStackTrace();
//        }
//    }
}
