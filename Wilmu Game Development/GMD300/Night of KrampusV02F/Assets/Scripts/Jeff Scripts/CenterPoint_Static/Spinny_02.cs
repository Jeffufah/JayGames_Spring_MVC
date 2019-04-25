using UnityEngine;
using System.Collections;

public class Spinny_02 : MonoBehaviour {
	private float speed = 0;
	private bool doubleBlades;
	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
		doubleBlades = gameObject.transform.parent.gameObject.GetComponent<PositionBOffSet_03>().doubleBlades;
		speed = gameObject.transform.parent.gameObject.GetComponent<PositionBOffSet_03>().rotatingSpeed;
		transform.Rotate (0,0,speed * Time.deltaTime);

		if (doubleBlades == false) 
		{
			Destroy (gameObject);
		}
	}
}
