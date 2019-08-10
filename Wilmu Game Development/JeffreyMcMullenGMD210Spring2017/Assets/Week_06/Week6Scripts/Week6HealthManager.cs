using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class Week6HealthManager : MonoBehaviour
{
    public float MaxHealth;
    public float CurrentHealth;

    public float HealAmount;
    public float DamageAmount;
	// Use this for initialization
	void Start ()
    {
        CurrentHealth = MaxHealth;
        ChangeHealthBarColor();
	}
	
	// Update is called once per frame
	void Update ()
    {
        CurrentHealth -= Time.deltaTime * 10;
        HealthClamp();
        ChangeHealthBarColor();
        GameObject.Find("HealthBar").GetComponent<Image>().fillAmount = CurrentHealth / MaxHealth;
    }

    public void HealUp()
    {
        CurrentHealth += HealAmount;
        HealthClamp();
        ChangeHealthBarColor();
        GameObject.Find("HealthBar").GetComponent<Image>().fillAmount = CurrentHealth / MaxHealth;
    }

    public void DealDamage()
    {
        CurrentHealth -= DamageAmount;
        HealthClamp();
        ChangeHealthBarColor();
        GameObject.Find("HealthBar").GetComponent<Image>().fillAmount = CurrentHealth / MaxHealth;
    }

    public void HealthClamp()
    {
        if (CurrentHealth >= MaxHealth)
        {
            CurrentHealth = MaxHealth;
        }

        else if (CurrentHealth <= 0)
        {
            CurrentHealth = 0;
        }
    }

    public void ChangeHealthBarColor()
    {
        if (CurrentHealth / MaxHealth >= .75f)
        {
            GameObject.Find("HealthBar").GetComponent<Image>().color = Color.green;
        }

        else if (CurrentHealth / MaxHealth <= .35f)
        {
            GameObject.Find("HealthBar").GetComponent<Image>().color = Color.red;
        }

        else
        {
            GameObject.Find("HealthBar").GetComponent<Image>().color = Color.yellow;
        }
    }
}
