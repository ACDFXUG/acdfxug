package Java.Learn.Collection.MovieManage;

import java.util.Objects;

public class Movie {
    private String name;
    private double score;
    private String actor;
    private double price;
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return the score
     */
    public double getScore() {
        return score;
    }
    /**
     * @param score the score to set
     */
    public void setScore(double score) {
        this.score = score;
    }
    /**
     * @return the actor
     */
    public String getActor() {
        return actor;
    }
    /**
     * @param actor the actor to set
     */
    public void setActor(String actor) {
        this.actor = actor;
    }
    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }
    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }
    /**
     * 
     */
    public Movie() {
    }
    /**
     * @param name
     * @param score
     * @param actor
     * @param price
     */
    public Movie(String name, double score, String actor, double price) {
        this.name = name;
        this.score = score;
        this.actor = actor;
        this.price = price;
    }
    @Override
    public String toString() {
        return "Movie [name=" + name + ", score=" + score + ", actor=" + actor + ", price=" + price + "]";
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, score, actor, price);
    }
    @Override
    public boolean equals(Object obj) {
        if(obj==this) return true;
        if(obj==null) return false;
        return obj instanceof Movie m
            &&name.equals(m.name)&&score==m.score
            &&actor.equals(m.actor)&&price==m.price;
    }
    
}
