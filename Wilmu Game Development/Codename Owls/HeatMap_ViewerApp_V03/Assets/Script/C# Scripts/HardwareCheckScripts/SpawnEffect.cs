using UnityEngine;
using System.Collections;

public class SpawnEffect : MonoBehaviour
{
    public GameObject[] SpawnThings;
    // Use this for initialization
    void Start ()
    {
	
	}
	
	// Update is called once per frame
	void Update ()
    {
	    if (Input.GetKeyDown(KeyCode.Alpha1))
        {
            //SpawnCupIndicator(SpawnThings[0]);
        }
	}

    public void SpawnCupIndicator()
    {
        var XoffSet = Random.Range(-5, 6);
        var ZoffSet = Random.Range(-5, 6);
        var newPosition = new Vector3(transform.position.x + XoffSet, transform.position.y, transform.position.z + ZoffSet);
        Instantiate(SpawnThings[0], newPosition, transform.rotation);
    }

    public void SpawnMotionIndicator()
    {
        var XoffSet = Random.Range(-5, 6);
        var ZoffSet = Random.Range(-5, 6);
        var newPosition = new Vector3(transform.position.x + XoffSet, transform.position.y, transform.position.z + ZoffSet);
        Instantiate(SpawnThings[1], newPosition, transform.rotation);
    }

    public void SpawnRFIDIndicator()
    {
        var XoffSet = Random.Range(-5, 6);
        var ZoffSet = Random.Range(-5, 6);
        var newPosition = new Vector3(transform.position.x + XoffSet, transform.position.y, transform.position.z + ZoffSet);
        Instantiate(SpawnThings[2], newPosition, transform.rotation);
    }

    public void SpawnWandIndicator()
    {
        Debug.Log("FireSpawn");
        var XoffSet = Random.Range(-5, 6);
        var ZoffSet = Random.Range(-5, 6);
        var newPosition = new Vector3(transform.position.x + XoffSet, transform.position.y, transform.position.z + ZoffSet);
        Instantiate(SpawnThings[3], newPosition, transform.rotation);
    }

}
