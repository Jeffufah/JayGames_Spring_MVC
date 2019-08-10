using UnityEngine;
using System.Collections;

public class Week2ChangeColorScript : MonoBehaviour
{
    public Renderer MyObjectRenderer;

    public void ChangeObjectColor()
    {
        //Assign a new color to our material.color in the form of (R, G, B)
        MyObjectRenderer.material.color = new Color(Random.value, Random.value, Random.value);
        //MyObjectRenderer.material.color = Color.blue;
    }
}
