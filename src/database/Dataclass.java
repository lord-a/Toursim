package Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Dataclass {

    public static final String file = "pj1database.db";
    public static final String connectionfile = "Jdbc:sqlite:/home/sujeet/Documents/JavaFiles/Project1/Databaseprj1/" + file;

    public static final String Tablelogin = "LogIn";
    public static final String login_username = "Username";
    public static final String login_password = "Password";

    public static final String Agencyform = "form";
    public static final String names = "name";
    public static final String place = "place";
    public static final String type = "Type";
    public static final String phoneno = "phonenumber";
    public static final String Email ="email";
    public static final String key="keynumber";

    public static final String Querythroughname= " SELECT * FROM "+ Agencyform + " WHERE "+ names + " = ? ";
    public static final String Querythroughgenre="SELECT * FROM " + Agencyform + " WHERE " + type + "= ?";
    public static final String Queryinregister="SELECT " + login_username + "  FROM " + Tablelogin + " WHERE " + login_username + "=?";
    public static final String Queryinagecny="SELECT " + names + " FROM " + Agencyform + " WHERE " +  names + " = ?";
    public static final String Insertintoregister="INSERT INTO "+ Tablelogin + "(" + login_username  + "," +
                                                    login_password + ")" + "VALUES (?,?) ";
    public static final String Insertintoagency="INSERT INTO "+ Agencyform +"(" + names  + "," + place + ","
                                                    +   type + ","+ phoneno  + "," + Email + "," + key + ")" +
                                                       " VALUES (?,?,?,?,?,?) ";
    public static final String queryforlogin="SELECT * FROM " + Tablelogin + " WHERE " + login_username + "= ?"
                                                + " AND " + login_password + "= ?";
    public static final String queryagencyfororder= " SELECT * FROM " + Agencyform + " ORDER BY " + names + " COLLATE NOCASE ";
    public static final String querythroughkey="SELECT * FROM " + Agencyform + " WHERE " + key + "= ?";
    public static final String updateagency = " UPDATE " + Agencyform + " SET " + names + "= ?," + place + "= ?,"+ type + "= ?,"
            + phoneno + "= ?,"+ Email + "= ?"  + " WHERE " + key + "= ? ";
    public static final String searchthroughplace=" SELECT * FROM " + Agencyform + " WHERE " + place + " =? ";

    private Connection conn;
    private PreparedStatement pstQuerythroughname;
    private PreparedStatement pstquerythroughtype;
    private PreparedStatement pstqueryinregister;
    private PreparedStatement pstqueryinagency;
    private PreparedStatement pstinsertintoregister;
    private PreparedStatement pstinsertintoagency;
    private PreparedStatement pstqueryforlogin;
    private PreparedStatement pstqueryagencyplace;
    private PreparedStatement pstQuerythroughkey;
    private  PreparedStatement pstupdateagency;
    private  static Dataclass instance=new Dataclass();
    private Dataclass(){

    }
    public static Dataclass getInstance() {
        return instance;
    }

    public boolean open() {
        try {
            conn = DriverManager.getConnection(connectionfile);
            pstQuerythroughname=conn.prepareStatement(Querythroughname);
            pstquerythroughtype=conn.prepareStatement(Querythroughgenre);
            pstqueryinregister= conn.prepareStatement(Queryinregister);
            pstqueryinagency=conn.prepareStatement(Queryinagecny);
            pstinsertintoregister=conn.prepareStatement(Insertintoregister);
            pstinsertintoagency=conn.prepareStatement(Insertintoagency);
            pstqueryagencyplace = conn.prepareStatement(searchthroughplace);
            pstqueryforlogin=conn.prepareStatement(queryforlogin);
            pstQuerythroughkey=conn.prepareStatement(querythroughkey);
            pstupdateagency=conn.prepareStatement(updateagency);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void close() {
        try {
            if (conn != null) {
                conn.close();
            }
            if(pstquerythroughtype!=null){
                pstquerythroughtype.close();
            }if(pstQuerythroughname!=null){
                pstQuerythroughname.close();
            }if(pstinsertintoagency!=null){
                pstinsertintoagency.close();
            }if(pstinsertintoregister!=null){
                pstinsertintoregister.close();
            }if(pstqueryinagency!=null){
                pstqueryinagency.close();
            }if(pstqueryinregister!=null){
                pstqueryinregister.close();
            }
         if(pstqueryagencyplace!=null){
                pstqueryagencyplace.close();
          }
            if(pstQuerythroughkey!=null){
                pstQuerythroughkey.close();
            }if(pstupdateagency!=null){
                pstupdateagency.close();
            }
        } catch (SQLException e) {
            System.out.println("1" + e.getMessage());

        }
    }

    public Agency queryAgency(String origin) {
        try  {
            pstQuerythroughname.setString(1,origin);
            ResultSet result= pstQuerythroughname.executeQuery();
            Agency agencyobj = new Agency();
            while (result.next()) {

                agencyobj.setName(result.getString(names));
                agencyobj.setType(result.getString(type));
                agencyobj.setPlace(result.getString(place));
                agencyobj.setPhonenumber(result.getInt(phoneno));
                agencyobj.setEmail(result.getString(Email));
            }
            return agencyobj;
        } catch (SQLException e) {
            System.out.println("2" + e.getMessage());
            return  null;
        }
    }
    public Agency queryAgencythoughkey(int origin) {
        try  {
            pstQuerythroughkey.setInt(1,origin);
            ResultSet result= pstQuerythroughkey.executeQuery();
            Agency agencyobj1 = null;
            while (result.next()) {
                agencyobj1 = new Agency();
                agencyobj1.setName(result.getString(names));
                agencyobj1.setType(result.getString(type));
                agencyobj1.setPlace(result.getString(place));
                agencyobj1.setPhonenumber(result.getInt(phoneno));
                agencyobj1.setEmail(result.getString(Email));
                agencyobj1.setKeyno(result.getInt(key));
            }
            return agencyobj1;
        } catch (SQLException e) {
            System.out.println("querythorugh agency error" + e.getMessage());
            return  null;
        }
    }

    public int queryregister(String name , String pass) throws SQLException{
        pstqueryforlogin.setString(1,name);
        pstqueryforlogin.setString(2,pass);
        ResultSet result =pstqueryforlogin.executeQuery();
        if(result.next()){
            return 1;
        }else{
            return 0;
        }
    }
    public List<Agency> querytype(String genre) {
        try  {
            pstquerythroughtype.setString(1,genre);
            ResultSet result=pstquerythroughtype.executeQuery();
            List<Agency> querylists = new ArrayList<>();
            while (result.next()) {
                Agency namecomes=new Agency();
               namecomes.setName(result.getString(names));
                namecomes.setPlace(result.getString(place));
                namecomes.setType(result.getString(type));
                namecomes.setType(result.getString(Email));
                namecomes.setPhonenumber(Integer.parseInt( result.getString(phoneno)));
                querylists.add(namecomes);
            }
            return querylists;
        } catch (SQLException e) {
            System.out.println("3" + e.getMessage());
            return  null;
        }
    }
    public int updateinagency(String n, String p, String t, long ph , String em, int key){
        try{
            pstupdateagency.setString(1,n);
            pstupdateagency.setString(2,p);
            pstupdateagency.setString(3,t);
            pstupdateagency.setLong(4,ph);
            pstupdateagency.setString(5,em);
            pstupdateagency.setInt(6,key);
            pstupdateagency.executeUpdate();
            return 1;
        }catch(SQLException e){
            System.out.println(e.getMessage());
       return 0; }
    }
    public List<Agency> querynamebyorder(String order) {
        StringBuilder sb=new StringBuilder(queryagencyfororder);
        sb.append(order);

        try  {
            Statement st=conn.createStatement();
            ResultSet result=st.executeQuery(sb.toString());
            List<Agency> querylists = new ArrayList<>();
            while (result.next()) {
                Agency namecomes=new Agency();
                namecomes.setName(result.getString(names));
                namecomes.setPlace(result.getString(place));
                namecomes.setType(result.getString(type));
                namecomes.setType(result.getString(Email));
                namecomes.setPhonenumber(Integer.parseInt( result.getString(phoneno)));
                querylists.add(namecomes);
            }
            return querylists;
        } catch (SQLException e) {
            System.out.println("4" + e.getMessage());
            return  null;
        }
    }
    public List<Agency> querynamebyplace(String pla) throws SQLException {
        try  {
            pstqueryagencyplace.setString(1,pla);
            ResultSet result=pstqueryagencyplace.executeQuery();
            List<Agency> querylists = new ArrayList<>();
            while (result.next()) {
                Agency namecomes=new Agency();
                namecomes.setName(result.getString(names));
                namecomes.setPlace(result.getString(place));
                namecomes.setType(result.getString(type));
                namecomes.setType(result.getString(Email));
                namecomes.setPhonenumber(Integer.parseInt( result.getString(phoneno)));
                querylists.add(namecomes);
            }
            return querylists;
        } catch (SQLException e) {
            System.out.println("4" + e.getMessage());
            return  null;
        }
    }
    public int insertinregister(String n ,String p)throws SQLException{//two parameter are username and password
     pstqueryinregister.setString(1,n);
     ResultSet result= pstqueryinregister.executeQuery();
     if(result.next()) {

         return 0;
     }else{
         pstinsertintoregister.setString(1,n);
         pstinsertintoregister.setString(2,p);
        int key= pstinsertintoregister.executeUpdate();
        if(key!=1){
            throw new SQLException("error occured");
        }
        ResultSet generatedkeys=pstinsertintoregister.getGeneratedKeys();
        if(generatedkeys.next()){
            return 1;
        }else{
            throw new SQLException("Couldnt getinto Table");
        }
    }
    }

    public int insertintoagency(String n, String p, String t, long ph , String em, int kn) throws SQLException{
            pstqueryinagency.setString(1,n);
            ResultSet result=pstqueryinagency.executeQuery();
        if(result.next()){
        return 0;
        }else{
        pstinsertintoagency.setString(1,n);
        pstinsertintoagency.setString(2,p);
        pstinsertintoagency.setString(3,t);
        pstinsertintoagency.setLong(4,ph);
        pstinsertintoagency.setString(5,em);
        pstinsertintoagency.setLong(6,kn);
        int key =pstinsertintoagency.executeUpdate();
        if(key!=1){
            throw new SQLException("error occured");
        }
        ResultSet generatedkeys=pstinsertintoagency.getGeneratedKeys();
        if(generatedkeys.next()){
            return 1;
        }else{
            throw new SQLException("Couldnt getinto Table");
        }
    }
    }


}



















