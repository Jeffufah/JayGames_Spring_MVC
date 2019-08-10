using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Week3PlayerTriggerScript : MonoBehaviour
{

	// Use this for initialization
	void Start () {
		
	}
	
	// Update is called once per frame
	void Update () {
		
	}

    public void OnTriggerEnter(Collider TheObjectIHit)
    {
        //Debug.Log(TheObjectIHit.gameObject.transform.name);
        if (TheObjectIHit.gameObject.transform.tag == "Cube")
        {
            Debug.Log(TheObjectIHit.gameObject.transform.name);

            //Call the function inside the script that is attached to our cube.
            TheObjectIHit.gameObject.transform.GetComponent<Week2ChangeColorScript>().ChangeObjectColor();
        }
    }
}
