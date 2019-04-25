using UnityEngine;
using System.Collections;

public class Ry_Move_Vert : MonoBehaviour {

	// Pub[l]ic variables (lolz)
	public float y_min = -1.0f;
	public float y_max = 1.0f;
	public float speed;
	
	// Privates (lolz)
	float temp_y_min;
	float temp_y_max;
	float temptemp;
	float current_y;
	float accumulator;
	float start_y;
	
	Vector2 tempPos;
	
	// Use this for initialization
	void Start () {
		temp_y_min = y_min;
		temp_y_max = y_max;
		start_y = transform.position.y;
		tempPos = Vector2.zero;
		tempPos.x = transform.position.x;
		accumulator = 0;
	}
	
	// Update is called once per frame
	void FixedUpdate () {
		
		if (Mathf.Abs (current_y - temp_y_max) <= 0.10f) {
			// Swap temp_x_min and temp_x_max
			temptemp = temp_y_max;
			temp_y_max = temp_y_min;
			temp_y_min = temptemp;
			accumulator = 0;
		}
		
		current_y = Mathf.Lerp (temp_y_min, temp_y_max, accumulator * speed);
		tempPos.y = start_y + current_y;
		transform.position = tempPos;
		accumulator += Time.fixedDeltaTime;
	}
}

