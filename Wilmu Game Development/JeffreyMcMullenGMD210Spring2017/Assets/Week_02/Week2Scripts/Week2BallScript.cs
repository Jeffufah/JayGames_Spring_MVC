using UnityEngine;
using System.Collections;

public class Week2BallScript : MonoBehaviour
{
    public float AmountOfForce;
    public int MaxJumpsAllowed;
    public int JumpsUsed;

    // Use this for initialization
    void Start()
    {

    }

    // Update is called once per frame
    void FixedUpdate()
    {
        CheckForArrowKeyInput();
    }

    public void CheckForArrowKeyInput()
    {
        var horizontalInput = Input.GetAxis("Horizontal");
        var verticalInput = Input.GetAxis("Vertical");

        //Create a temporary variable named newMovement and assign it a new vector3 containing any horizontal input for the x axis on the ball,
        //and any vertical input for the z axis on the ball.
        //We don't want to mess with our y value because that would cause it to move up.
        Vector3 newMovement = new Vector3(horizontalInput, 0.0f, verticalInput);

        //Apply the newMovement direction force to our ball.
        gameObject.transform.GetComponent<Rigidbody>().AddForce(newMovement);

        if (Input.GetKeyDown(KeyCode.Space))
        {
            if (JumpsUsed < MaxJumpsAllowed)
            {
                //By adding force on the y axis and not x or z, it will move straight up.
                gameObject.transform.GetComponent<Rigidbody>().AddForce(0, 250, 0);

                //Add 1 to our jumps used counter.
                JumpsUsed++;
            }

            else
            {
                Debug.Log("Maximum jumps used.");
            }
        }
    }

    public void OnCollisionEnter(Collision TheObjectIHit)
    {
        //Debug.Log(TheObjectIHit.gameObject.transform.name);
        if (TheObjectIHit.gameObject.transform.tag == "Cube")
        {
            Debug.Log(TheObjectIHit.gameObject.transform.name);

            //Call the function inside the script that is attached to our cube.
            TheObjectIHit.gameObject.transform.GetComponent<Week2ChangeColorScript>().ChangeObjectColor();
        }

        if(TheObjectIHit.gameObject.transform.GetComponent<Week3GroundIdentityScript>().isGround == true)
        {
            JumpsUsed = 0;
        }

    }
}
