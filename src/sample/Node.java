package sample;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

class Node {
    ArrayList <ExpencesUSD> category = new ArrayList<>();
    ArrayList<ExpencesUSD> additional_score = new ArrayList<>();
    ArrayList<ExpencesUSD> score = new ArrayList<>();

    public void arrayIn (){
        String arrayIn = "SELECT category, additional_score, name_score, defference FROM consolid";
        try {
            Connection arrayDate = DatabaseHandler.getDbConnection();
            PreparedStatement inf = arrayDate.prepareStatement(arrayIn);
            ResultSet rs = inf.executeQuery();

            while (rs.next()){
                String categoryDb = rs.getString(1);
                String additional_scoreDB = rs.getString(2);
                String scoreDb = rs.getString(3);
                BigDecimal difference = new BigDecimal(rs.getString(4));
//                ExpencesUSD treeFill = new ExpencesUSD(categoryDb, additional_scoreDB, scoreDb, difference);

                boolean isTrue = category.stream().anyMatch(u -> u.getCategory().equals(categoryDb));
                if (isTrue){

                }
                else{

                }
                category.add(new ExpencesUSD(categoryDb, additional_scoreDB));

                additional_score.add(new ExpencesUSD(scoreDb));

                score.add(new ExpencesUSD(scoreDb, difference));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        for (int i = 0 ; i < additional_score.size(); i ++){
            additional_score.get(1);
        }
    }

}
