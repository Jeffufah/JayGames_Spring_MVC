using UnityEngine;
using System.Collections;

public class CameraController : MonoBehaviour {
	
	public float min_x;
	public float max_x;
	public float min_y;
	public float max_y;
	public float distance;
	Vector3 tempVect;
	public GameObject player;
	public GameObject cam;
	public GameObject background;
	// Use this for initialization
	void Start () {
		tempVect = Vector3.zero;

		
	}
	
	// Update is called once per frame
	void Update () {

		background.transform.position = new Vector3 (transform.position.x, transform.position.y, 10);

		min_x = GameObject.FindGameObjectWithTag("level").GetComponent<levelIndicator>().CameraXMin;
		max_x = GameObject.FindGameObjectWithTag("level").GetComponent<levelIndicator>().CameraXMax;
		min_y = GameObject.FindGameObjectWithTag("level").GetComponent<levelIndicator>().CameraXMin;
		max_y = GameObject.FindGameObjectWithTag("level").GetComponent<levelIndicator>().CameraXMax;
		distance = GameObject.FindGameObjectWithTag("level").GetComponent<levelIndicator>().CameraDistance;

		// Set camera to follow player
		player = GameObject.FindGameObjectWithTag ("Player");
		transform.position = new Vector3(player.transform.position.x, player.transform.position.y, player.transform.position.z - distance);
		tempVect = transform.position;
		
		// Unless...
		
		if (tempVect.x <= min_x) 
		{
			tempVect.x = min_x;
		}
		if (tempVect.x >= max_x) 
		{
			tempVect.x = max_x;
		}
		if (tempVect.y <= min_y) 
		{
			tempVect.y = min_y;
		}
		if (tempVect.y >= max_y) 
		{
			tempVect.y = max_y;
		}
		
		transform.position = tempVect;
	}
}
