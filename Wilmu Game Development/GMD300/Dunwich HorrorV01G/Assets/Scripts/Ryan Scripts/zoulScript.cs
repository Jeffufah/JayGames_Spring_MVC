using UnityEngine;
using System.Collections;

public class zoulScript : MonoBehaviour {
	public GameObject BloodBurst;
	public Transform target;
	public Transform[] patrolPoints;
	GameObject zoulChild;
	Animator zoulAnim;
	int MAX_PATROL_POINTS;
	int currentIndex;
	bool isPatrolling;
	bool isRunning;
	bool isAttacking;
	bool isDying;
	float walkSpeed;
	bool died;

	UnityEngine.AI.NavMeshAgent agent;
	
	
	// Use this for initialization
	void Start () {
		died = false;
		target = GameObject.FindGameObjectWithTag("player").transform;
		agent = GetComponent<UnityEngine.AI.NavMeshAgent>();
		agent.SetDestination (patrolPoints[0].position);
		isPatrolling = true;
		currentIndex = 0;
		MAX_PATROL_POINTS = 2;
		zoulChild = transform.Find ("zoul").gameObject;
		zoulAnim = zoulChild.GetComponent<Animator> ();
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
			}
		}




		if (isPatrolling) {
			// if ((agent.remainingDistance <= float.Epsilon) && (agent.remainingDistance != Mathf.Infinity))
			if (Vector3.Distance (patrolPoints [currentIndex].position, transform.position) <= 1.5) {
				currentIndex = (currentIndex + 1) % MAX_PATROL_POINTS;
				//transform.LookAt(patrolPoints[currentIndex].position);
				agent.SetDestination (patrolPoints [currentIndex].position);
				Debug.Log ("Setting destination!");
				zoulAnim.Play ("walk");
				agent.speed = walkSpeed;
			}
		} 
		else if (isRunning) 
		{
			// Set target to Player
			agent.SetDestination (target.position);
			zoulAnim.Play ("run");
			agent.speed = walkSpeed * 2;
			if (Vector3.Distance(target.position, transform.position) <= 3)
			{
				// Switch states from isRunning to isAttacking
				isRunning = false;
				isAttacking = true;
			}
		} 
		else if (isAttacking) 
		{
			// Make sure you're always looking at player
			transform.LookAt (target);
			zoulAnim.Play ("attack");
			// If player is too far away to attack (player ran away), chase the bastard down!
			if (Vector3.Distance(target.position, transform.position) > 3)
			{
				isAttacking = false;
				isRunning = true;
			}
		} 
		else if (isDying) 
		{
			//Instantiate (Explosion, transform.position, transform.rotation);
			died = true;
			Destroy (gameObject, 1);
		}

		//Debug.Log ("Remaining distance = " + Vector3.Distance(patrolPoints[currentIndex].position, transform.position).ToString ());
		//Debug.Log ("float.Epsilon = " + float.Epsilon.ToString ());
	}

	void OnTriggerEnter(Collider other)
	{
		// Switch from isPatrolling to isRunning
		if (isPatrolling) 
		{
			isPatrolling = false;
			isRunning = true;
		}
	}
	void OnTriggerExit(Collider other)
	{
		isAttacking = false;
		isRunning = false;
		isPatrolling = true;
		agent.SetDestination (patrolPoints [currentIndex].position);
	}
}
