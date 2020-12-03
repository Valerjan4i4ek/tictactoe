package test.tictactoe;

import java.io.*;

public class TicTacToe{

    public static void main(String[] args) throws IOException {
        System.out.println("Select menu item: \n1 Play \n2 History \n3 Exit");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String menu = reader.readLine();
        String again = "";
        Game game = new Game();
        FileInputStream fin = new FileInputStream("notes.txt");


        if(menu.equals("1")){
            System.out.println("Enter players names");
            String xPlayer = reader.readLine();
            String oPlayer = reader.readLine();
            if(!xPlayer.isEmpty() && !oPlayer.isEmpty()){
                game.game(xPlayer, oPlayer);

                System.out.println("Again? y/n");
                again = reader.readLine();
                if(again.equalsIgnoreCase("y")){
                    game.game(xPlayer, oPlayer);
                }
                else if(again.equalsIgnoreCase("n")){
                    System.exit(0);
                }
            }
        }

        else if(menu.equals("2")){
            int i=-1;
            while((i=fin.read())!=-1){
                System.out.print((char)i);
            }
        }

        else if(menu.equals("3")){
            System.exit(0);
        }
        else{
            System.out.println("Restart application");
        }
    }
