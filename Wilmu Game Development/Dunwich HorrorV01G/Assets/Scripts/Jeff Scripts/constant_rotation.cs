using UnityEngine;
using System.Collections;

public class constant_rotation : MonoBehaviour {

	public Vector3 rotationDirection;
	public float rotationRate;
	
	// Update is called once per frame
	void Update () {
		transform.Rotate (rotationDirection * rotationRate*Time.deltaTime);
	}
}
