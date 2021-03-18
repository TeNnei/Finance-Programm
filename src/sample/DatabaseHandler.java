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

//    public void writerUser(User org){
//            String insert = "INSERT INTO " + Constant.TABLE_OF_ORGANIZATION + "(" + Constant.NAME_OF_ORGANIZATIONS
//                    + "," + Constant.MONEY_OF_ORGANIZATION + "," + Constant.VMONEY_OF_ORGANIZATION +")" + "VALUES(?,?,?)";
//        try {
//            PreparedStatement prst = getDbConnection().prepareStatement(insert);
//            prst.setString(1, org.getOrganization());
//            prst.setInt(2, org.getMoney());
//            prst.setInt(3, org.getVmoney());
//            prst.executeUpdate();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
//
//   public ResultSet getInformation (User auth){
//  ResultSet resultSet = null;
//  String select = "SELECT * FROM " + Constant.TABLE_OF_ORGANIZATION + " WHERE "
//     + Constant.NAME_OF_ORGANIZATIONS + "=?";
//       try {
//           PreparedStatement prst0 = getDbConnection().prepareStatement(select);
//           prst0.setString(1, auth.getOrganization());
//
//          resultSet = prst0.executeQuery();
//       } catch (SQLException throwables) {
//           throwables.printStackTrace();
//       }
//       return resultSet;
//   }
//
//    public ResultSet setInformation (User auth1){
//        ResultSet resultSet = null;
//        String select1 = "UPDATE " + Constant.TABLE_OF_ORGANIZATION + " SET " + Constant.MONEY_OF_ORGANIZATION + " = " + Constant.MONEY_OF_ORGANIZATION + " - ?" + " WHERE " + Constant.NAME_OF_ORGANIZATIONS + " =?;";
//        try {
//            PreparedStatement prst0 = getDbConnection().prepareStatement(select1);
//            prst0.setString(2, auth1.getOrganization());
//            prst0.setInt(1, auth1.getMoney());
//
//           prst0.executeUpdate();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return resultSet;
//    }
//    public ResultSet setInformation1 (User auth2){
//        ResultSet resultSet = null;
//        String select1 = "UPDATE " + Constant.TABLE_OF_ORGANIZATION + " SET " + Constant.MONEY_OF_ORGANIZATION + " = " + Constant.MONEY_OF_ORGANIZATION + " + ?" + " WHERE " + Constant.NAME_OF_ORGANIZATIONS + " =?;";
//        try {
//            PreparedStatement prst0 = getDbConnection().prepareStatement(select1);
//            prst0.setString(2, auth2.getOrganization());
//            prst0.setInt(1, auth2.getMoney());
//            prst0.executeUpdate();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return resultSet;
//    }
//    public ResultSet DeleteInformation (User auth1){
//        ResultSet resultSet = null;
//        String select1 = "DELETE FROM " + Constant.TABLE_OF_ORGANIZATION + " WHERE "  + Constant.NAME_OF_ORGANIZATIONS + " =?;";
//        try {
//            PreparedStatement prst0 = getDbConnection().prepareStatement(select1);
//            prst0.setString(1, auth1.getOrganization());
//            prst0.executeUpdate();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return resultSet;
//    }
//    public ResultSet setInformation2 (User auth1){
//        ResultSet resultSet = null;
//        String select1 = "UPDATE " + Constant.TABLE_OF_ORGANIZATION + " SET " + Constant.VMONEY_OF_ORGANIZATION + " = " + Constant.VMONEY_OF_ORGANIZATION + " - ?" + " WHERE " + Constant.NAME_OF_ORGANIZATIONS + " =?;";
//        try {
//            PreparedStatement prst0 = getDbConnection().prepareStatement(select1);
//            prst0.setString(2, auth1.getOrganization());
//            prst0.setInt(1, auth1.getMoney());
//
//            prst0.executeUpdate();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return resultSet;
//    }
//    public ResultSet setInformation3 (User auth2){
//        ResultSet resultSet = null;
//        String select1 = "UPDATE " + Constant.TABLE_OF_ORGANIZATION + " SET " + Constant.VMONEY_OF_ORGANIZATION + " = " + Constant.VMONEY_OF_ORGANIZATION + " + ?" + " WHERE " + Constant.NAME_OF_ORGANIZATIONS + " =?;";
//        try {
//            PreparedStatement prst0 = getDbConnection().prepareStatement(select1);
//            prst0.setString(2, auth2.getOrganization());
//            prst0.setInt(1, auth2.getMoney());
//
//            prst0.executeUpdate();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return resultSet;
//    }
//
//    public void WriteOperation(User2 org1){
//        String insert = "INSERT INTO " + Constant2.TABLE_OF_ORGANIZATION + "(" + Constant2.FROM_WHOM
//                + "," + Constant2.TO_WHOM + "," + Constant2.AMOUNT_OF_TRANSACTION + "," + Constant2.COMMENTS+ "," + Constant2.DATE_OF_TRANSACTION +")" + " VALUES(?,?,?,?,?)";
//        try {
//            PreparedStatement prst = getDbConnection().prepareStatement(insert);
//            prst.setString(1, org1.getFromWhom());
//            prst.setString(2, org1.getToWhom());
//            prst.setString(3, org1.getAmount());
//            prst.setString(4, org1.getComments());
//            Date utilDate = new Date();
//            java.sql.Date date = new java.sql.Date(utilDate.getTime());
//            prst.setDate(5, date);
//            prst.executeUpdate();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
//
//    public void RegistUser (User3 user){
//        String insert = "INSERT INTO " + Constant3.TABLE_OF_USER + "(" + Constant3.USER_NAME
//                + "," + Constant3.SECOND_USER_NAME + "," + Constant3.LOGIN + "," + Constant3.PASSWORD + "," + Constant3.PASSPORT_NUMBER +")" + "VALUES(?,?,?,?,?)";
//        try {
//            PreparedStatement prst = getDbConnection().prepareStatement(insert);
//            prst.setString(1, user.userName());
//            prst.setString(2, user.secondName());
//            prst.setString(3, user.Loginin());
//            prst.setString(4, user.password());
//            prst.setInt(5, user.getPassportNumber());
//            prst.executeUpdate();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
//

        public Boolean checkCode(ConsolidInfin auth) {
        ResultSet resultSet;
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
        String updateOut = "UPDATE consolid SET saldo_out_usd = (SELECT saldo_in_usd FROM consolid WHERE " + ConsolidInf.CODE + " =?) + ((SELECT debit_usd FROM consolid WHERE " + ConsolidInf.CODE + " =?) - (SELECT credit_usd FROM consolid WHERE " + ConsolidInf.CODE + " =?))" + " WHERE " + ConsolidInf.CODE + " =?";
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
        String updateOut = "UPDATE consolid SET difference_usd = (SELECT saldo_out_usd FROM consolid WHERE " + ConsolidInf.CODE + " =?) - (SELECT saldo_in_usd FROM consolid WHERE " + ConsolidInf.CODE + " =?)" + " WHERE " + ConsolidInf.CODE + " =?";
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
}
