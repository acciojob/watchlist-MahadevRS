package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MovieRepository {

    Map<String,Movie> movieMap=new HashMap<>();
    Map<String,Director> directorMap=new HashMap<>();
    Map<String , List<String>> pairMap=new HashMap<>();

    public String addMovie(Movie movie){
        movieMap.put(movie.getName(),movie);
        return "success";
    }

    public Movie getMovieByName(String name){
        if(movieMap.containsKey(name)){
            return movieMap.get(name);
        }
        else return new Movie();
    }

    public String addDirector(Director director){
        directorMap.put(director.getName(),director);
        return "success";
    }

    public Director getDirectorByName(String name){
        if(directorMap.containsKey(name)){
            return directorMap.get(name);
        }
        else return new Director();
    }

    public String addMovieDirectorPair(String moviename,String directorname){
        if(movieMap.containsKey(moviename) && directorMap.containsKey(directorname)){
            if(pairMap.containsKey(directorname)){
                List<String> temp=pairMap.get(directorname);
                if(!(temp.contains(moviename))){
                    temp.add(moviename);
                    pairMap.put(directorname,temp);
                    return "success";
                }
            }
            else{
                List<String> temp=new ArrayList<>();
                temp.add(moviename);
                pairMap.put(directorname,temp);
                return "success";
            }
        }
        return "no success";

    }

    public List<String> getMoviesByDirectorName(String name){
        if(pairMap.containsKey(name)) return pairMap.get(name);
        return new ArrayList<>();
    }

    public List<String> findAllMovies(){
        List<String> movielist=new ArrayList<>();
        for(String moviename: movieMap.keySet()){
            movielist.add(moviename);
        }
        return movielist;
    }

    public String deleteDirectorByName(String name){
        List<String> movies=pairMap.get(name);
        for(String moviename: movies ){
            movieMap.remove(moviename);
            pairMap.remove(name);
        }
        directorMap.remove(name);
        return "success";
    }

    public String deleteAllDirectors(){
        for(String directorname: pairMap.keySet()){
            List<String> movies=pairMap.get(directorname);
            for (String moviename: movies) {
                movieMap.remove(moviename);
            }
            directorMap.remove(directorname);
            pairMap.remove(directorname);
        }
        return "success";
    }
}
