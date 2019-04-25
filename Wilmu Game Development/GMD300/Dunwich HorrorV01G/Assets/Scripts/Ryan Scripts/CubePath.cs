using UnityEngine;
using System.Collections;

public class CubePath : MonoBehaviour {
	public Transform target;
	public Transform[] patrolPoints;
	int MAX_PATROL_POINTS;
	int currentIndex;
	bool isPatrolling;

	UnityEngine.AI.NavMeshAgent agent;


	// Use this for initialization
	void Start () {
		agent = GetComponent<UnityEngine.AI.NavMeshAgent>();
		agent.SetDestination (patrolPoints[0].position);
		isPatrolling = true;
		currentIndex = 0;
		MAX_PATROL_POINTS = 2;

	}
	
	// Update is called once per frame
	void Update () {

		if (isPatrolling) 
		{
			// if ((agent.remainingDistance <= float.Epsilon) && (agent.remainingDistance != Mathf.Infinity))
			if (Vector3.Distance(patrolPoints[currentIndex].position, transform.position) <= 0.25)
			{
				currentIndex = (currentIndex + 1) % MAX_PATROL_POINTS;
				//transform.LookAt(patrolPoints[currentIndex].position);
				agent.SetDestination (patrolPoints[currentIndex].position);
				Debug.Log ("Setting destination!");
			}
		}
		Debug.Log ("Remaining distance = " + Vector3.Distance(patrolPoints[currentIndex].position, transform.position).ToString ());
		Debug.Log ("float.Epsilon = " + float.Epsilon.ToString ());
	}
}
