package com.bswen.lambdas.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * test copy or clone of collections.
 * Created on 2018/5/7.
 */
public class CopyOrClone1 {

    private static void processGame(List<Game> game, Consumer<Game> consumer) {
        game.stream().forEach(consumer);
    }

    public static List<Game> cloneList(List<Game> list) {
        List<Game> clone = new ArrayList<Game>(list.size());
        for (Game game : list) clone.add(new Game(game));
        return clone;
    }

    //demo deep copy.
    public static void main(String[] args) {
        List<Game> games1 = Arrays.asList(
                new Game("blizzard","starcraft",99.99),
                new Game("blizzard","warcraft",129.99)
        );

        System.out.println("before deep copy processed");
        processGame(games1,game-> System.out.println(game));

        Consumer<Game> c1 = game->{
            game.price=game.price+0.01;
            game.name= game.name+"_changed";
        };
        List<Game> clonedList = cloneList(games1);
        processGame(clonedList,c1);

        System.out.println("\nafter deep copy processed,the original list");
        processGame(games1,game-> System.out.println(game));

        System.out.println("\nafter deep copy processed,the cloned list");
        processGame(clonedList,game-> System.out.println(game));
    }

    public static void main_demo_shadow_copy(String[] args) {
//    public static void main(String[] args) {
        List<Game> games1 = Arrays.asList(
                new Game("blizzard","starcraft",99.99),
                new Game("blizzard","warcraft",129.99)
        );

        System.out.println("before processed");
        processGame(games1,game-> System.out.println(game));

        Consumer<Game> c1 = game->game.price=game.price+0.01;
        processGame(games1,c1);

        System.out.println("\nafter processed");
        processGame(games1,game-> System.out.println(game));
    }

    static class Game {
        private String manu;
        private String name;
        private double price;

        public Game(String manu, String name, double price) {
            this.manu = manu;
            this.name = name;
            this.price = price;
        }

        public Game(Game game) {
            this.manu = game.getManu();//String is immutable, so point to same instance is safe, it can not be changed
            this.name = game.getName();
            this.price = game.getPrice();
        }

        @Override
        public String toString() {
            return "Game{" +
                    "manu='" + manu + '\'' +
                    ", name='" + name + '\'' +
                    ", price=" + price +
                    '}';
        }

        public String getManu() {
            return manu;
        }

        public void setManu(String manu) {
            this.manu = manu;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }
}
