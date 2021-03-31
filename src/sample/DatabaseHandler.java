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

        public Boolean checkUser(OperatorsCheck auth) {
        ResultSet resultSet = null;
        String select = "SELECT * FROM " + Operators.TABLE_OF_OPERATORS + " WHERE "
                + Operators.LOGIN + "=? AND " + Operators.PASSWORD + "=?";
        try {
            PreparedStatement prst0 = getDbConnection().prepareStatement(select);
            prst0.setString( 1, auth.getLogin());
            prst0.setString(2, auth.getPassword());
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
            prst.setString(3, org.getDebit());
            prst.setString(4, org.getKredit());
            prst.setDate(5, org.getDate());
            prst.setString(6, org.getComments());
            prst.setBigDecimal(7, org.getSom());
            prst.setBigDecimal(8, org.getUsd());
            prst.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void consolidUpdateDifference (ConsolidInfin auth2){
        String updateOut = "UPDATE consolid SET defference = (SELECT saldo_out_som FROM consolid WHERE " + ConsolidInf.CODE + " =?) - (SELECT saldo_in_som FROM consolid WHERE " + ConsolidInf.CODE + " =?)" + " WHERE " + ConsolidInf.CODE + " =?";
        try {
            PreparedStatement prepare = getDbConnection().prepareStatement(updateOut);
            prepare.setBigDecimal(1, auth2.getDebit());
            prepare.setBigDecimal(2, auth2.getDebit());
            prepare.setBigDecimal (3, auth2.getDebit());
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
            prepare.setBigDecimal(1, auth2.getDebit());
            prepare.setBigDecimal(2, auth2.getDebit());
            prepare.setBigDecimal (3, auth2.getDebit());
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
            prepare.setString(2, auth3.getCode());
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
            prepare.setString(2, auth3.getCode());
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
            prepare.setString(2, auth3.getCode());
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
            prepare.setString(2, auth3.getDebit());
            prepare.setBigDecimal(3, auth3.getSom());
            prepare.setBigDecimal(4, auth3.getUsd());
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
            prepare.setString(2, auth3.getDebit());
            prepare.setBigDecimal(3, auth3.getSom());
            prepare.setBigDecimal(4, auth3.getUsd());
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
            prepare.setString(2, auth3.getDebit());
            prepare.setBigDecimal(3, auth3.getSom());
            prepare.setBigDecimal(4, auth3.getUsd());
            prepare.executeUpdate();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void consolidinfDate (SubTotalGetterSetter dateinf) throws SQLException {
        String dateInfConsolid = "INSERT INTO consolid (" + ConsolidInf.CODE + ") " + " SELECT code FROM summary_subtotal GROUP BY code ON CONFLICT (code) DO NOTHING;"
                + "UPDATE consolid SET " +ConsolidInf.DEBET + "= (SELECT SUM (debit_som) FROM summary_subtotal WHERE today >= ? AND today <= ? AND code = consolid.code GROUP BY code);";
        PreparedStatement dateinfTable = getDbConnection().prepareStatement(dateInfConsolid);
        dateinfTable.setDate(1, dateinf.getTodayFrom());
        dateinfTable.setDate(2, dateinf.getTodayTo());
        dateinfTable.executeUpdate();
    }
    public void consolidinfDateSaldoInSom (SubTotalGetterSetter dateinf) throws SQLException {
        String dateInfConsolid = "UPDATE consolid SET " +ConsolidInf.SALDO_IN_SOM + "= COALESCE((SELECT saldo_out_som FROM summary_subtotal WHERE code = consolid.code AND today < ? ORDER BY today DESC LIMIT 1), 0);";
        PreparedStatement dateinfTable = getDbConnection().prepareStatement(dateInfConsolid);
        dateinfTable.setDate(1, dateinf.getTodayTo());
        dateinfTable.executeUpdate();
    }
    public void consolidinfDateCredit (SubTotalGetterSetter dateinf) throws SQLException {
        String dateInfConsolid = "UPDATE consolid SET " +ConsolidInf.KREDIT + "= COALESCE((SELECT SUM (credit_som) FROM summary_subtotal WHERE today >= ? AND today <= ? AND code = consolid.code GROUP BY code), 0);";
        PreparedStatement dateinfTable = getDbConnection().prepareStatement(dateInfConsolid);
        dateinfTable.setDate(1, dateinf.getTodayFrom());
        dateinfTable.setDate(2, dateinf.getTodayTo());
        dateinfTable.executeUpdate();
    }
    public void consolidinfDateSaldoOutSom (SubTotalGetterSetter dateinf) throws SQLException {
        String dateInfConsolid = "UPDATE consolid SET " +ConsolidInf.SALDO_OUT_SOM + "= COALESCE((SELECT SUM (saldo_out_som) FROM summary_subtotal WHERE today >= ? AND today <= ? AND code = consolid.code GROUP BY code), 0);";
        PreparedStatement dateinfTable = getDbConnection().prepareStatement(dateInfConsolid);
        dateinfTable.setDate(1, dateinf.getTodayFrom());
        dateinfTable.setDate(2, dateinf.getTodayTo());
        dateinfTable.executeUpdate();
    }
    public void consolidinfDateSaldoInUsd (SubTotalGetterSetter dateinf) throws SQLException {
        String dateInfConsolid = "UPDATE consolid SET " +ConsolidInf.SALDO_IN_USD + "= COALESCE((SELECT saldo_out_usd FROM summary_subtotal WHERE code = consolid.code AND today < ? ORDER BY today DESC LIMIT 1), 0);";
        PreparedStatement dateinfTable = getDbConnection().prepareStatement(dateInfConsolid);
        dateinfTable.setDate(1, dateinf.getTodayTo());
        dateinfTable.executeUpdate();
    }
    public void consolidinfDateDebitUsd (SubTotalGetterSetter dateinf) throws SQLException {
        String dateInfConsolid = "UPDATE consolid SET " +ConsolidInf.DEBET_USD + "= COALESCE((SELECT SUM (debit_usd) FROM summary_subtotal WHERE today >= ? AND today <= ? AND code = consolid.code GROUP BY code), 0);";
        PreparedStatement dateinfTable = getDbConnection().prepareStatement(dateInfConsolid);
        dateinfTable.setDate(1, dateinf.getTodayFrom());
        dateinfTable.setDate(2, dateinf.getTodayTo());
        dateinfTable.executeUpdate();
    }
    public void consolidinfDateCreditUsd (SubTotalGetterSetter dateinf) throws SQLException {
        String dateInfConsolid = "UPDATE consolid SET " +ConsolidInf.KREDIT_USD + "= COALESCE((SELECT SUM (credit_usd) FROM summary_subtotal WHERE today >= ? AND today <= ? AND code = consolid.code GROUP BY code), 0);";
        PreparedStatement dateinfTable = getDbConnection().prepareStatement(dateInfConsolid);
        dateinfTable.setDate(1, dateinf.getTodayFrom());
        dateinfTable.setDate(2, dateinf.getTodayTo());
        dateinfTable.executeUpdate();
    }
    public void consolidinfDateSaldoOutUsd (SubTotalGetterSetter dateinf) throws SQLException {
        String dateInfConsolid = "UPDATE consolid SET " +ConsolidInf.SALDO_OUT_USD + " = COALESCE((SELECT SUM (saldo_out_usd) FROM summary_subtotal WHERE today >= ? AND today <= ? AND code = consolid.code GROUP BY code), 0);";
        PreparedStatement dateinfTable = getDbConnection().prepareStatement(dateInfConsolid);
        dateinfTable.setDate(1, dateinf.getTodayFrom());
        dateinfTable.setDate(2, dateinf.getTodayTo());
        dateinfTable.executeUpdate();
    }
}
