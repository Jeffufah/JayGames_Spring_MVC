using System.Collections;
using System.Collections.Generic;
using UnityEngine;

[System.Serializable]
public class JumpActivateCommand : CommandBase
{
    public JumpActivateCommand(int entityId)
    {
        this.entityId = entityId;
        commandType = "JumpActivate";
    }

    public override void ExecuteCommand()
    {
        GameObject obj = EntityManager.getEntity(entityId);
        obj.GetComponent<PlayerModifiers>().activateSuperJump(10);
        obj.GetComponent<PlayerInventory>().jumpBoost = false;

        int hudEntityId = GameObject.Find("HUD").GetComponent<EntityIdentity>().getEntityId();
        HealthPickupUICommand healthPickupUICommand = new HealthPickupUICommand(hudEntityId, 3, false);
        obj.GetComponent<CommandHandler>().SendCommand(healthPickupUICommand);
    }
}