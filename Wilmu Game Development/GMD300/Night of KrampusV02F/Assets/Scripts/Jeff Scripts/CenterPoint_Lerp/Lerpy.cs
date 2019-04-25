using UnityEngine;
using System.Collections;

public class Lerpy : MonoBehaviour
{
    public float lerpSpeed;

    //offSet is set to the main parent object's lerp offset which is made to be on a scale of 1 to 10 but with lerping it should be 0 to 1 so this is why it is divided by 10.
    public float offSet;

    private float point;
    public Transform PointA;
    public Transform PointB;
    Vector3 usedPointA;
    Vector3 usedPointB;
    Vector3 currentPosition;
    float startTime;
    public float duration;
    private bool change = true;
    private bool setUp = true;
    void Start()
    {

    }

    void LateUpdate()
    {
        if (setUp == true)
        {
            lerpSpeed = gameObject.transform.parent.gameObject.GetComponent<Movement_02>().lerpSpeed;
            offSet = gameObject.transform.parent.gameObject.GetComponent<Movement_02>().lerpOffSet;
            point = offSet / 10;
            usedPointA = PointA.transform.position;
            usedPointB = PointB.transform.position;

            //If the point was set to 5, it would actually be .5 in this context which is halfway between two points.
            currentPosition = Vector3.Lerp(usedPointA, usedPointB, point);
            transform.position = currentPosition;
            setUp = false;
        }

        else
        {

            //Debug.Log("Distance between pos and B = " + Vector3.Distance(usedPointB, currentPosition));

            if (Vector3.Distance(usedPointB, currentPosition) <= 0)
            {
                if (change == true)
                {

                    startTime = Time.timeSinceLevelLoad;
                    // Reverse usedPointA and usedPointB positions
                    usedPointA = PointB.transform.position;
                    // point a has now become point b
                    usedPointB = PointA.transform.position;
                    //point b has now become point a
                    change = false;

                }
                else
                {

                    startTime = Time.timeSinceLevelLoad;
                    // Reverse usedPointA and usedPointB positions back to normal
                    usedPointA = PointA.transform.position;
                    //point a is now back to normal
                    usedPointB = PointB.transform.position;
                    //point b is now back to normal 
                    change = true;
                }
                point = 0;

            }

            if (change == false)
            {
                usedPointA = PointB.transform.position;
                usedPointB = PointA.transform.position;
            }
            else
            {
                usedPointA = PointA.transform.position;
                usedPointB = PointB.transform.position;
            }

            point += lerpSpeed / 100 * Time.deltaTime;
            currentPosition = Vector3.Lerp(usedPointA, usedPointB, point);
            transform.position = currentPosition;
        }
    }
}
