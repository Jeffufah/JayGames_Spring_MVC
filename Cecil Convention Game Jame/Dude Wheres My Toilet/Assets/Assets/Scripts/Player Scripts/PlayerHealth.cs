using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class PlayerHealth : MonoBehaviour
{
    public float maxHealth;

    private float playerHealth;

    AudioSource audioData;

    public AudioClip takeDamageSound;
    public AudioClip notHealedSound;
    public AudioClip healedSound;

    void Start()
    {
        playerHealth = maxHealth;
        audioData = gameObject.GetComponentInChildren<AudioSource>();
    }

    public void takeDamage(float damage)
    {
        Debug.Log("Taking damage");
        playerHealth -= damage;
        audioData.PlayOneShot(takeDamageSound);
        
        if (playerHealth <= 0)
        {
            SceneManager.LoadScene("Game Over");
        }
        else
        {
            HealthbarCommand healthbarCommand = new HealthbarCommand(GameObject.Find("HUD").GetComponent<EntityIdentity>().getEntityId(), playerHealth);
            GetComponent<CommandHandler>().SendCommand(healthbarCommand);
        }

    }

    public bool receiveHeal(float heal)
    {
        if (playerHealth >= maxHealth)
        {
            audioData.PlayOneShot(healedSound);
            return false;
        }
        else
        {
            audioData.PlayOneShot(notHealedSound);
            playerHealth += heal;
            HealthbarCommand healthbarCommand = new HealthbarCommand(GameObject.Find("HUD").GetComponent<EntityIdentity>().getEntityId(), playerHealth);
            GetComponent<CommandHandler>().SendCommand(healthbarCommand);

            HealthPickupUICommand healthPickupUICommand = new HealthPickupUICommand(GameObject.Find("HUD").GetComponent<EntityIdentity>().getEntityId(), 1, false);
            GetComponent<CommandHandler>().SendCommand(healthPickupUICommand);
            return true;
        }
    }

    private void Update()
    {
        if (playerHealth > maxHealth)
        {
            playerHealth = maxHealth;
        }
    }

    public float getPlayerHealth()
    {
        return playerHealth;
    }
}