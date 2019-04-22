using System.Collections;
using System.Collections.Generic;
using UnityEngine;

[System.Serializable]
public class SpeedActivateCommand : CommandBase
{
    public SpeedActivateCommand(int entityId)
    {
        this.entityId = entityId;
        commandType = "SpeedActivate";
    }

    public override void ExecuteCommand()
    {
        GameObject obj = EntityManager.getEntity(entityId);
        obj.GetComponent<PlayerModifiers>().activateSuperSpeed(10);
        obj.GetComponent<PlayerInventory>().speedBoost = false;

        int hudEntityId = GameObject.Find("HUD").GetComponent<EntityIdentity>().getEntityId();
        HealthPickupUICommand healthPickupUICommand = new HealthPickupUICommand(hudEntityId, 2, false);
        obj.GetComponent<CommandHandler>().SendCommand(healthPickupUICommand);
    }
}