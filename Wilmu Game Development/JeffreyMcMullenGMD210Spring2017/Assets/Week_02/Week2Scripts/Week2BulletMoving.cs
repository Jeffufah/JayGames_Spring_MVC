using UnityEngine;
using System.Collections;

public class Week2BulletMoving : MonoBehaviour
{
    public float BulletSpeed;
    public float LifeTimer;

    // Use this for initialization
    void Start ()
    {
        InitializeBullet();
    }
	
	// Update is called once per frame
	void Update ()
    {
        CountLifeSeconds();
	}

    public void InitializeBullet()
    {
        gameObject.transform.GetComponent<Rigidbody>().AddForce(transform.forward * BulletSpeed);
    }

    public void CountLifeSeconds()
    {
        LifeTimer += Time.deltaTime;
        if (LifeTimer >= 3)
        {
            Destroy(gameObject);
        }
    }

    public void OnCollisionEnter(Collision TheObjectIHit)
    {
        //Debug.Log(TheObjectIHit.gameObject.transform.name);
        if (TheObjectIHit.gameObject.transform.tag == "Cube")
        {
            //Debug.Log(TheObjectIHit.gameObject.transform.name);

            //Call the function inside the script that is attached to our cube.
            TheObjectIHit.gameObject.transform.GetComponent<Week3CubeDieScript>().IGotShot();
        }

        Destroy(gameObject);


    }
}
