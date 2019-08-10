using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Week1PracticeScript : MonoBehaviour 
{
	public int SomeWholeNumber;
	public float SomeFloatNumber;
	public string SomeStringValue;
	public GameObject SomeGameObjectPrefab;


	// Use this for initialization
	void Start () 
	{
		//Send message concatenates different datatypes together and debugs it to the console.
		SendMessage ();
	}
	
	// Update is called once per frame
	void Update () 
	{
		CheckForReturnKeyPress ();
	}

	public void SendMessage()
	{
		SomeWholeNumber = 0;
		//For hardcoded decimal numbers, C# wants an f at the end of the number if it isn't a whole number.
		SomeFloatNumber = .5f;

		SomeStringValue = "Jeffrey";

		var message = "My name is " + SomeStringValue + " My int number is " + SomeWholeNumber.ToString () + " My float number is " + SomeFloatNumber.ToString ();
		Debug.Log (message);
	}

	public void CheckForReturnKeyPress()
	{
		if (Input.GetKeyDown (KeyCode.Return)) 
		{
			Instantiate (SomeGameObjectPrefab, gameObject.transform.position, gameObject.transform.rotation);
		}
	}
}
