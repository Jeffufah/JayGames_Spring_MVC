using UnityEngine;
using System.Collections;

public class Ry_Move_Horiz : MonoBehaviour {

	// Pub[l]ic variables (lolz)
	public float x_min = -1.0f;
	public float x_max = 1.0f;
	public float speed;
	public Vector2 prevPosition;
	public Vector2 currentPosition;
	// Privates (lolz)
	float temp_x_min;
	float temp_x_max;
	float temptemp;
	float current_x;
	float accumulator;
	float start_x;

	Vector2 tempPos;

	// Use this for initialization
	void Start () {
		prevPosition = Vector2.zero;
		temp_x_min = x_min;
		temp_x_max = x_max;
		start_x = transform.position.x;
		tempPos = Vector2.zero;
		tempPos.y = transform.position.y;
		accumulator = 0;
	}
	
	// Update is called once per frame
	void FixedUpdate () {

		if (Mathf.Abs (current_x - temp_x_max) <= 0.10f) {
			// Swap temp_x_min and temp_x_max
			temptemp = temp_x_max;
			temp_x_max = temp_x_min;
			temp_x_min = temptemp;
			accumulator = 0;
		}

		prevPosition.x = transform.position.x;
		current_x = Mathf.Lerp (temp_x_min, temp_x_max, accumulator * speed);
		tempPos.x = start_x + current_x;
		currentPosition = tempPos;
		transform.position = tempPos;
		accumulator += Time.fixedDeltaTime;
	}


}
