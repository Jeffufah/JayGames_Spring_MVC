using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class Win : MonoBehaviour
{
	[SerializeField] private string loadLevel;

   private void OnTriggerEnter (Collider theCollision)
    {
        try
        {
            if (theCollision.gameObject.GetComponent<EntityIdentity>().entityName == "Player")
            {
                SceneManager.LoadScene("You Win");
            }
            else
            {
                Debug.Log("Not player");
            }
        }
        catch
        {
            Debug.Log("Doesn't have EntityIdentity");
        }

    }    


}
