/*
 * Course ID: EYF-649
 * Submission type: Assignment DS01_BagTestingProgram
 * Due Date: 2018/10/22
 * Author: Jeffrey McMullen II
 * Description: This program creates an instance of the LinkedBag class which
 * implements the BagInterface. The datatype that will be placed into this bag will
 * be the Cookie object included in this project. This program will test all 
 * methods implemented from the BagInterface.
 */
public class TestLinkedBag 
{
    public static void main(String[] args) 
    {
        //Create a new LinkedBag class that implements BagInterface for the type
        //Cookie.
        BagInterface<Cookie> cookieBag = new LinkedBag<Cookie>();
        
        //Check if the bag is full.
        System.out.println("Bag full status: " + cookieBag.isFull());
        
        //Check if the bag is empty.
        System.out.println("Bag empty status: " + cookieBag.isEmpty());
        
        //Create some cookies and pass their names to the Cookie class constructor.
        Cookie cookie1 = new Cookie("Chocolate Chip");
        Cookie cookie2 = new Cookie("Sugar");
        Cookie cookie3 = new Cookie("Oreo");
        Cookie cookie4 = new Cookie("Oatmeal Raisin");
        
        //Add the cookies to the cookieBag.
        cookieBag.add(cookie1);
        cookieBag.add(cookie2);
        cookieBag.add(cookie3);
        cookieBag.add(cookie4);
        cookieBag.add(cookie1);
        
        //Notify that the contents are about to be displayed.
        System.out.println("Displaying Cookie Bag contents: ");
        
        //Assign an Object array named cookies to the return value of 
        //cookieBag's toArray method.
        Object[] cookies = cookieBag.toArray();
        
        //Use a foreach loop to iterate through the cookies array and cast each
        //element to the object type Cookie in order to use the underlying
        //getType() method contained in the Cookie class.
        for(Object cookie : cookies)
        {
            Cookie castCookie = (Cookie)cookie;
            System.out.println(castCookie.getType());
        }
        
        //Display the occurence of chocolate chip cookies.
        System.out.println("The occurence of Chocolate Chip cookies are: " 
                + cookieBag.getFrequencyOf(cookie1));
        
        //Display whether the cookie bag contains an Oreo cookie or not.
        System.out.println("Does the bag contain an Oreo cookie? " 
                + cookieBag.contains(cookie3));
        
        //Notify that a cookie is being removed from the bag.
        System.out.println("Removing a cookie from the bag...");
        
        //Remove a cookie from the bag.
        Cookie removedCookie = cookieBag.remove();
        
        if (removedCookie != null)
        {
            //Display the contents of the cookie that was removed from the bag.
            System.out.println("The cookie removed was: " + removedCookie.getType());
        }
        else
        {
            //Notify that there wasn't a cookie to be removed.
            System.out.println("There are no cookies in the bag.");
        }
        
        //Notify of the attempt to remove an oreo cookie.
        System.out.println("Attempting to remove Oreo cookie...");
        
        if (cookieBag.remove(cookie3))
        {
            //Notify that the cookie was removed successfully.
            System.out.println(cookie3.getType() + " cookie removed successfully.");
        }
        else
        {
            //Notify that the cookie was removed unsuccessfully.
            System.out.println(cookie3.getType() + " cookie removed unsuccessfully.");
        }
        
        //Notify that the cookieBag is going to be cleared.
        System.out.println("Clearing Cookie Bag...");
        
        //Clear the cookieBag.
        cookieBag.clear();
        
        //Check if the bag is full.
        System.out.println("Bag full status: " + cookieBag.isFull());
        
        //Check if the bag is empty.
        System.out.println("Bag empty status: " + cookieBag.isEmpty());
    }
}