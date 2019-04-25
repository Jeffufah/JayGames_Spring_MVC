using UnityEngine;
using System.Collections;

public class zoulSummonScript : MonoBehaviour {
	public GameObject friendlyZoulPrefab;

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
	 	if (Input.GetMouseButtonUp(0))
		{
			// Check if there's any objects in the way that aren't the floor or ceiling
			if(!Physics.Linecast(transform.Find("ZoulSpawnAreaA").position, transform.Find("ZoulSpawnAreaB").position))
			{
				// Instantiate Friendly Zoul!
				GameObject.Instantiate(friendlyZoulPrefab, transform.Find("ZoulSpawnAreaB").position, Quaternion.identity);
			}
		}
	}
}
