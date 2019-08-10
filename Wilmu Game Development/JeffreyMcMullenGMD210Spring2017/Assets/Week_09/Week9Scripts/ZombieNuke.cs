using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ZombieNuke : MonoBehaviour
{
    public bool ZombiesInScene;

    public GameObject ZombiePrefab;

    public GameObject[] EmptyGameObjectArray = new GameObject[0];
    public GameObject[] ZombieArray = new GameObject[0];
    public List<GameObject> ZombieRespawnList = new List<GameObject>();

	// Use this for initialization
	void Start ()
    {
        ZombiesInScene = true;
	}
	
	// Update is called once per frame
	void Update ()
    {
		if (Input.GetKeyDown(KeyCode.Delete))
        {
            if (ZombiesInScene == true)
            {
                EmptyZombieRespawnList();
                FillUpArrayAndList();
                ZombiesInScene = false;
            }
            else
            {
                Debug.Log("There aren't any zombies to be killed.");
            }
        }

        if (Input.GetKeyDown(KeyCode.Return))
        {
            RespawnZombies();
            ZombiesInScene = true;
        }
    }

    public void RespawnZombies()
    {
        while(ZombieRespawnList.Count > 0)
        {
            float XOffSet = Random.Range(-10, 11);
            float YOffSet = Random.Range(-10, 11);
            float ZOffSet = Random.Range(-10, 11);

            Vector3 newPosition = new Vector3(XOffSet, YOffSet, ZOffSet);

            Instantiate(ZombiePrefab, newPosition, transform.rotation);

            ZombieRespawnList.RemoveAt(0);
        }
    }

    public void FillUpArrayAndList()
    {
        ZombieArray = GameObject.FindGameObjectsWithTag("Zombie");
        
        foreach(GameObject Zombie in ZombieArray)
        {
            ZombieRespawnList.Add(ZombiePrefab);
        }

        for(int ZombieCounter = 0; ZombieCounter < ZombieArray.Length; ZombieCounter++)
        {
            ZombieArray[ZombieCounter].gameObject.GetComponent<ZombieDie>().Kaboom();
        }

        ZombieArray = EmptyGameObjectArray;
    }

    public void EmptyZombieRespawnList()
    {
        ListResize(ZombieRespawnList, 0);
    }

    public void ListResize<T>(List<T> list, int size)
    {
        if (size > list.Count)
        {
            while (size - list.Count > 0)
            {
                list.Add(list[0]);
            }
        }
        else if (size < list.Count)
        {
            while (list.Count - size > 0)
            {
                list.RemoveAt(list.Count - 1);
            }
        }
    }
}
