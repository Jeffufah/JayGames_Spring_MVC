using System.Collections;
using System.Collections.Generic;
using UnityEngine;

[System.Serializable]
public class TakeDamageCommand : CommandBase
{
    private float damage;

    public TakeDamageCommand(int entityId, float damage)
    {
        this.entityId = entityId;
        this.damage = damage;
        commandType = "TakeDamage";
    }

    public override void ExecuteCommand()
    {
        GameObject obj = EntityManager.getEntity(entityId);
        obj.GetComponent<PlayerHealth>().takeDamage(damage);
    }
}
