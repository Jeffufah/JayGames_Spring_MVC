using System.Collections;
using System.Collections.Generic;
using UnityEngine;

[System.Serializable]
public class ForwardCommand : CommandBase
{
    public ForwardCommand(int entityId) //: base()
    {
        this.entityId = entityId;
        commandType = "MoveForward";
    }

    public override void ExecuteCommand() 
    {
        GameObject obj = EntityManager.getEntity(entityId);
        PlayerModifiers modifier = obj.GetComponent<PlayerModifiers>();

        float speedBonus = 1;

        if (modifier.isSprinting)
        {
            speedBonus = 1.5f;
        }

        obj.transform.Translate(Vector3.forward * modifier.moveSpeed * speedBonus * Time.deltaTime);
    }
}
