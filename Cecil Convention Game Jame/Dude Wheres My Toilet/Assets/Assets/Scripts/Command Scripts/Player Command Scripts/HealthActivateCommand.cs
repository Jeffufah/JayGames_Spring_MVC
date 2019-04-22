using System.Collections;
using System.Collections.Generic;
using UnityEngine;

[System.Serializable]
public class HealthActivateCommand : CommandBase
{
    public HealthActivateCommand(int entityId)
    {
        this.entityId = entityId;
        commandType = "HealthActivate";
    }

    public override void ExecuteCommand()
    {
        GameObject obj = EntityManager.getEntity(entityId);
        obj.GetComponent<PlayerHealth>().receiveHeal(25);
        obj.GetComponent<PlayerInventory>().health = false;

        int hudEntityId = GameObject.Find("HUD").GetComponent<EntityIdentity>().getEntityId();
        HealthPickupUICommand healthPickupUICommand = new HealthPickupUICommand(hudEntityId, 1, false);
        obj.GetComponent<CommandHandler>().SendCommand(healthPickupUICommand);
    }
}