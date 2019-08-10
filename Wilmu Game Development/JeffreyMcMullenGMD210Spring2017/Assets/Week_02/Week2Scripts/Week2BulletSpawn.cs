using UnityEngine;
using System.Collections;

public class Week2BulletSpawn : MonoBehaviour
{
    public GameObject Bullet;
    public float RoundsPerMinute;
    private float RoundsPerSecond;
    private float FireDelay;
	// Use this for initialization
	void Start ()
    {
        CalculateFiringRate();
    }
	
	// Update is called once per frame
	void FixedUpdate ()
    {
        CheckForMouseClick();
	}

    public void CalculateFiringRate()
    {
        RoundsPerSecond = RoundsPerMinute / 60;
        FireDelay = (1 / RoundsPerSecond);
    }

    public void CheckForMouseClick()
    {
        //Input.GetMouseButtonDown means only one bullet per down click.
        //Input.GetMouseButtonUp means only one bullet when you pickup after clicking.
        //Input.GetMouseButton means that as long as you are holding down the button, it will be called every frame.
        /*
        if (Input.GetMouseButtonDown(0))
        {
            Instantiate(Bullet, gameObject.transform.position, gameObject.transform.rotation);
        }
        */

        /*
        if (Input.GetMouseButtonUp(0))
        {
            Instantiate(Bullet, gameObject.transform.position, gameObject.transform.rotation);
        }
        */

        //Full Auto
        //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
        if (Input.GetMouseButton(0))
        {
            if (FireDelay >= (1 / RoundsPerSecond))
            {
                Instantiate(Bullet, gameObject.transform.position, gameObject.transform.rotation);

                FireDelay = 0;
            }
        }

        FireDelay += Time.deltaTime;
    }
}
