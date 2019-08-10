using UnityEngine;
using System.Collections;

public class Week2OutOfBounds : MonoBehaviour
{
    public void OnTriggerEnter(Collider TheObjectIHit)
    {
        if (TheObjectIHit.gameObject.transform.name == "Sphere")
        {
            //Assign a restart point in world space for our ball to be placed if it falls out of bounds.
            TheObjectIHit.transform.position = new Vector3(0, 1, 0);
        }
    }
}
