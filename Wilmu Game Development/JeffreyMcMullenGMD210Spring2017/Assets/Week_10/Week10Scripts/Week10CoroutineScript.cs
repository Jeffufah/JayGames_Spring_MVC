using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Week10CoroutineScript : MonoBehaviour
{
    public float timeDelay;
	// Use this for initialization
	void Start ()
    {
	}
	
	// Update is called once per frame
	void Update ()
    {
		if (Input.GetKeyDown(KeyCode.Return))
        {

            StartCoroutine(YieldThenDebugAMessage());        
        }
	}

    public IEnumerator YieldThenDebugAMessage()
    {
        //yield return new WaitForSeconds(timeDelay);
        

        yield return SayHello();
        yield return SayWhatsUp();
    }

    public IEnumerator SayHello()
    {
        yield return new WaitForSeconds(timeDelay);
        Debug.Log("Hello.");
    }

    public IEnumerator SayWhatsUp()
    {
        yield return new WaitForSeconds(timeDelay);
        Debug.Log("What's up?");
    }
}
