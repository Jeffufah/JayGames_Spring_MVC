using UnityEngine;
using System.Collections;

public class object_moving : MonoBehaviour {

	public float moveRange;
	//Vector3 point1;
	//Vector3 point2;
	public float timer;
	float timeOnRoute = 0.0f;
	int directionalVal = 1;

	public bool horizontal;
	float x = 0f;
	float y = 0f;
	public GameObject pointA;
	public GameObject pointB;

	// Use this for initialization
	void Start () {
		if (horizontal == true)
		{x = moveRange;}
		else
		{y = moveRange;}
		//point1 = transform.position;
		//point2 = transform.position + new Vector3(x,y,0);

	}
	
	// Update is called once per frame
	void FixedUpdate () {
		timeOnRoute += Time.fixedDeltaTime*directionalVal;
		if(timeOnRoute >= timer)
		{
			timeOnRoute = timer;
			directionalVal = -1;
		}

		if(timeOnRoute <= 0)
		{
			timeOnRoute = 0;
			directionalVal = 1;
		}


		//transform.position = Vector3.Lerp(point1,point2,timeOnRoute/timer);
		transform.position = Vector3.Lerp(pointA.transform.position,pointB.transform.position,timeOnRoute/timer);
	}
}
