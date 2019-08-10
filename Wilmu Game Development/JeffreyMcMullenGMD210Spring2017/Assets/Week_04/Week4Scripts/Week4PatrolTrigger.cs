using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Week4PatrolTrigger : MonoBehaviour
{
    public void OnTriggerEnter(Collider TheObjectIHit)
    {
        if (TheObjectIHit.gameObject.GetComponent<Week4Identifier>().ObjectType == "PatrolPoint")
        {
            gameObject.GetComponentInParent<Week4AIPatrol>().NextPatrolPoint();
        }
    }
}
