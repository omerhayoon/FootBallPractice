package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.groupingBy;

public class LeagueManager {
    private List<Team> teams;
    private Player player;
    private List<List<Match>> leagueTable;
    private List<Team> scoreboard;
    private int cycle = 0;



    public LeagueManager() {
        createTeam();
        createLeagueTable();
        startGame();
        //findMatchesByTeam(3);
        // findTopScoringTeams(3);
        // findPlayersWithAtLeastNGoals(5);
        // getTeamByPosition(10);
        //   getTopScorers(2);

    }




    public List<Match> findMatchesByTeam(int teamId) {
        List<Match> MatchesByTeam = leagueTable.stream()
                .flatMap(List::stream)//משטח לרשימה אחת של קרבות משחק
                .filter(item -> item.getHomeTeam().getId() == teamId || item.getAwayTeam().getId() == teamId).toList();// עושה את הסינון בין כל קרבות המשחק
        return MatchesByTeam;
    }

    public List<Team> findTopScoringTeams(int n) {
        List<Team> topScoring = teams.stream()
                .sorted(Comparator.comparingInt(Team::getPoints).reversed())
                .limit(n)
                .toList();
        System.out.println(topScoring);
        return topScoring;

    }

    public List<Player> findPlayersWithAtLeastNGoals(int n) {
        List<List<Goal>> listAllGoal = leagueTable.stream().flatMap(List::stream).map(Match::getGoals).toList();// יצירת רשימה של כל הרשימות של גול
        Map<Player, Long> playersLeastNGoals = listAllGoal.stream().flatMap(List::stream)// רשימה אחת של גול
                .collect(groupingBy(Goal::getScorer, Collectors.counting()));// יצירת מפה עם כמות הפעמים שמופיע השחקן
        List<Player> result = playersLeastNGoals.entrySet().stream().filter(item -> item.getValue() >= n).map(Map.Entry::getKey).toList();
        System.out.println(result);

        return result;
    }

    public Team getTeamByPosition(int position) {
        System.out.println(scoreboard.get(position - 1));
        return scoreboard.get(position - 1);

    }

    public Map<Integer, Integer> getTopScorers(int n) {
        Map<Integer, Integer> topScorers = leagueTable.stream()
                .flatMap(List::stream)
                .map(Match::getGoals)
                .flatMap(List::stream)
                .map(Goal::getScorer)
                .collect(Collectors.groupingBy(Player::getId, Collectors.summingInt(player -> 1)));

        Map<Integer, Integer> result = topScorers.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(n)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        return result;
    }


    public void startGame() {
       // Scanner scanner = new Scanner(System.in);
        IntStream.range(0, this.leagueTable.size()).forEach(value -> {
            System.out.println("Game cycle: " + (value+1));
            List<Match> game = this.leagueTable.get(value);
            game.stream().map(Match::startMatch).toList();
            if (value == cycle) {
                printLeagueTable();
                choseOption();
                cycle++;
            }

        });
        //printLeagueTable();
        //choseOption();
    }

    public void choseOption() {
        Scanner scanner = new Scanner(System.in);
        int chose = 0;
        System.out.println("Choose an option between 1 to 5 :" + "\n" +
                " 1 : Find matches by team " + "\n" +
                " 2 : Find top scoring teams" + "\n" +
                " 3 : Find players with at least n goals" + "\n" +
                " 4 : Get team by position" + "\n" +
                " 5 : Get top scorers"
        );

            while (chose < 1 || chose > 5){
                    chose = scanner.nextInt();
                    if(chose < 1 || chose > 5)System.out.println("Enter number between 1 - 5");
            }

        switch (chose) {
            case 1:// מחזיר כתובות במקום את הרשימה עצמה   לתקן !
                System.out.println("Enter teamId:");
                int teamId = scanner.nextInt();
                System.out.println(findMatchesByTeam(teamId));
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                break;

            case 2:// עובד. רק לסדר הכנסת קלט תקין
                System.out.println("Enter numbers of team:");
                int numberOfTeam = scanner.nextInt();
                findTopScoringTeams(numberOfTeam);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                break;


            case 3:// עובד. רק לסדר הכנסת קלט תקין
                System.out.println("Enter at least goals:");
                int gol = scanner.nextInt();
                findPlayersWithAtLeastNGoals(gol);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                break;


            case 4:// עובד. רק לסדר הכנסת קלט תקין
                System.out.println("Enter the position you want to get:");
                int position = scanner.nextInt();
                getTeamByPosition(position);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                break;

            case 5://עובד. רק לסדר הכנסת קלט תקין
                System.out.println("Enter the number of player that score the most goals:");
                int topScorers = scanner.nextInt();
                System.out.println(getTopScorers(topScorers));
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                break;

        }

    }


    public void printLeagueTable() {

        scoreboard = teams.stream()
                .sorted(Comparator.comparing(Team::getPoints)
                        .thenComparing(Team::getDifference).reversed()
                        .thenComparing(Team::getName)).toList();

        System.out.println("League results: ");
        IntStream.range(0, scoreboard.size()).forEach(value -> {
            System.out.println("the place " + (value + 1) + ": " +
                    scoreboard.get(value).getName() +
                    " Points: " + scoreboard.get(value).getPoints() +
                    " Difference " + scoreboard.get(value).getDifference());
        });


    }

    public void createLeagueTable() {
        List<List<Integer>> combinations = IntStream.range(0, teams.size())
                .boxed()
                .flatMap(i -> IntStream.range(i + 1, teams.size()).mapToObj(j -> List.of(i, j)))
                .collect(Collectors.toList());// יוצר את כל האפשריות משחק האפשריים
        //System.out.println(combinations);
        Collections.shuffle(combinations);// מערבב את השילובים כדי להפוך את הסדר לאקראי
        //System.out.println(combinations);

        this.leagueTable = IntStream.range(0, 9) //פותח 9 מחזורי משחק
                .mapToObj(round -> combinations.stream() // עובר על כלל האפשריות הקיימות
                        .skip(round * 5)// מדלג בקפיצות של חמש
                        .limit(5)// מגביל בחמש
                        .map(pair -> new Match(teams.get(pair.get(0)), teams.get(pair.get(1))))// יוצר קרבות משחק
                        .collect(Collectors.toList())).toList();
       // System.out.println(leagueTable);

    }

    public void createTeam() {
        List<List<String>> readForFile;
        try {
            readForFile = readFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.teams = readForFile.stream().map(item -> {
            int id = Integer.parseInt(item.get(0));
            String name = item.get(1);
            List<Player> players = createPlayers();
            return new Team(id, name, players);
        }).toList();
        //System.out.println(teams);

    }

    public List<Player> createPlayers() {
        List<Player> players = new ArrayList<>();
        IntStream.range(1, 16).forEach(value -> players.add(player = new Player()));
        return players;
    }

    public List<List<String>> readFile() throws IOException {
        List<List<String>> records = new ArrayList<>();

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(Utils.fileAddress));
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }

        String line;
        line = br.readLine();
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            List<String> record = new ArrayList<>();
            for (String part : parts) {
                record.add(part.trim());
            }
            records.add(record);
        }


        return records;
    }


}



