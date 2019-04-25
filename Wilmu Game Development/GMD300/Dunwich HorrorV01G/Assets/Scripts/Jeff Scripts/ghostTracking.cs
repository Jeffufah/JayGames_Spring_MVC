using UnityEngine;
using System.Collections;

public class ghostTracking : MonoBehaviour {
	public float speed = 1;
	public GameObject playerPosition;
	public float counter = 0;
	public float timer;
	public float maxSpeed;
	public float threeStrikes;
	public float min;
	public float max;
	public float respawnDistance;
	// Use this for initialization
	void Start () {
		//speed = 1;
		threeStrikes = 0;
	}
	
	// Update is called once per frame
	void Update () 
	{
		playerPosition = GameObject.FindGameObjectWithTag("player");

		Vector3 relativePos = playerPosition.transform.position - transform.position;
		Quaternion rotation = Quaternion.LookRotation(relativePos);
		transform.rotation = rotation;
		transform.Translate (Vector3.forward * speed * Time.deltaTime);
		//print (speed);
		//print (counter);

		var myPosition = transform.position;
		var diff = (playerPosition.transform.position - myPosition);
		var curDistance = diff.sqrMagnitude;

		if (curDistance >= respawnDistance) 
		{
			print ("poof");
			speed += 1;
			threeStrikes += 1;
			var reposition = Random.Range(1,5);
			
			//If reposition generates a 2, a landMine will relocate to a rectangular space offset to you on the -x axis.			
			if (reposition == 1) 
			{
				var tempXleftn = Random.Range(min,max + 1);
				var tempZsidesOne = Random.Range(min * -1,max + 1);
				transform.position = new Vector3(playerPosition.transform.position.x - tempXleftn, playerPosition.transform.position.y, playerPosition.transform.position.z + tempZsidesOne);							}   	   	
			
			
			//If reposition generates a 2, a landMine will relocate to a rectangular space offset to you on the +x axis.  	
			if (reposition == 2) 
			{
				var tempXrightp = Random.Range(min,max + 1);
				var tempZsidesTwo = Random.Range(min * -1,max + 1);
				transform.position = new Vector3(playerPosition.transform.position.x + tempXrightp, playerPosition.transform.position.y, playerPosition.transform.position.z + tempZsidesTwo);
			}
			
			
			//If reposition generates a 3, a landMine will relocate to a rectangular space offset to you on the -z axis.
			if (reposition == 3)
			{
				var tempZleftn = Random.Range(min,max + 1);
				var tempXsidesOne = Random.Range(min * -1,max + 1);
				transform.position = new Vector3(playerPosition.transform.position.x + tempXsidesOne,playerPosition.transform.position.y, playerPosition.transform.position.z - tempZleftn);
			}
			
			
			//If reposition generates a 4, a landMine will relocate to a rectangular space offset to you on the +z axis.  	
			if (reposition == 4)
			{
				var tempZrightp = Random.Range(min,max + 1);
				var tempXsidesTwo = Random.Range(min * -1,max + 1);
				transform.position = new Vector3(playerPosition.transform.position.x + tempXsidesTwo,playerPosition.transform.position.y, playerPosition.transform.position.z + tempZrightp);
			}
		}


		if (speed < maxSpeed) {
			counter += Time.deltaTime;
			if (counter > 10) {
				speed += .25f;
				counter = 0;
			}
		} 
		else 
		{
			speed = maxSpeed;
		}

		if (threeStrikes >= 3) 
		{
			speed = maxSpeed;
		}


	}

	void OnTriggerEnter(Collider other)
	{
		if (other.gameObject.tag == "player") {
		
			Destroy(gameObject);
		}
	}

}

