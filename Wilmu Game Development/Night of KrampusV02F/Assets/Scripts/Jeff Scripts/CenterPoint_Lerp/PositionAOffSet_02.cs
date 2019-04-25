using UnityEngine;
using System.Collections;

public class PositionAOffSet_02 : MonoBehaviour {

	public GameObject centerPoint;
	public float negativeOffSet;
	public GameObject cube;
	public float rotatingSpeed;
	
	void Start () 
	{
		rotatingSpeed = gameObject.transform.parent.gameObject.GetComponent<Movement_02>().sawRotateSpeed;
		negativeOffSet = gameObject.transform.parent.gameObject.GetComponent<Movement_02>().negativeOffSet;
		negativeOffSet = negativeOffSet / 1.5625f;
		transform.position = new Vector3(centerPoint.transform.localPosition.x + negativeOffSet, centerPoint.transform.localPosition.y, centerPoint.transform.localPosition.z);

		if (gameObject.transform.parent.gameObject.GetComponent<Movement_02> ().referenceCubes == true) 
		{
			Instantiate (cube, transform.position, transform.rotation);
		}


	}

	void Update () 
	{

	}
}
