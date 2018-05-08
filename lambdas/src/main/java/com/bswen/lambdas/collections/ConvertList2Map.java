package com.bswen.lambdas.collections;

import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * convert a list to map
 * Created on 2018/5/8.
 */
public class ConvertList2Map {

    //the normal old method
    private static Map<String,Game> transformNormal(List<Game> games) {
        Map<String,Game> result = new HashMap<>();
        for(Game game:games) {
            result.put(game.getName(),game);
        }
        return result;
    }

    private static Map<String,Game> transformGuava(List<Game> games) {
        return Maps.uniqueIndex(games,game->game.getName());
    }

    private static Map<String,Game> transformGuava2(List<Game> games) {
        return Maps.uniqueIndex(games, Game::getName);
    }

    private static Map<String,Game> transformStream(List<Game> games) {
        return games.stream().collect(Collectors.toMap(Game::getName, game->game));
    }

    private static void printMap(String title,Map<String,Game> map) {
        System.out.println("---"+title+"---");
        for(String key:map.keySet()) {
            System.out.println(key+"->"+map.get(key));
        }
        System.out.println();
    }


    public static void main(String[] args) {
        List<Game> games = Arrays.asList(
                new Game("game1",1.1),
                new Game("game2",2.1)
        );

        printMap("normal old transform",transformNormal(games));
        printMap("guava lambda transform",transformGuava(games));
        printMap("guava method reference transform",transformGuava2(games));
        printMap("stream transform",transformStream(games));
    }

    private static class Game {
        private String name;
        private double price;

        public Game(String name, double price) {
            this.name = name;
            this.price = price;
        }

        @Override
        public String toString() {
            return "Game{" +
                    "name='" + name + '\'' +
                    ", price=" + price +
                    '}';
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
