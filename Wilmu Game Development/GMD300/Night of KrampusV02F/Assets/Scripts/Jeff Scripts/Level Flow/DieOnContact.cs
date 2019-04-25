using UnityEngine;
using System.Collections;

public class DieOnContact: MonoBehaviour {
	public int levelIncrement = 0;
	public GameObject Player;
	public GameObject respawn;
	public GameObject level;
	private bool waitaframe = true;
	public ParticleSystem spatter;

	public AudioClip ouch;
	public AudioClip ouch2;
	public AudioClip ouch3;
	public AudioClip ouch4;
	public AudioClip scream;
	// Use this for initialization
	void Start () {
	
		level = GameObject.FindGameObjectWithTag ("level");
		gameObject.transform.parent = level.transform;
		//print (level);
	}
	
	// Update is called once per frame
	void Update () {
		//respawn = GameObject.FindGameObjectWithTag ("spawn");
	}

	void OnTriggerEnter2D(Collider2D other)
	{
		if(other.gameObject.tag == "Bad")
		{ 

			if (waitaframe == true)
			{
				Instantiate (spatter, transform.position, transform.rotation);

				int random = Random.Range(0, 5);	
				if (random == 0)
				{
						AudioSource.PlayClipAtPoint (ouch, Vector3.zero);
						waitaframe = false;
					
				}
				
				if (random == 1)
				{
						AudioSource.PlayClipAtPoint (ouch2, Vector3.zero);
						waitaframe = false;
					
				}

				if (random == 2)
				{
					AudioSource.PlayClipAtPoint (ouch3, Vector3.zero);
					waitaframe = false;
					
				}

				if (random == 3)
				{
					AudioSource.PlayClipAtPoint (ouch4, Vector3.zero);
					waitaframe = false;
					
				}

				if (random == 4)
				{
					AudioSource.PlayClipAtPoint (scream, Vector3.zero, 0.5f);
					waitaframe = false;
					
				}
			}
			
			else
			{
				waitaframe = true;
			}




			//print ("ouch");
			respawn = GameObject.Find("Spawn");
			Instantiate (Player, respawn.transform.position, respawn.transform.rotation);
			GameObject.Destroy(gameObject);
		}
	}
}

