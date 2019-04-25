using UnityEngine;
using System.Collections;

public class PlatformTriggerScript : MonoBehaviour {
	GameObject player;
	Vector2 prevPosition;
	Vector2 currentPosition;
	// Use this for initialization
	void Start () {
		player = GameObject.FindGameObjectWithTag("Player");
		Debug.Log ("Player found in Start!");
		prevPosition = Vector2.zero;
	}
	
	// Update is called once per frame
	void FixedUpdate () {
		// In case player died...
		if (player == null) 
		{
			// Get new player object
			player = GameObject.FindGameObjectWithTag ("Player");
			Debug.Log ("Player found in Update!");
		}

		prevPosition = transform.position;
	}

	void OnCollisionStay2D(Collision2D other)
	{
		Debug.Log ("Delta Movement = " + (transform.position.x - prevPosition.x).ToString ());
		if (!player.GetComponent<PlayerScript> ().isJumping) 
		{
			prevPosition = transform.parent.GetComponent<Ry_Move_Horiz>().prevPosition;
			currentPosition = transform.parent.GetComponent<Ry_Move_Horiz>().currentPosition;
			player.GetComponent<Rigidbody2D> ().MovePosition (new Vector2 (player.transform.position.x + (currentPosition.x - prevPosition.x), player.transform.position.y));
		}
	}
}
