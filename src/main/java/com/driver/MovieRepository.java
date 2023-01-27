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
    Map<String , String> pairMap=new HashMap<>();

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
        if(!pairMap.containsKey(moviename)) pairMap.put(moviename,directorname);
        return "success";
    }

    public List<String> getMoviesByDirectorName(String name){
        List<String> movielist=new ArrayList<>();
        for(String moviename: pairMap.keySet()){
            if(pairMap.get(moviename).equals(name)){
                movielist.add(moviename);
            }
        }
        return movielist;
    }

    public List<String> findAllMovies(){
        List<String> movielist=new ArrayList<>();
        for(String moviename: movieMap.keySet()){
            movielist.add(moviename);
        }
        return movielist;
    }

    public String deleteDirectorByName(String name){
        for(String moviename: pairMap.keySet()){
            if(pairMap.get(moviename).equals(name)){
                movieMap.remove(moviename);
                pairMap.remove(moviename);
            }
        }
        directorMap.remove(name);
        return "success";
    }

    public String deleteAllDirectors(){
        for(String moviename: pairMap.keySet()){
            movieMap.remove(moviename);
            directorMap.remove(moviename);
            pairMap.remove(moviename);
        }
        return "success";
    }
}
