/*
 * Course ID: EYF-649
 * Submission type: Lab13Part2
 * Due Date: 2018/10/01
 * Author: Jeffrey McMullen II
 * Description: This program will create a Transcript object and utilize its
 * constructor to input a name, and an id. It will also have 3 courses added
 * to its courses ArrayList. A new Transcript will be created by using the 
 * clone method implemented by the Clonable interface. The contents of both
 * Transcripts will be displayed to the console.
 */

public class Lab13TestTranscript 
{
    public static void main(String[] args) throws CloneNotSupportedException 
    {
        Transcript transcript1 = new Transcript("Jeffrey", 1);
        transcript1.addCourse("Math");
        transcript1.addCourse("English");
        transcript1.addCourse("Science");      

        Transcript transcript2 = (Transcript)transcript1.clone();        
        
        System.out.println("Transcript 1:\n" + transcript1.toString());
        System.out.println();
        System.out.println("Transcript 2:\n" + transcript2.toString());
    }
}
