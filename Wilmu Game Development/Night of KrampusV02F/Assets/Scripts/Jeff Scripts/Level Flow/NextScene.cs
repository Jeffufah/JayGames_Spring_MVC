using UnityEngine;
using System.Collections;

public class NextScene : MonoBehaviour {
	public float waitasecond = 0;

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
		waitasecond += Time.deltaTime;
		print (waitasecond);

		if (waitasecond >= 10) 
		{
			Application.LoadLevel("KrampusCredits");
		}

		if (Input.GetKey(KeyCode.Return)) {
			Application.LoadLevel("KrampusCredits");
		}


	}
}
