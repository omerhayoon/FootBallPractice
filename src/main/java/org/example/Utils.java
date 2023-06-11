package org.example;

import java.util.List;

public class Utils {
    public static final List<String> FIRST_NAMES = List.of("gil","or", "david", "ben", "dov", "dan", "ron", "pelg", "ram", "rom", "don" , "ori" , "omer", "yuda", "dvir,","daniel","yogev","lior","ari","yosef","amir","oleg","eden","alex","matan","avi","ravid","ofek","sun","dror");
    public static final List<String> LAST_NAMES = List.of("hatar","hamar","hadad","swisa","benzakai","edri","levi","buzaglo","dadon","hatav","goren","bashtaker","bechor","biran","alon","mizrahi","arel","gilboha","avidan","nezer","kadosh","malihac","ovad","cohen","bentov","haim","zion","dagan","golan","vaizman");
    public static final int ROUND_START=1;
    public static  int idPlayer=0;
    public static int getIdPlayer(){
        idPlayer++;
        return idPlayer;
    }
    public static  int idTeam=0;
    public static int getIdTeam(){
        idTeam++;
        return idTeam;
    }

    public static  int idGoal=0;
    public static int getIdGoal(){
        idGoal++;
        return idGoal;
    }
    public static  int idMatch=0;
    public static String fileAddress="C:\\Users\\אביחי\\IdeaProjects\\FootBallPractice1\\src\\main\\java\\org\\example\\Teams.csv";
    public static int idMatch(){
        idMatch++;
        return idMatch;
    }
}
