using UnityEngine;
using System.Collections;

public class Spinny_03 : MonoBehaviour {
	private float speed = 0;
	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
		speed = gameObject.transform.parent.gameObject.GetComponent<Movement_02>().sawRotateSpeed;
		transform.Rotate (0,0,speed * Time.deltaTime);
	}
}
