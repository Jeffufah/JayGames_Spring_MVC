using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Week5FunctionScript : MonoBehaviour
{

	// Use this for initialization
	void Start ()
    {
		
	}
	
	// Update is called once per frame
	void Update ()
    {
		if (Input.GetKeyDown(KeyCode.Alpha1))
        {
            DebugSomething();
        }

        if (Input.GetKeyDown(KeyCode.Alpha2))
        {
            string myLittleString = "SaySomething";
            DebugTheParameter(myLittleString);
        }

        if (Input.GetKeyDown(KeyCode.Alpha3))
        {
            string FirstWord = "What's ";
            string SecondWord = "Up?";

            DebugMultipleParameters(FirstWord, SecondWord);
        }

        if (Input.GetKeyDown(KeyCode.Alpha4))
        {
            string SomeWord = "Jeff";
            string MakeSentence = PassSentenceBack(SomeWord);
            Debug.Log(MakeSentence);
        }

        if (Input.GetKeyDown(KeyCode.Alpha5))
        {
            int a = 1;
            int b = 2;

            int sum = SimpleMaths(a, b);
            Debug.Log(sum);
        }

        if (Input.GetKeyDown(KeyCode.Alpha6))
        {
            string word = "sup";
            string response = GameObject.Find("ExternalObject").GetComponent<Week5ExternalFunctionScript>().RespondToHello(word);
            Debug.Log(response);
        }

    }

    public void DebugSomething()
    {
        Debug.Log("Something.");
    }

    public void DebugTheParameter(string WordToDebug)
    {
        Debug.Log(WordToDebug);
    }

    public void DebugMultipleParameters(string WordOne, string WordTwo)
    {
        Debug.Log(WordOne + WordTwo);
    }

    public string PassSentenceBack(string Word)
    {
        string mySentence = "Hello, my name is ";

        mySentence = mySentence + Word + ".";

        return mySentence;
    }

    public int SimpleMaths(int a, int b)
    {
        int c = 0;
        c = a + b;
        return c;
    }
}
