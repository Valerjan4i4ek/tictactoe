package test.tictactoe;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.*;
import java.util.*;

public class TicTacToe implements Serializable{



    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("Выберите пункт меню: \n1 Играть \n2 История \n3 Выход");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String menu = reader.readLine();
        Game game = new Game();

        if(menu.equals("1")){
            System.out.println("Введите имена игроков");
            String xPlayer = reader.readLine();
            String oPlayer = reader.readLine();
            if(!xPlayer.isEmpty() && !oPlayer.isEmpty()){
                game.game(xPlayer, oPlayer);
            }
        }
        else if(menu.equals("2")){
            ArrayList<History> list = new ArrayList<>();
            try(ObjectInputStream is = new ObjectInputStream(new FileInputStream("tictactoe.txt"))){
                list = (ArrayList<History>) is.readObject();
            }catch(Exception ex){

                System.out.println(ex.getMessage());
            }


            for(History h : list){
                System.out.println(h.getPl1() + " " + h.getPl2());
                System.out.println(h.getTable().toString());
            }
        }

        else if(menu.equals("3")){
            System.exit(0);
        }
        else{
            System.out.println("Пункт меню указан неверно. Запустите приложение заново");
        }

    }


}
