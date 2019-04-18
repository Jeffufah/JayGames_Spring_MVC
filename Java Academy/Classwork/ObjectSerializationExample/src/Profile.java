public class Profile implements java.io.Serializable /*<---------- Don't forget this implementation!*/{
    private static final long serialVersionUID = 1L;
    private String name = null;
    private int age = 0;
    private String favoriteSong = null;
         
    Profile() {
        
    }
    
    /** This constructor does things */
    Profile(int age, String name, String favoriteSong){
        this.name = name;
        this.age = age;
        this.favoriteSong = favoriteSong;
    }
    
    public String getName(){
        return name;
    }
    
    public int getAge(){
        return age;
    }
    
    public String getFavoriteSong(){
        return favoriteSong;
    }
}