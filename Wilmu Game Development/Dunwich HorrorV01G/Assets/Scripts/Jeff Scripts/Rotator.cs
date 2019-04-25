using UnityEngine;
using System.Collections;

public class Rotator : MonoBehaviour 
{
	public float PivotRotSpeed = 1;
	public bool rotBetweenVals = false;

	public float threshHoldMinA;
	public float threshHoldMaxA;
	public float threshHoldMinB;
	public float threshHoldMaxB;

	private int direction = -1;
	private bool setUp = true;
	void Start () 
	{
		
	}
	
	
	void LateUpdate () 
	{
		if (setUp == true) {
			transform.Rotate (transform.rotation.x, 0, transform.rotation.z);
			setUp = false;
		} 
		else 
		{
			//Debug.Log (transform.eulerAngles.z);
			
			if (rotBetweenVals == true) {
				transform.Rotate (0, PivotRotSpeed * Time.deltaTime * direction, 0);
				
				if ((transform.localEulerAngles.y >= threshHoldMinA) && (transform.localEulerAngles.y <= threshHoldMaxA)) 
				{
					direction = -1;
					//Debug.Log (transform.eulerAngles.z);
				}

				if ((transform.localEulerAngles.y >= threshHoldMinB) && (transform.localEulerAngles.y <= threshHoldMaxB)) 
				{
					direction = 1;
					//Debug.Log (transform.eulerAngles.z);
				}
			} 
		}
	}
}

