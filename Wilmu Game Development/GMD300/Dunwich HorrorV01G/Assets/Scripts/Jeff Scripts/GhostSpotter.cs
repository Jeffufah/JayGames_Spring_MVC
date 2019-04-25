using UnityEngine;
using System.Collections;

public class GhostSpotter : MonoBehaviour {
	public GameObject Ghost;
	public float ghostHits;
	public float lookAwayCounter = 0;
	public float min;
	public float max;
	public bool on = true;
	public GameObject BloodBurst;

	// Use this for initialization
	void Start () {
		ghostHits = 2;
		lookAwayCounter = 0;
		on = true;
	}
	
	void Update() {

		if (on == true)
		{
			lookAwayCounter += Time.deltaTime;
			
			if (lookAwayCounter > 1) 
			{
				ghostHits = 2;
			}
			
			
			RaycastHit hit;
			Ray ray = new Ray(transform.position, transform.forward);
			Vector3 forward = transform.TransformDirection (Vector3.forward) * 10;
			Debug.DrawRay (transform.position, forward, Color.green);
			//if (Physics.Raycast(transform.position, fwd, 10))
			
			if (Physics.Raycast (ray, out hit)) 
			{
				if (hit.rigidbody != null)
				{
					if (hit.transform.gameObject.tag == "ghost") 
					{
						lookAwayCounter = 0;
						Ghost = hit.transform.gameObject;
						var myPosition = transform.position;
						
						var diff = (Ghost.transform.position - myPosition);
						var curDistance = diff.sqrMagnitude;
						
						if (curDistance <= 200)
						{
							ghostHits = hit.transform.gameObject.GetComponent<ghostHealth>().ghostHp;
							ghostHits -= Time.deltaTime;
							print (ghostHits);
							//print ("I see you!");
							
							if (ghostHits <= 0)
							{
								Instantiate(BloodBurst, Ghost.transform.position, Ghost.transform.rotation);
								ghostHits = 2;
								
								
								if (curDistance < 200)
								{
									var reposition = Random.Range(1,5);
									
									//If reposition generates a 2, a landMine will relocate to a rectangular space offset to you on the -x axis.			
									if (reposition == 1) 
									{
										var tempXleftn = Random.Range(min,max + 1);
										var tempZsidesOne = Random.Range(min * -1,max + 1);
										Ghost.transform.parent.transform.position = new Vector3(transform.position.x - tempXleftn, transform.position.y, transform.position.z + tempZsidesOne);							}   	   	
									
									
									//If reposition generates a 2, a landMine will relocate to a rectangular space offset to you on the +x axis.  	
									if (reposition == 2) 
									{
										var tempXrightp = Random.Range(min,max + 1);
										var tempZsidesTwo = Random.Range(min * -1,max + 1);
										Ghost.transform.parent.transform.position = new Vector3(transform.position.x + tempXrightp, transform.position.y, transform.position.z + tempZsidesTwo);
									}
									
									
									//If reposition generates a 3, a landMine will relocate to a rectangular space offset to you on the -z axis.
									if (reposition == 3)
									{
										var tempZleftn = Random.Range(min,max);
										var tempXsidesOne = Random.Range(min * -1,max + 1);
										Ghost.transform.parent.transform.position = new Vector3(transform.position.x + tempXsidesOne,transform.position.y, transform.position.z - tempZleftn);
									}
									
									
									//If reposition generates a 4, a landMine will relocate to a rectangular space offset to you on the +z axis.  	
									if (reposition == 4)
									{
										var tempZrightp = Random.Range(min,max + 1);
										var tempXsidesTwo = Random.Range(min * -1,max + 1);
										Ghost.transform.parent.transform.position = new Vector3(transform.position.x + tempXsidesTwo,transform.position.y, transform.position.z + tempZrightp);
									}	
								}
							} 
						}
					}
					else 
					{
						//print ("That object isn't a ghost");
					}
					
				}
				else 
				{
					//print ("No rigidbody in sight");
				}
			}
		}




	}
}
