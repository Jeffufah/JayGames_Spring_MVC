using UnityEngine;
using System.Collections;

public class PriestScript : MonoBehaviour {
	public Transform target;
	public Transform[] patrolPoints;
	GameObject Priest;
	int MAX_PATROL_POINTS;
	int currentIndex;
	bool isPatrolling;
	bool isRunning;
	bool isAttacking;
	bool isDying;
	bool died;
	float walkSpeed;
	public GameObject bulletPositioner;
	public GameObject Bullet;
	public GameObject BloodBurst;
	public Vector3 lookAtPos;
	public float bulletFireCoolDown;
	//Set y of LookAt target to be my height.



	UnityEngine.AI.NavMeshAgent agent;
	
	
	// Use this for initialization
	void Start () {

		bulletFireCoolDown = 0;
		target = GameObject.Find("EnemyTarget").transform;


		agent = GetComponent<UnityEngine.AI.NavMeshAgent>();
		agent.SetDestination (patrolPoints[0].position);
		isPatrolling = true;
		currentIndex = 0;
		MAX_PATROL_POINTS = 2;
		walkSpeed = agent.speed;
	}
	
	// Update is called once per frame

	void Update () {

		if (died == false) 
		{
			if (gameObject.GetComponent<EnemyHealth>().hitPoints <= 0) 
			{	
				Instantiate(BloodBurst, transform.position, transform.rotation);
				isPatrolling = false; 
				isAttacking = false; 
				isRunning = false; 
				isDying = true; 
				print ("i dead");
			}
		}



		lookAtPos = target.position;
		lookAtPos.y = transform.position.y;

		bulletFireCoolDown += Time.deltaTime;

		var myPosition = transform.position;
		
		var diff = (target.transform.position - myPosition);
		var curDistance = diff.sqrMagnitude;




		if (isPatrolling) {
			/*RaycastHit hit;
			Ray ray = new Ray(transform.position, transform.forward);
			Vector3 forward = transform.TransformDirection(Vector3.forward) * 10;
			Debug.DrawRay (transform.position, forward, Color.green);*/
			// if ((agent.remainingDistance <= float.Epsilon) && (agent.remainingDistance != Mathf.Infinity))
			if (Vector3.Distance (patrolPoints [currentIndex].position, transform.position) <= 1.5) 
			{
				currentIndex = (currentIndex + 1) % MAX_PATROL_POINTS;
				//transform.LookAt(patrolPoints[currentIndex].position);
				agent.SetDestination (patrolPoints [currentIndex].position);
				//Debug.Log ("Setting destination!");
				agent.speed = walkSpeed;
			}
		} 
		else if (isRunning) 
		{
			transform.LookAt (target);
			RaycastHit hit;
			Ray ray = new Ray(transform.position, transform.forward);
			Vector3 forward = transform.TransformDirection(Vector3.forward) * 10;
			Debug.DrawRay (transform.position, forward, Color.green);
			// Set target to Player


			//transform.LookAt (target);
			
			if (Physics.Raycast (ray, out hit)) 
			{
				if (hit.collider != null)
				{

					if (hit.collider.gameObject.name == "Player") 
					{

					//	print ("I CAN STILL SEE YOU");
						if (curDistance <= 800)
						{
							agent.Stop ();
							isRunning = false;
							isPatrolling = false;
							isAttacking = true;
						}
						else
						{
						//	print ("getting closer");
							agent.SetDestination (target.position);
							agent.speed = walkSpeed * 2;
							
							
							agent.Resume();
							isRunning = true;
							isPatrolling = false;
							isAttacking = false;
						}

					}

					else 
					{
					//	print ("I CAN NOLONGER SEE YOU");
						agent.SetDestination (target.position);
						agent.speed = walkSpeed * 2;


						agent.Resume();
						isRunning = true;
						isPatrolling = false;
						isAttacking = false;
					}
				}		
			}
		} 
		else if (isAttacking) 
		{
			transform.LookAt (target);
			RaycastHit hit;
			Ray ray = new Ray(transform.position, transform.forward);
			Vector3 forward = transform.TransformDirection(Vector3.forward) * 10;
			Debug.DrawRay (transform.position, forward, Color.green);
			//print ("attack!");
			// Make sure you're always looking at player

			
			if (Physics.Raycast (ray, out hit)) 
			{
				if (hit.collider != null)
				{
					if (hit.collider.gameObject.name == "Player") 
					{
						if (curDistance >= 800)
						{
							agent.Resume ();
							//print ("where did you go?");
							isAttacking = false;
							isPatrolling = false;
							isRunning = true;
						}

						else
						{
							if (bulletFireCoolDown >= 1)
							{
								Instantiate(Bullet, gameObject.transform.GetChild(0).gameObject.transform.position, gameObject.transform.GetChild(0).gameObject.transform.rotation);
								print(gameObject.transform.GetChild (0).gameObject.transform.position);
								//print ("keep attacking");
								agent.Stop ();
								bulletFireCoolDown = 0;
							}

						}

					}

					else
					{
						//if (isAttacking)
						//{
						agent.Resume ();
						//print ("where did you go?");
						isAttacking = false;
						isPatrolling = false;
						isRunning = true;
						//}
					}
				}
			}

		} 

		else if (isDying) 
		{
			//Instantiate (Explosion, transform.position, transform.rotation);
			print ("blehhh");
			died = true;
			Destroy (gameObject, 1);
		}
		
		//Debug.Log ("Remaining distance = " + Vector3.Distance(patrolPoints[currentIndex].position, transform.position).ToString ());
		//Debug.Log ("float.Epsilon = " + float.Epsilon.ToString ());

		
		//Debug.Log ("Remaining distance = " + Vector3.Distance(patrolPoints[currentIndex].position, transform.position).ToString ());
		//Debug.Log ("float.Epsilon = " + float.Epsilon.ToString ());

		if (curDistance <= 1500) 
		{
			//print ("I sense your presence");
			transform.LookAt (target);
			RaycastHit hit;
			Ray ray = new Ray(transform.position, transform.forward);
			Vector3 forward = transform.TransformDirection(Vector3.forward) * 10;
			Debug.DrawRay (transform.position, forward, Color.green);
			
			if (Physics.Raycast (ray, out hit)) 
			{
			//	print ("something");
				
				if (hit.collider != null)
				{
					if (hit.transform.gameObject.name == "Player") 
					{
					//	print ("i see you!");
						// Switch from isPatrolling to isRunning
						//if (isPatrolling) 
						//	{
						//print ("Switching");
						agent.Stop ();
						isPatrolling = false;
						isRunning = false;
						isAttacking = true;
						//}
					}
					
				}
				
				else
				{
					//print ("null");
				}
				
			}
		}
	}

/*	void OnCollisionEnter(Collision other)
	{
		if (other.gameObject.tag == "Bullet") {
			print ("ouch");
			// Decrease health by 25 for first Shotgun
			gameObject.GetComponent<EnemyHealth> ().hitPoints = gameObject.GetComponent<EnemyHealth> ().hitPoints - 25;
		}
	}
*/
}
