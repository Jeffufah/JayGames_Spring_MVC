using UnityEngine;
using System.Collections;

public class playerStarter : MonoBehaviour {
	public GameObject Player;
	private bool waitaframe = true;
	private bool oneanddone = true;
	// Use this for initialization
	void Start () {
		waitaframe = true;
		oneanddone = true;
	}
	
	// Update is called once per frame
	void Update () {

		if (waitaframe == true) 
		{
			waitaframe = false;
		} 

		else 
		{
			if (oneanddone == true)
			{
				Instantiate (Player, transform.position, transform.rotation);
				oneanddone = false;
			}
		}



	}
}
