using UnityEngine;
using System.Collections;

public class Movement_03 : MonoBehaviour 
{
	public bool referenceCubes = true;
	public float sawRotateSpeed = 1;
	public bool doubleBlades = true;
	public float negativeOffSet;
	public float positiveOffSet;
	public float PivotRotSpeed = 1;
	public bool clkWiseRot = false;
	public bool cntrClkWiseRot = false;
	public bool rotBetweenVals = false;
	public float AngleA;
	public float AngleB;
	public float startAngle;
	private int direction = -1;
	private bool setUp = true;
	void Start () 
	{

	}


	void LateUpdate () 
	{
	if (setUp == true) {
			transform.rotation = Quaternion.Euler (0, 0, startAngle);
			setUp = false;
		} 
		else 
		{
			//Debug.Log (transform.eulerAngles.z);
			if (clkWiseRot == true) {
				transform.Rotate (0, 0, PivotRotSpeed * Time.deltaTime * -1);
			}

			if (cntrClkWiseRot == true) {
				transform.Rotate (0, 0, PivotRotSpeed * Time.deltaTime);
			}

			if (rotBetweenVals == true) {
				transform.Rotate (0, 0, PivotRotSpeed * Time.deltaTime * direction);
				

				if (transform.eulerAngles.z > AngleB) {

					direction = -1;
					//Debug.Log (transform.eulerAngles.z);
				}
				

				if (transform.eulerAngles.z < AngleA) {
					direction = 1;
				}
			} 

		}
	}
}
