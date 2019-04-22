using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class changeScene : MonoBehaviour
{ 

    public void Change (string sceneName)
    {
        Application.LoadLevel(sceneName);
    }
}
