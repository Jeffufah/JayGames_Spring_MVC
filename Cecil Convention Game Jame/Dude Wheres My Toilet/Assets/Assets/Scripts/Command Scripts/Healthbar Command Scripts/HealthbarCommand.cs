using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class HealthbarCommand : CommandBase
{
    float health = 1.0f;
    public HealthbarCommand(int entityId, float playerHealth)
    {
        this.entityId = entityId;
        commandType = "Healthbar";

        health = playerHealth;
    }

    public override void ExecuteCommand()
    {
        GameObject obj = EntityManager.getEntity(entityId);
        /*
        if (health > obj.GetComponent<Image>().fillAmount)
        {
            obj.GetComponent<HealthBarScript>().IncreaseHealthBar(health - obj.GetComponent<Image>().fillAmount);
        }
        if (health < obj.GetComponent<Image>().fillAmount)
        {
            obj.GetComponent<HealthBarScript>().IncreaseHealthBar(obj.GetComponent<Image>().fillAmount - health);
        }
        */

        obj.GetComponent<HealthBarScript>().DisplayHealthChange(health);
    }
}
