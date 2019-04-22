using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class HUDIconScripts : MonoBehaviour
{
    public Image Icon1;
    public Image Icon2;
    public Image Icon3;

    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    public void SetIcon1Enabled(bool isEnabled)
    {
        if (isEnabled) //It seems to always be enabled.
        {

            Icon1.color = new Color(Icon1.color.r, Icon1.color.g, Icon1.color.b, 1.0f);
        }
        else
        {
            Icon1.color = new Color(Icon1.color.r, Icon1.color.g, Icon1.color.b, 0.5f);
        }
    }

    public void SetIcon2Enabled(bool isEnabled)
    {
        if (isEnabled)
        {
            Icon2.color = new Color(Icon2.color.r, Icon2.color.g, Icon2.color.b, 1.0f);
        }
        else
        {
            Icon2.color = new Color(Icon2.color.r, Icon2.color.g, Icon2.color.b, 0.5f);
        }
    }

    public void SetIcon3Enabled(bool isEnabled)
    {
        if (isEnabled)
        {
            Icon3.color = new Color(Icon3.color.r, Icon3.color.g, Icon3.color.b, 1.0f);
        }
        else
        {
            Icon3.color = new Color(Icon3.color.r, Icon3.color.g, Icon3.color.b, 0.5f);
        }
    }
}
