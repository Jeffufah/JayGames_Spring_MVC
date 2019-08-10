using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Week4AIPatrol : MonoBehaviour
{
    public GameObject[] PatrolPoints;
    public int PatrolCounter;

    public UnityEngine.AI.NavMeshAgent NavigationAgent;



	// Use this for initialization
	void Start ()
    {
        NavigationAgent = GetComponent<UnityEngine.AI.NavMeshAgent>();
        NavigationAgent.SetDestination(PatrolPoints[PatrolCounter].transform.position);
    }
	
	// Update is called once per frame
	void Update ()
    {
		
	}

    public void NextPatrolPoint()
    {
        if (PatrolCounter == (PatrolPoints.Length - 1))
        {
            PatrolCounter = 0;
            NavigationAgent.SetDestination(PatrolPoints[PatrolCounter].transform.position);
        }

        else
        {
            PatrolCounter = PatrolCounter + 1;
            NavigationAgent.SetDestination(PatrolPoints[PatrolCounter].transform.position);
        }
    }
}
