using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class Week11HealthChanger : MonoBehaviour
{
    public float Health;
	// Use this for initialization
	void Start ()
    {
        Health = Week11StaticClassObject.PlayerHealth;
        Debug.Log("Current health is " + Health);
	}
	
	// Update is called once per frame
	void Update ()
    {
		if (Input.GetKeyDown(KeyCode.Return))
        {
            Week11StaticClassObject.PlayerHealth = Health;
            Debug.Log("Setting static PlayerHealth to Health variable.");
        }

        if (Input.GetKeyDown(KeyCode.Backspace))
        {
            LoseHealth();
        }

        if (Input.GetKeyDown(KeyCode.Alpha1))
        {
            SceneManager.LoadScene("Week11TestScene1");
        }

        if (Input.GetKeyDown(KeyCode.Alpha2))
        {
            SceneManager.LoadScene("Week11TestScene2");
        }

        if (Input.GetKeyDown(KeyCode.Alpha3))
        {
            SceneManager.LoadScene("Week11TestScene3");
        }
    }

    public void LoseHealth()
    {
        Week11StaticClassObject.PlayerHealth -= 10;
        Health = Week11StaticClassObject.PlayerHealth;
    }
}
