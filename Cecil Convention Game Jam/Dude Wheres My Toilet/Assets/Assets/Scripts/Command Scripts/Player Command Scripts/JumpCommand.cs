using System.Collections;
using System.Collections.Generic;
using UnityEngine;

[System.Serializable]
public class JumpCommand : CommandBase
{
    public JumpCommand(int entityId)
    {
        this.entityId = entityId;
        commandType = "Jump";
    }

   public override void ExecuteCommand()
    {
        GameObject obj = EntityManager.getEntity(entityId);
        PlayerModifiers modifier = obj.GetComponent<PlayerModifiers>();

        obj.GetComponent<Rigidbody>().AddForce(Vector3.up * modifier.jumpPower);
    }
}
