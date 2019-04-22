using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class EntityManager : MonoBehaviour
{
    public static Dictionary<int, GameObject> entities = new Dictionary<int, GameObject>();

    private static int entityCounter = 0;

    public static GameObject getEntity(int entityId)
    {
        if (entities.ContainsKey(entityId))
        {
            return entities[entityId];
        }
        else
        {
            return null;
        }
    }

    public static int addEntity(GameObject entity)
    {
        entityCounter++;
        entities.Add(entityCounter, entity);
        return entityCounter;  
    }

    public static bool removeEntity(int entityId)
    {
        if (entities.ContainsKey(entityId))
        {
            entities.Remove(entityId);
            return true;
        }
        else
        {
            return false;
        }
    }
}
