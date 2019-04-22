using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class HealthBarScript : MonoBehaviour
{
    public Image HealthBar;

    // Start is called before the first frame update
    void Start()
    {
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    public void DisplayHealthChange(float amount)
    {
        HealthBar.fillAmount = amount / 100;
    }

    public void DecreaseHealthBar(float amount)
    {
        HealthBar.fillAmount -= amount;
    }

    public void IncreaseHealthBar(float amount)
    {
        HealthBar.fillAmount += amount;
    }
}
