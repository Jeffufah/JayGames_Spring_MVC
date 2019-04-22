using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class SpawnPointCollection : MonoBehaviour
{
    public static GameObject spCollection;
    static List<Transform> spawnPoints = new List<Transform>();

    public GameObject[] prefabs;

    public GameObject toilet;

    public static GameObject getSpCollection()
    {
        return spCollection;
    }

    private void Awake()
    {
        spCollection = gameObject;
    }

    private void Start()
    {
        StartCoroutine(spawnInGamePlayEntities());
    }

    public static void addSpawnPoint(Transform spawnPoint)
    {
        spawnPoints.Add(spawnPoint);
    }

    public static Transform getRandomSpawnTransform()
    {
        return spawnPoints[Random.Range(0, spawnPoints.Count)];
    }

    private IEnumerator spawnInGamePlayEntities()
    {
        yield return new WaitForEndOfFrame();

        List<Transform> tPoints = new List<Transform>();
        foreach(Transform t in spawnPoints)
        {
            tPoints.Add(t);
        }
        Debug.Log(tPoints.Count);

        for (int i = 0; i < 20; i++)
        {
            if (i < 1)
            {
                int randomTForm = Random.Range(0, tPoints.Count);
                GameObject obj = new GameObject();
                obj = Instantiate(toilet, tPoints[randomTForm]);
            }
            else
            {
                int randomTForm = Random.Range(0, tPoints.Count);
                GameObject obj = new GameObject();
                obj = Instantiate(prefabs[Random.Range(0, prefabs.Length)], tPoints[randomTForm]);
            }
        }
    }
}
