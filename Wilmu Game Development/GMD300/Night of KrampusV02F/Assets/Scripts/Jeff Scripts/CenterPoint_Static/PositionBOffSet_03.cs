using UnityEngine;
using System.Collections;

public class PositionBOffSet_03 : MonoBehaviour {
	
	public GameObject centerPoint;
	public float positiveOffSet;
	public GameObject cube;
	public float rotatingSpeed;
	public bool doubleBlades;

	void Start () 
	{
		doubleBlades = gameObject.transform.parent.gameObject.GetComponent<Movement_03>().doubleBlades;
		rotatingSpeed = gameObject.transform.parent.gameObject.GetComponent<Movement_03>().sawRotateSpeed;
		positiveOffSet = gameObject.transform.parent.gameObject.GetComponent<Movement_03>().positiveOffSet;
		positiveOffSet = positiveOffSet / 1.5625f;
		transform.position = new Vector3(centerPoint.transform.localPosition.x + positiveOffSet, centerPoint.transform.localPosition.y, centerPoint.transform.localPosition.z);

		if (gameObject.transform.parent.gameObject.GetComponent<Movement_03> ().referenceCubes == true) 
		{
			Instantiate (cube, transform.position, transform.rotation);
		}
	}

	void Update () 
	{
		
	}
}
