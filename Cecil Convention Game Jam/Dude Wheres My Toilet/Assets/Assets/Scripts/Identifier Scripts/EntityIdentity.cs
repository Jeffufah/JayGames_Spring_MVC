using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class EntityIdentity : MonoBehaviour
{
    private int entityId;
    public string entityName;

    private void Awake()
    {
        entityId = EntityManager.addEntity(gameObject);
    }

    public int getEntityId()
    {
        return entityId;
    }
}
