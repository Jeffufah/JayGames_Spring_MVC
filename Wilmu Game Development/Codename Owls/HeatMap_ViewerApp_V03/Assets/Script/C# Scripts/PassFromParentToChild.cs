using UnityEngine;
using System.Collections;
using UnityEngine.UI;
using System.Collections.Generic;

public class PassFromParentToChild : MonoBehaviour
{

	// Use this for initialization
	void Start ()
    {
	
	}
	
	// Update is called once per frame
	void Update ()
    {
	
	}

    public void SetChildObjectName(string Name)
    {
        gameObject.transform.GetChild(0).gameObject.transform.GetComponent<Text>().text = Name;
    }
}
