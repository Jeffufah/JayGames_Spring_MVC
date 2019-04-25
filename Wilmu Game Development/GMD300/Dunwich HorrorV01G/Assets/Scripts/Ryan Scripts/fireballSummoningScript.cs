using UnityEngine;
using System.Collections;

public class fireballSummoningScript : MonoBehaviour {
	public GameObject fireball;
	Transform fireballSpawnArea;
	
	// Use this for initialization
	void Start () {
		fireballSpawnArea = gameObject.transform.Find ("directionObject");
	}
	
	// Update is called once per frame
	void Update () {
		if (Input.GetMouseButtonUp (0)) 
		{
			GameObject.Instantiate (fireball, fireballSpawnArea.position,  fireballSpawnArea.rotation);
		}
	}
}
